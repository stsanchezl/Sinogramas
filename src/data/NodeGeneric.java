package data;

public class NodeGeneric<T> {
    private T data;
    private NodeGeneric<T> next;

    public NodeGeneric(T data) {
        this.data = data;
        next = null;
    }
    
    public NodeGeneric() {
        this(null);
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
