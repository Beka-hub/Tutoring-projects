package dev.beka.sortingAlgorithms.bubbleSort;

import java.util.Arrays;

public class MergeSort extends AbstractSort {

    public MergeSort(int[] array) {
        super(array);
    }

    @Override
    public void sort() {
        if (array == null || array.length < 2) {return;}

        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if(left == right){
            return;
        }

        int middle = (left + right) / 2;

        // [left part]
        mergeSort(array, left, middle);

        // [right part]
        mergeSort(array, middle + 1, right);

        //array, [left part], [right part]
        merge(array, left, middle, right);
    }

    private void merge(int[] array, int left, int mid, int right) {
        int[] tempArr = new int[right - left + 1];

        int leftIndex = left;
        int rightIndex = mid + 1;
        int k = 0;

        while (leftIndex <= mid && rightIndex <= right) {

            if (array[leftIndex] < array[rightIndex]) {
                tempArr[k] = array[leftIndex];
                k++; leftIndex++;
            }

            else {
                tempArr[k] = array[rightIndex];
                k++; rightIndex++;
            }
        }

        //append left over leftArray
        while (leftIndex <= mid){
            tempArr[k] = array[leftIndex];
            leftIndex++; k++;
        }

        //append left over rightArray
        while (rightIndex <= right){
            tempArr[k] = array[rightIndex];
            rightIndex++; k++;
        }

        //override
        for(int t=0; t < tempArr.length; t++ ){
             array[left + t] = tempArr[t];
        }
    }

    public int[] getArray(){
        return array;
    }

    public static void main(String[]args){
        int[] array = new int[]{6,5,4,3,2,1,0};
        MergeSort myMergeSort = new MergeSort(array);

        myMergeSort.sort();

        System.out.println(Arrays.toString(myMergeSort.getArray()));
    }
}
