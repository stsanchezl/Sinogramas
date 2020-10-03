package data;

public class StackRefGeneric<T> implements StackGeneric<T> {

    private NodeGeneric<T> top;
    private int counter = 0;

    public StackRefGeneric() {
        top = null;
    }

    @Override
    public boolean empty() {
        return top==null;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public T pop() {
        if(empty()) {
            throw new RuntimeException("Stack is empty");
        } else {
            T itemToReturn = top.getData();
            top=top.getNext();
            counter--;
            return itemToReturn;
        }
    }

    @Override
    public void push(T item) {
        NodeGeneric<T> newItem = new NodeGeneric<>(item);
        newItem.setNext(top);
        top = newItem;
        counter++;
    }

    @Override
    public String toString() {
        String toReturn = "[]";
        if (top != null) {
            toReturn = top.toString();
        }
        return toReturn;
    }

    public int size(){
        return counter;
    }
    
}
