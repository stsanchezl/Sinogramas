package data.sinogramas;

/**
 * This is an interface of a List.
 * The user can choose using references or arrays
 * This makes it easier for them to implement lists
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 2.0
 * @since 15/09/2020
 */

public interface ListGeneric<T> {

    /**
     * This method shows if the list is empty or not
     * @return true whenever the list is empty
     */
    public boolean empty();

    /**
     * This method shows if the list is full or not
     * @return true whenever the list is full
     */
    public boolean full();

    /**
     * This method adds an element to the list only if the element is not in the list yet
     * @param item: item to be added
     * @return true if the element was added
     */
    public boolean insert(T item);
   
    /**
     * This method removes a given element from the list, position does not matter
     * @param item: element to be removed
     * @return true if the element was removed
     */
    public boolean delete(T item);
    
    /**
     * This method search for a specific element in the list
     * @param item: item to look for
     * @return true if the given element was found
     */
    public boolean search(T item);
    

    public boolean sort();

    /**
     * This method shows a representation of a List
     * @return a string representing the list
     */
    public String toString();

    /**
     * This method prints in console the elements of the list
     */
    public void output(); 

    /**
     * This method shows the length of the list
     * @return an integer with the length of the list
     */
    public int length();
}
