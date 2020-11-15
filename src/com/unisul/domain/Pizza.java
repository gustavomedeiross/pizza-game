package com.unisul.domain;

import com.unisul.utils.LinkedListImpl;

public class Pizza {
    private Flavor flavor;
    public LinkedListImpl<Ingredient> ingredients;

    public Pizza(Flavor flavor) {
        this.flavor = flavor;
        this.ingredients = new LinkedListImpl<>(requiredIngredients());
    }

    public boolean needsIngredient(Ingredient ingredient) {
        int pizzaIngredientIndex = this.ingredients.indexOf(ingredient);
        return pizzaIngredientIndex >= 0;
    }

    public boolean addIngredient() {
        Ingredient[] ingredients = requiredIngredients();

        for (int i = 0; i < ingredients.length; i++) {
            if (needsIngredient(ingredients[i])) {
                addIngredient(ingredients[i]);
                return true;
            }
        }
        return false;
    }

    public boolean addIngredient(Ingredient ingredient) {
        if (needsIngredient(ingredient)) {
            int pizzaIngredientIndex = ingredients.indexOf(ingredient);
            ingredients.remove(pizzaIngredientIndex);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeIngredient() {
        Ingredient[] ingredients = requiredIngredients();

        for (int i = 0; i < ingredients.length; i++) {
            if (! needsIngredient(ingredients[i])) {
                this.ingredients.add(ingredients[i]);
                return true;
            }
        }
        return false;
    }

    // TODO usar linked list ao inves de um array
    private Ingredient[] requiredIngredients() {
        if (flavor == Flavor.CALABRESA) {
            return new Ingredient[] {
                    Ingredient.CALABRESA,
                    Ingredient.QUEIJO,
                    Ingredient.CEBOLA
            };
        } else if (flavor == Flavor.MUCARELA) {
            return new Ingredient[] {
                    Ingredient.QUEIJO,
                    Ingredient.AZEITONA,
                    Ingredient.TOMATE
            };
        } else if (flavor == Flavor.PORTUGUESA) {
            return  new Ingredient[] {
                    Ingredient.CALABRESA,
                    Ingredient.QUEIJO,
                    Ingredient.CEBOLA,
                    Ingredient.OVOS
            };
        } else {
            throw new RuntimeException();
        }
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public enum Flavor {
        CALABRESA,
        PORTUGUESA,
        MUCARELA
    }

    public enum Ingredient {
        CEBOLA,
        CALABRESA,
        QUEIJO,
        TOMATE,
        OVOS,
        AZEITONA,
        MILHO,
        PRESUNTO,
        BROCOLIS,
    }
}
