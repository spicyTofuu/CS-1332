import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.PriorityQueue;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Jiaxuan Chen
 * @version 1.0
 * @userid jchen813
 * @GTID 903425077
 *
 * Collaborators: N/A
 *
 * Resources: N/A
 */
public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new java.lang.IllegalArgumentException("Array or comparator is null");
        }
        for (int i = 0; i < arr.length; i++) {
            int j = i - 1;
            T temp = arr[i];
            while (j >= 0 && comparator.compare(temp, arr[j]) < 0) {
                arr[j + 1] = arr[j];
                arr[j] = temp;
                temp = arr[j];
                j--;
            }
        }


    }

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for bubble sort. You
     * MUST implement bubble sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new java.lang.IllegalArgumentException("Input array or comparator is null");
        }
        int stopIndex = arr.length - 1;
        while (stopIndex > 0) {
            int lastSwapped = 0;
            for (int i = 0; i < stopIndex; i++) {

                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = temp;
                    lastSwapped = i;
                }
            }
            stopIndex = lastSwapped;

        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new java.lang.IllegalArgumentException("Input array or comparator is null");
        }
        if (arr.length > 1) {
            T[] leftArray = (T[]) new Object[arr.length / 2];
            T[] rightArray = (T[]) new Object[arr.length - arr.length / 2];
            for (int i = 0; i < leftArray.length; i++) {
                leftArray[i] = arr[i];
            }
            for (int i = 0; i < rightArray.length; i++) {
                rightArray[i] = arr[i + leftArray.length];
            }
            mergeSort(leftArray, comparator);
            mergeSort(rightArray, comparator);
            int i = 0;
            int j = 0;
            while (i < leftArray.length && j < rightArray.length) {
                if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                    arr[i + j] = leftArray[i];
                    i++;
                } else {
                    arr[i + j] = rightArray[j];
                    j++;
                }
            }
            while (i < leftArray.length) {
                arr[i + j] = leftArray[i];
                i++;
            }
            while (j < rightArray.length) {
                arr[i + j] = rightArray[j];
                j++;
            }
        }

    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new java.lang.IllegalArgumentException("The input array is null");
        }
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<>();
        }
        int maxNumber = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxNumber) {
                maxNumber = arr[i];
            }
        }
        int maxDigit = 1;
        while (maxNumber >= 10) {
            maxNumber /= 10;
            maxDigit++;
        }
        int iterations = 1;
        for (int i = 0; i < maxDigit; i++) {
            for (int j = 0; j < arr.length; j++) {

                int bucket = arr[j] / iterations;
                bucket = bucket % 10;
                buckets[bucket + 9].add(arr[j]);
            }
            int idx = 0;
            for (LinkedList<Integer> bkt : buckets) {
                for (int item : bkt) {
                    arr[idx++] = item;
                }
                bkt.clear();
            }
            iterations *= 10;
        }
    }

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The input data is null");
        }
        int length = data.size();
        PriorityQueue<Integer> heapSort = new PriorityQueue<>(data);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = heapSort.poll();
        }
        return arr;
    }

    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     * @throws java.lang.IllegalArgumentException if the array or comparator
     *                                            or rand is null or k is not
     *                                            in the range of 1 to arr
     *                                            .length
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                  Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new java.lang.IllegalArgumentException("The input array or comparator or rand is null");
        }
        if (k > arr.length || k < 1) {
            throw new java.lang.IllegalArgumentException("k is not in the range of 1 to arr.length");
        }
        int end = arr.length - 1;
        int start = 0;
        T temp = kthHelper(k, arr, start, end, comparator, rand);
        return temp;


    }

    /**
     *
     * @param k the index to retrieve data
     * @param arr the array to that contains targeted data
     * @param start the starting index to perform quick select
     * @param end the ending index to perform quick select
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param <T> data type to sort
     * @return kth smallest value
     */
    private static <T> T kthHelper(int k, T[] arr, int start,
                                      int end, Comparator<T> comparator, Random rand) {
        int pivotIdx;
        if (end == start) {
            return arr[start];
        } else {
            pivotIdx = rand.nextInt(end - start + 1) + start;
        }
        T pivotVal = arr[pivotIdx];
        arr[pivotIdx] = arr[start];
        arr[start] = pivotVal;
        int i = start + 1;
        int j = end;
        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], pivotVal) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], pivotVal) >= 0) {
                j--;
            }
            if (i <= j) {
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        T temp = arr[j];
        arr[j] = arr[start];
        arr[start] = temp;
        if (j == k - 1) {
            return arr[j];
        }
        if (j > k - 1) {
            return kthHelper(k, arr, start, j - 1, comparator, rand);
        } else {
            return kthHelper(k, arr, j + 1, end, comparator, rand);
        }
    }

}
