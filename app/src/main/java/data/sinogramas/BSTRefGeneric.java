/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author dequi
 */
public class BSTRefGeneric<T extends Comparable<T>> {
    private NodeGeneric<T> root;
    public BSTRefGeneric() {
        root = null;
    }
    public void insertBST(T data) {
        root = insert(data, root);
    }
    private NodeGeneric<T> insert(T data, NodeGeneric<T> p) {
        if(p == null)
            p = new NodeGeneric<>(data);
        else
            if(((T) p.getData()).compareTo(data) > 0)
                p.setPrev(insert(data, p.getPrev()));
            else
                if(((T) p.getData()).compareTo(data) < 0)
                    p.setNext(insert(data, p.getNext()));
                else
                    return p;
        return p;
    }
    public void removeBST(T data){
        root = remove(data, root);
    }
    private NodeGeneric<T> remove(T data, NodeGeneric<T> p){
        if(p != null)
            if(p.getData().compareTo(data) > 0)
                p.setPrev(remove(data, p.getPrev()));
            else
                if(p.getData().compareTo(data) < 0)
                    p.setNext(remove(data, p.getNext()));
                else
                    if(p.getPrev() == null && p.getNext() == null)
                        p = null;
                    else
                        if(p.getPrev() == null)
                            p = p.getNext();
                        else
                            if(p.getNext() == null)
                                p = p.getPrev();
                            else {
                                NodeGeneric<T> t = findMin(p.getNext());
                                p.setData(t.getData());
                                p.setNext(remove(p.getData(), p.getNext()));
                            }
        else
            System.out.println("Item not in tree and not removed");
        return p;
    }
    private NodeGeneric<T> findMin(NodeGeneric<T> p) {
        if(p != null)
            while(p.getPrev() != null)
                p = p.getPrev();
        return p;
    }

    public void traverseBST() {
        System.out.print("The tree is:");
        if(root != null)
            traverse(root);
        else
            System.out.print(" " + "Empty");
        System.out.println();
    }
    private void traverse(NodeGeneric<T> ptr) {
        if(ptr.getPrev() != null)
            traverse(ptr.getPrev());
        System.out.print(" " + ptr.getData());
        if(ptr.getNext() != null)
            traverse(ptr.getNext());
    }
    
    public boolean contains(NodeGeneric<T> p, T data) {
        if (data.compareTo(p.getData()) == 0)
            return true;
        else if (data.compareTo(p.getData()) > 0)
            contains(p.getNext(), data);
        else
            contains(p.getPrev(), data);
        return false;
    }

}
