package data.sinogramas;

/**
 * This is an interface of a Stack.
 * The user can choose using references or arrays
 * This makes it easier for them to implement stacks
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 2.0
 * @since 03/09/2020
 */

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

    /**
     * This method shows a representation of a Stack
     * @return a string representing the stack
     */
    public String toString();

    /**
     * This method shows the length of the stack
     * @return an integer with the length of the stack
     */
    public int length();
}
