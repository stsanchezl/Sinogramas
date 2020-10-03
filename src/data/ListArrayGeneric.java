package data;

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
}
