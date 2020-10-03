package data;

public interface ListGeneric<T> {
    public boolean empty();
    public boolean full();
    public boolean insert(T item);
    public boolean delete(T item);
    public boolean search(T item);
    public String toString();
    public void output(); 
}
