/**
 * Undocumentated methods are getters and setters
 */
package data.sinogramas;

/**
 * This class is a "fixed" version of the Node class seen in the data structures class
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León
 * @version 2.0
 * @since 15/09/2020
 */

public class NodeGeneric<T> {
    private T data;
    private NodeGeneric<T> next; // right
    private NodeGeneric<T> prev; // left

    /**
     * Constructor of a Node: stores a value in memory and sets the next node to null
     * @param data: key to store
     */
    public NodeGeneric(T data) {
        this.data = data;
        next = null;
    }

    /**
     * Constructor, calls the other constructor with the data being null;
     */
    public NodeGeneric() {
        this(null);
    }

    NodeGeneric(int num) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeGeneric<T> getNext() {
        return this.next;
    }

    public void setNext(NodeGeneric<T> next) {
        this.next = next;
    }

    public NodeGeneric<T> getPrev() {
        return this.prev;
    }

    public void setPrev(NodeGeneric<T> prev) {
        this.prev = next;
    }

    /**
     * This method gives a visual representation of a node, if next attribute is not empty, the nodes looks like a list
     * @return a string with the data of a node within square brackes
     */
    public String toString() {
        StringBuilder toPrint = new StringBuilder("[");
        NodeGeneric<T> ref = this;
        while(ref!=null) {
            if(ref.getData()!=null) {
                toPrint.append("["+ref.getData()+"]");
            }
            ref = ref.getNext();
        }
        toPrint.append("]");
        return toPrint.toString();
    }


}