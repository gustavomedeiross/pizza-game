package com.unisul.utils;

public class StackImpl<T> implements Stack<T> {
    private Node<T> head;

    public void push(T t) {
        Node<T> last = new Node<>(t);
        last.previous = head;
        head = last;
    }

    public T pop() {
        if (isEmpty()) {
            throw new StackException(StackException.ErrorCode.EMPTY);
        }

        Node<T> popped = head;
        head = head.previous;
        return popped.value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new StackException(StackException.ErrorCode.EMPTY);
        }
        return head.value;
    }

    public int size() {
        return size(head);
    }

    private int size(Node<T> node)  {
        if (node != null) {
            return 1 + size(node.previous);
        }
        return 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean isNotEmpty() {
        return ! this.isEmpty();
    }

    private class Node<E> {
        Node<E> previous;
        E value;

        Node(E value) {
            this.value = value;
        }
    }
}
