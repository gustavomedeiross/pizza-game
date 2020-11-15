package com.unisul.domain;

import com.unisul.utils.CircularLinkedList;

public class Player {
    public CircularLinkedList<Position>.Iterator position;
    public Pizza pizza;

    public Player(Pizza pizza) {
        this.pizza = pizza;
    }
}
