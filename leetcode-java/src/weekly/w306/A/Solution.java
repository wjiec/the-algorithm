package weekly.w306.A;

/**
 * 6148. Largest Local Values in a Matrix
 *
 * https://leetcode.cn/contest/weekly-contest-306/problems/largest-local-values-in-a-matrix/
 *
 * You are given an n x n integer matrix grid.
 *
 * Generate an integer matrix maxLocal of size (n - 2) x (n - 2) such that:
 *
 * maxLocal[i][j] is equal to the largest value of the 3 x 3 matrix in grid centered around row i + 1 and column j + 1.
 * In other words, we want to find the largest value in every contiguous 3 x 3 matrix in grid.
 *
 * Return the generated matrix.
 */

public class Solution {

    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] ans = new int[n - 2][n - 2];
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans.length; j++) {
                ans[i][j] = max(grid, i + 1, j + 1);
            }
        }
        return ans;
    }

    private int max(int[][] grid, int x, int y) {
        int ans = Integer.MIN_VALUE;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                ans = Math.max(ans, grid[i][j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
