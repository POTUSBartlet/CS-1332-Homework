
/**
 * Your implementation of an ArrayList.
 *
 * @author
 * @version 1
 * @userid
 * @GTID
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     * <p>
     * You may add statements to this method.
     */
    public ArrayList() {
        size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index >= backingArray.length || index < 0
                || index > size) {
            throw new IndexOutOfBoundsException(
                    "Chosen index is out of bounds");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else {
            if (size == backingArray.length) {
                T[] temp = backingArray;
                backingArray = (T[]) new Object[backingArray.length * 2];
                for (int i = size; i > index; i--) {
                    backingArray[i] = backingArray[i - 1];
                }
                backingArray[index] = data;
                size++;
            } else if (backingArray[index] == null) {
                backingArray[index] = data;
                size++;
            } else {
                if (index == 0) {
                    addToFront(data);
                } else {
                    for (int i = size; i > index; i--) {
                        backingArray[i] = backingArray[i - 1];
                    }
                    backingArray[index] = data;
                    size++;
                }
            }
        }
    }


    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "Cannot add null data to data structure");
        } else {

            if (backingArray[0] == null) {
                backingArray[0] = data;
                size++;
            } else {
                if (size == backingArray.length) {
                    for (int i = size; i > 0; i++) {
                        backingArray[i] = backingArray[i - 1];
                    }
                } else {
                    if (backingArray[0] != null) {
                        for (int i = size; i > 0; i--) {
                            backingArray[i] = backingArray[i - 1];
                        }
                        backingArray[0] = data;
                        size++;
                    }
                }
            }
        }
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "Cannot add null data to data structure");
        } else {
            if (size == backingArray.length) {
                T[] temp = backingArray;
                backingArray = (T[]) new Object[backingArray.length * 2];
                for (int i = 0; i < size; i++) {
                    backingArray[i] = temp[i];
                }
                backingArray[size] = data;
                size++;
            } else {
                if (backingArray[size] == null) {
                    backingArray[size] = data;
                    size++;
                }
            }
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Array index out of bounds");
        } else {
            T y = null;
            if (backingArray[index] != null) {
                y = backingArray[index];
                backingArray[index] = null;
                for (int i = index; i < size; i++) {
                    backingArray[i] = backingArray[i + 1];
                }
                size--;
            }
            return y;
        }
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        } else {
            T x = backingArray[0];
            for (int i = 0; i < size; i++) {
                backingArray[i] = backingArray[i + 1];
            }
            size--;
            return x;
        }
    }

    @Override
    public T removeFromBack() {
        T x = null;
        if (isEmpty()) {
            return null;
        } else if (backingArray[size - 1] != null) {
            x = backingArray[size - 1];
            backingArray[size - 1] = null;
            size--;
        }
        return x;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        } else {
            return backingArray[index];
        }
    }

    @Override
    public boolean isEmpty() {
        if (backingArray[0] == null) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }

    /**
     * Helper method to grow array
     */
    private void growArray() {
        T[] temp = backingArray;
        backingArray = (T[]) new Object[backingArray.length * 2];
        for (int i = 0; i < temp.length; i++) {
            backingArray[i] = temp[i];
        }
    }

    public static void main(String[] args) {

        System.out.println("Before recursion");
        recurse(3, "Go");
        System.out.println("After recursion");
    }

    public static void recurse(int x, String y) {
        if (x == 0) {
            System.out.println("Gatech");
            return;
        }

        if (y.equals("Jackets")) {
            System.out.println("THWg");
            recurse(x - 1, "Go");
            return;
        }
        recurse(x - 1, "Jackets");
        System.out.println("GT");
    }
}