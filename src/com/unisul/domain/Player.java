package com.unisul.domain;

import com.unisul.utils.CircularLinkedList;
import com.unisul.utils.LinkedList;

public class Player {
    public CircularLinkedList<Position>.Iterator position;
    public Pizza pizza;

    public Player(Pizza pizza) {
        this.pizza = pizza;
    }

    public void stealIngredientFromAnotherPlayer(LinkedList<Player> players) {
        System.out.println(this.pizza.getFlavor() + " vai roubar algum ingrediente de alguem!");
        boolean foundNeededIngredientOnAnotherPlayer = false;

        // try to find any needed ingredient on another player
        // iterate through all other players
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            // (checks if player is not the current round player)
            if (p != this) {
                // checks if the other player has any ingredient that i need
                for (int j = 0; j < p.pizza.ingredientsFound().size(); j++) {
                    Pizza.Ingredient ing = p.pizza.ingredientsFound().get(j);
                    if (this.pizza.needsIngredient(ing)) {
                        foundNeededIngredientOnAnotherPlayer = true;
                        System.out.print(this.pizza.getFlavor() + " roubou a pizza de  ");
                        System.out.print(p.pizza.getFlavor());
                        System.out.println(" => Ingrediente: " + ing);
                        this.pizza.addIngredient(ing);
                        p.pizza.removeIngredient(ing);
                        break;
                    } else {
                        System.out.println(this.pizza.getFlavor() + " nao precisa de" + ing);
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
            System.out.println(this.pizza.getFlavor() + " nao encontrou nenhum ingrediente em outro jogador");
            for (int i = 0; i < players.size(); i++) {
                Player p = players.get(i);
                if (p != this && p.pizza.foundAnyIngredient()) {
                    System.out.println("entao removi algum ingrediente da " + p.pizza.getFlavor());
                    p.pizza.removeIngredient();
                    break;
                }
            }
        }
    }
}
