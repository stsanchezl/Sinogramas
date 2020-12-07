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
        if (node == null)  {
            return (new NodeGeneric<T>(key)); 
        }
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
    
    public int size(NodeGeneric<Unihan> node) 
    { 
        if (node == null) 
            return 0; 
        else
            return(size(node.getPrev()) + 1 + size(node.getNext())); 
    } 
    
    
    public NodeGeneric<Unihan> find(NodeGeneric<Unihan> R, char c) {
        // Base Cases: root is null or key is present at root 
        if (R==null || R.getData().getCharacter() == c) 
            return R; 
  
        // Key is greater than root's key 
        if (R.getData().getCharacter() < c) 
            return find(R.getNext(), c); 
  
        // Key is smaller than root's key 
        return find(R.getPrev(), c); 
    }
    
    NodeGeneric<T> minValueNode(NodeGeneric<T> node)  
    {  
        NodeGeneric<T> current = node;  
  
        /* loop down to find the leftmost leaf */
        while (current.getPrev() != null)  
        current = current.getPrev();  
  
        return current;  
    }  
  
    public NodeGeneric<T> deleteNode(NodeGeneric<T> root, T key)  
    {  
        
        NodeGeneric<T> n = new NodeGeneric<>(key);
        // STEP 1: PERFORM STANDARD BST DELETE  
        if (root == null)  
            return root;  
  
        // If the key to be deleted is smaller than  
        // the root's key, then it lies in left subtree  
        if (root.getData().compareTo(n.getData()) > 0)  
            root.setPrev(deleteNode(root.getPrev(), key));  
  
        // If the key to be deleted is greater than the  
        // root's key, then it lies in right subtree  
        else if (root.getData().compareTo(n.getData()) < 0)  
            root.setNext(deleteNode(root.getNext(), key));   
  
        // if key is same as root's key, then this is the node  
        // to be deleted  
        else
        {  
  
            // node with only one child or no child  
            if ((root.getPrev() == null) || (root.getNext() == null))  
            {  
                NodeGeneric<T> temp = null;  
                if (temp == root.getPrev())  
                    temp = root.getNext();  
                else
                    temp = root.getPrev();  
  
                // No child case  
                if (temp == null)  
                {  
                    temp = root;  
                    root = null;  
                }  
                else // One child case  
                    root = temp; // Copy the contents of  
                                // the non-empty child  
            }  
            else
            {  
  
                // node with two children: Get the inorder  
                // successor (smallest in the right subtree)  
                NodeGeneric<T> temp = minValueNode(root.getNext());  
  
                // Copy the inorder successor's data to this node  
                root.setData(temp.getData());  
  
                // Delete the inorder successor  
                root.setNext(deleteNode(root.getNext(), temp.getData()));  
            }  
        }  
            // If the tree had only one node then return  
        if (root == null)  
            return root;  
  
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE  
        root.setHeight(max(height(root.getPrev()), height(root.getNext())) + 1);  
  
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether  
        // this node became unbalanced)  
        int balance = getBalance(root);  
  
        // If this node becomes unbalanced, then there are 4 cases  
        // Left Left Case  
        if (balance > 1 && getBalance(root.getPrev()) >= 0)  
            return rightRotate(root);  
  
        // Left Right Case  
        if (balance > 1 && getBalance(root.getPrev()) < 0)  
        {  
            root.setPrev(leftRotate(root.getPrev()));  
            return rightRotate(root);  
        }  
  
        // Right Right Case  
        if (balance < -1 && getBalance(root.getNext()) <= 0)  
            return leftRotate(root);  
  
        // Right Left Case  
        if (balance < -1 && getBalance(root.getNext()) > 0)  
        {  
            root.setNext(rightRotate(root.getNext()));  
            return leftRotate(root);  
        }  
  
        return root;  
    }  
    
    public void preOrder(NodeGeneric<T> node)  
    {  
        if (node != null)  
        {  
            System.out.print(node.getData() + " ");  
            preOrder(node.getPrev());  
            preOrder(node.getNext());  
        }  
    } 
    
    public void inOrder(NodeGeneric<Unihan> node)  
    {  
        if (node != null)  
        {  
            inOrder(node.getPrev()); 
            System.out.print(node.getData() + " ");  
            inOrder(node.getNext());  
        }  
    }  
    
    public void levelOrder(NodeGeneric<Unihan> root){
      //Write your code here
      QueueDynamicArrayGeneric<NodeGeneric> queue = new QueueDynamicArrayGeneric<>(); 
      if (root != null) {
        queue.enqueue(root);
        while (!queue.empty()) {
            NodeGeneric t = queue.dequeue();
            System.out.print(t.getData() + " ");
            if(t.getPrev() != null) {
                queue.enqueue(t.getPrev());
            }
            if(t.getNext() != null) {
                queue.enqueue(t.getNext());
            }
        }
      }
    }
    
}
