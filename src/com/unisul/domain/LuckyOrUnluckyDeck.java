package com.unisul.domain;

import com.unisul.utils.Stack;
import com.unisul.utils.StackImpl;

import java.util.Random;

public class LuckyOrUnluckyDeck {
    private Stack<Type> cards;

    LuckyOrUnluckyDeck() {
        cards = new StackImpl<>();
        shuffle();
    }

    private void shuffle() {
        for (int i = 0; i < 20; i++)
            cards.push(pickARandomType());
    }

    private Type pickARandomType() {
        int pick = new Random().nextInt(Type.values().length);
        return Type.values()[pick];
    }

    public Type getCard() {
        if (cards.size() == 0)
            shuffle();
        return cards.pop();
    }

    enum Type {
        LOSE_AN_INGREDIENT,
        GAIN_TWO_INGREDIENTS,
        STEAL_INGREDIENT_FROM_ANOTHER_PLAYER,
        BURN_THE_PIZZA
    }
}
