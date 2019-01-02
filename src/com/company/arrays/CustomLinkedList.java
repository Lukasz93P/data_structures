package com.company.arrays;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomLinkedList<T> implements List<T> {
    private Node<T> headNode;

    private int size = 0;

    private CustomLinkedList() {
    }

    public static <T> CustomLinkedList<T> create() {
        return new CustomLinkedList<>();
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
        if (headNode == null) {
            return false;
        }
        Node<T> node = headNode;
        for (; node.next != null; node = node.next) {
            if (node.data.equals(o)) {
                return true;
            }
        }

        return node.data.equals(o);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        if (headNode == null) {
            return array;
        }
        int arrayIndex = 0;
        Node<T> node = headNode;
        for (; node.next != null; node = node.next) {
            array[arrayIndex++] = node.data;
        }
        array[arrayIndex] = node.data;
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if (headNode == null) {
            headNode = new Node<>(t);
            size++;
            return true;
        }

        Node<T> lastNode = headNode;
        for (; lastNode.next != null; lastNode = lastNode.next) {
            if (lastNode.data.equals(t)) {
                throw new IllegalArgumentException("Duplicate element");
            }
        }
        Node<T> newNode = new Node<>(t);
        newNode.setPrevious(lastNode);
        lastNode.setNext(newNode);
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (headNode == null) {
            return false;
        }
        Node<T> node = headNode;
        for (; node.getNext() != null; node = node.getNext()) {
            if (node.getData().equals(o)) {
                removeNode(node);
                size--;
                return true;
            }
        }
        if (node.getData().equals(o)) {
            removeNode(node);
            size--;
            return true;
        }

        return false;
    }

    private void removeNode(Node<T> node) {
        Node<T> previous = node.previous;
        Node<T> next = node.next;

        if (next != null) {
            if (previous != null) {
                next.setPrevious(previous);
                previous.setNext(next);
            } else {
                next.setPrevious(null);
                headNode = next;
            }
        } else {
            if (previous != null) {
                previous.setNext(null);
            } else {
                headNode = null;
            }
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
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

    private static class Node<T> {
        private Node<T> previous;

        private Node<T> next;

        private T data;

        public Node(T data) {
            this.data = data;
        }

        public Node(Node<T> previous, Node<T> next, T data) {
            this.previous = previous;
            this.next = next;
            this.data = data;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
