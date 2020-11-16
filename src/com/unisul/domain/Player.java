package com.unisul.domain;

import com.unisul.utils.CircularLinkedList;
import com.unisul.utils.LinkedList;

public class Player {
    public CircularLinkedList<Position>.Iterator position;
    public Pizza pizza;

    public Player(Pizza pizza) {
        this.pizza = pizza;
    }

    public boolean stealIngredientFromAnotherPlayer(LinkedList<Player> players) {
        boolean stealed = tryToStealIngredientFromAnotherPlayer(players);
        if (stealed) {
            return true;
        } else {
            return thenJustRemoveAnyIngredient(players);
        }
    }

    private boolean tryToStealIngredientFromAnotherPlayer(LinkedList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (playerIsNotMe(player)) {
                if (playerHasAnyIngredientThatINeed(player)) {
                    stealIngredient(player);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean playerIsNotMe(Player player) {
        return player != this;
    }

    private boolean playerHasAnyIngredientThatINeed(Player player) {
        for (int i = 0; i < player.pizza.ingredientsFound().size(); i++) {
            Pizza.Ingredient ingredient = player.pizza.ingredientsFound().get(i);
            if (this.pizza.needsIngredient(ingredient)) return true;
        }
        return false;
    }

    private void stealIngredient(Player player) {
        for (int j = 0; j < player.pizza.ingredientsFound().size(); j++) {
            Pizza.Ingredient ingredient = player.pizza.ingredientsFound().get(j);
            if (this.pizza.needsIngredient(ingredient)) {
                this.pizza.addIngredient(ingredient);
                player.pizza.removeIngredient(ingredient);
                System.out.println("Roubou o ingrediente " + ingredient + " do " + player);
                return;
            }
        }
    }

    private boolean thenJustRemoveAnyIngredient(LinkedList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            if (p != this && p.pizza.foundAnyIngredient()) {
                p.pizza.removeIngredient();
                System.out.println("Removeu um ingrediente do " + p);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String message = "Jogador da pizza de " + this.pizza.getFlavor() + " [";
        for (int i = 0; i < this.pizza.ingredientsFound().size() - 1; i++) {
            message += this.pizza.ingredientsFound().get(i) + ", ";
        }
        return message + this.pizza.ingredientsFound().last() + "]";
    }
}
