package com.company.tests;

import com.company.arrays.CustomArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {

    private CustomArrayList<String> customArrayList;

    @BeforeEach
    void setUp() {
        customArrayList = CustomArrayList.get(String.class, 5);
    }

    @Test
    void size() {
        assertEquals(0, customArrayList.size());
        customArrayList.add("a");
        customArrayList.add("b");
        assertEquals(2, customArrayList.size());
        customArrayList.add("a");
        assertEquals(2, customArrayList.size());
    }

    @Test
    void isEmpty() {
        assertTrue(customArrayList.isEmpty());
        customArrayList.add("a");
        assertFalse(customArrayList.isEmpty());
        customArrayList.remove("a");
        assertTrue(customArrayList.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(customArrayList.contains("a"));
        assertFalse(customArrayList.contains("b"));
        customArrayList.add("a");
        customArrayList.add("b");
        assertTrue(customArrayList.contains("a"));
        assertTrue(customArrayList.contains("b"));
        assertFalse(customArrayList.contains("c"));
    }

    @Test
    void iterator() {
    }

    @Test
    void toArray() {
        assertEquals(0, customArrayList.toArray().length);
        customArrayList.add("a");
        customArrayList.add("b");
        Object[] arrayOf = customArrayList.toArray();
        assertEquals(2, arrayOf.length);
        assertEquals("a", arrayOf[0]);
        assertEquals("b", arrayOf[1]);
    }

    @Test
    void add() {
        List<String> stringList = List.of("a", "b", "c", "d", "e", "f");
        for (int i = 0; i < stringList.size(); i++) {
            customArrayList.add(stringList.get(i));
            assertEquals(stringList.get(i), customArrayList.get(i));
            assertEquals(i + 1, customArrayList.size());
        }
    }

    @Test
    void remove() {
        assertFalse(customArrayList.remove("a"));
        customArrayList.add("a");
        customArrayList.add("b");
        assertTrue(customArrayList.remove("a"));
        assertTrue(customArrayList.contains("b"));
        assertFalse(customArrayList.contains("a"));
        assertEquals(1, customArrayList.size());
    }

    @Test
    void containsAllAndAddAll() {
            List<String> stringList = List.of("a", "b", "c");
            assertFalse(customArrayList.containsAll(stringList));
            customArrayList.addAll(stringList);
            assertTrue(customArrayList.containsAll(List.of("b", "c", "a")));
            assertTrue(customArrayList.containsAll(List.of("b", "c")));
            customArrayList.remove("a");
            assertFalse(customArrayList.containsAll(List.of("b", "c", "a")));
            assertTrue(customArrayList.containsAll(List.of("b", "c")));
    }

    @Test
    void addAllWithStartingIndex(){
        List<String> stringList = List.of("a", "b", "c");
        customArrayList.addAll(stringList);
        List<String> stringList2 = List.of("d", "e", "f");
        customArrayList.addAll(2, stringList2);
        assertEquals(stringList.size() + stringList2.size(), customArrayList.size());
        assertEquals("b", customArrayList.get(1));
        assertEquals("c", customArrayList.get(5));
        customArrayList.addAll(List.of("a", "b"));
        assertEquals(stringList.size() + stringList2.size(), customArrayList.size());
        assertEquals("b", customArrayList.get(1));
        assertEquals("c", customArrayList.get(5));
    }

    @Test
    void removeAll() {
        List<String> stringList = List.of("a", "b", "c");
        customArrayList.addAll(stringList);
        customArrayList.removeAll(List.of("b"));
        assertEquals(2, customArrayList.size());
        customArrayList.removeAll(List.of("b", "c"));
        assertEquals(1, customArrayList.size());
        assertFalse(customArrayList.contains("b"));
        assertFalse(customArrayList.contains("c"));
        assertTrue(customArrayList.contains("a"));
    }

    @Test
    void retainAll() {
        List<String> stringList = List.of("a", "b", "c");
        customArrayList.addAll(stringList);
        customArrayList.retainAll(List.of("b", "a"));
        assertEquals(2, customArrayList.size());
        customArrayList.retainAll(List.of("a"));
        assertEquals(1, customArrayList.size());
        assertFalse(customArrayList.contains("b"));
        assertFalse(customArrayList.contains("c"));
        assertTrue(customArrayList.contains("a"));
    }

    @Test
    void clear() {
        List<String> stringList = List.of("a", "b", "c");
        customArrayList.addAll(stringList);
        customArrayList.clear();
        assertTrue(customArrayList.isEmpty());
        assertEquals(0, customArrayList.size());
    }

    @Test
    void get() {
        List<String> stringList = List.of("a", "b", "c");
        assertThrows(IndexOutOfBoundsException.class, ()-> customArrayList.get(0));
        customArrayList.addAll(stringList);
        assertEquals("b", customArrayList.get(1));
        assertEquals("c", customArrayList.get(2));
    }

    @Test
    void set() {
        assertThrows(IndexOutOfBoundsException.class, ()-> customArrayList.set(3, "a"));
        customArrayList.add("a");
        customArrayList.add("b");
        customArrayList.set(1, "c");
        assertEquals(2, customArrayList.size());
        assertEquals("c", customArrayList.get(1));
        assertFalse(customArrayList.contains("b"));
    }

    @Test
    void add1() {
        assertThrows(IndexOutOfBoundsException.class, ()-> customArrayList.set(3, "a"));
        customArrayList.add("a");
        customArrayList.add("b");
        customArrayList.add(1, "c");
        assertEquals(3, customArrayList.size());
        assertEquals("c", customArrayList.get(1));
        assertEquals("b", customArrayList.get(2));
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