package com.jason.leetcode.medium;

public class NumMatrix {
  private final int[][] prefixSum;
  private final int[][] data;

  public NumMatrix(int[][] matrix) {
    // Constructor
    int m = matrix.length, n = matrix[0].length;
    prefixSum = new int[m][n];
    data = matrix;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        prefixSum[i][j] =
            matrix[i][j]
                + getValueNullSafe(i - 1, j)
                + getValueNullSafe(i, j - 1)
                - getValueNullSafe(i - 1, j - 1);
      }
    }
  }

  private int getValueNullSafe(int row, int col) {
    if (row < 0 || col < 0) {
      return 0;
    }
    return prefixSum[row][col];
  }

  public void update(int row, int col, int val) {
    // Update value at matrix[row][col]
    for (int i = 0; i < prefixSum.length; i++) {
      for (int j = 0; j < prefixSum[0].length; j++) {
        if (i >= row && j >= col) {
          prefixSum[i][j] += val - data[row][col];
        }
      }
    }
    data[row][col] = val;
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    // Return sum of rectangle
    return prefixSum[row2][col2]
        - getValueNullSafe(row1 - 1, col2)
        - getValueNullSafe(row2, col1 - 1)
        + getValueNullSafe(row1 - 1, col1 - 1);
  }
}
