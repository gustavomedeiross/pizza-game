package com.unisul.domain;

import com.unisul.utils.LinkedListImpl;

public class Pizza {
    private Flavor flavor;
    public LinkedListImpl<Ingredient> ingredients;

    public Pizza(Flavor flavor) {
        this.flavor = flavor;
        ingredients = new LinkedListImpl<>();

        switch(flavor) {
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
                ingredients.add(Ingredient.QUEIJO);
                ingredients.add(Ingredient.TOMATE);
                ingredients.add(Ingredient.OVOS);
                break;
            }
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
