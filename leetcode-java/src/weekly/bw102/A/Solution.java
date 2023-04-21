package weekly.bw102.A;

/**
 * 2639. Find the Width of Columns of a Grid
 *
 * https://leetcode.cn/contest/biweekly-contest-102/problems/find-the-width-of-columns-of-a-grid/
 *
 * You are given a 0-indexed m x n integer matrix grid. The width of a column is the maximum length of its integers.
 *
 * For example, if grid = [[-10], [3], [12]], the width of the only column is 3 since -10 is of length 3.
 * Return an integer array ans of size n where ans[i] is the width of the ith column.
 *
 * The length of an integer x with len digits is equal to len if x is non-negative, and len + 1 otherwise.
 */

public class Solution {

    public int[] findColumnWidth(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] ans = new int[n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                ans[j] = Math.max(ans[j], String.valueOf(grid[i][j]).length());
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
