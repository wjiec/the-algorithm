package problem.p1329sortthematrixdiagonally;

import common.Checker;

import java.util.PriorityQueue;

/**
 * 1329. Sort the Matrix Diagonally
 *
 * https://leetcode.cn/problems/sort-the-matrix-diagonally/
 *
 * A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or
 * leftmost column and going in the bottom-right direction until reaching the matrix's end.
 * For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes
 * cells mat[2][0], mat[3][1], and mat[4][2].
 *
 * Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and
 * return the resulting matrix.
 */

public class Solution {

    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) sort(mat, i, 0);
        for (int i = 0; i < n; i++) sort(mat, 0, i);
        return mat;
    }

    private void sort(int[][] mat, int x, int y) {
        int m = mat.length, n = mat[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = x, j = y; i < m && j < n; i++, j++) {
            pq.add(mat[i][j]);
        }
        for (int i = x, j = y; i < m && j < n; i++, j++) {
            mat[i][j] = pq.remove();
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().diagonalSort(new int[][]{
            {3,3,1,1},
            {2,2,1,2},
            {1,1,1,2}
        }), new int[][]{
            {1,1,1,1},
            {1,2,2,2},
            {1,2,3,3}
        });

        assert Checker.check(new Solution().diagonalSort(new int[][]{
            {11,25,66,1,69,7},
            {23,55,17,45,15,52},
            {75,31,36,44,58,8},
            {22,27,33,25,68,4},
            {84,28,14,11,5,50}
        }), new int[][]{
            {5,17,4,1,52,7},
            {11,11,25,45,8,69},
            {14,23,25,44,58,15},
            {22,27,31,36,50,66},
            {84,28,75,33,55,68}
        });
    }

}
