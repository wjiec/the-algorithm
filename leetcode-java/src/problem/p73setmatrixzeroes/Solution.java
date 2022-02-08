package problem.p73setmatrixzeroes;

import java.util.Arrays;

/**
 * 73. Set Matrix Zeroes
 *
 * https://leetcode-cn.com/problems/set-matrix-zeroes/
 *
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.
 *
 * You must do it in place.
 */

public class Solution {

    public void setZeroes(int[][] matrix) {
        boolean hasZero = false;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) hasZero = true;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (hasZero) matrix[i][0] = 0;
        }
    }

    public static void main(String[] args) {
        int[][] m1 = new int[][]{
            {1,1,1},
            {1,0,1},
            {1,1,1},
        };
        new Solution().setZeroes(m1);
        System.out.println(Arrays.deepToString(m1));

        int[][] m2 = new int[][]{
            {0,1,2,0},
            {3,4,5,2},
            {1,3,1,5},
        };
        new Solution().setZeroes(m2);
        System.out.println(Arrays.deepToString(m2));
    }

}
