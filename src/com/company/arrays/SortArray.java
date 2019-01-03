package com.company.arrays;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SortArray {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static final Runtime runtime = Runtime.getRuntime();

    private SortArray() {
        throw new UnsupportedOperationException();
    }

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
            for (int i = 1; i <= lastUnsortedElementIndex; i++) {
                if (array[i] >= array[highestValueElementIndex]) {
                    highestValueElementIndex = i;
                }
            }
            swap(array, highestValueElementIndex, lastUnsortedElementIndex);
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
        parallelMergeSort(array, start, end, runtime.availableProcessors(), null);
    }

    private static void parallelMergeSort(int[] array, int start, int end, int numThreads, CountDownLatch countDownLatch) {
        if (end - start < 2) {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
            return;
        }
        int middle = (start + end) / 2;

        if (numThreads >= 2) {
            CountDownLatch selfCountdown = new CountDownLatch(2);
            executorService.submit(() -> SortArray.parallelMergeSort(array, start, middle, numThreads - 2, selfCountdown));
            executorService.submit(() -> SortArray.parallelMergeSort(array, middle, end, numThreads - 2, selfCountdown));
            try {
                selfCountdown.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            parallelMergeSort(array, start, middle, numThreads - 2, null);
            parallelMergeSort(array, middle, end, numThreads - 2, null);
        }
        merge(array, start, middle, end);
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public static void merge(int[] array, int start, int middle, int end) {
        if (array[middle - 1] <= array[middle]) {
            return;
        }
        int[] tempArray = new int[end - start];
        int tempArrayIndex = 0;

        int leftArrayIterator = start;
        int rightArrayIterator = middle;

        while (leftArrayIterator < middle && rightArrayIterator < end) {
            tempArray[tempArrayIndex++] = array[leftArrayIterator] <= array[rightArrayIterator]
                    ? array[leftArrayIterator++]
                    : array[rightArrayIterator++];
        }

        System.arraycopy(array, leftArrayIterator, array, start + tempArrayIndex, middle - leftArrayIterator);
        System.arraycopy(tempArray, 0, array, start, tempArrayIndex);
    }

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
