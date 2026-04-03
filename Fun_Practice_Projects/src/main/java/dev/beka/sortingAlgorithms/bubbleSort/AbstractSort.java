package dev.beka.sortingAlgorithms.bubbleSort;

import java.util.Arrays;

public abstract class AbstractSort {
    public int[] array;

    public AbstractSort(int[] array){
        this.array = Arrays.copyOf(array, array.length);
    }

    public abstract void sort();

    public int[] getArray() {
        return this.array;
    }
}
