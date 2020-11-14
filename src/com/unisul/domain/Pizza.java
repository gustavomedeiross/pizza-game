package com.unisul.domain;

import com.unisul.utils.LinkedList;
import com.unisul.utils.LinkedListImpl;

public class Pizza {
    private LinkedList<Ingredient> ingredients;

    public Pizza(Type type) {
        ingredients = new LinkedListImpl<>();

        switch(type) {
            case CALABRESA: {
                ingredients.add(Ingredient.CALABRESA);
                ingredients.add(Ingredient.AZEITONA);
                ingredients.add(Ingredient.QUEIJO);
                break;
            }
            case MUCARELA: {
                ingredients.add(Ingredient.AZEITONA);
                ingredients.add(Ingredient.QUEIJO);
                ingredients.add(Ingredient.MILHO);
                break;
            }
            case PORTUGUESA: {
                ingredients.add(Ingredient.CALABRESA);
                ingredients.add(Ingredient.AZEITONA);
                ingredients.add(Ingredient.QUEIJO);
                ingredients.add(Ingredient.TOMATE);
                ingredients.add(Ingredient.OVOS);
                break;
            }
        }
    }

    public enum Type {
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
