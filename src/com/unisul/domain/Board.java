package com.unisul.domain;

import com.unisul.utils.CircularLinkedList;

public class Board {
    private CircularLinkedList<Position> positions = new CircularLinkedList<Position>();

    public CircularLinkedList<Position> positions() {
        return positions;
    }

    public Board() {
        positions.add(new Position(Position.Type.LOSE_ALL_INGREDIENTS));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.CEBOLA));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.CALABRESA));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.QUEIJO));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.TOMATE));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.OVOS));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.AZEITONA));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.MILHO));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.AZEITONA));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.PRESUNTO));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.TOMATE));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.MILHO));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.BROCOLIS));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.OVOS));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.CEBOLA));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.CALABRESA));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.PRESUNTO));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.QUEIJO));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.AZEITONA));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.BROCOLIS));
        positions.add(new Position(Position.Type.INGREDIENT, Pizza.Ingredient.AZEITONA));
        positions.add(new Position(Position.Type.LUCKY_OR_UNLUCKY));
    }
}

