package weekly.bw179.C;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Q3. Minimum XOR Path in a Grid
 *
 * https://leetcode.cn/contest/biweekly-contest-179/problems/minimum-xor-path-in-a-grid/
 *
 * You are given a 2D integer array grid of size m * n.
 *
 * You start at the top-left cell (0, 0) and want to reach the bottom-right cell (m - 1, n - 1).
 *
 * At each step, you may move either right or down.
 *
 * The cost of a path is defined as the bitwise XOR of all the values
 * in the cells along that path, including the start and end cells.
 *
 * Return the minimum possible XOR value among all valid paths from (0, 0) to (m - 1, n - 1).
 */

public class Solution {

    // 0 <= grid[i][j] <= 1023, 只要 9 个二进制位就可以覆盖
    public int minCost(int[][] grid) {
        int n = grid[0].length;
        Set<Integer>[][] dp = new Set[2][n];
        Arrays.setAll(dp[0], i -> new HashSet<>());
        Arrays.setAll(dp[1], i -> new HashSet<>());
        for (int j = 0, x = 0; j < n; j++) dp[0][j].add(x ^= grid[0][j]);

        for (int i = 1; i < grid.length; i++) {
            int curr = i & 1, prev = curr ^ 1;
            for (int j = 0; j < n; j++) dp[curr][j].clear();

            for (var v : dp[prev][0]) dp[curr][0].add(v ^ grid[i][0]);
            for (int j = 1; j < n; j++) {
                for (var v : dp[prev][j]) dp[curr][j].add(v ^ grid[i][j]); // 上方转移
                for (var v : dp[curr][j - 1]) dp[curr][j].add(v ^ grid[i][j]); // 左侧转移
            }
        }

        int ans = Integer.MAX_VALUE;
        for (var v : dp[(grid.length & 1) ^ 1][n - 1]) ans = Math.min(ans, v);
        return ans;
    }

    private static class Optimization {
        public int minCost(int[][] grid) {
            int mx = 0, m = grid.length, n = grid[0].length;
            for (var row : grid) for (var v : row) mx |= v;

            seen = new boolean[m][n][mx + 1];
            dfs(grid, m - 1, n - 1, 0);
            return ans;
        }

        private int ans = Integer.MAX_VALUE;
        private boolean[][][] seen = null;

        private void dfs(int[][] grid, int i, int j, int xor) {
            if (ans == 0 || i < 0 || j < 0 || seen[i][j][xor]) return;

            seen[i][j][xor] = true; xor ^= grid[i][j];
            if (i == 0 && j == 0) ans = Math.min(ans, xor);
            dfs(grid, i - 1, j, xor); dfs(grid, i, j - 1, xor);
        }
    }

    public static void main(String[] args) {
        assert new Solution().minCost(new int[][]{{5,15}}) == 10;

        assert new Solution().minCost(new int[][]{{1,2},{3,4}}) == 6;
        assert new Solution().minCost(new int[][]{{6,7},{5,8}}) == 9;
        assert new Solution().minCost(new int[][]{{2,7,5}}) == 0;
    }

}
