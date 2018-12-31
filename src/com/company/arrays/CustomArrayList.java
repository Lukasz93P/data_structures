package com.company.arrays;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class CustomArrayList<T> implements List<T> {

    private T[] innerArray;

    private int capacity;

    private int size = 0;

    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    @SuppressWarnings(value = "unchecked")
    private CustomArrayList(int capacity) {
        this.capacity = capacity;
        innerArray = (T[]) new Object[capacity];
    }

    @SuppressWarnings(value = "unchecked")
    private CustomArrayList(Collection<? extends T> collection) {
        innerArray = (T[]) collection.toArray();
        size = innerArray.length;
    }

    public static <T> CustomArrayList<T> of(Collection<T> collection) {
        return new CustomArrayList<>(collection);
    }

    public static <T> CustomArrayList<T> get(Class<T> elementsClass) {
        return new CustomArrayList<>(DEFAULT_INITIAL_CAPACITY);
    }

    public static <T> CustomArrayList<T> get(Class<T> elementsClass, int capacity) {
        return new CustomArrayList<>(capacity);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    @Override
    public boolean contains(Object o) {
        return IntStream.range(0, size)
                .filter(e -> innerArray[e].equals(o))
                .findAny()
                .isPresent();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOfRange(innerArray, 0, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        Objects.requireNonNull(t);

        if (contains(t)) {
            return false;
        }
        if (size == capacity) {
            resizeInnerArray();
        }

        innerArray[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0) {
            shiftArrayDown(index, index + 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream()
                .filter(this::contains)
                .count() == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        List<T> nonDuplicates = filterNonDuplicates(c);
        if (!(nonDuplicates.size() > 0)) {
            return false;
        }
        addAndShiftUp(nonDuplicates);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        List<T> nonDuplicates = filterNonDuplicates(c);
        if (nonDuplicates.size() > 0) {
            addAndShiftUp(index, nonDuplicates);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return bulkRemove(getIntersection(c));
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return bulkRemove(diff(c));
    }

    @Override
    public void clear() {
        Arrays.fill(innerArray, null);
        size = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return innerArray[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        innerArray[index] = element;
        return element;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);
        addAndShiftUp(index, element);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T toRemove = get(index);
        shiftArrayDown(index, index + 1);
        return toRemove;
    }

    @Override
    public int indexOf(Object o) {
        return IntStream.range(0, size)
                .filter(integer -> innerArray[integer].equals(o))
                .findFirst().orElse(-1);
    }

    @Override
    public int lastIndexOf(Object o) {
        return IntStream.range(0, size)
                .boxed()
                .sorted((x, y) -> -Integer.compare(x, y))
                .filter(integer -> innerArray[integer].equals(o))
                .findAny().orElse(-1);
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private List<T> filterNonDuplicates(Collection<? extends T> toFilter) {
        return toFilter.stream()
                .filter((e) -> !contains(e))
                .collect(Collectors.toList());
    }

    private List<T> getIntersection(Collection<?> collection) {
        return Arrays.stream(Arrays.copyOfRange(innerArray, 0, size))
                .filter(collection::contains)
                .collect(Collectors.toList());
    }

    private List<T> diff(Collection<?> collection) {
        return Arrays.stream(Arrays.copyOfRange(innerArray, 0, size))
                .filter((e) -> !collection.contains(e))
                .collect(Collectors.toList());
    }

    private boolean bulkRemove(Collection<T> toRemove) {
        if (toRemove.size() > 0) {
            for (T element : toRemove
            ) {
                remove(element);
            }

            return true;
        }
        return false;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @SuppressWarnings("unchecked")
    private void resizeInnerArray(int additionalCapacity) {
        if (additionalCapacity > 0) {
            Object[] newInnerArray = new Object[innerArray.length + additionalCapacity];
            System.arraycopy(innerArray, 0, newInnerArray, 0, size);
            innerArray = (T[]) newInnerArray;
        }
    }

    @SuppressWarnings("unchecked")
    private void resizeInnerArray() {
        Object[] newInnerArray = new Object[innerArray.length + 10];
        System.arraycopy(innerArray, 0, newInnerArray, 0, innerArray.length);
        innerArray = (T[]) newInnerArray;
    }

    private void addAndShiftUp(Collection<T> newElements) {
        prepareForBulkAdd(newElements.size());

        for (T element : newElements
        ) {
            add(element);
        }
    }

    private void addAndShiftUp(int startingIndex, Collection<T> newElements) {
        int newElementsSize = newElements.size();
        resizeInnerArray(capacity - startingIndex - newElementsSize);
        System.arraycopy(innerArray, startingIndex, innerArray, startingIndex + newElementsSize, size - startingIndex);

        Iterator<T> newElementsIterator = newElements.iterator();
        for (; startingIndex < newElementsSize; startingIndex++) {
            innerArray[startingIndex] = newElementsIterator.next();
        }

        size += newElementsSize;
    }

    private void addAndShiftUp(int startingIndex, T newElement) {
        resizeInnerArray(capacity - startingIndex - 1);
        System.arraycopy(innerArray, startingIndex, innerArray, startingIndex + 1, size - startingIndex);
        innerArray[startingIndex] = newElement;
        size++;
    }

    private void prepareForBulkAdd(int newElementsQuantity) {
        int capacityDeficit = (capacity - size) - newElementsQuantity;
        if (capacityDeficit > 0) {
            resizeInnerArray(capacityDeficit);
        }
    }

    private void shiftArrayDown(int from, int to) {
        assert from < to;
        int rangeBetweenElementsToSwap = to - from;

        for (int i = from; i < to; i++) {
            innerArray[i] = innerArray[i + rangeBetweenElementsToSwap];
            innerArray[i + rangeBetweenElementsToSwap] = null;
        }
        for (; to < size; to++) {
            innerArray[to] = innerArray[to + 1];
        }

        size -= rangeBetweenElementsToSwap;
    }
}
