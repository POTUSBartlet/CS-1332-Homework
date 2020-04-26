import java.util.NoSuchElementException;

/**
 * Your implementation of a linked queue. It should NOT be circular.
 *
 * @author
 * @version 1.0
 * @userid
 * @GTID
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        T data = null;
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (!isEmpty()) {
            data = head.getData();
            head = head.getNext();
            size--;
            if (size == 0) {
                tail = null;
            }
        }
        return data;
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            LinkedNode<T> newNode = new LinkedNode<T>(data, null);
            if (head == null) {
                head = newNode;
                head.setNext(null);
                tail = head;
                size++;
            } else {
                tail.setNext(newNode);
                tail = newNode;
                tail.setNext(null);
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
     * Returns the head of this queue.
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

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     * <p>
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }
}