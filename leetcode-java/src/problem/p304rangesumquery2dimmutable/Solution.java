package problem.p304rangesumquery2dimmutable;

/**
 * 304. Range Sum Query 2D - Immutable
 *
 * https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
 *
 * Given a 2D matrix, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of matrix inside the rectangle defined by
 * its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Implement the NumMatrix class:
 *
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the
 * elements of matrix inside the rectangle defined by its upper left corner (row1, col1)
 * and lower right corner (row2, col2).
 */

public class Solution {

    private static class NumMatrix {
        private final int[][] sums;
        public NumMatrix(int[][] matrix) {
            this.sums = new int[matrix.length + 1][matrix[0].length + 1];
            for (int i = 0, m = matrix.length; i < m; i++) {
                for (int j = 0, n = matrix[0].length; j < n; j++) {
                    sums[i + 1][j + 1] = sums[i][j + 1] + sums[i + 1][j] - sums[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sums[row2 + 1][col2 + 1] - sums[row1][col2 + 1] - sums[row2 + 1][col1] + sums[row1][col1];
        }
    }

    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][]{
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        });
        assert numMatrix.sumRegion(2, 1, 4, 3) == 8;
        assert numMatrix.sumRegion(1, 1, 2, 2) == 11;
        assert numMatrix.sumRegion(1, 2, 2, 4) == 12;
    }

}
