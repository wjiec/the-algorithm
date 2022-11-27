package problem.p2606differencebetweenonesandzerosinrowandcolumn;

import common.Checker;

/**
 * 6277. Difference Between Ones and Zeros in Row and Column
 *
 * https://leetcode.cn/problems/difference-between-ones-and-zeros-in-row-and-column/
 *
 * You are given a 0-indexed m x n binary matrix grid.
 *
 * A 0-indexed m x n difference matrix diff is created with the following procedure:
 *
 * Let the number of ones in the ith row be onesRowi.
 * Let the number of ones in the jth column be onesColj.
 * Let the number of zeros in the ith row be zerosRowi.
 * Let the number of zeros in the jth column be zerosColj.
 * diff[i][j] = onesRowi + onesColj - zerosRowi - zerosColj
 *
 * Return the difference matrix diff.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int[][] onesMinusZeros(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rows = new int[m], cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows[i]++; cols[j]++;
                }
            }
        }

        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = rows[i] + cols[j] - (m - rows[i]) - (n - cols[j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().onesMinusZeros(new int[][]{{0,1,1},{1,0,1},{0,0,1}}), new int[][]{{0,0,4},{0,0,4},{-2,-2,2}});
        assert Checker.check(new Solution().onesMinusZeros(new int[][]{{1,1,1},{1,1,1}}), new int[][]{{5,5,5},{5,5,5}});
    }

}
