package dev.beka.sortingAlgorithms.bubbleSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BubbleSortTest {
    @Test
    void sort(){
        BubbleSort myBubbleSort = new BubbleSort(new int[]{7,6,5,4,3,2,1});
        myBubbleSort.sort();
        int[] actual = myBubbleSort.getArray();
        int[] expected = new int[]{1, 2, 3, 4, 5, 6, 7};
        assertArrayEquals(expected, actual);
    }
}
