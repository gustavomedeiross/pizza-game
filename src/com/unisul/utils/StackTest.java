package com.unisul.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    private Stack<String> sut;

    @BeforeEach
    void setUp() {
        sut = new StackImpl<String>();
    }

    @Test
    public void testPushOneItem() {
        sut.push("x");
        assertEquals("x", sut.peek());
        assertEquals(1, sut.size());
        assertFalse(sut.isEmpty());
    }

    @Test
    public void testPushAndPopOneItem() {
        sut.push("x");
        Object popped = sut.pop();
        assertEquals("x", popped);
        assertEquals(0, sut.size());
        assertTrue(sut.isEmpty());
        assertFalse(sut.isNotEmpty());
    }

    @Test
    public void testPushTwoItems() {
        sut.push("x");
        sut.push("y");
        assertEquals("y", sut.peek());
        assertFalse(sut.isEmpty());
        assertEquals(2, sut.size());
    }

    @Test
    public void testPushTwoItemsAndPopOneItem() {
        sut.push("x");
        sut.push("y");
        assertEquals("y", sut.peek());
        assertFalse(sut.isEmpty());
        assertEquals(2, sut.size());

        Object popped = sut.pop();
        assertEquals("y", popped);
        assertEquals(1, sut.size());
        assertEquals("x", sut.peek());
    }

    @Test
    public void testPush3ItemsPop2AndPush1() {
        sut.push("x");
        sut.push("y");
        sut.push("z");
        assertEquals(3, sut.size());
        assertEquals("z", sut.peek());

        sut.pop();
        sut.pop();
        assertEquals(1, sut.size());
        assertEquals("x", sut.peek());

        sut.push("y");
        assertEquals(2, sut.size());
        assertEquals("y", sut.peek());
    }

    @Test
    public void testPush5ItemsAndPop3() {
        sut.push("1");
        sut.push("2");
        sut.push("3");
        sut.push("4");
        sut.push("5");
        assertEquals(5, sut.size());
        assertEquals("5", sut.peek());

        sut.pop();
        sut.pop();
        sut.pop();
        assertEquals(2, sut.size());
        assertEquals("2", sut.peek());
    }

    @Test
    public void testPeekEmptyStack() {
        try {
            sut.peek();
            fail();
        } catch(StackException e) {
            assertEquals(StackException.ErrorCode.EMPTY, e.getErrorCode());
            assertTrue(sut.isEmpty());
            assertEquals(0, sut.size());
        }
    }

    @Test
    public void testPopEmptyStack() {
        try {
            sut.pop();
            fail();
        } catch(StackException e) {
            assertEquals(StackException.ErrorCode.EMPTY, e.getErrorCode());
            assertTrue(sut.isEmpty());
            assertEquals(0, sut.size());
        }
    }
}

