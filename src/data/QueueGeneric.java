package data;

/**
 * This is an interface of a Queue.
 * The user can choose using references or arrays
 * This makes it easier for them to implement queues
 * @author Stiven Leonardo Sánchez León
 * @version 2.0
 * @since 08/09/2020
 */

public interface QueueGeneric<T> {

    /**
     * This methods tells whether the queue is empty or not
     * @return true if the queue is empty
     */
    public boolean empty();

    /**
     * This methods tells whether the queue is full or not
     * @return true if the queue is full;
     */
    public boolean full();

    /**
     * This method takes the front element of the queue and takes it out and returns it
     * @return T item; element in the front of the queue;
     */
    public T dequeue();
    
    /**
     * This method adds a generic object at the end of the queue
     * @param item: object to be added
     */
    public void enqueue(T item);

    /**
     * This method represents the queue as a list so that the user may see it easily
     * @return a string representation of the objects from the queue
     */
    public String toString();

    /**
     * This method shows the length of the queue
     * @return an integer with the length of the queue
     */
    public int length();
}
