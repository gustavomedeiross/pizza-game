package com.unisul.domain;

public class DiceImpl implements Dice {
    @Override
    public int roll() {
        return (int) (6 * Math.random());
    }
}
