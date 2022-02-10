package problem.p766toeplitzmatrix;

import java.util.LinkedList;

/**
 * 766. Toeplitz Matrix
 *
 * https://leetcode-cn.com/problems/toeplitz-matrix/
 *
 * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
 *
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.
 */

public class Solution {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        if (n == 1) return true;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isToeplitzMatrix1(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        if (n == 1) return true;

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            list.add(matrix[0][i]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1, k = 0; j < m; j++, k++) {
                if (list.get(k) != matrix[i][j]) {
                    return false;
                }
            }

            list.removeLast();
            list.addFirst(matrix[i][0]);
        }

        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().isToeplitzMatrix(new int[][]{{97,97}, {80,6}, {10,79}});
        assert new Solution().isToeplitzMatrix(new int[][]{{1,2,3,4}, {5,1,2,3}, {9,5,1,2}});
        assert !new Solution().isToeplitzMatrix(new int[][]{{1,2}, {1,2}});
    }

}
