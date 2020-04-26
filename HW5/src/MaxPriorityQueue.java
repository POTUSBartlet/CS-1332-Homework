import java.util.NoSuchElementException;

/**
 * Your implementation of a max priority queue.
 *
 * @author
 * @version 1.0
 * @userid
 * @GTID
 */
public class MaxPriorityQueue<T extends Comparable<? super T>>
        implements PriorityQueueInterface<T> {

    private HeapInterface<T> backingHeap;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a priority queue.
     */
    public MaxPriorityQueue() {
        backingHeap = new MaxHeap<T>();
    }

    @Override
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            backingHeap.add(item);
        }
    }

    @Override
    public T dequeue() {
        T temp;
        if (backingHeap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        } else {
            temp = backingHeap.remove();
        }
        return temp;
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap.isEmpty();
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap.size();
    }

    @Override
    public void clear() {
        backingHeap = new MaxHeap<T>();
    }

    @Override
    public HeapInterface<T> getBackingHeap() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap;
    }

}
