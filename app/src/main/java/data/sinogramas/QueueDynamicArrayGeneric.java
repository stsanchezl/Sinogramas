/**
 * Undocumentated methods are descripted in the interface this class implements.
 */
package data.sinogramas;

/**
 * This class is a "fixed" version of the QueueArray seen in the data structures class 
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 2.0
 * @since 08/09/2020
 */

public class QueueDynamicArrayGeneric<T> implements QueueGeneric<T> {
    private int front;
    private int rear;
    private int count;  //This keeps tracks of the elements of the array
    private int size;   //This is the fixed size of the array
    private T[] queueArray;

    /**
     * Class constructor
     */
    public QueueDynamicArrayGeneric() {
        front = rear = count =0;
        queueArray = (T[]) new Object[16];
        this.size = queueArray.length;
    }
    
    @Override
    public boolean empty() {
        return count <=0;
    }

    @Override
    public boolean full() {
        return count>=this.size;
    }

    @Override
    public T dequeue() {
        T item = null;
        if(this.empty()) {
            throw new RuntimeException("Queue is empty");
        } else {
            item = queueArray[front];
            front = (front+1)% this.size;
            count--;
        }
        return item;
    }

    @Override
    public void enqueue(T item) {
        if (this.count == this.size) {
            T[] newArray = (T[]) new Comparable[2 * this.size];
            for (int i = 0; i < this.count; i++) {
                newArray[i] = queueArray[i];
            }
            this.queueArray = newArray;
            this.size = queueArray.length;
        }
        queueArray[rear] = item;
        rear = (rear+1)%this.size;
        count++; 
    }

    /**
     * This method give the position of the counter
     * @return count: counter; 
     */
    public int getCount() {
        return count;
    }
 
    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder("[");
        int iterator = front; 
        for (int i=0; i<count; i++) {
            toPrint.append(queueArray[iterator] + ",");
            iterator++;
            if (iterator%this.length()==0) {
                iterator=0;
            }
        }
        if (toPrint.length()>1) {
            toPrint.setCharAt(toPrint.lastIndexOf(","), ']');
        } else {
            toPrint.append(']');
        }
        
        return toPrint.toString();
    }

    @Override
    public int length() {
        return this.count;
    }

    public T getItem(int position) {
        return this.queueArray[position];
    }
}
