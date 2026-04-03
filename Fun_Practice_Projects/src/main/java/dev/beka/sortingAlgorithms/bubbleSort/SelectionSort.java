package dev.beka.sortingAlgorithms.bubbleSort;

public class SelectionSort extends AbstractSort {
    public SelectionSort(int[] array){
        super(array);
    }

    @Override
    public void sort() {
        //7,6,5,4,3,2,1
        //1,6,5,4,3,2,7
        //1,2,5,4,3,6,7

        for(int i = 0; i < array.length - 1; i++){
            int minIndex = i;

            for(int j = i + 1; j < array.length; j++){

                if(array[j] < array[minIndex]){
                    minIndex = j;
                }
            }

            //swap
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}
