/**
 * Undocumentated methods are descripted in the interface this class implements.
 */
package data;

/**
 * This class is a "fixed" version of the Queue using Nodes seen in the data structures class 
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 2.0
 * @since 21/09/2020
 */

public class QueueRefGeneric<T> implements QueueGeneric<T> {

    private NodeGeneric<T> front;
    private NodeGeneric<T> rear;
    private int counter; //Counter added so one can keep tracks of the length of the Queue.

    public QueueRefGeneric() {
        front = null;
        rear = null;
    }

    @Override
    public boolean empty() {
        return front==null;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public T dequeue() {
        T elementToReturn = front.getData();
        if (empty()) {
            front = rear = null;
        } else {
            front = front.getNext();
        }
        counter--;
        return elementToReturn;
    }

    @Override
    public void enqueue(T item) {
        NodeGeneric<T> nodeToAdd = new NodeGeneric<>(item);
        if (rear!=null) {
            rear.setNext(nodeToAdd);
        } else {
            front = nodeToAdd;
        }
        rear = nodeToAdd;
        counter++;
    }

    @Override
    public String toString() {
        String toReturn = "[]";
        if (front != null) {
            toReturn = front.toString();
        }
        return toReturn;
    }

    @Override
    public int length() {
        return counter;
    }
    
}
