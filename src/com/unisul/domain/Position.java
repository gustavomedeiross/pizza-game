package com.unisul.domain;

public class Position {
    private Type type;
    private Pizza.Ingredient ingredient;

    public Position(Type type) {
        if (type == Type.INGREDIENT) throw new RuntimeException();

        this.type = type;
    }

    public Position(Type type, Pizza.Ingredient ingredient) {
        if (type != Type.INGREDIENT) throw new RuntimeException();

        this.type = type;
        this.ingredient = ingredient;
    }

    public Type getType() {
        return type;
    }

    public Pizza.Ingredient getIngredient() {
        if (type != Type.INGREDIENT) throw new RuntimeException();
        return ingredient;
    }

    public enum Type {
        INGREDIENT,
        LUCKY_OR_UNLUCKY,
        LOSE_ALL_INGREDIENTS,
    }
}
