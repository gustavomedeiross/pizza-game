package com.unisul;

import com.unisul.domain.DiceImpl;
import com.unisul.domain.Game;
import com.unisul.domain.Pizza;
import com.unisul.domain.Player;
import com.unisul.utils.CircularLinkedList;

public class Main {
    public static void main(String[] args) {
        CircularLinkedList<Player> list = new CircularLinkedList<Player>();
        list.add(new Player(new Pizza(Pizza.Flavor.CALABRESA)));
        list.add(new Player(new Pizza(Pizza.Flavor.MUCARELA)));
        new Game(list, new DiceImpl()).play();
    }
}
