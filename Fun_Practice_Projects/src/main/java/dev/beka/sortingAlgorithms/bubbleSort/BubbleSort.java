package dev.beka.sortingAlgorithms.bubbleSort;


public class BubbleSort extends AbstractSort{

    BubbleSort(int[] array) {
        super(array);
    }

    @Override
    public void sort() {
        if (array == null) {
            throw new NullPointerException();
        }

        //detects if elements swaped  already sorted to exit early
        boolean swapped;
        //
        for(int i = 0; i < array.length; i++){
            swapped = false;

            //last comparison must be j+1 (length - 1 gives us 1 unreachable element at the end)
            for(int j = 0; j < array.length - 1 - i; j++){
                if(array[j] > array[j+1]){
                    swapped = true;
                    int numHolder = array[j];
                    array[j] = array[j+1];
                    array[j+1] = numHolder;
                }
            }

            if(!swapped){break;}
        }
    }
}
