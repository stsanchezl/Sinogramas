/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import logic.HeapSort;

/**
 *
 * @author dequi
 */
public class HeapArray<T extends Comparable<T>> {
    private T[] array;
    private int size;
    public HeapArray(int cap) {
        this.array = (T[]) new Comparable[cap];
        size = 0;
    }
    public void insertItem(T x) {
        array[size] = x;
        moveUp();
        size++;
    }
    private void moveUp() {
        int child = size;
        int parent = (child-1)/2;
        T temp = array[child];
        while(child > 0 && array[parent].compareTo(temp) > 0) {
            array[child] = array[parent];
            child = parent;
            parent = (child-1)/2;
        }
        array[child]=temp;
    }
    public T removeMin() {
        T min=array[0];
        array[0]=array[--size];
        moveDown();
        return min;
    }
    private void moveDown() {
        boolean flag = false;
        T smallest = null;
        int parent = 0;
        int child = 2*parent+1;
        T temp = array[parent];
        while(child < size && !flag) {
            smallest = array[child];
            if(child+1 < size && array[child+1].compareTo(array[child]) < 0)
                smallest = array[++child];
            if(smallest.compareTo(temp) < 0) {
                array[parent] = smallest;
                parent = child;
            }
            else
                flag = true;
            child = 2*parent+1;
        }
        array[parent] = temp;
    }
    
    public void sort() {
        HeapSort<T> hS = new HeapSort<>();
        hS.heapSort(array, size);
    }
}
