package com.unisul.utils;

public class LinkedListImpl<T> implements LinkedList<T> {
    private Node head;

    private LinkedListImpl.Iterator iterator;

    public Iterator iterator() {
        if (iterator != null) {
            return iterator;
        } else {
            iterator = new LinkedListImpl.Iterator(head);
            return iterator;
        }
    }


    @Override
    public void add(T t) {
        Node node = new Node(t);
        if (isEmpty()) {
            head = node;
        } else {
            last(head).next = node;
        }
    }

    @Override
    public void put(T t, int index) {
        Node newNode = new Node(t);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = getNodeByIndex(index-1);
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    @Override
    public void remove(int index) {
        if (index == 0) {
            head = head.next;
        } else {
            Node node = getNodeByIndex(index - 1);
            node.next = node.next.next;
        }
    }

    private Node getNodeByIndex(int index) {
        Node current = head;
        for (int i=0; i<index; i++)
            current = current.next;
        return current;
    }

    @Override
    public T first() {
        if (isNotEmpty()) {
            return head.value;
        }
        return null;
    }

    @Override
    public T last() {
        if (isNotEmpty()) {
            return last(head).value;
        }
        return null;
    }

    private Node last(Node node) {
        return node.next != null ? last(node.next) : node;
    }

    @Override
    public T get(int index) {
        if (index+1 > size()) {
            throw new IndexOutOfBoundsException();
        } else {
            return getNodeByIndex(index).value;
        }
    }

    @Override
    public int indexOf(T t) {
        int size = size();
        Node current = head;

        for(int i = 0; i<size; i++) {
            if (t.equals(current.value)) {
                return i;
            }
            current = current.next;
        }

        return -1;
    }

    @Override
    public int size() {
        return size(head);
    }

    private int size(Node node)  {
        if (node != null) {
            return 1 + size(node.next);
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return size() <= 0;
    }

    private boolean isNotEmpty()  {
        return ! isEmpty();
    }

    private class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
            next = null;
        }
    }

    public class Iterator {
        private LinkedListImpl<T>.Node currentNode;

        Iterator(LinkedListImpl<T>.Node current) {
            currentNode = current;
        }

        public T current() {
            return currentNode.value;
        }

        public T next() {
            currentNode = currentNode.next;
            return currentNode.value;
        }
    }
}

