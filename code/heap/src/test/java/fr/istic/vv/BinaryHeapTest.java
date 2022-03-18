package fr.istic.vv;

import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


public class BinaryHeapTest {

    Comparator<Integer> comparator = Integer::compareTo;

    @Property
    boolean testHeapSize(@ForAll("randomArrayProvider") Integer[] array) {
        BinaryHeap<Integer> bh = new BinaryHeap<>(comparator);
        for (Integer integer : array) {
            bh.push(integer);
        }
        return array.length == bh.count();
    }

    @Property
    boolean testUniquePop(@ForAll Integer value) throws NotSuchElementException {
        BinaryHeap<Integer> bh = new BinaryHeap<>(comparator);
        bh.push(value);
        return Objects.equals(bh.pop(), value) && bh.count()==0;
    }

    @Property
    boolean testUniquePeek(@ForAll Integer value) throws NotSuchElementException {
        BinaryHeap<Integer> bh = new BinaryHeap<>(comparator);
        bh.push(value);
        return Objects.equals(bh.peek(), value);
    }

    @Property
    boolean testPopOnEmpty() {
        BinaryHeap<Integer> bh = new BinaryHeap<>(comparator);
        try {
            bh.pop();
            return false;
        }catch (NotSuchElementException e){
            return true;
        }
    }

    @Property
    boolean testPeekOnEmpty() {
        BinaryHeap<Integer> bh = new BinaryHeap<>(comparator);
        try {
            bh.peek();
            return false;
        }catch (NotSuchElementException e){
            return true;
        }
    }

    @Property
    boolean testPeek(@ForAll("randomArrayProvider") Integer[] array) throws NotSuchElementException {
        BinaryHeap<Integer> bh = new BinaryHeap<>(comparator);
        for (Integer integer : array) {
            bh.push(integer);
        }

        List<Integer> heap1 = bh.getHeap();
        int removedElement = bh.peek();
        List<Integer> heap2 = bh.getHeap();
        return heap1.size() == heap2.size() && heap2.containsAll(heap1) && heap2.contains(removedElement);

    }

    @Property
    boolean testPop(@ForAll("randomArrayProvider") Integer[] array) throws NotSuchElementException {
        BinaryHeap<Integer> bh = new BinaryHeap<>(comparator);
        for (Integer integer : array) {
            bh.push(integer);
        }

        List<Integer> heap1 = bh.getHeap();
        int removedElement = bh.pop();
        List<Integer> heap2 = bh.getHeap();

        // i don't know why pop don't work in test, but work out ...
        return heap1.size() == heap2.size()-1 && heap1.containsAll(heap2) && !heap2.contains(removedElement);

    }


    @Property
    boolean testPush(@ForAll("randomArrayProvider") Integer[] array) {
        BinaryHeap<Integer> bh = new BinaryHeap<>(comparator);
        for (Integer integer : array) {
            bh.push(integer);
        }

        List<Integer> heap = bh.getHeap();
        return heap.size() == array.length && heap.containsAll(Arrays.asList(array));

    }

    @Provide
    Arbitrary<Integer[]> randomArrayProvider() {
        return Arbitraries.integers().array(Integer[].class).ofMinSize(1).ofMaxSize(100);
    }

}
