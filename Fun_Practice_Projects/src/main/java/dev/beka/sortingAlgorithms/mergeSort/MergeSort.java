package dev.beka.sortingAlgorithms.mergeSort;

import dev.beka.sortingAlgorithms.bubbleSort.AbstractSort;

import java.util.Arrays;

public class MergeSort  {
    public int[] array;

    public MergeSort(int[] array) {
        this.array = array;
    }

    public void sort() {
        if (array == null || array.length < 2) {return;}

        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if(left == right){
            return;
        }

        int middle = (left + right) / 2;
        System.out.println(middle);

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

                            //[left] < [right]
            tempArr[k++] = (array[leftIndex] <= array[rightIndex])

                            // true -> tempArr[] = [left]
                            ? array[leftIndex++]

                            // false -> tempArr[] = [right]
                            : array[rightIndex++];

        }

        //append left over leftArray
        while (leftIndex <= mid){
            tempArr[k++] = array[leftIndex++];
        }

        //append left over rightArray
        while (rightIndex <= right){
            tempArr[k++] = array[rightIndex++];
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
        int[] array = new int[]{20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0};
        MergeSort myMergeSort = new MergeSort(array);

        myMergeSort.sort();

        System.out.println(Arrays.toString(myMergeSort.getArray()));
    }
}

