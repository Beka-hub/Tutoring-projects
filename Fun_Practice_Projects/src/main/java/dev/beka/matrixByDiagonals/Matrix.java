package dev.beka.matrixByDiagonals;

import java.util.*;

public class Matrix {
    int[][] array;
    Map<Integer, LinkedList<Integer>> myMap = new HashMap<>();

    public Matrix(int[][] array) {
        this.array = array;
    }

    public void sortMatrix() {
        myMap.clear();

        if (array == null || array.length == 0) {
            return;
        }

        int rowLen = array.length;
        int colLen = array[0].length;

        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                int key = row - col;

                myMap.computeIfAbsent(key, k -> new LinkedList<>())
                        .addLast(array[row][col]);
            }
        }


        for (Map.Entry<Integer, LinkedList<Integer>> myEntries : myMap.entrySet()) {
            int key = myEntries.getKey();

            //ascending
            if (key < 0) {
                myEntries.getValue()
                        .sort(Comparator.naturalOrder());
            }

            //descending
            if (key >= 0) {
                myEntries.getValue()
                        .sort(Comparator.reverseOrder());
            }
        }

        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                int key = row - col;

                array[row][col] = myMap.get(key).removeFirst();
            }
        }
    }

    public static void main(String[] args) {

        int[][] array = {{1, 2, 3, 1}, {4, 5, 6, 1}, {7, 8, 9, 1}, {1, 1, 1, 1}};
        Matrix matrix = new Matrix(array);

        matrix.sortMatrix();

        int rowLen = array.length;
        int colLen = array[0].length;

        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {

                System.out.print(array[row][col]);
            }

            System.out.println();
        }
    }
}
