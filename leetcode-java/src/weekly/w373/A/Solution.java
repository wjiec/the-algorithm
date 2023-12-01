package weekly.w373.A;

/**
 * 2946. Matrix Similarity After Cyclic Shifts
 *
 * https://leetcode.cn/contest/weekly-contest-373/problems/matrix-similarity-after-cyclic-shifts/
 *
 * You are given a 0-indexed m x n integer matrix mat and an integer k.
 *
 * You have to cyclically right shift odd indexed rows k times and cyclically left shift even indexed rows k times.
 *
 * Return true if the initial and final matrix are exactly the same and false otherwise.
 */

public class Solution {

    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] newMat = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i % 2 == 0) newMat[i][j] = mat[i][(((j - k) % n) + n) % n];
                else newMat[i][j] = mat[i][(((j + k) % n) + n) % n];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != newMat[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
