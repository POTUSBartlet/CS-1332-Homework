import java.util.NoSuchElementException;

/**
 * Your implementation of a linked stack. It should NOT be circular.
 *
 * @author
 * @version 1.0
 * @userid
 * @GTID
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public T pop() {
        T data;
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        } else {
            data = head.getData();
            head = head.getNext();
            size--;
        }
        return data;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            LinkedNode<T> newNode = new LinkedNode<>(data);
            if (isEmpty()) {
                head = newNode;
                head.setNext(null);
                size++;
            } else {
                newNode.setNext(head);
                head = newNode;
                size++;
            }
        }
    }

    @Override
    public T peek() {
        if (head == null) {
            return null;
        } else {
            return head.getData();
        }
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the head of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     * <p>
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }
}