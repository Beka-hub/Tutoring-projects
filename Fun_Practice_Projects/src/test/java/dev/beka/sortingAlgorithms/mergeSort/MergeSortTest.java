package dev.beka.sortingAlgorithms.mergeSort;

import dev.beka.sortingAlgorithms.bubbleSort.AbstractSort;
import dev.beka.sortingAlgorithms.bubbleSort.MergeSort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {
    @Test
    void sort(){
        AbstractSort mySelectionSort = new MergeSort(new int[]{7,6,5,4,3,2,1});
        mySelectionSort.sort();
        int[] actual = mySelectionSort.getArray();
        int[] expected = new int[]{1, 2, 3, 4, 5, 6, 7};
        assertArrayEquals(expected, actual);
    }
}
