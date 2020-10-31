package logic.sinogramas;

import data.sinogramas.NodeGeneric;

/** 
 * Java program for implementation of QuickSort 
 * @author Balasubramanian.N
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 2.0
 */ 
public class RefQuickSort<T extends Comparable<T>> { 

    /**
     * Constructor; no action to be performed
     */
    public RefQuickSort() {
        super();
    }

    /**
     * The main function that implements QuickSort(), it sorts from a given node to another one
     * @param start: Starting node
     * @param end: Ending node
     */
    public void sort(NodeGeneric<T> start, NodeGeneric<T> end) { 
        if(start == end) {
            return;
        } 
        NodeGeneric<T> pivot_prev = partition(start, end); // split list and partion recurse 
        sort(start, pivot_prev); 
        
        // if pivot is picked and moved to the start, 
        // that means start and pivot is same  
        // so pick from next of pivot 
        if( pivot_prev != null && pivot_prev == start) {
            sort(pivot_prev.getNext(), end); 
        } 
        // if pivot is in between of the list, 
        // start from next of pivot,  
        // since we have pivot_prev, so we move two nodes 
        else if(pivot_prev != null && pivot_prev.getNext() != null) {
            sort(pivot_prev.getNext().getNext(), end);
        } 
    } 

    /**
     * This method takes the last element as pivot, then places it at its correct position in the list
     * and the smaller elements are places to the left and the greatest to the right of the pivot
     * @param start: Starting node
     * @param end: Ending node
     * @return NodeGeneric: Indexing node (pivot)
     */
    public NodeGeneric<T> partition(NodeGeneric<T> start, NodeGeneric<T> end) { 
        if(start == end || start == null || end == null) {
            return start; 
        }
    
        NodeGeneric<T> pivot_prev = start; 
        NodeGeneric<T> curr = start;  
        T pivot = (T) end.getData();  
        
        // iterate till one before the end,  
        // no need to iterate till the end  
        // because end is pivot 
        while(start != end ) { 
            if(((T)start.getData()).compareTo(pivot)<0) {  
                pivot_prev = curr;  // keep tracks of last modified item 
                T temp = (T) curr.getData();  
                curr.setData(start.getData());  
                start.setData(temp);  
                curr = curr.getNext();  
            } 
            start = start.getNext();  
        } 
        // swap the position of curr i.e., next suitable index and pivot 
        T temp = (T) curr.getData();  
        curr.setData(pivot);  
        end.setData(temp);  
        // return one previous to current because current is now pointing to pivot 
        return pivot_prev; 
    } 
} 

/*This code is contributed by Rajat Mishra */
