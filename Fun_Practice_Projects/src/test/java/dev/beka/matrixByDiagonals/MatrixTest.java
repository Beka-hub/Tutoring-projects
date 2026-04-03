package dev.beka.matrixByDiagonals;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    void testNormalMatrix() {
        int[][] input = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        Matrix matrix = new Matrix(input);
        int[][] result = matrix.sortMatrix();

        int[][] expected = {
                {9, 2, 3},
                {8, 5, 6},
                {7, 4, 1}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    void testSingleElement() {
        int[][] input = {{5}};

        Matrix matrix = new Matrix(input);
        int[][] result = matrix.sortMatrix();

        assertArrayEquals(new int[][]{{5}}, result);
    }

    @Test
    void testEmptyMatrix() {
        int[][] input = {};

        Matrix matrix = new Matrix(input);
        int[][] result = matrix.sortMatrix();

        assertArrayEquals(new int[][]{}, result);
    }

    @Test
    void testNullMatrix() {
        Matrix matrix = new Matrix(null);
        assertNull(matrix.sortMatrix());
    }

    @Test
    void testSingleRow() {
        int[][] input = {{3, 1, 2}};

        Matrix matrix = new Matrix(input);
        int[][] result = matrix.sortMatrix();

        // each element is its own diagonal → no change
        assertArrayEquals(new int[][]{{3, 1, 2}}, result);
    }

    @Test
    void testSingleColumn() {
        int[][] input = {
                {3},
                {1},
                {2}
        };

        Matrix matrix = new Matrix(input);
        int[][] result = matrix.sortMatrix();

        // each element is its own diagonal → no change
        assertArrayEquals(new int[][]{
                {3},
                {1},
                {2}
        }, result);
    }

    @Test
    void testDuplicates() {
        int[][] input = {
                {2, 2},
                {2, 2}
        };

        Matrix matrix = new Matrix(input);
        int[][] result = matrix.sortMatrix();

        assertArrayEquals(new int[][]{
                {2, 2},
                {2, 2}
        }, result);
    }

    @Test
    void testNegativeNumbers() {
        int[][] input = {
                {-1, -2},
                {-3, -4}
        };

        Matrix matrix = new Matrix(input);
        int[][] result = matrix.sortMatrix();

        int[][] expected = {
                {-1, -2},
                {-3, -4}
        };

        assertArrayEquals(expected, result);
    }

    @Test
    void testRectangularMatrix() {
        int[][] input = {
                {3, 1, 2},
                {6, 5, 4}
        };

        Matrix matrix = new Matrix(input);
        int[][] result = matrix.sortMatrix();

        // just ensure no crash and dimensions preserved
        assertEquals(2, result.length);
        assertEquals(3, result[0].length);
    }

    @Test
    void testAlreadySortedDiagonals() {
        int[][] input = {
                {9, 2, 3},
                {8, 5, 6},
                {7, 4, 1}
        };

        Matrix matrix = new Matrix(input);
        int[][] result = matrix.sortMatrix();

        assertArrayEquals(input, result);
    }
}