import java.util.ArrayList;
import java.util.Random;
import java.util.Comparator;
import java.util.List;


/**
 * Your implementation of various sorting algorithms.
 *
 * @author
 * @version 1.0
 * @userid
 * @GTID
 */
public class Sorting {

    /**
     * Implement bubble sort.
     * <p>
     * It should be:
     * in-place
     * stable
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n)
     * <p>
     * You may assume that the array doesn't contain any null elements.
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator cannot be null");
        } else {
            int length = arr.length;
            int i = 0;
            boolean swapped = true;
            while (i < length - 1 && swapped) {
                swapped = false;
                for (int j = 0; j < length - i - 1; j++) {
                    if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                        swap(arr, j, j + 1);
                        swapped = true;
                    }
                }
                i++;
            }
        }
    } // DONE

    /**
     * Implement insertion sort.
     * <p>
     * It should be:
     * in-place
     * stable
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n)
     * <p>
     * You may assume that the array doesn't contain any null elements.
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        } else {
            int length = arr.length;
            for (int i = 1; i < length; i++) {
                T x = arr[i];
                int j = i - 1;
                while (j >= 0 && comparator.compare(arr[j], x) > 0) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }
                arr[j + 1] = x;
            }
        }
    } // DONE

    /**
     * Implement selection sort.
     * <p>
     * It should be:
     * in-place
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n^2)
     * <p>
     * <p>
     * You may assume that the array doesn't contain any null elements.
     * <p>
     * Note that there may be duplicates in the array.
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        } else {

            int length = arr.length;
            for (int i = 0; i < length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < length; j++) {
                    if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                        minIndex = j;
                    }
                }
                swap(arr, minIndex, i);
            }
        }
    }

    /**
     * Implement quick sort.
     * <p>
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     * <p>
     * int pivotIndex = rand.nextInt(b - a) + a;
     * <p>
     * It should be:
     * in-place
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n log n)
     * <p>
     * You may assume that the array doesn't contain any null elements.
     * <p>
     * Note that there may be duplicates in the array.
     * <p>
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws IllegalArgumentException if the array or comparator or rand is
     *                                  null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        if (rand == null) {
            throw new IllegalArgumentException("random cannot be null ");
        } else {

            quickSortHelper(arr, 0, arr.length - 1, rand, comparator);

        }


    }

    /**
     * Helper method for quick sort
     *
     * @param array      Array being sorted
     * @param left       left side
     * @param right      right side
     * @param rand       random number
     * @param comparator comparison checker
     * @param <T>        generic type
     */

    private static <T> void quickSortHelper(T[] array, int left, int right,
                                            Random rand,
                                            Comparator<T> comparator) {

        if (left >= right) {
            return;
        } else {
            int leftIndex = left + 1;
            int rightIndex = right;
            int pivotIndex = rand.nextInt(right - left) + left;
            T pivot = array[pivotIndex];
            swap(array, pivotIndex, left);

            while (leftIndex <= rightIndex) {
                while (leftIndex <= rightIndex
                        && comparator.compare(array[leftIndex], pivot) <= 0) {
                    leftIndex++;
                }
                while (leftIndex <= rightIndex
                        && comparator.compare(array[rightIndex], pivot) >= 0) {
                    rightIndex--;
                }
                if (leftIndex <= rightIndex) {
                    swap(array, leftIndex, rightIndex);
                    leftIndex++;
                    rightIndex--;
                }
            }
            swap(array, rightIndex, left);
            quickSortHelper(array, left, rightIndex - 1, rand, comparator);
            quickSortHelper(array, rightIndex + 1, right, rand, comparator);
        }
    }

    /**
     * Implement merge sort.
     * <p>
     * It should be:
     * stable
     * <p>
     * Have a worst case running time of:
     * O(n log n)
     * <p>
     * And a best case running time of:
     * O(n log n)
     * <p>
     * You may assume that the array doesn't contain any null elements.
     * <p>
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     * <p>
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws IllegalArgumentException if the array or comparator is null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        } else {
            if (arr.length > 1) {

                int length = arr.length;
                int midIndex = length / 2;

                T[] leftArray = (T[]) new Object[length / 2];
                T[] rightArray = (T[]) new Object[arr.length - (length / 2)];
                for (int i = 0; i < length / 2; i++) {
                    leftArray[i] = arr[i];
                }

                for (int i = length / 2; i < arr.length; i++) {
                    rightArray[i - (length / 2)] = arr[i];
                }

                mergeSort(leftArray, comparator);
                mergeSort(rightArray, comparator);

                int leftIndex = 0;
                int rightIndex = 0;
                int currentIndex = 0;

                while (leftIndex < midIndex && rightIndex < length - midIndex) {
                    if (comparator.compare(leftArray[leftIndex],
                            rightArray[rightIndex]) <= 0) {
                        arr[currentIndex] = leftArray[leftIndex];
                        leftIndex++;
                    } else {
                        arr[currentIndex] = rightArray[rightIndex];
                        rightIndex++;
                    }
                    currentIndex++;
                }
                while (leftIndex < midIndex) {
                    arr[currentIndex] = leftArray[leftIndex];
                    leftIndex++;
                    currentIndex++;
                }
                while (rightIndex < length - midIndex) {
                    arr[currentIndex] = rightArray[rightIndex];
                    rightIndex++;
                    currentIndex++;
                }

            }
        }
    } // DONE

    /**
     * Implement LSD (least significant digit) radix sort.
     * <p>
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     * <p>
     * It should be:
     * stable
     * <p>
     * Have a worst case running time of:
     * O(kn)
     * <p>
     * And a best case running time of:
     * O(kn)
     * <p>
     * Refer to the PDF for more information on LSD Radix Sort.
     * <p>
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @param arr the array to be sorted
     * @throws IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) { // TODO
        if (arr == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        if (arr.length == 0) {
            return;
        }

        int div = 1;
        int itr = getIteration(arr);
        for (int i = 0; i < itr; i++) {
            ArrayList<Integer>[] bucket = new ArrayList[19];

            for (int item : arr) {
                int digit = (item / div) % 10;
                if (bucket[digit + 9] == null) {
                    bucket[digit + 9] = new ArrayList<>();
                }
                bucket[digit + 9].add(item);
            }

            int index = 0;
            for (ArrayList<Integer> list : bucket) {
                if (list != null) {
                    for (int j = 0; j < list.size(); j++) {
                        arr[index] = list.get(j);
                        index++;
                    }
                }
            }
            div *= 10;
        }

    }

    private static int getIteration(int[] arr) {
        int min = arr[0];
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int digits = 0;
        while (!(min == 0 && max == 0)) {
            min /= 10;
            max /= 10;
            digits++;
        }
        return digits;
    }

    /**
     * Method for swapping entries in an array
     *
     * @param array    The array being passed in
     * @param indexOne The first index to swap
     * @param indexTwo Second index to swap
     * @param <T>      Generic type
     */
    private static <T> void swap(T[] array, int indexOne, int indexTwo) {
        T temp = array[indexOne];
        array[indexOne] = array[indexTwo];
        array[indexTwo] = temp;
    }


}
