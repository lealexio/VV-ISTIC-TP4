package fr.istic.vv;
import java.util.Comparator;

public class Sorting {

    // ------- Bubble Sort -------
    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {
        array = array.clone();
        for (int x = 0; x < array.length-1; x++)
            for (int y = 0; y < array.length-x-1; y++)
                if (comparator.compare(array[y], array[y+1]) > 0) {
                    swap(array, y, y+1);
                }
        return array;
    }

    // ------- Quick Sort -------

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
        array = array.clone();
        return quicksort(array, comparator, 0, array.length - 1);
    }

    private static <T> T[] quicksort(T[] array, Comparator<T> comparator, int low, int high) {
        if (low < high) {
            int partition = partition(array, comparator, low, high);
            quicksort(array, comparator, low, partition - 1);
            quicksort(array, comparator, partition + 1, high);
        }
        return array;
    }

    /**
     * Place all smaller than pivot to left of pivot and all bigger elements to right of pivot
     * @param array input array
     * @param comparator comparator for current Type
     * @param low Index of smaller element and
     * @param high index of bigger element (pivot)
     * @param <T> type
     * @return partition position
     */
    private static <T> int partition(T[] array, Comparator<T> comparator, int low, int high) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(pivot, array[j]) > 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        swap(array,i + 1 ,high);
        return i + 1;
    }

    // ------- Merge Sort -------

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        array = array.clone();
        if (array.length > 0) {
            mergesort(array, comparator, 0, array.length - 1);
        }
        return array;
    }

    private static <T> void mergesort(T[] array, Comparator<T> comparator, int low, int high) {
        if (high <= low){
            return;
        }
        int mid = (low+high)/2;
        mergesort(array, comparator, low, mid);
        mergesort(array, comparator, mid+1, high);
        merge(array, comparator, low, mid, high);
    }


    /**
     * Merge results into a sorted array
     * @param array array to merge
     * @param comparator comparator for current type
     * @param low array begin
     * @param mid array mid
     * @param high array end
     * @param <T> type
     */
    private static <T> void merge(T[] array, Comparator<T> comparator, int low, int mid, int high) {
        T[] leftArray = (T[]) new Object[mid - low + 1];
        T[] rightArray = (T[]) new Object[high - mid];

        for (int i = 0; i < leftArray.length; i++)
            leftArray[i] = array[low + i];
        for (int i = 0; i < rightArray.length; i++)
            rightArray[i] = array[mid + i + 1];

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = low; i < high + 1; i++) {
            if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (comparator.compare(leftArray[leftIndex], rightArray[rightIndex]) < 0) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < rightArray.length) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }


    // ------- Utility -------

    /**
     * Swap two elements in an array
     * @param array array to swipe
     * @param pos1 position of first element
     * @param pos2 position of second element
     */
    private static <T> void swap(T[] array, int pos1, int pos2){
        T tmp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = tmp;
    }
}
