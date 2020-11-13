package com.unisul.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// TODO refactor + create more tests
class CircularLinkedListTest {
    private LinkedList<Integer> sut;

    @BeforeEach
    protected void setUp() {
        sut = new CircularLinkedList<Integer>();
    }

    @Test
    public void testAddOneItem() {
        sut.add(5);
        assertEquals(5, sut.first());
        assertEquals(5, sut.last());
        assertEquals(1, sut.size());
        assertFalse(sut.isEmpty());
    }

    @Test
    public void testAddTwoItems() {
        sut.add(5);
        sut.add(2);
        assertEquals(5, sut.first());
        assertEquals(2, sut.last());
        assertEquals(2, sut.size());
        assertFalse(sut.isEmpty());
    }

    @Test
    public void testAddMultipleItemsWithCircularStructure() {
        sut.add(2);
        sut.add(3);
        sut.add(5);
        sut.add(6);
        sut.add(2);
        sut.add(3);
        sut.add(5);
        sut.add(6);

        assertEquals(2, sut.first());
        assertEquals(6, sut.last());
        assertEquals(8, sut.size());
        assertFalse(sut.isEmpty());
    }

    @Test
    public void testGetElement() {
        sut.add(5);
        sut.add(2);
        sut.add(10);
        assertEquals(2, sut.get(1));
        assertEquals(5, sut.get(0));
        assertEquals(10, sut.get(2));

        try {
            sut.get(3);
            fail("get() with invalid index should have thrown an IndexOutOfBoundsException");
        } catch(IndexOutOfBoundsException e) {
            return; // passes
        }
        fail();
    }

    @Test
    public void testIndexOf() {
        sut.add(5);
        sut.add(2);
        sut.add(10);
        sut.add(2);
        assertEquals(0, sut.indexOf(5));
        assertEquals(2, sut.indexOf(10));
        assertEquals(1, sut.indexOf(2));
        assertEquals(-1, sut.indexOf(8));
    }

    @Test
    public void testPutInTheMiddle() {
        sut.add(5);
        sut.add(3);
        sut.add(4);
        sut.add(2);
        sut.add(7);

        sut.put(10, 2);
        assertEquals(2, sut.indexOf(10));
        assertEquals(5, sut.get(0));
        assertEquals(3, sut.get(1));
        assertEquals(10, sut.get(2));
        assertEquals(4, sut.get(3));
        assertEquals(2, sut.get(4));
    }

    @Test
    public void testPutAtTheStart() {
        sut.add(5);
        sut.add(3);
        sut.add(4);
        sut.add(2);
        sut.add(7);

        sut.put(10, 0);
        assertEquals(0, sut.indexOf(10));
        assertEquals(10, sut.get(0));
        assertEquals(5, sut.get(1));
        assertEquals(3, sut.get(2));
        assertEquals(4, sut.get(3));
        assertEquals(2, sut.get(4));
    }

    @Test
    public void testPutAtTheEnd() {
        sut.add(5);
        sut.add(3);
        sut.add(4);
        sut.add(2);
        sut.add(7);

        sut.put(10, 5);
        assertEquals(5, sut.indexOf(10));
        assertEquals(5, sut.get(0));
        assertEquals(3, sut.get(1));
        assertEquals(4, sut.get(2));
        assertEquals(2, sut.get(3));
        assertEquals(7, sut.get(4));
        assertEquals(10, sut.get(5));
    }

    @Test
    public void testRemoveItemOnTheMiddle() {
        sut.add(5);
        sut.add(3);
        sut.add(4);
        sut.add(7);
        sut.add(2);

        sut.remove(2);
        assertEquals(-1, sut.indexOf(4));
        assertEquals(5, sut.get(0));
        assertEquals(3, sut.get(1));
        assertEquals(7, sut.get(2));
        assertEquals(2, sut.get(3));
    }

    @Test
    public void testRemoveFirstItem() {
        sut.add(5);
        sut.add(3);
        sut.add(4);
        sut.add(7);
        sut.add(2);

        sut.remove(0);
        assertEquals(-1, sut.indexOf(5));
        assertEquals(3, sut.get(0));
        assertEquals(4, sut.get(1));
        assertEquals(7, sut.get(2));
        assertEquals(2, sut.get(3));
    }

    @Test
    public void testRemoveLastItem() {
        sut.add(5);
        sut.add(3);
        sut.add(4);
        sut.add(7);
        sut.add(2);

        sut.remove(4);
        assertEquals(-1, sut.indexOf(2));
        assertEquals(5, sut.get(0));
        assertEquals(3, sut.get(1));
        assertEquals(4, sut.get(2));
        assertEquals(7, sut.get(3));
    }

    @Test
    public void testSimpleIteratorNext() {
        sut.add(5);
        sut.add(3);

        assertEquals(sut.iterator().current(), 5);
        assertEquals(sut.iterator().next(), 3);
        assertEquals(sut.iterator().current(), 3);
    }

    @Test
    public void testIteratorCircularNext() {
        sut.add(5);
        sut.add(3);

        assertEquals(sut.iterator().current(), 5);
        assertEquals(sut.iterator().next(), 3);
        assertEquals(sut.iterator().current(), 3);
        assertEquals(sut.iterator().current(), 3);
        assertEquals(sut.iterator().next(), 5);
        assertEquals(sut.iterator().current(), 5);
    }

    @Test
    public void testNextAndPrevious() {
        sut.add(5);
        sut.add(3);
        sut.add(7);

        assertEquals(sut.iterator().current(), 5);
        assertEquals(sut.iterator().next(), 3);
        assertEquals(sut.iterator().current(), 3);
        assertEquals(sut.iterator().previous(), 5);
        assertEquals(sut.iterator().current(), 5);
    }

    @Test
    public void testCircularPrevious() {
        sut.add(5);
        sut.add(3);
        sut.add(7);

        assertEquals(sut.iterator().current(), 5);
        assertEquals(sut.iterator().previous(), 7);
        assertEquals(sut.iterator().current(), 7);
        assertEquals(sut.iterator().previous(), 3);
    }
}
