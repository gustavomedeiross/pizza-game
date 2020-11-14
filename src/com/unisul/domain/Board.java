package com.unisul.domain;

import com.unisul.utils.CircularLinkedList;

public class Board {
    private CircularLinkedList<Position> positions = new CircularLinkedList<Position>();

    public CircularLinkedList<Position> positions() {
        return positions;
    }

    public Board() {
        positions.add(Position.PERDE_TUDO);
        positions.add(Position.CEBOLA);
        positions.add(Position.SORTE_OU_AZAR);
        positions.add(Position.CALABRESA);
        positions.add(Position.QUEIJO);
        positions.add(Position.SORTE_OU_AZAR);
        positions.add(Position.SORTE_OU_AZAR);
        positions.add(Position.TOMATE);
        positions.add(Position.OVOS);
        positions.add(Position.AZEITONA);
        positions.add(Position.SORTE_OU_AZAR);
        positions.add(Position.SORTE_OU_AZAR);
        positions.add(Position.MILHO);
        positions.add(Position.AZEITONA);
        positions.add(Position.SORTE_OU_AZAR);
        positions.add(Position.PRESUNTO);
        positions.add(Position.TOMATE);
        positions.add(Position.MILHO);
        positions.add(Position.SORTE_OU_AZAR);
        positions.add(Position.SORTE_OU_AZAR);
        positions.add(Position.BROCOLIS);
        positions.add(Position.SORTE_OU_AZAR);
        positions.add(Position.OVOS);
        positions.add(Position.CEBOLA);
        positions.add(Position.SORTE_OU_AZAR);
        positions.add(Position.CALABRESA);
        positions.add(Position.SORTE_OU_AZAR);
        positions.add(Position.PRESUNTO);
        positions.add(Position.SORTE_OU_AZAR);
        positions.add(Position.QUEIJO);
        positions.add(Position.AZEITONA);
        positions.add(Position.SORTE_OU_AZAR);
        positions.add(Position.BROCOLIS);
        positions.add(Position.AZEITONA);
        positions.add(Position.SORTE_OU_AZAR);
    }

    public enum Position {
        PERDE_TUDO,
        CEBOLA,
        SORTE_OU_AZAR,
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
