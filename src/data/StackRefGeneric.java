/**
 * Undocumentated methods are descripted in the interface this class implements.
 */
package data;

/**
 * This class is a "fixed" version of the Stack with Nodes seen in the data structures class 
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 2.0
 * @since 17/09/2020
 */

public class StackRefGeneric<T> implements StackGeneric<T> {

    private NodeGeneric<T> top;
    private int counter = 0;  //Counter added so one can keep tracks of the length of the Stack.

    /**
     * Constructor.
     * Initalizates null node, so at first the Stack is empty
     */
    public StackRefGeneric() {
        top = null;
    }

    @Override
    public boolean empty() {
        return top==null;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public T pop() {
        if(empty()) {
            throw new RuntimeException("Stack is empty");
        } else {
            T itemToReturn = top.getData();
            top=top.getNext();
            counter--;
            return itemToReturn;
        }
    }

    @Override
    public void push(T item) {
        NodeGeneric<T> newItem = new NodeGeneric<>(item);
        newItem.setNext(top);
        top = newItem;
        counter++;
    }

    @Override
    public String toString() {
        String toReturn = "[]";
        if (top != null) {
            toReturn = top.toString();
        }
        return toReturn;
    }

    @Override
    public int length(){
        return counter;
    }
    
}
