/**
 * Undocumentated methods are descripted in the interface this class implements.
 */
package data.sinogramas;

/**
 * This class pretends to be a mix between a stack and a list to build an unordered list
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 1.1
 * @since 08/10/2020
 */

import logic.ArrayQuickSort;

public class UnorderedListArrayGeneric<T extends Comparable<T>> implements ListGeneric<T> {
    private int top;
    private T[] slarray;
    private boolean sorted;
    private int position;

    /**
     * Class constructor
     * @param size: int that shows the top of the stack
     */
    
    public UnorderedListArrayGeneric(int size) {
        this.top = 0;
        this.slarray = (T[]) new Comparable[size]; // newLine
        this.sorted = false;
    }

    @Override
    public boolean empty() {
        return top <= 0;
    }

    @Override
    public boolean full() {
        return top >= slarray.length;
    }


    @Override
    public boolean insert(T item) {
        if (full()) {
            throw new RuntimeException("List is full");
        }
        slarray[top] = item;
        top++;
        return true;
    }

    @Override
    public boolean delete(T item) {
        if (!this.sorted) this.sort();
        boolean deleted = false;
        if (!empty()) {
            if (search(item)) {
                for (int j=position; j<top-1; j++) {
                    slarray[j] = slarray[j+1];
                }
                top--;
                deleted = true;
            }
        } else {
            throw new RuntimeException("List is empty");
        }
        return deleted;
    }

    @Override
    public boolean search(T item) {
        if (!this.sorted) this.sort();
        boolean found = false;
        boolean stop = false;
        position = 0;
        while (position!=top && !stop) {
            if(slarray[position].compareTo(item)>=0) {
                stop = true;
                if (slarray[position].compareTo(item) == 0) {
                    found = true;
                }
            }
            else {
                position++;
            }
        } 
        return found;
    }

    @Override
    public void output() {
        System.out.println("List: ");
        int j = 0;
        while (j != top) {
            System.out.println(slarray[j]+" ");
            j++;
        } 
        System.out.println();
    }

    @Override
    public int length() {
        return this.top;
    }

    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder("[");
        for (int i=top-1; i>=0; i--) {
            toPrint.append(slarray[i] + ",");
        }
        if (empty()) {
            toPrint.append("]");
        } else {
            toPrint.setCharAt(toPrint.lastIndexOf(","), ']');
        }
        return toPrint.toString();   
    }

    /**
     * This method tells whether the stack is sorted or not
     * @return true whenever the stack is sorted
     */
    public boolean isSorted() {
        return this.sorted;
    }
    
    /**
     * This method sorts the unodered list using a quicksort algorithm.
     */
    @Override
    public boolean sort() {
        ArrayQuickSort<T> qS = new ArrayQuickSort<>();
        qS.sort(this.slarray, 0, this.top-1);
        this.sorted = true;
        return true;
    }
    
    public int getTop() {
        return top;
    }
}

