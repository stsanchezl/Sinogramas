/**
 * Undocumentated methods are descripted in the interface this class implements.
 */
package data;
/**
 * This class is a "fixed" version of the ListArray seen in the data structures class 
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 2.0
 * @since 15/09/2020
 */

public class ListArrayGeneric<T extends Comparable<T>> implements ListGeneric<T> {
    private int position;
    private int count;
    private T[] listArray;
    T reference;

    public ListArrayGeneric(int size) {
        count=0;
        listArray = (T[]) new Comparable[size];
    }

    @Override
    public boolean empty() {
        return count<=0;
    }

    @Override
    public boolean full() {
        return count>= listArray.length;
    }

    @Override
    public boolean insert(T item) {
        boolean inserted = false;
        if (!full()) {
            if (!search(item)) {
                for (int j=count; j>position; j--) {
                    listArray[j] = listArray[j-1];
                }
                listArray[position] = item;
                count++;
                inserted = true;
            }
        } else {
            throw new RuntimeException("List is full");
        }
        return inserted;
    }

    @Override
    public boolean delete(T item) {
        boolean deleted = false;
        if (!empty()) {
            if (search(item)) {
                for (int j=position; j<count-1; j++) {
                    listArray[j] = listArray[j+1];
                }
                count--;
                deleted = true;
            }
        } else {
            throw new RuntimeException("List is empty");
        }
        return deleted;
    }

    @Override
    public boolean search(T item) {
        boolean found = false;
        boolean stop = false;
        position = 0;
        while (position!=count && !stop) {
            if(listArray[position].compareTo(item)>=0) {
                stop = true;
                if (listArray[position].compareTo(item) == 0) {
                    found = true;
                }
            }
            else {
                position++;
            }
        } 
        return found;
    }

    @Override
    public void output() {
        System.out.println("List: ");
        int j = 0;
        while (j != count) {
            System.out.println(listArray[j]+" ");
            j++;
        } 
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder("[");
        for (int i=0; i<this.listArray.length; i++) {
            toPrint.append(this.listArray[i] + ",");
        }
        if (this.listArray.length == 0) {
            toPrint.append("]");
        } else {
            toPrint.setCharAt(toPrint.lastIndexOf(","), ']');
        }
        return toPrint.toString();
    }

    /**
     * This method allows comparison
     * @param item to be compared with the reference
     * @return -1 if reference is less than the item, 0 if they're equal; 1 otherwise
     */
    public int compareTo(T item) {
        int result;
        if (reference.compareTo(item)>0) {
            result = 1;
        } else {
            if (reference.compareTo(item) <0) {
                result = -1;
            } else {
                result = 0;
            }
        }
        return result;
    }

    public int length() {
        return this.listArray.length;
    }
}
