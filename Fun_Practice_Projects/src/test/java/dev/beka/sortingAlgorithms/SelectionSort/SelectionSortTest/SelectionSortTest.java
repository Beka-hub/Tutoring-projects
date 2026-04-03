package dev.beka.sortingAlgorithms.SelectionSort.SelectionSortTest;

import dev.beka.sortingAlgorithms.bubbleSort.AbstractSort;
import dev.beka.sortingAlgorithms.bubbleSort.MergeSort;
import dev.beka.sortingAlgorithms.bubbleSort.SelectionSort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SelectionSortTest {
    @Test
    void sort(){
        AbstractSort mySelectionSort = new SelectionSort(new int[]{7,6,5,4,3,2,1});
        mySelectionSort.sort();
        int[] actual = mySelectionSort.getArray();
        int[] expected = new int[]{1, 2, 3, 4, 5, 6, 7};
        assertArrayEquals(expected, actual);
    }
}
//check if it satabele