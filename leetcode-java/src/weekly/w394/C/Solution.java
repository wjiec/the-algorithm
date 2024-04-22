package weekly.w394.C;

/**
 * 100290. Minimum Number of Operations to Satisfy Conditions
 *
 * https://leetcode.cn/contest/weekly-contest-394/problems/minimum-number-of-operations-to-satisfy-conditions/
 *
 * You are given a 2D matrix grid of size m x n. In one operation, you can change the value of any
 * cell to any non-negative number. You need to perform some operations such that each cell grid[i][j] is:
 *
 * Equal to the cell below it, i.e. grid[i][j] == grid[i + 1][j] (if it exists).
 * Different from the cell to its right, i.e. grid[i][j] != grid[i][j + 1] (if it exists).
 *
 * Return the minimum number of operations needed.
 */

public class Solution {

    public int minimumOperations(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] map = new int[n][10];
        for (int[] row : grid) {
            for (int j = 0; j < n; j++) {
                map[j][row[j]]++;
            }
        }

        int[][] dp = new int[n + 1][10];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                int mi = Integer.MAX_VALUE;
                for (int k = 0; k < 10; k++) {
                    if (k == j) continue;
                    mi = Math.min(mi, dp[i - 1][k]);
                }
                dp[i][j] = mi + m - map[i - 1][j];
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < 10; j++) {
            ans = Math.min(ans, dp[n][j]);
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
