/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.sinogramas;

public class AVLTreeGeneric<T extends Comparable<T>> {
    
    NodeGeneric<T> root;
    
    public NodeGeneric<T> getRoot() {
        return this.root;
    }
    
    public void setRoot(NodeGeneric<T> r) {
        this.root = r;
    }
    
  
    // A utility function to get the height of the tree 
    int height(NodeGeneric<T> N) { 
        if (N == null) 
            return 0; 
  
        return N.getHeight(); 
    } 
  
    // A utility function to get maximum of two integers 
    int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 
  
    // A utility function to right rotate subtree rooted with y 
    // See the diagram given above. 
    NodeGeneric<T> rightRotate(NodeGeneric<T> y) { 
        NodeGeneric<T> x = y.getPrev(); 
        NodeGeneric<T> T2 = x.getNext(); 
  
        // Perform rotation 
        x.setNext(y); 
        y.setPrev(T2); 
  
        // Update heights 
        y.setHeight(max(height(y.getPrev()), height(y.getNext())) + 1); 
        x.setHeight(max(height(x.getPrev()), height(x.getNext())) + 1); 
  
        // Return new root 
        return x; 
    } 
  
    // A utility function to left rotate subtree rooted with x 
    // See the diagram given above. 
    NodeGeneric<T> leftRotate(NodeGeneric<T> x) { 
        NodeGeneric<T> y = x.getNext(); 
        NodeGeneric<T> T2 = y.getPrev(); 
  
        // Perform rotation 
        y.setPrev(x); 
        x.setNext(T2); 
  
        //  Update heights 
        x.setHeight(max(height(x.getPrev()), height(x.getNext())) + 1); 
        y.setHeight(max(height(y.getPrev()), height(y.getNext())) + 1); 
  
        // Return new root 
        return y; 
    } 
  
    // Get Balance factor of node N 
    int getBalance(NodeGeneric<T> N) { 
        if (N == null) 
            return 0; 
  
        return height(N.getPrev()) - height(N.getNext()); 
    } 
  
    public NodeGeneric<T> insert(NodeGeneric<T> node, T key) { 
  
        /* 1.  Perform the normal BST insertion */
        if (node == null) 
            return (new NodeGeneric<T>(key)); 
  
        if (node.getData().compareTo(key) > 0) 
            node.setPrev(insert(node.getPrev(), key)); 
        else if (node.getData().compareTo(key) < 0) 
            node.setNext(insert(node.getNext(), key)); 
        else // Duplicate keys not allowed 
            return node; 
  
        /* 2. Update height of this ancestor node */
        node.setHeight(1 + max(height(node.getPrev()), height(node.getNext()))); 
  
        /* 3. Get the balance factor of this ancestor 
              node to check whether this node became 
              unbalanced */
        int balance = getBalance(node); 
  
        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && node.getPrev().getData().compareTo(key) > 0) 
            return rightRotate(node); 
  
        // Right Right Case 
        if (balance < -1 && node.getNext().getData().compareTo(key) < 0) 
            return leftRotate(node); 
  
        // Left Right Case 
        if (balance > 1 && node.getPrev().getData().compareTo(key) < 0) { 
            node.setPrev(leftRotate(node.getPrev())); 
            return rightRotate(node); 
        } 
  
        // Right Left Case 
        if (balance < -1 && node.getNext().getData().compareTo(key) > 0) { 
            node.setNext(rightRotate(node.getNext())); 
            return leftRotate(node); 
        } 
  
        /* return the (unchanged) node pointer */
        return node; 
    }
    
    public NodeGeneric<Unihan> find(NodeGeneric<Unihan> R, char c) {
        if (R.getData().getCharacter() == c) {
            return R;
        } else if (R.getData().getCharacter() > c) {
            if (R.getPrev() != null)
                return find(R.getPrev(), c);
            return R;
        } else if (R.getData().getCharacter() < c) {
            if (R.getNext() != null)
                return find(R.getNext(), c);
            return R;
        }
        return R;
    }
}
