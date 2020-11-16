package com.unisul.domain;

import com.unisul.utils.CircularLinkedList;

public class Game {
    public static void main(String[] args) {
        CircularLinkedList<Player> list = new CircularLinkedList<Player>();
        list.add(new Player(new Pizza(Pizza.Flavor.CALABRESA)));
        list.add(new Player(new Pizza(Pizza.Flavor.MUCARELA)));
        new Game(list, new DiceImpl()).play();
    }

    public Player winner;
    private CircularLinkedList<Player> players;
    private Player currentPlayer;
    private LuckyOrUnluckyDeck deck;
    private Dice dice;

    public Game(CircularLinkedList<Player> players, Dice dice) {
        this.players = players;
        this.dice = dice;
        this.deck = new LuckyOrUnluckyDeck();
        setUpBoard();
    }

    private void setUpBoard() {
        Board board = new Board();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).position = board.positions().newIterator();
        }
    }

    public void play() {
        currentPlayer = players.iterator().previous();
        
        while (currentPlayer.pizza.stillNeedIngredients()) {
            selectNextPlayer();
            System.out.println("Rodada do " + currentPlayer);
            rollDiceAndMovePlayer();
            resolvePosition();
            System.out.println("===");
        }

        winner = currentPlayer;
        System.out.println(winner + " ganhou!");
    }

    private void selectNextPlayer() {
        currentPlayer = players.iterator().next();
    }

    private void rollDiceAndMovePlayer() {
        int diceResult = dice.roll();
        System.out.println("Dado: " + diceResult);
        for (int i = 0; i < diceResult; i++) {
            currentPlayer.position.next();
        }
    }

    private void resolvePosition() {
        Position position = currentPlayer.position.current();
        System.out.println("Posição: " + position.getType());
        if (position.getType() == Position.Type.INGREDIENT) {
            tryToAddIngredient(position.getIngredient());
        } else if(position.getType() == Position.Type.LOSE_ALL_INGREDIENTS) {
            removeAllIngredientsOfCurrentPlayer();
        } else if (position.getType() == Position.Type.LUCKY_OR_UNLUCKY) {
            resolveLuckyOrUnluckyPosition();
        } else {
            throw new RuntimeException("Invalid position: " + position.getType());
        }
    }

    private void tryToAddIngredient(Pizza.Ingredient ingredient) {
        if (currentPlayer.pizza.addIngredient(ingredient)) {
            System.out.println("Ingrediente " + ingredient + " adicionado!");
        } else {
            System.out.println("O ingredienete " + ingredient + " não é necessário");
        }
    }

    private void removeAllIngredientsOfCurrentPlayer() {
        currentPlayer.pizza.removeAllIngredients();
        System.out.println("Todos os ingredientes foram removidos :(");
    }

    private void resolveLuckyOrUnluckyPosition() {
        switch (this.deck.getCard()) {
            case LOSE_AN_INGREDIENT: {
                currentPlayer.pizza.removeIngredient();
                System.out.println("Perdeu um ingrediente :(");
                return;
            }
            case BURN_THE_PIZZA: {
                currentPlayer.pizza.removeAllIngredients();
                System.out.println("Perdeu todos os ingredientes :(");
                return;
            }
            case GAIN_TWO_INGREDIENTS: {
                currentPlayer.pizza.addIngredient();
                currentPlayer.pizza.addIngredient();
                System.out.println("Ganhou dois ingredientes!");
                return;
            }
            case STEAL_INGREDIENT_FROM_ANOTHER_PLAYER: {
                currentPlayer.stealIngredientFromAnotherPlayer(players);
                return;
            }
        }
    }
}
