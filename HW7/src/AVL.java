import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author
 * @userid
 * @GTID
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null || data.contains(null)) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            for (int i = 0; i < size; i++) {
                add((T) data);
            }
        }
    }


    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            AVLNode<T> node = root;
            if(node == null){
                node = new AVLNode<T>(data);
                size++;
            }
            else if(data.compareTo(node.getData()) > 0){
                //node.setRight(add());
            }
        }
    }

    private AVLNode<T> add(T data, AVLNode<T> node){
        return null;
    }

    @Override
    public T remove(T data) { // TODO
        AVLNode<T> temp = root;
        AVLNode<T> storage = null;
        if(root == null){
            throw new NoSuchElementException("Tree is empty");
        }
        if(data.compareTo(temp.getData()) < 0){
            temp.setLeft(temp.getLeft());
            remove(data);
        }
        if(data.compareTo(temp.getData()) > 0){
            temp.setRight(temp.getRight());
            remove(data);
        }
        else{
            storage = temp;
            if(temp.getLeft() == null
                    && temp.getRight()== null){
                return null;
            }
            else if(temp.getRight() != null && temp.getLeft() == null){
                return temp.getRight().getData();
            }
            else if(temp.getRight() == null && temp.getLeft() != null){
                return temp.getLeft().getData();
            }
            else{
                // FINISH THIS
            }
        }



        return null;
    }

    @Override
    public T get(T data) { // TODO
        return null;
    }

    @Override
    public boolean contains(T data) { // TODO
        return true;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public T getSecondLargest() { // TODO
        return null;
    }

    @Override
    public boolean equals(Object obj) { // TODO
        return true;
    }

    @Override
    public void clear() { // TODO

    }

    @Override
    public int height() {
        if(root == null){
            return -1;
        }
        else {
            return root.getHeight();
        }
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

//    private AVLNode<T> addHelper(T data, AVLNode<T> node){
//        if(node == null){
//            node = new AVLNode<>(data);
//            size++;
//        }
//        else if(data.compareTo(node.getData()) < 0){
//            node.setLeft(addHelper(data, node.getLeft()));
//        }
//        else if(data.compareTo(node.getData()) > 0){
//            node.setRight(addHelper(data, node.getRight()));
//        }
//        node.setBalanceFactor(balanceFactor(node));
//        return node;
//    }

    private AVLNode<T> rotateRight(T data, AVLNode<T> node) {// TODO
        return null;
    }

    private AVLNode<T> rotateLeft(T data, AVLNode<T> node) { // TODO
        return null;
    }

    private AVLNode<T> rotateRightLeft(T data, AVLNode<T> node){ // TODO
        return null;
    }

    private AVLNode<T> rotateLeftRight(T data, AVLNode<T> node){ // TODO
        return null;
    }

    private int balanceFactor(AVLNode<T> node) { // TODO
        int balanceFactor = 0;
        if(node != null){
            int leftHeight;
            int rightHeight;
            AVLNode<T> left = node.getLeft();
            AVLNode<T> right = node.getRight();

            if(left == null){ // Maybe throws NullPointer. Check back later
                leftHeight = -1;
            }
            else{
                leftHeight = left.getHeight();
            }
            if(right == null){
                rightHeight = -1;
            }
            else{
                rightHeight = right.getHeight();
            }
            balanceFactor = leftHeight - rightHeight;
        }
        return balanceFactor;
    }

    private void rebalanceTree(AVLNode<T> node){

    }

    private AVLNode<T> getPredecessor(){
        return null;
    }

}
