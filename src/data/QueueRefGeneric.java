package data;

public class QueueRefGeneric<T> implements QueueGeneric<T> {

    private NodeGeneric<T> front;
    private NodeGeneric<T> rear;

    public QueueRefGeneric() {
        front = null;
        rear = null;
    }

    @Override
    public boolean empty() {
        return front==null;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public T dequeue() {
        T elementToReturn = front.getData();
        if (empty()) {
            front = rear = null;
        } else {
            front = front.getNext();
        }
        return elementToReturn;
    }

    @Override
    public void enqueue(T item) {
        NodeGeneric<T> nodeToAdd = new NodeGeneric<>(item);
        if (rear!=null) {
            rear.setNext(nodeToAdd);
        } else {
            front = nodeToAdd;
        }
        rear = nodeToAdd;
    }
    
}
