import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;


/**
 * Your implementation of a binary search tree.
 *
 * @author
 * @version 1.0
 * @userid
 * @GTID
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (data.contains(null)) {
            throw new IllegalArgumentException("Element cannot be null");
        } else {
            for (T node : data) {
                add(node);
            }
        }
    } // DONE

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else if (root == null) {
            root = new BSTNode<>(data);
            size++;
        } else {
            addHelper(data, root);
        }
    } //DONE

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (size == 0) {
            throw new NoSuchElementException("Tree is empty");
        } else {
            BSTNode<T> temp = new BSTNode<>(null);
            root = removeHelper(data, root, temp);
            size--;
            return temp.getData();
        }
    } // DONE

    @Override
    public T get(T data) { //TODO
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            return getHelper(data, root);
        }
    } // DONE

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            return containsHelper(data, root);
        }
    } // DONE

    @Override
    public List<T> preorder() {
        List<T> list = new ArrayList<>();
        return preOrderHelper(root, list);
    } // DONE

    @Override
    public List<T> postorder() {
        List<T> list = new ArrayList<>();
        return postOrderHelper(root, list);
    } // DONE

    @Override
    public List<T> inorder() {
        List<T> list = new ArrayList<>();
        return inOrderHelper(root, list);
    } // DONE

    @Override
    public List<T> levelorder() {
        List<T> list = new ArrayList<>();
        LinkedList<BSTNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> node = queue.removeFirst();
            if (node != null) {
                list.add(node.getData());
                queue.add(node.getLeft());
                queue.add(node.getRight());
            }
        }
        return list;
    }

    @Override
    public int distanceBetween(T data1, T data2) { //TODO
        return 1;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    } // DONE

    @Override
    public int height() {
        return heightHelper(root);
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Helper for the add method
     *
     * @param data      the data being checked
     * @param nodeCheck the node being checked
     */
    private void addHelper(T data, BSTNode<T> nodeCheck) {
        BSTNode<T> tempNode = nodeCheck;
        if (tempNode == null) {
            tempNode.setData(data);
            size++;
        } else if (data.compareTo(tempNode.getData()) > 0) {
            if (tempNode.getRight() == null) {
                tempNode.setRight(new BSTNode<>(data));
                size++;
            } else {
                tempNode = tempNode.getRight();
                addHelper(data, tempNode);
            }
        } else if (data.compareTo(tempNode.getData()) < 0) {
            if (tempNode.getLeft() == null) {
                tempNode.setLeft(new BSTNode<>(data));
                size++;
            } else {
                tempNode = tempNode.getLeft();
                addHelper(data, tempNode);
            }
        }
    } // DONE

    /**
     * Helper method for predecessor node
     *
     * @param nodeCheck   The node being checked against
     * @param storageNode temp node to hold values
     * @return the predecessor node
     */
    private BSTNode<T> getPredecessorNode(BSTNode<T> nodeCheck,
                                          BSTNode<T> storageNode) {
        if (nodeCheck.getRight() == null) {
            storageNode.setData(nodeCheck.getData());
            if (nodeCheck.getLeft() != null) {
                return nodeCheck.getLeft();
            } else {
                return null;
            }
        } else {
            storageNode.setRight(getPredecessorNode(
                    nodeCheck.getRight(), storageNode));
        }
        return nodeCheck;
    } // DONE


    /**
     * Remove method helper
     *
     * @param data        the data to be removed
     * @param nodeCheck   The node being checked against
     * @param storageNode The temporary storage node
     * @return the node to be removed
     */
    private BSTNode<T> removeHelper(T data, BSTNode<T> nodeCheck,
                                    BSTNode<T> storageNode) {
        storageNode.setData(nodeCheck.getData());

        if (data.compareTo(nodeCheck.getData()) > 0) {
            nodeCheck.setRight(removeHelper(data,
                    nodeCheck.getRight(), storageNode));
        } else if (data.compareTo(nodeCheck.getData()) < 0) {
            nodeCheck.setLeft(removeHelper(data,
                    nodeCheck.getLeft(), storageNode));
        } else if (data.compareTo(nodeCheck.getData()) == 0) {
            storageNode.setData(nodeCheck.getData());

            if (nodeCheck.getRight() != null
                    && nodeCheck.getLeft() == null) {
                return nodeCheck.getRight();
            } else if (nodeCheck.getRight() == null
                    && nodeCheck.getLeft() != null) {
                return nodeCheck.getLeft();
            } else if (nodeCheck.getLeft() == null
                    && nodeCheck.getRight() == null) {
                return null;
            } else {
                BSTNode<T> temp = new BSTNode<>(null);
                BSTNode<T> temp2 = nodeCheck.getLeft();
                temp2 = getPredecessorNode(temp2, temp);
                nodeCheck.setData(temp.getData());
                nodeCheck.setLeft(temp2);
            }
        }
        return nodeCheck;
    } // DONE

    /**
     * Helper method for get
     *
     * @param data      the data to be found
     * @param nodeCheck the node being checked against
     * @return the node found
     */
    private T getHelper(T data, BSTNode<T> nodeCheck) {
        if (nodeCheck == null) {
            throw new NoSuchElementException("Data not in tree");
        }
        if (data.compareTo(nodeCheck.getData()) > 0) {
            return getHelper(data, nodeCheck.getRight());
        } else if (data.compareTo(nodeCheck.getData()) < 0) {
            return getHelper(data, nodeCheck.getLeft());
        } else {
            return nodeCheck.getData();
        }
    } // DONE

    /**
     * helper method for finding a node
     *
     * @param data      the data to be found
     * @param nodeCheck the node being checked against
     * @return whether the node was found or not
     */
    private boolean containsHelper(T data, BSTNode<T> nodeCheck) {
        if (nodeCheck == null) {
            return false;
        } else if (data.compareTo(nodeCheck.getData()) > 0) {
            return containsHelper(data, nodeCheck.getRight());
        } else if (data.compareTo(nodeCheck.getData()) < 0) {
            return containsHelper(data, nodeCheck.getLeft());
        } else {
            return true;
        }
    } // DONE

    /**
     * helper method for preorder traverasal
     *
     * @param node the current node
     * @param list the list to add the nodes to
     * @return the list in pre order form
     */
    private List<T> preOrderHelper(BSTNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getData());
            preOrderHelper(node.getLeft(), list);
            preOrderHelper(node.getRight(), list);
        }
        return list;
    }

    /**
     * helper method for postorder traversal
     *
     * @param node the current node
     * @param list the list to add the node to
     * @return the list in postorder form
     */
    private List<T> postOrderHelper(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return list;
        } else {
            postOrderHelper(node.getLeft(), list);
            postOrderHelper(node.getRight(), list);
            list.add(node.getData());
            return list;
        }
    }

    /**
     * inorder helper method
     *
     * @param node current node
     * @param list the list
     * @return a list in inorder form
     */
    private List<T> inOrderHelper(BSTNode<T> node, List<T> list) {
        if (node != null) {
            inOrderHelper(node.getLeft(), list);
            list.add(node.getData());
            inOrderHelper(node.getRight(), list);
        }
        return list;
    }

    /**
     * helper method for calculating height
     *
     * @param nodeCheck the node to check
     * @return the height
     */
    private int heightHelper(BSTNode<T> nodeCheck) {
        if (nodeCheck == null) {
            return -1;
        } else {
            return Math.max(heightHelper(nodeCheck.getLeft()),
                    heightHelper(nodeCheck.getRight())) + 1;
        }
    }
}