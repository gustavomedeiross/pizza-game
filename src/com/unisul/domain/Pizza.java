package com.unisul.domain;

import com.unisul.utils.LinkedList;
import com.unisul.utils.LinkedListImpl;

public class Pizza {
    private Flavor flavor;
    private LinkedListImpl<Ingredient> ingredients;

    public Pizza(Flavor flavor) {
        this.flavor = flavor;
        this.ingredients = new LinkedListImpl<>(requiredIngredients());
    }

    public boolean hasAllIngredients() {
        return ingredients.isEmpty();
    }

    public boolean stillNeedIngredients() {
        return !hasAllIngredients();
    }

    public boolean needsIngredient(Ingredient ingredient) {
        return this.ingredients.has(ingredient);
    }

    public boolean foundAnyIngredient() {
        Ingredient[] ingredients = requiredIngredients();
        return ingredients.length > this.ingredients.size();
    }

    public LinkedList<Ingredient> ingredientsFound() {
        Ingredient[] ingredients = requiredIngredients();

        LinkedList<Ingredient> found = new LinkedListImpl<>();

        for (int i = 0; i < ingredients.length; i++) {
            if (! this.ingredients.has(ingredients[i]))
                found.add(ingredients[i]);
        }

        return found;
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

    public boolean removeIngredient(Ingredient ingredient) {
        Ingredient[] ingredients = requiredIngredients();
        boolean ingredientIsRequired = false;

        for (int i = 0; i < ingredients.length; i++) {
            if (ingredients[i] == ingredient) {
                ingredientIsRequired = true;
                break;
            }
        }

        if (ingredientIsRequired && ! needsIngredient(ingredient)) {
            this.ingredients.add(ingredient);
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

    public void removeAllIngredients() {
        Ingredient[] ingredients = requiredIngredients();

        for (int i = 0; i < ingredients.length; i++)
            if (! needsIngredient(ingredients[i]))
                this.ingredients.add(ingredients[i]);
    }

    // TODO usar linked list ao inves de um array
    private Ingredient[] requiredIngredients() {
        if (flavor == Flavor.CALABRESA) {
            return new Ingredient[] {
                    Ingredient.CALABRESA,
                    Ingredient.QUEIJO,
                    Ingredient.CEBOLA,
                    Ingredient.AZEITONA,
                    Ingredient.TOMATE,
            };
        } else if (flavor == Flavor.MUCARELA) {
            return new Ingredient[] {
                    Ingredient.QUEIJO,
                    Ingredient.AZEITONA,
                    Ingredient.TOMATE,
                    Ingredient.MILHO,
                    Ingredient.OVOS,
            };
        } else if (flavor == Flavor.PORTUGUESA) {
            return  new Ingredient[] {
                    Ingredient.CALABRESA,
                    Ingredient.QUEIJO,
                    Ingredient.CEBOLA,
                    Ingredient.OVOS,
                    Ingredient.AZEITONA,
            };
        } else if (flavor == Flavor.LOMBINHO) {
            return  new Ingredient[] {
                    Ingredient.QUEIJO,
                    Ingredient.AZEITONA,
                    Ingredient.TOMATE,
                    Ingredient.PRESUNTO,
                    Ingredient.BROCOLIS,
            };
        } else if (flavor == Flavor.BAIANA) {
            return  new Ingredient[] {
                    Ingredient.CALABRESA,
                    Ingredient.QUEIJO,
                    Ingredient.CEBOLA,
                    Ingredient.OVOS,
                    Ingredient.MILHO,
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
        MUCARELA,
        LOMBINHO,
        BAIANA,
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
