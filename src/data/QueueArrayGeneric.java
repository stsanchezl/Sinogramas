/**
 * Undocumentated methods are descripted in the interface this class implements.
 */
package data;

/**
 * This class is a "fixed" version of the QueueArray seen in the data structures class 
 * @author Stiven Leonardo Sánchez León
 * @version 2.0
 * @since 08/09/2020
 */

public class QueueArrayGeneric<T> implements QueueGeneric<T> {
    private int front;
    private int rear;
    private int count;
    private T[] queueArray;

    /**
     * Class constructor
     * @param size, size of the queue
     */
    public QueueArrayGeneric(int size) {
        front = rear = count =0;
        queueArray = (T[]) new Object[size];
    }
    
    @Override
    public boolean empty() {
        return count <=0;
    }

    @Override
    public boolean full() {
        return count>=this.length();
    }

    @Override
    public T dequeue() {
        T item = null;
        if(this.empty()) {
            throw new RuntimeException("Stack is empty");
        } else {
            item = queueArray[front];
            front = (front+1)% this.length();
            count--;
        }
        return item;
    }

    @Override
    public void enqueue(T item) {
        if (this.full()) {
            throw new RuntimeException("Stack is full");
        } else {
            queueArray[rear] = item;
            rear = (rear+1)%this.length();
            count++; 
        }
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
        return this.queueArray.length;
    }
}
