package data;

public class LinkedListGeneric<T extends Comparable<T>> implements ListGeneric<T>  {

    private NodeGeneric<T> head;

    /**
     * Constructor- Creating an empty list (a null Node)
     */
    public LinkedListGeneric() {
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
        boolean inserted = false;
        NodeGeneric<T> pointer = this.head;
        NodeGeneric<T> previous = null;
        while (pointer!=null && pointer.getData().compareTo(item)<0) {
            previous = pointer;
            pointer = pointer.getNext();
        }
        if (pointer==null || pointer.getData().compareTo(item)!=0) {
            inserted = true;
            NodeGeneric<T> newPointer = new NodeGeneric<>(item);
            newPointer.setNext(pointer);
            if (previous == null) {
                head = newPointer;
            } else {
                previous.setNext(newPointer);
            }
        }
        return inserted;
    }

    /**
     * TODO
     */
    @Override
    public boolean delete(T item) {
        return false;
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
    
}
