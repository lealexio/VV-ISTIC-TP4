package fr.istic.vv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BinaryHeap<T> {

    private final Comparator<T> comparator;
    private final List<T> heap;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        heap = new ArrayList<>();
    }

    /**
     * Getter for current heap
     * Unmodifiable list returned
     * @return unmodifiable representation of current heap
     */
    public List<T> getHeap() {
        return Collections.unmodifiableList(heap);
    }

    /**
     *
     * @return the minimum object and remove it from the BinaryHeap
     */
    public T pop() {
        heap.sort(comparator);
        T e = heap.get(heap.size()-1);
        heap.remove(e);
        return e;
    }

    /**
     *
     * @return the minimum object but it does not remove it from the BinaryHeap
     */
    public T peek() {
        heap.sort(comparator);
        return heap.get(heap.size()-1);
    }

    /**
     * Add element to heap
     * @param element element to add
     */
    public void push(T element) { heap.add(element);}

    /**
     * Get size of heap
     * @return size of heap
     */
    public int count() { return heap.size(); }

}