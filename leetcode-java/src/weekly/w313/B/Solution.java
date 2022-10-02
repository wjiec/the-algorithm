package weekly.w313.B;

/**
 * 6193. Maximum Sum of an Hourglass
 *
 * https://leetcode.cn/problems/maximum-sum-of-an-hourglass/
 *
 * You are given an m x n integer matrix grid.
 *
 * We define an hourglass as a part of the matrix with the following form:
 *
 * Return the maximum sum of the elements of an hourglass.
 *
 * Note that an hourglass cannot be rotated and must be entirely contained within the matrix.
 */

public class Solution {

    public int maxSum(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length - 2; i++) {
            for (int j = 0; j < grid[0].length - 2; j++) {
                ans = Math.max(ans, calc(grid, i, j));
            }
        }
        return ans;
    }

    private int calc(int[][] grid, int x, int y) {
        return grid[x][y] + grid[x][y + 1] + grid[x][y + 2]
            + grid[x + 1][y + 1]
            + grid[x + 2][y] + grid[x + 2][y + 1] + grid[x + 2][y + 2];
    }

    public static void main(String[] args) {
    }

}
