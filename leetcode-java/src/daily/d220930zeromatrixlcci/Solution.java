package daily.d220930zeromatrixlcci;

import common.Checker;

import java.util.Arrays;

/**
 * 01.08. Zero Matrix LCCI
 *
 * https://leetcode.cn/problems/zero-matrix-lcci/
 *
 * Write an algorithm such that if an element in an MxN matrix is 0, its
 * entire row and column are set to 0.
 */

public class Solution {

    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean vZero = false, hZero = false;
        for (int[] row : matrix) vZero = vZero || row[0] == 0;
        for (int i = 0; i < n; i++) hZero = hZero || matrix[0][i] == 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (hZero) Arrays.fill(matrix[0], 0);
        if (vZero) for (int i = 0; i < m; i++) matrix[i][0] = 0;
    }

    public static void main(String[] args) {
        int[][] s1 = new int[][]{
            {1,1,1},
            {1,0,1},
            {1,1,1}
        };

        new Solution().setZeroes(s1);
        assert Checker.check(s1, new int[][]{
            {1,0,1},
            {0,0,0},
            {1,0,1}
        });

        int[][] s2 = new int[][]{
            {0,1,2,0},
            {3,4,5,2},
            {1,3,1,5}
        };

        new Solution().setZeroes(s2);
        assert Checker.check(s2, new int[][]{
            {0,0,0,0},
            {0,4,5,0},
            {0,3,1,0}
        });
    }

}
