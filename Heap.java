/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 * Nathan Moeller
 * CSCI 295 Data Structures
 * Homework 16-Generic Heap Part I
 * This program uses the heap interface and creates a generic heap that can be used for part II.
 * Dr. Lillis
 */

import java.util.Comparator;

public class Heap<E> implements HeapInterface<E> {
    private Comparator<E> comparator;
    E[] array;
    private int size;

    public Heap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.array = (E[]) new Object[INITIAL_SIZE];
        this.size = 0;
    }

    @Override
    public void add(E element) {
        if (size >= array.length) {
            doubleArraySize();
        }
        array[size] = element;
        size++;
        siftUp(size - 1);
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E remove() throws EmptyHeapException {
        if (isEmpty()) {
            throw new EmptyHeapException();
        }
        E item = array[0];
        size--;
        array[0] = array[size];
        array[size] = null;
        siftDown(0);
        return item;
    }
    
    @Override
    public int size() {
        return size;
    }

    private void doubleArraySize() {
        E[] newArray = (E[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (comparator.compare(array[index], array[parentIndex]) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        while (index * 2 + 1 < size) {
            int leftChildIndex = index * 2 + 1;
            int rightChildIndex = index * 2 + 2;
            int maxChildIndex = leftChildIndex;
            if (rightChildIndex < size && comparator.compare(array[rightChildIndex], array[leftChildIndex]) < 0) {
                maxChildIndex = rightChildIndex;
            }
            if (comparator.compare(array[maxChildIndex], array[index]) < 0) {
                swap(index, maxChildIndex);
                index = maxChildIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
     public Comparator<E> comparator() {
        return comparator;
    }
}
