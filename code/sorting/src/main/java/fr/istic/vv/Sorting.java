package fr.istic.vv;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {
        for (int x = 0; x < array.length-1; x++)
            for (int y = 0; y < array.length-x-1; y++)
                if (comparator.compare(array[y], array[y+1]) > 0) {
                    swap(array, y, y+1);
                }
        return array;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
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

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) { return null; }


    /**
     * Place all smaller than pivot to left of pivot and all bigger elements to right of pivot
     * @param array input array
     * @param comparator comparator for current Type
     * @param low Index of smaller element and
     * @param high index of bigger element (pivot)
     * @param <T>
     * @return
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
