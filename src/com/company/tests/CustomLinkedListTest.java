package com.company.tests;

import com.company.arrays.CustomLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomLinkedListTest {

    private CustomLinkedList<String> customLinkedList;

    @BeforeEach
    void setUp() {
        customLinkedList = CustomLinkedList.create();
    }

    @Test
    void size() {
        assertEquals(0, customLinkedList.size());
        customLinkedList.add("a");
        assertEquals(1, customLinkedList.size());
        customLinkedList.add("b");
        assertEquals(2, customLinkedList.size());
    }

    @Test
    void isEmpty() {
        assertTrue(customLinkedList.isEmpty());
        customLinkedList.add("a");
        assertFalse(customLinkedList.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(customLinkedList.contains("a"));
        customLinkedList.add("a");
        assertTrue(customLinkedList.contains("a"));
        assertFalse(customLinkedList.contains("b"));
        customLinkedList.add("b");
        assertTrue(customLinkedList.contains("b"));
    }

    @Test
    void iterator() {
    }

    @Test
    void toArray() {
        assertEquals(0, customLinkedList.toArray().length);
        customLinkedList.add("a");
        customLinkedList.add("b");
        Object[] arrayOf = customLinkedList.toArray();
        assertEquals(2, arrayOf.length);
        assertEquals("a", arrayOf[0]);
        assertEquals("b", arrayOf[1]);
    }

    @Test
    void toArray1() {
    }

    @Test
    void add() {
        customLinkedList.add("a");
        customLinkedList.add("b");
        assertTrue(customLinkedList.contains("a"));
        assertTrue(customLinkedList.contains("b"));
        assertFalse(customLinkedList.contains("bc"));
        assertEquals(2, customLinkedList.size());
    }

    @Test
    void remove() {
        customLinkedList.add("a");
        customLinkedList.add("b");
        customLinkedList.add("c");
        customLinkedList.add("d");
        customLinkedList.add("e");
        customLinkedList.add("f");
        assertEquals(6, customLinkedList.size());
        customLinkedList.remove("c");
        assertEquals(5, customLinkedList.size());
        assertTrue(customLinkedList.contains("d"));
        assertTrue(customLinkedList.contains("b"));
        assertFalse(customLinkedList.contains("c"));
    }

    @Test
    void containsAll() {
    }

    @Test
    void addAll() {
    }

    @Test
    void addAll1() {
    }

    @Test
    void removeAll() {
    }

    @Test
    void retainAll() {
    }

    @Test
    void clear() {
    }

    @Test
    void get() {
    }

    @Test
    void set() {
    }

    @Test
    void add1() {
    }

    @Test
    void remove1() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void lastIndexOf() {
    }

    @Test
    void listIterator() {
    }

    @Test
    void listIterator1() {
    }

    @Test
    void subList() {
    }
}