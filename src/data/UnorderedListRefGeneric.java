/**
 * Undocumentated methods are descripted in the interface this class implements.
 */
package data;

import logic.QuickSortSLL;

/**
 * This class pretends to be a mix between a stack and a list to build an unordered list using nodes
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 1.0
 * @since 16/10/2020
 */

public class UnorderedListRefGeneric<T extends Comparable<T>> implements ListGeneric<T>  {

    private NodeGeneric<T> head;
    private int counter; //Counter added so one can keep tracks of the length of the Queue.
    private NodeGeneric<T> top;
    private boolean sorted = false;

    /**
     * Constructor- Creating an empty list (a null Node)
     */
    public UnorderedListRefGeneric() {
        head = null;
    }

    @Override
    public boolean empty() {
        return head==null;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public boolean insert(T item) {
        NodeGeneric<T> newItem = new NodeGeneric<>(item);
        newItem.setNext(top);
        top = newItem;
        counter++;
        return true;
    }

    @Override
    public boolean delete(T item) {
        boolean deleted = false;
        if (!empty() && search(item)) {
            NodeGeneric<T> ptr = head;
            NodeGeneric<T> prev = null;
            while (ptr != null && ptr.getData().compareTo(item) != 0) {
                prev = ptr;
                ptr = ptr.getNext();
            }
            if (ptr == null || ptr.getData().compareTo(item) == 0) {
                deleted = true;
                if (prev == null)
                    head = ptr.getNext();
                else
                    prev.setNext(ptr.getNext());
                counter--;
            }
        }
        return deleted;
    }

    @Override
    public boolean search(T item) {
        boolean found = false;
        NodeGeneric<T> pointer = head;
        while (pointer!=null && !found) {
            if (pointer.getData().compareTo(item)==0) {
                found = true;
            }
            pointer = pointer.getNext();
        }
        return found;
    }

    @Override
    public void output() {
        System.out.println(this.head);
    }

    @Override
    public String toString() {
        return this.head.toString();
    }

    @Override
    public int length() {
        return this.counter;
    }
    
    /**
     * This method tells whether the stack is sorted or not
     * @return true whenever the stack is sorted
     */
    public boolean isSorted() {
        return this.sorted;
    }
    
    /**
     * This method sorts the unodered arra using a quicksort algorithm.
     */
    public void sort() {
        NodeGeneric<T> p = this.top; 
        while(p.getNext() != null) p = p.getNext(); 
        QuickSortSLL<T> qS = new QuickSortSLL<>();
        qS.sort(this.top, p);
        this.sorted = true;
    }
    
    
}
