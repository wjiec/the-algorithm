package weekly.w475.C;

import ability.Benchmark;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Q3. Maximum Path Score in a Grid
 *
 * https://leetcode.cn/contest/weekly-contest-475/problems/maximum-path-score-in-a-grid/
 *
 * You are given an m x n grid where each cell contains one of the values 0, 1, or 2.
 * You are also given an integer k.
 *
 * You start from the top-left corner (0, 0) and want to reach the bottom-right
 * corner (m - 1, n - 1) by moving only right or down.
 *
 * Each cell contributes a specific score and incurs an associated cost, according to their cell values:
 *
 * 0: adds 0 to your score and costs 0.
 * 1: adds 1 to your score and costs 1.
 * 2: adds 2 to your score and costs 1.
 *
 * Return the maximum score achievable without exceeding a total cost of k, or -1 if no valid path exists.
 * Note: If you reach the last cell but the total cost exceeds k, the path is invalid.
 */

public class Solution {

    public int maxPathScore(int[][] grid, int k) {
        m = grid.length; n = grid[0].length;
        req = new int[m][n];
        for (var r : req) Arrays.fill(r, -1);
        minStep(grid, 0, 0);
        if (k < req[0][0]) return -1;

        memo = new Map[m][n];
        for (var r : memo) Arrays.setAll(r, i -> new HashMap<>());
        return Math.max(dfs(grid, 0, 0, k), -1);
    }

    private int m = 0, n = 0;
    private int[][] req = null;
    private Map<Integer, Integer>[][] memo = null;

    private int minStep(int[][] grid, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n) return Integer.MAX_VALUE >> 1;
        if (req[x][y] >= 0) return req[x][y];

        if (x == m - 1 && y == n - 1) return req[x][y] = Math.min(grid[x][y], 1);
        return req[x][y] = Math.min(minStep(grid, x + 1, y), minStep(grid, x, y + 1)) + Math.min(grid[x][y], 1);
    }

    private int dfs(int[][] grid, int x, int y, int k) {
        if (x < 0 || x >= m || y < 0 || y >= n || k < req[x][y]) return Integer.MIN_VALUE;
        if (memo[x][y].containsKey(k)) return memo[x][y].get(k);

        int nk = k - Math.min(grid[x][y], 1);
        if (x == m - 1 && y == n - 1) return nk >= 0 ? grid[x][y] : Integer.MIN_VALUE;

        int ans = grid[x][y] + Math.max(dfs(grid, x + 1, y, nk), dfs(grid, x, y + 1, nk));
        memo[x][y].put(k, ans);
        return ans;
    }

    private static class Optimization {
        public int maxPathScore(int[][] grid, int k) {
            int m = grid.length, n = grid[0].length;

            // 预计算所有的单元格开销
            int[][] cost = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    cost[i][j] = Math.min(grid[i][j], 1);
                }
            }

            // 反向计算想要到达 grid[m - 1][n - 1] 所需的最少花费
            int[][] step = cost.clone();
            for (int j = n - 2; j >= 0; j--) step[m - 1][j] = cost[m - 1][j + 1] + cost[m - 1][j];
            for (int i = m - 2; i >= 0; i--) {
                step[i][n - 1] = cost[i + 1][n - 1] + cost[i][n - 1];
                for (int j = n - 2; j >= 0; j--) {
                    step[i][j] = Math.min(cost[i + 1][j], cost[i][j + 1]) + cost[i][j];
                }
            }
            if (k < step[0][0]) return -1;
            // 如果是 0 花费的话, 说明没有单元格可以得分
            if (step[0][0] == 0) return 0;

            // dp[i][j][k] 表示当到达 grid[i][j] 时, 剩余花费为 k 时的最大得分是多少
            //  - 当前位置 grid[i][j] 所需要的花费为 c = cost[i][j]
            //  - 可以从 dp[i - 1][j][k + c] 或 dp[i][j - 1][k + c] 转移得来
            // 总计算开销为 m * n * k ~= 2e2 * 2e2 * 1e3
            //                      ~= 4e7
            //
            // 当到达一个位置时, 如果可用的 k 越大, 那么肯定得分就会越高, 在一个位置
            //  - 可以是分数最大 (满足 k >= cost)
            //  - 也可以是剩余花费最大(也就是可以走更多的格子)
            // 我们需要记录在每一个位置最大的 k 以及最大的分数对应的 pair 各是什么

            return 1;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("optimization", () -> {
            assert new Optimization().maxPathScore(new int[][]{
                {0, 0, 1, 2, 2, 2, 1, 1, 2, 2},{0, 1, 2, 1, 2, 2, 2, 0, 2, 2},{0, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 2, 1, 2, 0, 2, 0},{1, 1, 1, 0, 1, 2, 0, 1, 0, 0},{2, 2, 2, 0, 2, 2, 1, 1, 1, 1},
                {1, 1, 0, 0, 2, 2, 0, 2, 1, 2},{2, 1, 1, 2, 0, 2, 1, 1, 0, 2},{2, 0, 2, 1, 1, 2, 0, 2, 2, 0},
                {0, 2, 1, 2, 0, 2, 0, 2, 0, 0},{1, 2, 2, 0, 1, 1, 2, 0, 1, 1},{2, 0, 1, 2, 1, 2, 1, 0, 2, 0},
                {0, 1, 1, 2, 0, 2, 0, 0, 0, 2},{1, 1, 2, 1, 1, 2, 2, 1, 0, 1},{0, 2, 0, 1, 2, 2, 1, 2, 0, 2},
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},{0, 0, 1, 2, 2, 2, 0, 0, 0, 2},{0, 2, 1, 0, 0, 0, 2, 2, 1, 1},
                {1, 2, 1, 1, 1, 0, 0, 0, 0, 0},{1, 1, 0, 1, 2, 0, 1, 1, 1, 1},{0, 2, 2, 0, 1, 2, 0, 0, 2, 1},
                {2, 0, 2, 0, 0, 1, 0, 2, 2, 2},{0, 0, 1, 2, 0, 1, 2, 0, 1, 1},{2, 2, 2, 1, 0, 0, 2, 2, 2, 2},
                {1, 2, 0, 0, 1, 0, 2, 0, 0, 1},{1, 1, 1, 0, 2, 2, 2, 2, 2, 2},{0, 1, 0, 1, 2, 0, 0, 1, 1, 1},
                {2, 1, 0, 1, 2, 1, 0, 2, 1, 2},{0, 0, 1, 1, 2, 2, 0, 2, 1, 1},{1, 0, 0, 0, 0, 2, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 2, 1, 1, 2, 0},{0, 1, 1, 2, 0, 2, 1, 2, 1, 0},{2, 0, 0, 2, 1, 2, 1, 2, 0, 2},
                {0, 2, 2, 2, 2, 2, 2, 1, 1, 0},{1, 1, 0, 1, 1, 1, 0, 0, 1, 0},{0, 0, 0, 1, 2, 1, 0, 0, 1, 0},
                {1, 2, 1, 1, 1, 1, 1, 2, 1, 1},{0, 0, 0, 2, 0, 2, 1, 2, 2, 2},{0, 0, 2, 0, 2, 1, 2, 2, 2, 1},
                {0, 1, 2, 1, 1, 0, 2, 1, 0, 0},{0, 0, 0, 0, 1, 0, 0, 0, 2, 0},{2, 2, 2, 1, 2, 1, 0, 1, 1, 0},
                {1, 2, 0, 1, 2, 0, 2, 0, 2, 2},{0, 1, 1, 0, 1, 1, 0, 1, 1, 2},{0, 1, 2, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1, 0, 0, 1, 1, 1},{2, 2, 2, 0, 1, 0, 2, 2, 0, 2},{2, 0, 1, 0, 0, 2, 0, 2, 2, 2},
                {1, 1, 0, 2, 0, 1, 0, 1, 1, 1},{0, 0, 0, 0, 2, 0, 2, 1, 1, 2},{0, 2, 1, 0, 2, 1, 2, 0, 1, 2},
                {0, 2, 2, 0, 2, 1, 1, 0, 1, 0},{2, 1, 1, 1, 0, 2, 1, 2, 1, 0},{1, 2, 1, 1, 1, 1, 2, 2, 2, 1},
                {2, 0, 1, 2, 0, 0, 2, 0, 2, 2},{1, 0, 1, 2, 1, 2, 1, 2, 1, 2},{2, 1, 0, 0, 2, 1, 2, 0, 1, 0},
                {2, 0, 2, 2, 0, 2, 0, 1, 0, 1},{2, 1, 0, 1, 2, 1, 2, 2, 1, 1},{0, 1, 1, 1, 2, 0, 0, 2, 2, 2},
                {0, 1, 1, 1, 2, 0, 1, 0, 1, 0},{1, 2, 2, 1, 0, 0, 1, 0, 2, 0},{0, 1, 2, 0, 1, 0, 1, 0, 1, 2},
                {0, 1, 0, 0, 1, 2, 2, 0, 2, 1},{0, 2, 1, 0, 1, 2, 0, 1, 1, 0},{0, 0, 0, 0, 2, 2, 2, 1, 0, 1},
                {1, 0, 0, 1, 2, 0, 0, 1, 0, 1},{1, 1, 0, 2, 2, 2, 1, 2, 1, 1},{1, 1, 1, 1, 0, 1, 1, 1, 0, 0},
                {2, 0, 2, 0, 2, 0, 2, 1, 1, 2},{2, 0, 2, 1, 1, 0, 2, 1, 1, 2},{0, 0, 0, 2, 1, 1, 2, 2, 2, 1},
                {0, 1, 0, 2, 1, 1, 2, 1, 1, 1},{0, 0, 1, 2, 1, 1, 2, 1, 1, 1},{0, 2, 2, 1, 0, 0, 2, 2, 1, 1},
                {0, 0, 1, 2, 1, 2, 0, 2, 0, 1},{2, 0, 0, 0, 2, 2, 0, 0, 2, 2},{1, 0, 0, 1, 1, 2, 2, 2, 2, 2}
            }, 325) == 117;

            assert new Optimization().maxPathScore(new int[200][200], 0) == 0;
            assert new Optimization().maxPathScore(new int[][]{{0, 1, 1, 1}, {1, 2, 2, 0}, {1, 0, 1, 2}}, 4) == 7;
            assert new Optimization().maxPathScore(new int[][]{{0, 1}, {1, 2}}, 1) == -1;
            assert new Optimization().maxPathScore(new int[][]{{0, 2, 2}, {1, 1, 1}, {0, 0, 2}}, 3) == 5;
        });

        assert new Solution().maxPathScore(new int[][]{{0, 1}, {1, 2}}, 1) == -1;
        assert new Solution().maxPathScore(new int[][]{{0, 2, 2}, {1, 1, 1}, {0, 0, 2}}, 3) == 5;
    }

}
