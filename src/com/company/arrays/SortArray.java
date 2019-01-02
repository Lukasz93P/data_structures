package com.company.arrays;

import java.util.Arrays;

public class SortArray {

    private SortArray() {
        throw new UnsupportedOperationException();
    }

//    public static void main(String[] args) {
//        int[] a = new int[]{1,2,3,4,5,6,7};
//        reverse(a);
//        Arrays.stream(a).forEach(System.out::println);
//    }

    public static void bubbleSort(int[] array) {
        for (int lastUnsortedElementIndex = array.length - 1; lastUnsortedElementIndex > 0; lastUnsortedElementIndex--) {
            for (int i = 0; i < lastUnsortedElementIndex; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        for (int lastUnsortedElementIndex = array.length - 1; lastUnsortedElementIndex > 0; lastUnsortedElementIndex--) {
            int highestValueElementIndex = 0;
            for (int i = 0; i <= lastUnsortedElementIndex; i++) {
                if (array[i] >= array[highestValueElementIndex]) {
                    highestValueElementIndex = i;
                }
            }
            swap(array, lastUnsortedElementIndex, highestValueElementIndex);
        }
    }

    public static void insertionSort(int[] array) {
        for (int firstUnsortedElementIndex = 1; firstUnsortedElementIndex < array.length; firstUnsortedElementIndex++) {
            int temp = array[firstUnsortedElementIndex];
            int i = firstUnsortedElementIndex;
            for (; i > 0 && array[i - 1] > temp; i--) {
                swap(array, i, i - 1);
            }
            array[i] = temp;
        }
    }

    public static void shellInsertionSort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int temp = array[i];
                int j = i;
                while (j >= gap && array[j - gap] > temp) {
                    swap(array, j, j - gap);
                    j -= gap;
                }
                array[j] = temp;
            }
        }
    }

    public static void mergeSort(int[] array, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int middleIndex = (start + end) / 2;
        mergeSort(array, start, middleIndex);
        mergeSort(array, middleIndex, end);
        merge(array, start, middleIndex, end);
    }

    private static void merge(int[] array, int start, int middle, int end) {
        if (array[middle - 1] < array[middle]) {
            return;
        }
        int[] tempArray = new int[end - start];
        int tempArrayIndex = 0;

        int leftArrayCounter = start;
        int rightArrayCounter = middle;

        while (leftArrayCounter < middle && rightArrayCounter < end) {
            tempArray[tempArrayIndex++] = array[leftArrayCounter] <= array[rightArrayCounter]
                    ? array[leftArrayCounter++]
                    : array[rightArrayCounter++];
        }
        System.arraycopy(array, leftArrayCounter, array, start + tempArrayIndex, middle - leftArrayCounter);
        System.arraycopy(tempArray, 0, array, start, tempArrayIndex);
    }


//    public static void bubbleSort(int[] array) {
//        for (int lastUnsortedElementIndex = array.length - 1; lastUnsortedElementIndex > 0; lastUnsortedElementIndex--) {
//            for (int i = 0; i < lastUnsortedElementIndex; i++) {
//                if (array[i] > array[i + 1]) {
//                    swap(array, i, i + 1);
//                }
//            }
//        }
//    }
//
//    public static void selectionSort(int[] array) {
//        for (int lastUnsortedElementIndex = array.length - 1; lastUnsortedElementIndex > 0; lastUnsortedElementIndex--) {
//            int highestValueElementIndex = 0;
//            for (int i = 1; i <= lastUnsortedElementIndex; i++) {
//                if (array[i] >= array[highestValueElementIndex]) {
//                    highestValueElementIndex = i;
//                }
//            }
//            swap(array, highestValueElementIndex, lastUnsortedElementIndex);
//        }
//    }
//
//    public static void insertionSort(int[] array) {
//        for (int firstUnsortedElementIndex = 1; firstUnsortedElementIndex < array.length; firstUnsortedElementIndex++) {
//            int temp = array[firstUnsortedElementIndex];
//            int counter = firstUnsortedElementIndex;
//            while (counter > 0 && array[counter - 1] > array[counter]) {
//                swap(array, counter, counter - 1);
//                counter--;
//            }
//            array[counter] = temp;
//        }
//    }
//
//    public static void shellInsertionSort(int[] array) {
//        for (int gap = array.length / 2; gap > 0; gap /= 2) {
//            for (int i = gap; i < array.length; i++) {
//                int temp = array[i];
//                int counter = i;
//                while (counter >= gap && array[counter - gap] > array[counter]) {
//                    swap(array, counter, counter - gap);
//                    counter -= gap;
//                }
//                array[counter] = temp;
//            }
//        }
//    }
//
//    public static void mergeSort(int[] array, int start, int end) {
//        if (end - start < 2) {
//            return;
//        }
//
//        int middle = (start + end) / 2;
//        mergeSort(array, start, middle);
//        mergeSort(array, middle, end);
//        merge(array, start, middle, end);
//    }
//
//    private static void merge(int[] array, int start, int middle, int end) {
//        if (array[middle - 1] < array[middle]) {
//            return;
//        }
//
//        int[] tempArray = new int[end - start];
//        int tempArrayIndex = 0;
//
//        int leftArrayIterator = start;
//        int rightArrayIterator = middle;
//
//        while (leftArrayIterator < middle && rightArrayIterator < end) {
//            tempArray[tempArrayIndex++] = array[leftArrayIterator] <= array[rightArrayIterator]
//                    ? array[leftArrayIterator++]
//                    : array[rightArrayIterator++];
//        }
//
//        System.arraycopy(array, leftArrayIterator, array, start + tempArrayIndex, middle - leftArrayIterator);
//        System.arraycopy(tempArray, 0, array, start, tempArrayIndex);
//    }

    public static void swap(int[] array, int firstIndex, int secondIndex) {
        if (firstIndex == secondIndex) {
            return;
        }
        if (firstIndex >= array.length || secondIndex >= array.length) {
            throw new IndexOutOfBoundsException("First index : "
                    + firstIndex + " second index " + secondIndex
                    + " array length : " + array.length);
        }

        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}
