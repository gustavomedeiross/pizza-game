package com.unisul.domain;

import com.unisul.utils.CircularLinkedList;

public class Game {
    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        Board board = new Board();

        for(int i=0; i < 40; i++) {
            System.out.println(board.positions().iterator().current());
            board.positions().iterator().next();
        }
    }
}
