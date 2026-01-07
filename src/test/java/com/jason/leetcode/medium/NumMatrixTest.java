package com.jason.leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumMatrixTest {

    @Test
    void testBasicSumRegion() {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        NumMatrix numMatrix = new NumMatrix(matrix);
        assertEquals(8, numMatrix.sumRegion(2, 1, 4, 3));
        assertEquals(11, numMatrix.sumRegion(1, 1, 2, 2));
        assertEquals(12, numMatrix.sumRegion(1, 2, 2, 4));
    }

    @Test
    void testUpdateAndSumRegion() {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        NumMatrix numMatrix = new NumMatrix(matrix);

        // Initial sum
        assertEquals(8, numMatrix.sumRegion(2, 1, 4, 3));

        // Update value
        numMatrix.update(3, 2, 2);

        // Sum should change
        assertEquals(10, numMatrix.sumRegion(2, 1, 4, 3));
    }

    @Test
    void testSingleCell() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        NumMatrix numMatrix = new NumMatrix(matrix);
        assertEquals(5, numMatrix.sumRegion(1, 1, 1, 1));
        assertEquals(9, numMatrix.sumRegion(2, 2, 2, 2));
    }

    @Test
    void testEntireMatrix() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        NumMatrix numMatrix = new NumMatrix(matrix);
        assertEquals(45, numMatrix.sumRegion(0, 0, 2, 2));
    }

    @Test
    void testMultipleUpdates() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        NumMatrix numMatrix = new NumMatrix(matrix);

        // Original sum
        assertEquals(45, numMatrix.sumRegion(0, 0, 2, 2));

        // Multiple updates
        numMatrix.update(0, 0, 10);  // 1 -> 10 (diff: +9)
        assertEquals(54, numMatrix.sumRegion(0, 0, 2, 2));

        numMatrix.update(2, 2, 0);   // 9 -> 0 (diff: -9)
        assertEquals(45, numMatrix.sumRegion(0, 0, 2, 2));

        numMatrix.update(1, 1, 100); // 5 -> 100 (diff: +95)
        assertEquals(140, numMatrix.sumRegion(0, 0, 2, 2));
    }

    @Test
    void testRowSum() {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        NumMatrix numMatrix = new NumMatrix(matrix);

        // Sum entire first row
        assertEquals(10, numMatrix.sumRegion(0, 0, 0, 3));

        // Sum entire second row
        assertEquals(26, numMatrix.sumRegion(1, 0, 1, 3));
    }

    @Test
    void testColumnSum() {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        NumMatrix numMatrix = new NumMatrix(matrix);

        // Sum entire first column
        assertEquals(15, numMatrix.sumRegion(0, 0, 2, 0));

        // Sum entire last column
        assertEquals(24, numMatrix.sumRegion(0, 3, 2, 3));
    }

    @Test
    void testNegativeNumbers() {
        int[][] matrix = {
                {-1, -2, -3},
                {-4, 5, -6},
                {-7, -8, 9}
        };

        NumMatrix numMatrix = new NumMatrix(matrix);

        assertEquals(-17, numMatrix.sumRegion(0, 0, 2, 2));
        assertEquals(5, numMatrix.sumRegion(1, 1, 1, 1));

        numMatrix.update(1, 1, -5);
        assertEquals(-27, numMatrix.sumRegion(0, 0, 2, 2));
    }

    @Test
    void testZeroMatrix() {
        int[][] matrix = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        NumMatrix numMatrix = new NumMatrix(matrix);
        assertEquals(0, numMatrix.sumRegion(0, 0, 2, 2));

        numMatrix.update(1, 1, 5);
        assertEquals(5, numMatrix.sumRegion(0, 0, 2, 2));
        assertEquals(5, numMatrix.sumRegion(1, 1, 1, 1));
    }

    @Test
    void testLargeValues() {
        int[][] matrix = {
                {100000, -100000},
                {50000, -50000}
        };

        NumMatrix numMatrix = new NumMatrix(matrix);
        assertEquals(0, numMatrix.sumRegion(0, 0, 1, 1));
        assertEquals(100000, numMatrix.sumRegion(0, 0, 0, 0));
    }

    @Test
    void testSubregions() {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };

        NumMatrix numMatrix = new NumMatrix(matrix);

        // Test 2x2 subregion
        assertEquals(40, numMatrix.sumRegion(1, 1, 2, 2)); // 7+8+12+13

        // Test 3x3 subregion
        assertEquals(117, numMatrix.sumRegion(1, 1, 3, 3)); // 7+8+9+12+13+14+17+18+19

        // Test after update
        numMatrix.update(2, 2, 100);
        assertEquals(204, numMatrix.sumRegion(1, 1, 3, 3)); // Added 87 to previous sum
    }

    @Test
    void testEdgeCases() {
        // Single element matrix
        int[][] singleElement = {{42}};
        NumMatrix nm1 = new NumMatrix(singleElement);
        assertEquals(42, nm1.sumRegion(0, 0, 0, 0));

        nm1.update(0, 0, 100);
        assertEquals(100, nm1.sumRegion(0, 0, 0, 0));

        // Single row matrix
        int[][] singleRow = {{1, 2, 3, 4, 5}};
        NumMatrix nm2 = new NumMatrix(singleRow);
        assertEquals(15, nm2.sumRegion(0, 0, 0, 4));
        assertEquals(9, nm2.sumRegion(0, 1, 0, 3));

        // Single column matrix
        int[][] singleColumn = {{1}, {2}, {3}, {4}, {5}};
        NumMatrix nm3 = new NumMatrix(singleColumn);
        assertEquals(15, nm3.sumRegion(0, 0, 4, 0));
        assertEquals(9, nm3.sumRegion(1, 0, 3, 0));
    }

    @Test
    void testSequentialUpdates() {
        int[][] matrix = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        NumMatrix numMatrix = new NumMatrix(matrix);
        assertEquals(9, numMatrix.sumRegion(0, 0, 2, 2));

        // Update each cell sequentially
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                numMatrix.update(i, j, i * 3 + j + 1);
            }
        }

        // Matrix is now:
        // 1  2  3
        // 4  5  6
        // 7  8  9
        assertEquals(45, numMatrix.sumRegion(0, 0, 2, 2));
        assertEquals(12, numMatrix.sumRegion(0, 0, 1, 1)); // 1+2+4+5
    }
}