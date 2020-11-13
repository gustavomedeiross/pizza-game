package com.unisul.utils;

public class CircularLinkedList<T> implements LinkedList<T> {
    private Node<T> head;

    @Override
    public void add(T t) {
        Node<T> node = new Node<T>(t);
        if (isEmpty()) {
            head = node;

            node.previous = node;
            node.next = node;
        } else {
            Node<T> last = last(head);

            Node<T> next = last.next;

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


        Node<T> current = head;
        int count = 1;

        while(current.next != head) {
            count++;
            current = current.next;
        }

        return count;
    }

    private Node<T> last(Node<T> node) {
        return head.previous;
    }

    @Override
    public void put(T t, int index) {
        Node<T> newNode = new Node<T>(t);
        if (index == 0) {
            Node<T> previous = head.previous;

            previous.next = newNode;
            newNode.previous = previous;

            newNode.next = head;
            head.previous = newNode;

            head = newNode;
        } else {
            Node<T> current = getNodeByIndex(index-1);
            // TODO non-reference to previous is not breaking any tests
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    @Override
    public void remove(int index) {
        if (index == 0) {
            Node<T> newHead = head.next;
            Node<T> previous = head.previous;

            newHead.previous = previous;
            previous.next = newHead;

            head = newHead;
        } else {
            Node<T> left = getNodeByIndex(index - 1);
            Node<T> middle = left.next;
            Node<T> right = middle.next;
            left.next = right;

            if (right != null)
                right.previous = left;
        }
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> current = head;
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
        Node<T> current = head;

        for(int i = 0; i<size; i++) {
            if (t.equals(current.value)) {
                return i;
            }
            current = current.next;
        }

        return -1;
    }

    private boolean isNotEmpty()  {
        return ! isEmpty();
    }

    private class Node<U> {
        U value;
        Node<U> previous;
        Node<U> next;

        Node(U value) {
            this.value = value;
            previous = null;
            next = null;
        }
    }
}
