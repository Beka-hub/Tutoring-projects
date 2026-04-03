package dev.beka.binarySearch;

public class BinarySearch {

    public static boolean binarySearch(int[] sortedArr, int target) {
        int left = 0;
        int right = sortedArr.length-1;

        while(left <= right){
            int middle = (left + right)/2;

            if(target == sortedArr[middle]){
                return true;
            }

            if (target > sortedArr[middle]){
                left = middle + 1;
            }

            if (target < sortedArr[middle]) {
                right = middle - 1;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,8,9,10};

        System.out.println(binarySearch(arr,7 ));
    }
}
