package com.unisul.utils;

public class CircularLinkedList<T> implements LinkedList<T> {
    private Node head;

    private Iterator iterator;

    public Iterator iterator() {
        if (iterator != null) {
            return iterator;
        } else {
            iterator = newIterator();
            return iterator;
        }
    }

    public Iterator newIterator() {
        return new Iterator(head);
    }

    @Override
    public void add(T t) {
        Node node = new Node(t);
        if (isEmpty()) {
            head = node;

            node.previous = node;
            node.next = node;
        } else {
            Node last = last(head);

            Node next = last.next;

            node.next = next;
            next.previous = node;

            node.previous = last;
            last.next = node;
        }
    }

    @Override
    public boolean isEmpty() {
        return size() <= 0;
    }

    @Override
    public int size() {
        if (head == null) return 0;
        if (head == head.next) return 1;


        Node current = head;
        int count = 1;

        while(current.next != head) {
            count++;
            current = current.next;
        }

        return count;
    }

    private Node last(Node node) {
        return head.previous;
    }

    @Override
    public void put(T t, int index) {
        Node newNode = new Node(t);
        if (index == 0) {
            Node previous = head.previous;

            previous.next = newNode;
            newNode.previous = previous;

            newNode.next = head;
            head.previous = newNode;

            head = newNode;
        } else {
            Node left = getNodeByIndex(index-1);
            Node right = left.next;
            newNode.next = right;
            right.previous = newNode;
            left.next = newNode;
        }
    }

    @Override
    public void remove(int index) {
        if (index == 0) {
            Node newHead = head.next;
            Node previous = head.previous;

            newHead.previous = previous;
            previous.next = newHead;

            head = newHead;
        } else {
            Node left = getNodeByIndex(index - 1);
            Node middle = left.next;
            Node right = middle.next;
            left.next = right;

            if (right != null)
                right.previous = left;
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
    public boolean has(T t) {
        return indexOf(t) != -1;
    }

    private boolean isNotEmpty()  {
        return ! isEmpty();
    }

    class Node {
        T value;
        Node previous;
        Node next;

        Node(T value) {
            this.value = value;
            previous = null;
            next = null;
        }
    }

    public class Iterator {
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
}
