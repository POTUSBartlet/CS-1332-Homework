import java.util.NoSuchElementException;

/**
 * Your implementation of an array-backed queue.
 *
 * @author Joseph McCall
 * @version 1.0
 * @userid jmccall9
 * @GTID 902102539
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Dequeue from the front of the queue.
     * <p>
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you should
     * explicitly reset front to 0.
     * <p>
     * You should replace any spots that you dequeue from with null. Failure to
     * do so can result in a loss of points.
     * <p>
     * See the homework pdf for more information on implementation details.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        T temp;
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        } else if (size == 1) {
            temp = backingArray[front];
            backingArray[front] = null;
            front = 0;
            size--;
        } else {
            temp = backingArray[front];
            backingArray[front] = null;
            if (front == backingArray.length - 1) {
                front = 0;
                size--;
            } else {
                front += 1;
                size--;
            }
        }
        return temp;
    }

    /**
     * Add the given data to the queue.
     * <p>
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length. If a regrow is necessary,
     * you should copy elements to the front of the new array and reset
     * front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            if (size == 0) {
                backingArray[0] = data;
                size++;
            } else {
                if (size == backingArray.length) {
                    growArray();
                }
                if (backingArray[size] == null) {
                    backingArray[size] = data;
                } else {
                    backingArray[(front + size) % backingArray.length] = data;
                }
                size++;
            }
        }
    }


    @Override
    public T peek() {
        T temp = null;
        if (isEmpty()) {
            return null;
        } else {
            temp = backingArray[front];
        }
        return temp;
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
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     * <p>
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Grows the backing array by 2x
     */
    private void growArray() {
        T[] temp = backingArray;
        backingArray = (T[]) new Object[backingArray.length * 2];

        for (int i = 0; i < temp.length; i++) {
            backingArray[i] = temp[front];
            if (front == temp.length - 1) {
                front = 0;
            } else {
                front++;
            }
        }
        front = 0;
    }
}