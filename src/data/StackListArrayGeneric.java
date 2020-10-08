/**
 * Undocumentated methods are descripted in the interface this class implements.
 */
package data;

/**
 * This class pretends to be a mix between a stack and a list to build an unordered list
 * Not sure if it's the right choice though
 * It only implements the stack push method for now, but it will also need a search and a delete implementation
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 2.0
 * @since 03/09/2020
 */

public class StackListArrayGeneric<T> implements StackGeneric<T>, ListGeneric<T> {
    private int top;
    private T[] slarray;
    private boolean ordered;

    /**
     * Class constructor
     * @param top: int that shows the top of the stack
     */
    public StackListArrayGeneric(int size) {
        this.top = 0;
        this.slarray = (T[]) new Object[size];
        this.ordered = false;
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
    public T pop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void push(T item) {
        if (full()) {
            throw new RuntimeException("Stack is full");
        }
        slarray[top] = item;
        top++;
    }
    
    public void sort() {
        QuickSort qs = new QuickSort();
        qs.sort(this.slarray, 0, slarray.length -1);
        ordered = true;
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

    public int length() {
        return this.slarray.length;
    }

    @Override
    public boolean insert(T item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(T item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean search(T item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void output() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

