/**
 * Undocumentated methods are descripted in the interface this class implements.
 */
package data;

/**
 * This class is a "fixed" version of the StackArray seen in the data structures class 
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 2.0
 * @since 03/09/2020
 */

public class StackDynamicArrayGeneric<T> implements StackGeneric<T> {
    private int top;
    private T[] stackArray;

    /**
     * Class constructor
     * @param top: int that shows the top of the stack
     */
    public StackDynamicArrayGeneric() {
        this.top = 0;
        this.stackArray = (T[]) new Object[16];
    }

    @Override
    public boolean empty() {
        return top <= 0;
    }

    @Override
    public boolean full() {
        return top >= stackArray.length;
    }

    @Override
    public T pop() {
        if (empty()) {
            throw new RuntimeException("Stack is empty");
        }
        top--;
        return stackArray[top];
    }

    @Override
    public void push(T item) {
        if (this.top == stackArray.length) {
            T[] newArray = (T[]) new Comparable[2 * stackArray.length];
            for (int i = 0; i < this.top; i++) {
                newArray[i] = stackArray[i];
            }
            this.stackArray = newArray;
        }
        stackArray[top] = item;
        top++;
    }

    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder("[");
        for (int i=top-1; i>=0; i--) {
            toPrint.append(stackArray[i] + ",");
        }
        if (empty()) {
            toPrint.append("]");
        } else {
            toPrint.setCharAt(toPrint.lastIndexOf(","), ']');
        }
        return toPrint.toString();   
    }

    public int length() {
        return this.top;
    }
}

