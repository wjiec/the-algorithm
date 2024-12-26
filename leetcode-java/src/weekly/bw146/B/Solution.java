package weekly.bw146.B;

/**
 * 3393. Count Paths With the Given XOR Value
 *
 * https://leetcode.cn/contest/biweekly-contest-146/problems/count-paths-with-the-given-xor-value/
 *
 * You are given a 2D integer array grid with size m x n. You are also given an integer k.
 *
 * Your task is to calculate the number of paths you can take from the top-left cell (0, 0)
 * to the bottom-right cell (m - 1, n - 1) satisfying the following constraints:
 *
 * You can either move to the right or down. Formally, from the cell (i, j) you may move to
 * the cell (i, j + 1) or to the cell (i + 1, j) if the target cell exists.
 *
 * The XOR of all the numbers on the path must be equal to k.
 *
 * Return the total number of such paths.
 *
 * Since the answer can be very large, return the result modulo 109 + 7.
 */

public class Solution {

    public int countPathsWithXorValue(int[][] grid, int k) {
        final int MOD = 1_000_000_007;
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m + 1][n][16]; dp[0][0][0] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i - 1][j];

                // 可以从左边或者上面进行转移
                for (int xor = 0; xor < 16; xor++) {
                    dp[i][j][xor ^ v] = (dp[i][j][xor ^ v] + dp[i - 1][j][xor]) % MOD;
                    if (j > 0) dp[i][j][xor ^ v] = (dp[i][j][xor ^ v] + dp[i][j - 1][xor]) % MOD;
                }
            }
        }

        return dp[m][n - 1][k];
    }

    public static void main(String[] args) {
        assert new Solution().countPathsWithXorValue(new int[][]{{2, 1, 5}, {7, 10, 0}, {12, 6, 4}}, 11) == 3;
        assert new Solution().countPathsWithXorValue(new int[][]{{1, 3, 3, 3}, {0, 3, 3, 2}, {3, 0, 1, 1}}, 2) == 5;
        assert new Solution().countPathsWithXorValue(new int[][]{{1, 1, 1, 2}, {3, 0, 3, 2}, {3, 0, 2, 2}}, 10) == 0;
    }

}
