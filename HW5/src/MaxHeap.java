import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a max heap.
 *
 * @author
 * @version 1.0
 * @userid
 * @GTID
 */
public class MaxHeap<T extends Comparable<? super T>>
        implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     * <p>
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     * <p>
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * <p>
     * The initial array before the Build Heap algorithm takes place should
     * contain the data in the same order as it appears in the ArrayList.
     * <p>
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {
        backingArray = (T[]) new Comparable[((data.size() * 2) + 1)];
        int i = 1;
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        for (T node : data) {
            if (node == null) {
                throw new IllegalArgumentException("Data cannot be null");
            } else {
                backingArray[i] = node;
                i++;
                size++;
            }
        }
        for (int j = backingArray.length / 2; j > 0; j--) {
            maxHeapify(backingArray, j);
        }
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            if (size == backingArray.length - 1) {
                growArray();

            }
            backingArray[size + 1] = item;
            size++;
            for (int j = backingArray.length / 2; j > 0; j--) {
                maxHeapify(backingArray, j);
            }
        }
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        } else {
            return removeHelper();
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

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Gets the left index of a left child node
     *
     * @param index the index being checked against
     * @return the index of the child
     */
    private int getLeftIndex(int index) {
        return 2 * index;
    }

    /**
     * Gets the index of a right child node
     *
     * @param index the index being checked against
     * @return the value of the index
     */
    private int getRightIndex(int index) {
        return (2 * index) + 1;
    }

    /**
     * Swaps a parent and chold
     *
     * @param childIndex  the index of the child
     * @param parentIndex the index of the parent
     */
    private void parentChildSwap(int childIndex, int parentIndex) {
        T temp = backingArray[parentIndex];
        backingArray[parentIndex] = backingArray[childIndex];
        backingArray[childIndex] = temp;
    }

    /**
     * Sorts the array into a max heap
     *
     * @param array the array to be sorted
     * @param index the index to be sorted from
     */
    private void maxHeapify(T[] array, int index) {
        int l = getLeftIndex(index);
        int r = getRightIndex(index);
        int largest;

        if (l <= size && backingArray[l].compareTo(backingArray[index]) > 0) {
            largest = l;
        } else {
            largest = index;
        }
        if (r <= size && backingArray[r].compareTo(backingArray[largest]) > 0) {
            largest = r;
        }
        if (largest != index) {
            parentChildSwap(largest, index);
            maxHeapify(backingArray, largest);
        }
    }

    /**
     * Helper method to grow array
     */
    private void growArray() {
        int i = 0;
        T[] temp = backingArray;
        backingArray = (T[]) new Comparable[(backingArray.length * 2)];
        for (T data : temp) {
            backingArray[i] = temp[i];
            i++;
        }
    }

    /**
     * Helper method to remove a node
     *
     * @return the data of the node removed
     */
    private T removeHelper() {
        T temp;
        if (size == 1) {
            temp = backingArray[1];
            clear();
            return temp;
        } else {
            for (int i = 1; i < size; i++) {
                if (backingArray[i] != null) {
                    temp = backingArray[i];
                    backingArray[i] = null;
                    size--;
                    return temp;
                }
            }
        }
        return null;
    }
}