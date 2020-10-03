package data;

public interface StackGeneric<T> {

    /**
     * This method shows if the stack is empty or not
     * @return true whenever the stack is empty
     */
    public boolean empty();

    /**
     * This method shows if the stack is full or not
     * @return true whenever the stack is full
     */
    public boolean full();

    /**
     * This method removes the top element from the stack and returns it
     * @return the top element
     */
    public T pop();
    
    /**
     * This method adds an element to the top to the stack
     * @param item: item to be added
     */
    public void push(T item);

    public String toString();

    public int size();
}
