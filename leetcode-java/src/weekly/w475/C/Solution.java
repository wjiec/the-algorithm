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
            // 要么向右要么向下, 所以最多只能走 m + n - 2 个步数
            k = Math.min(k, m + n - 2);

            // 预计算所有的单元格开销
            int[][] cost = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    cost[i][j] = Math.min(grid[i][j], 1);
                }
            }

            // 反向计算想要到达 (m - 1, n - 1) 所需的最少花费
            int[][] step = new int[m][n]; step[0][0] = cost[0][0];
            for (int j = 1; j < n; j++) step[0][j] = step[0][j - 1] + cost[0][j];
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    step[i][j] = step[i - 1][j] + cost[i][j];
                    if (j > 0) step[i][j] = Math.min(step[i][j], step[i][j - 1] + cost[i][j]);
                }
            }
            // 无法走到目标位置的提前判断
            if (k < step[m - 1][n - 1]) return -1;

            // dp[i][j][k] 表示当到达 grid[i][j] 时, 已经花费为 k 的最大得分
            //  - 当前位置 grid[i][j] 所需要的花费为 c = cost[i][j]
            //  - 可以从 dp[i - 1][j][k - c] 或 dp[i][j - 1][k - c] 转移得来
            // 总计算开销为 m * n * k ~= 2e2 * 2e2 * 1e3
            //                      ~= 4e7
            // 每一行只和上一行有关, 可以优化掉一个维度
            int[][][] dp = new int[2][n][k + 1];
            for (var r : dp[0]) Arrays.fill(r, Integer.MIN_VALUE);
            // 我们将 (1, 1) 作为起始坐标, 也就是从 (0, 1) 和 (1, 0) 转移而来
            dp[0][0][0] = 0; if (n > 1) dp[0][1][0] = 0;
            // 枚举所有位置并计算对应的花费的得分
            for (int i = 1; i <= m; i++) {
                int[][] curr = dp[i & 1], prev = dp[(i & 1) ^ 1];
                for (var r : curr) Arrays.fill(r, Integer.MIN_VALUE);

                for (int j = 0; j < n; j++) {
                    int c = cost[i - 1][j], v = grid[i - 1][j];
                    for (int kk = c; kk <= Math.min(k, i + j); kk++) {
                        curr[j][kk] = v + prev[j][kk - c];
                        if (j > 0) curr[j][kk] = Math.max(curr[j][kk], v + curr[j - 1][kk - c]);
                    }
                }
            }

            int ans = 0;
            for (int kk = step[m - 1][n - 1]; kk <= k; kk++) {
                ans = Math.max(ans, dp[m & 1][n - 1][kk]);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Optimization", () -> {
            assert new Optimization().maxPathScore(new int[][]{{0}}, 0) == 0;
            assert new Optimization().maxPathScore(new int[][]{{0, 1}, {2, 0}}, 1) == 2;

            assert new Optimization().maxPathScore(new int[][]{
                {0, 1, 1, 1, 0}, {0, 1, 0, 2, 0}, {0, 1, 2, 2, 1}, {1, 0, 0, 2, 0}, {2, 0, 1, 1, 2},
                {2, 1, 0, 2, 2}, {1, 0, 0, 0, 1}, {1, 2, 2, 1, 0}, {2, 0, 0, 0, 0}, {0, 1, 2, 2, 0},
                {1, 0, 2, 1, 2}, {0, 1, 1, 1, 2}, {0, 0, 2, 1, 1}, {0, 1, 1, 1, 2}, {1, 0, 0, 0, 1},
                {2, 0, 1, 0, 0}, {2, 1, 0, 2, 2}, {2, 1, 2, 0, 2}, {1, 1, 2, 2, 2}, {1, 2, 2, 0, 0},
                {0, 0, 1, 0, 2}, {0, 1, 2, 2, 0}, {0, 1, 2, 1, 1}, {2, 2, 0, 2, 2}, {1, 0, 1, 0, 0},
                {0, 1, 2, 1, 1}, {1, 2, 2, 2, 0}, {1, 2, 2, 2, 0}, {0, 1, 2, 0, 2}, {2, 1, 2, 2, 0},
                {1, 0, 0, 1, 2}, {1, 2, 1, 2, 0}, {2, 1, 2, 2, 0}, {2, 2, 1, 1, 1}, {0, 1, 1, 1, 0},
                {2, 1, 2, 1, 1}, {2, 2, 2, 1, 2}, {0, 0, 1, 2, 2}, {1, 2, 2, 1, 0}, {2, 1, 0, 1, 2},
                {1, 2, 0, 1, 1}, {0, 1, 2, 1, 2}, {0, 0, 0, 0, 2}, {1, 0, 1, 2, 2}, {0, 2, 2, 0, 1},
                {2, 0, 1, 2, 0}, {2, 0, 0, 1, 1}, {2, 0, 0, 0, 0}, {2, 2, 0, 0, 0}, {1, 1, 1, 2, 0},
                {0, 2, 0, 1, 1}, {2, 1, 0, 1, 0}, {2, 1, 2, 0, 0}, {1, 2, 0, 2, 1}, {2, 2, 1, 2, 2},
                {1, 0, 1, 0, 2}, {1, 0, 0, 2, 1}, {1, 2, 0, 1, 0}, {2, 1, 2, 2, 0}, {2, 0, 2, 0, 1},
                {1, 1, 1, 1, 0}, {1, 0, 2, 2, 0}, {1, 2, 0, 2, 1}, {0, 1, 0, 0, 2}, {0, 1, 0, 1, 2},
                {0, 1, 1, 0, 0},
            }, 43) == 69;
            assert new Optimization().maxPathScore(new int[][]{{0, 0}, {1, 0}}, 38) == 1;
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
            assert new Optimization().maxPathScore(new int[][]{
                {0, 1, 1, 1},
                {1, 2, 2, 0},
                {1, 0, 1, 2}
            }, 4) == 7;
            assert new Optimization().maxPathScore(new int[][]{{0, 1}, {1, 2}}, 1) == -1;
            assert new Optimization().maxPathScore(new int[][]{{0, 2, 2}, {1, 1, 1}, {0, 0, 2}}, 3) == 5;
        });

        assert new Solution().maxPathScore(new int[][]{{0, 1}, {1, 2}}, 1) == -1;
        assert new Solution().maxPathScore(new int[][]{{0, 2, 2}, {1, 1, 1}, {0, 0, 2}}, 3) == 5;
    }

}
