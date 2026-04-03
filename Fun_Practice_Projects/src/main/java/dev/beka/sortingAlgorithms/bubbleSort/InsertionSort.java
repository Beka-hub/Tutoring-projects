package dev.beka.sortingAlgorithms.bubbleSort;

public class InsertionSort extends AbstractSort {

    public InsertionSort(int[] array){
        super(array);
    }

    //|7,6,5,4,3,2,1
    //----------->
    //1,7,6,5,4,3,2
    //1,2,7,6,5,4,3
    // 1, 2, 3, 7,6,5,4

    @Override
    public void sort() {
        for (int i = 1; i < array.length; i++) {
            int key = array[i]; // index 1
            int j = i - 1; //indexj to 0

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        } //O(n^2)
    }
}
