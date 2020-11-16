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
    private Player currentPlayer;
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
            currentPlayer = players.iterator().next();

            int diceResult = dice.roll();
            System.out.println("Dice result: " + diceResult);

            // moves currentPlayer position n times
            // n = dice result
            for (int i = 0; i < diceResult; i++) {
                currentPlayer.position.next();
            }

            Position position = currentPlayer.position.current();
            switch (position.getType()) {
                case INGREDIENT: {
                    handleIngredientPosition(position);
                    break;
                }

                case PERDE_TUDO: {
                    handleLoseAllIngredients();
                    break;
                }

               case SORTE_OU_AZAR: {
                   handleLuckyOrUnluckyPosition();
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

    private void handleIngredientPosition(Position position) {
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

    }

    private void handleLoseAllIngredients() {
        System.out.print("Pizza do jogador: " + currentPlayer.pizza.getFlavor() + " -> ");
        System.out.println("perde tudo :(");
        currentPlayer.pizza.removeAllIngredients();
    }

    private void handleLuckyOrUnluckyPosition() {
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

            case STEAL_INGREDIENT_FROM_ANOTHER_PLAYER: {
                currentPlayer.stealIngredientFromAnotherPlayer(players);
                break;
            }
        }
    }
}
