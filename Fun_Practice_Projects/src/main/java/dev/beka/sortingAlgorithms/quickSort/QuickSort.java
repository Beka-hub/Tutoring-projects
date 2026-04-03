package dev.beka.sortingAlgorithms.quickSort;

import dev.beka.sortingAlgorithms.bubbleSort.AbstractSort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends AbstractSort {

    public QuickSort(int[] array){
        super(array);
    }

    @Override
    public void sort() {
        quickSort(0, array.length-1);
    }

    private void quickSort(int left, int right) {

        if(left>=right){
            return;
        }

        int pivotIndex = hoaresPartition(left, right);
        quickSort(left,pivotIndex-1);
        quickSort(pivotIndex+1,right);
    }

    private int partition(int left, int right){
        //int randomNum = ThreadLocalRandom.current().nextInt(left, right+1);
        int pivotIndex = left;
        int pivotElement = array[right]; //array[randomNum]; // todo try to find index at random

        for(int i = left; i <= right; i++){

            //find smallest
            if(array[i] < pivotElement){
                swap(i, pivotIndex);
                pivotIndex++;
            }
        }

        swap(pivotIndex, right);

        return pivotIndex;
    }

    public void hoaresQuickSort(int left, int right){
        if(left>=right){
            return;
        }

        int boundaryIndex = hoaresPartition(left,right);
        quickSort(left,boundaryIndex);
        quickSort(boundaryIndex+1,right);

    }

    //we dont care about pivots position
    // after each partition one element is sorted (wrong x), is not sorted the array just splited in hals where its <= to pivot or >= to pivot
    // whens its odd and the center part gose to the left
    public int hoaresPartition(int left, int right){
        int i = left, j=right;
        int randomNum= ThreadLocalRandom.current().nextInt(left, right+1);
        int pivot = array[randomNum];//(left + right)/2];

        while(true){
            while(array[i] < pivot){ i++;}
            while(array[j] > pivot){ j--;}

            if(i >= j){ return j;}

            swap(i,j);
            i++; j--;
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main (String[] args){
        QuickSort mySort = new QuickSort(new int[] {20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0} );

        mySort.sort();

        System.out.print(Arrays.toString(mySort.array));
    }
}
