package fr.istic.vv;

import net.jqwik.api.*;
import java.util.Comparator;
import java.util.stream.IntStream;

public class SortingTest {

    Comparator<Integer> comparator = Integer::compareTo;

    @Property
    boolean bubblesortArrayIsSorted(@ForAll("randomArrayProvider") Integer[] array) {
        Integer[] bubbleSortArray = Sorting.bubblesort(array, comparator);
        return IntStream.range(0, bubbleSortArray.length - 1).noneMatch(i -> comparator.compare(bubbleSortArray[i], bubbleSortArray[i + 1]) > 0);
    }

    @Property
    boolean mergesortArrayIsSorted(@ForAll("randomArrayProvider") Integer[] array) {
        Integer[] mergeSortArray = Sorting.mergesort(array, comparator);
        IntStream.range(0, mergeSortArray.length - 1).forEach(i -> {
            System.out.println(comparator.compare(mergeSortArray[i], mergeSortArray[i + 1]));

        });
        return IntStream.range(0, mergeSortArray.length - 1).noneMatch(i -> comparator.compare(mergeSortArray[i], mergeSortArray[i + 1]) > 0);
    }

    @Property
    boolean quicksortArrayIsSorted(@ForAll("randomArrayProvider") Integer[] array) {
        Integer[] quickSortArray = Sorting.quicksort(array, comparator);
        return IntStream.range(0, quickSortArray.length - 1).noneMatch(i -> comparator.compare(quickSortArray[i], quickSortArray[i + 1]) > 0);
    }


    @Provide
    Arbitrary<Integer[]> randomArrayProvider() {
        return Arbitraries.integers().array(Integer[].class).ofMinSize(0).ofMaxSize(100);
    }

}