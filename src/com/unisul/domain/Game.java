package com.unisul.domain;

import com.unisul.utils.CircularLinkedList;

public class Game {
    public static void main(String[] args) {
        CircularLinkedList<Player> list = new CircularLinkedList<Player>();
        list.add(new Player(new Pizza(Pizza.Flavor.CALABRESA)));
        list.add(new Player(new Pizza(Pizza.Flavor.MUCARELA)));
        new Game(list, new DiceImpl()).play();
    }

    private CircularLinkedList<Player> players;
    private LuckyOrUnluckyDeck deck;
    private Dice dice;

    public Game(CircularLinkedList<Player> players, Dice dice) {
        this.players = players;
        this.dice = dice;
        this.deck = new LuckyOrUnluckyDeck();

        Board board = new Board();

        for (int i = 0; i < players.size(); i++) {
            players.get(i).position = board.positions().newIterator();
        }
    }

    public void play() {
        while (true) {
            Player currentPlayer = players.iterator().next();

            int diceResult = dice.roll();
            System.out.println("Dice result: " + diceResult);

            // moves currentPlayer position n times
            // n = dice result
            for (int i = 0; i < diceResult; i++) {
                currentPlayer.position.next();
            }

            // if currentPlayerPosition is an ingredient:
            // else if currentPlayerPosition is SORTE_OU_AZAR
            // else if currentPlayerPosition is PERDE_TUDO
            Position position = currentPlayer.position.current();
            switch (position.getType()) {
                case INGREDIENT: {
                    Pizza.Ingredient ingredient = position.getIngredient();

                    // checks if the currentPlayer already has the
                    // ingredient he has found
                    // if the doesn't have (and needs), then add that
                    // ingredient to the pizza
                    if (currentPlayer.pizza.needsIngredient(ingredient)) {
                        System.out.print("Pizza do jogador: " + currentPlayer.pizza.getFlavor() + " -> ");
                        System.out.println("Ingrediente encontrado:" + position.getIngredient());
                        currentPlayer.pizza.addIngredient(ingredient);
                    } else {
                        System.out.print("Pizza do jogador: " + currentPlayer.pizza.getFlavor() + " -> ");
                        System.out.println("Ingrediente encontrado nao bate:" + position.getIngredient());
                    }

                    break;
                }

                // TODO refactor
               case SORTE_OU_AZAR: {
                   System.out.print("Pizza do jogador: " + currentPlayer.pizza.getFlavor() + " -> ");
                   System.out.println("sorte ou azar");
                   // get card and checks what to do with it
                   switch (this.deck.getCard()) {
                       case LOSE_AN_INGREDIENT: {
                           System.out.println(currentPlayer.pizza.getFlavor() + " perdeu ingrediente!");
                           currentPlayer.pizza.removeIngredient();
                           break;
                       }

                       case BURN_THE_PIZZA: {
                           System.out.println(currentPlayer.pizza.getFlavor() + " queimou a pizza!");
                           currentPlayer.pizza.removeAllIngredients();
                           break;
                       }

                       case GAIN_TWO_INGREDIENTS: {
                           System.out.println(currentPlayer.pizza.getFlavor() + " ganhou dois ingredientes!");
                           currentPlayer.pizza.addIngredient();
                           currentPlayer.pizza.addIngredient();
                           break;
                       }

                       case STOLE_AN_INGREDIENT_FROM_ANOTHER_PLAYER: {
                           System.out.println(currentPlayer.pizza.getFlavor() + " vai roubar algum ingrediente de alguem!");

                           boolean foundNeededIngredientOnAnotherPlayer = false;

                           // try to find any needed ingredient on another player
                           // iterate through all other players
                           for (int i = 0; i < players.size(); i++) {
                               Player p = players.get(i);
                               // (checks if player is not the current round player)
                               if (p != currentPlayer) {
                                   // checks if the other player has any ingredient that i need
                                   for (int j = 0; j < p.pizza.ingredientsFound().size(); j++) {
                                       Pizza.Ingredient ing = p.pizza.ingredientsFound().get(j);
                                       if (currentPlayer.pizza.needsIngredient(ing)) {
                                           foundNeededIngredientOnAnotherPlayer = true;
                                           System.out.print(currentPlayer.pizza.getFlavor() + " roubou a pizza de  ");
                                           System.out.print(p.pizza.getFlavor());
                                           System.out.println(" => Ingrediente: " + ing);
                                           currentPlayer.pizza.addIngredient(ing);
                                           p.pizza.removeIngredient(ing);
                                           break;
                                       } else {
                                           System.out.println(currentPlayer.pizza.getFlavor() + " nao precisa de" + ing);
                                       }
                                   }
                                   // TODO IMPORTANT break out of second loop
                                   // outer for break
                                   if (foundNeededIngredientOnAnotherPlayer) break;
                               }
                           }

                           // if didnt found any needed ingredient on another player,
                           // just remove any ingredient that any other player might have found
                           if (! foundNeededIngredientOnAnotherPlayer) {
                               System.out.println(currentPlayer.pizza.getFlavor() + " nao encontrou nenhum ingrediente em outro jogador");
                               for (int i = 0; i < players.size(); i++) {
                                   Player p = players.get(i);
                                   if (p != currentPlayer && p.pizza.foundAnyIngredient()) {
                                       System.out.println("entao removi algum ingrediente da " + p.pizza.getFlavor());
                                       p.pizza.removeIngredient();
                                       break;
                                   }
                               }
                           }

                           // deck card case break
                           break;
                       }
                   }
                   // sorte ou azar switch break;
                   break;
                }

                case PERDE_TUDO: {
                    System.out.print("Pizza do jogador: " + currentPlayer.pizza.getFlavor() + " -> ");
                    System.out.println("perde tudo :(");
                    currentPlayer.pizza.removeAllIngredients();
                    break;
                }
            }

            // checks if the current player finished the pizza
            if (currentPlayer.pizza.hasAllIngredients()) {
                System.out.println(currentPlayer.position.current());
                break;
            }
        }
    }
}
