package com.unisul.domain;

import com.unisul.utils.CircularLinkedList;

public class Board {
    private CircularLinkedList<Position> positions = new CircularLinkedList<Position>();

    public CircularLinkedList<Position> positions() {
        return positions;
    }

    public Board() {
        positions.add(new Position(Position.Type.PERDE_TUDO));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.CEBOLA));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.CALABRESA));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.QUEIJO));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.TOMATE));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.OVOS));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.AZEITONA));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.MILHO));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.AZEITONA));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.PRESUNTO));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.TOMATE));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.MILHO));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.BROCOLIS));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.OVOS));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.CEBOLA));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.CALABRESA));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.PRESUNTO));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.QUEIJO));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.AZEITONA));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.BROCOLIS));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.AZEITONA));
        positions.add(new Position(Position.Type.SORTE_OU_AZAR));
    }
}

