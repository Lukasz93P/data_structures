package com.company.tests;

import com.company.arrays.SortArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortArrayTest {
    private int[] testArray;
    private Object[] sortedTestArray;

    @BeforeEach
    void beforeEach() {
        testArray = new int[]{2, 4, 1, 8, 1, 3, 5, 20, 12};

        sortedTestArray = Arrays.stream(testArray)
                .boxed()
                .sorted(Integer::compareTo)
                .toArray();
    }

    @Test
    void bubbleSort() {
        SortArray.bubbleSort(testArray);
        assertArraysEquality();
    }

    @Test
    void selectionSort() {
        SortArray.selectionSort(testArray);
        assertArraysEquality();
    }

    @Test
    void insertionSort() {
        SortArray.insertionSort(testArray);
        assertArraysEquality();
    }

    @Test
    void shellInsertionSort() {
        SortArray.shellInsertionSort(testArray);
        assertArraysEquality();
    }

    @Test
    void mergeSort(){
        SortArray.mergeSort(testArray, 0, testArray.length);
        assertArraysEquality();
    }

    @Test
    void swap() {
        int testArrayLength = testArray.length;
        int elementAtIndexThree = testArray[3];
        int elementAtIndexSix = testArray[6];

        SortArray.swap(testArray, 3, 6);
        assertEquals(elementAtIndexSix, testArray[3]);
        assertEquals(elementAtIndexThree, testArray[6]);
        assertEquals(testArrayLength, testArray.length);
    }

    private void assertArraysEquality() {
        for (int i = 0; i < sortedTestArray.length; i++) {
            assertEquals(sortedTestArray[i], testArray[i]);
        }
    }
}