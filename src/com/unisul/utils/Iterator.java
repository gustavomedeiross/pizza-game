package com.unisul.utils;

public class Iterator<T> {
    private CircularLinkedList<T>.Node currentNode;

    Iterator(CircularLinkedList<T>.Node current) {
        currentNode = current;
    }

    public T current() {
        return currentNode.value;
    }

    public T next() {
        currentNode = currentNode.next;
        return currentNode.value;
    }

    public T previous() {
        currentNode = currentNode.previous;
        return currentNode.value;
    }
}
