/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.sinogramas;

import java.util.ArrayList;

/**
 *
 * @author dequi
 */
public class MaxHeap {
    private ArrayList<Unihan> Heap; 
    private int size; 
    private int maxsize; 
  
    // Constructor to initialize an 
    // empty max heap with given maximum 
    // capacity. 
    public MaxHeap() 
    { 
        this.maxsize = maxsize; 
        this.size = 0; 
        Heap = new ArrayList<>(); 
        // Crea un Unihan de mentiras con score igual a Double.MAX_VALUE
        Heap.add(0, new Unihan(Double.MAX_VALUE)); 
    } 
  
    // Returns position of parent 
    private int parent(int pos) 
    { 
        return pos / 2; 
    } 
  
    // Below two functions return left and 
    // right children. 
    private int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
    private int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 
  
    // Returns true of given node is leaf 
    private boolean isLeaf(int pos) 
    { 
        if (pos >= (size / 2) && pos <= size) { 
            return true; 
        } 
        return false; 
    } 
  
    private void swap(int fpos, int spos) 
    { 
        Unihan tmp; 
        tmp = Heap.get(fpos); 
        Heap.set(fpos, Heap.get(spos)); 
        Heap.set(spos, tmp); 
    } 
  
    // A recursive function to max heapify the given 
    // subtree. This function assumes that the left and 
    // right subtrees are already heapified, we only need 
    // to fix the root. 
    private void maxHeapify(int pos) 
    { 
        if (isLeaf(pos)) 
            return; 
  
        if (Heap.get(pos).getScore() < Heap.get(leftChild(pos)).getScore() || 
                Heap.get(pos).getScore() < Heap.get(rightChild(pos)).getScore()) { 
  
            if (Heap.get(leftChild(pos)).getScore() > Heap.get(rightChild(pos)).getScore()) { 
                swap(pos, leftChild(pos)); 
                maxHeapify(leftChild(pos)); 
            } 
            else { 
                swap(pos, rightChild(pos)); 
                maxHeapify(rightChild(pos)); 
            } 
        } 
    } 
  
    // Inserts a new element to max heap 
    public void insert(Unihan element) 
    { 
        Heap.add(++size, element); 
  
        // Traverse up and fix violated property 
        int current = size; 
        while (Heap.get(current).getScore() > Heap.get(parent(current)).getScore()) { 
            swap(current, parent(current)); 
            current = parent(current); 
        } 
    } 
  
  
    // Remove an element from max heap 
    public Unihan extractMax() 
    { 
        Unihan popped = Heap.get(1); 
        Heap.set(1, Heap.get(size--)); 
        maxHeapify(1); 
        return popped; 
    } 
}
