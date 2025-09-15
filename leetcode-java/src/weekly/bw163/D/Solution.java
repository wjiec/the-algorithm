package weekly.bw163.D;

import ability.Benchmark;

import java.util.*;

/**
 * Q4. Minimum Cost Path with Teleportations
 *
 * https://leetcode.cn/contest/biweekly-contest-163/problems/minimum-cost-path-with-teleportations/
 *
 * You are given a m x n 2D integer array grid and an integer k. You start at the
 * top-left cell (0, 0) and your goal is to reach the bottom‐right cell (m - 1, n - 1).
 *
 * There are two types of moves available:
 *
 * Normal move: You can move right or down from your current cell (i, j), i.e. you can
 * move to (i, j + 1) (right) or (i + 1, j) (down). The cost is the value of the destination cell.
 *
 * Teleportation: You can teleport from any cell (i, j), to any cell (x, y) such that
 * grid[x][y] <= grid[i][j]; the cost of this move is 0. You may teleport at most k times.
 *
 * Return the minimum total cost to reach cell (m - 1, n - 1) from (0, 0).
 */

public class Solution {

    // 可以有以下移动方式
    //  - 从 (i, j) 移动到 (i, j + 1) 或者 (i + 1, j) 代价为对应格子的值
    //  - 从 (i, j) 移动到任何 grid[x][y] <= grid[i][j] 的格子上, 代价为 0, 但是只允许移动 k 次
    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        List<int[]>[] g = new List[m * n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < m) g[i * n + j].add(new int[]{(i + 1) * n + j, grid[i + 1][j]});
                if (j + 1 < n) g[i * n + j].add(new int[]{i * n + j + 1, grid[i][j + 1]});
            }
        }

        Integer[] sorted = new Integer[m * n];
        Arrays.setAll(sorted, i -> i);
        Arrays.sort(sorted, Comparator.comparingInt(i -> grid[i / n][i % n]));
        for (int i = sorted.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                g[sorted[i]].add(new int[]{sorted[j], -1});
                if (grid[sorted[i] / n][sorted[i] % n] == grid[sorted[j] / n][sorted[j] % n]) {
                    g[sorted[j]].add(new int[]{sorted[i], -1});
                }
            }
        }

        int[][] seen = new int[k + 1][m * n];
        for (var row : seen) Arrays.fill(row, Integer.MAX_VALUE);
        Queue<int[]> pq = new ArrayDeque<>(); // [node, cost, k]
        pq.add(new int[]{0, 0, 0}); seen[0][0] = 0;
        while (!pq.isEmpty()) {
            var curr = pq.remove();
            int u = curr[0], s = curr[1], ck = curr[2];
            if (u == m * n - 1 || s > seen[ck][u]) continue;

            for (var next : g[u]) {
                int v = next[0], w = next[1];
                if (w < 0 && ck + 1 <= k && s < seen[ck + 1][v]) {
                    pq.add(new int[]{v, seen[ck + 1][v] = s, ck + 1});
                }
                if (w >= 0 && s + w < seen[ck][v]) {
                    pq.add(new int[]{v, seen[ck][v] = s + w, ck});
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++) ans = Math.min(ans, seen[i][m * n - 1]);
        return ans;
    }

    private static class Optimization {
        public int minCost(int[][] grid, int k) {
            int m = grid.length, n = grid[0].length;

            record Pos(int x, int y) {}
            Pos[] coordinates = new Pos[m * n];
            for (int i = 0, l = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    coordinates[l++] = new Pos(i, j);
                }
            }
            Arrays.sort(coordinates, Comparator.comparingInt(v -> grid[v.x][v.y]));

            // g[x][y] 我们可以从哪些坐标到达 (x, y)
            Set<Pos>[][] g = new Set[m][n];
            for (int i = 0; i < m; i++) Arrays.setAll(g[i], j -> new HashSet<>());
            // coordinates 是按坐标从小到大排序的
            for (int i = 0; i < coordinates.length; i++) {
                var dst = coordinates[i];
                // 我们可以从一个数跳到小于等于他的数上, 对于 i, 我们可以从任意 > i 的位置跳到 i
                for (int j = i + 1; j < coordinates.length; j++) {
                    var src = coordinates[j]; g[dst.x][dst.y].add(src);
                    // 如果相等, 则反向的也可以传送
                    if (grid[dst.x][dst.y] == grid[src.x][src.y]) g[src.x][src.y].add(dst);
                }
            }

            int[][][] dp = new int[k + 1][m + 1][n + 1];
            for (var mat : dp) for (var row : mat) Arrays.fill(row, Integer.MAX_VALUE / 2);
            for (int i = 0; i <= k; i++) {
                // 初始条件
                dp[i][1][0] = dp[i][0][1] = -grid[0][0];
                for (int x = 1; x <= m; x++) {
                    for (int y = 1; y <= n; y++) {
                        // 直接移动一步进行转移
                        dp[i][x][y] = Math.min(dp[i][x - 1][y], dp[i][x][y - 1]) + grid[x - 1][y - 1];
                        if (i != 0) {
                            // 也可以使用传送到达 (x - 1, y - 1)
                            for (var from : g[x - 1][y - 1]) {
                                dp[i][x][y] = Math.min(dp[i][x][y], dp[i - 1][from.x + 1][from.y + 1]);
                            }
                        }
                    }
                }
            }

            int ans = Integer.MAX_VALUE;
            for (int i = 0; i <= k; i++) ans = Math.min(ans, dp[i][m][n]);
            return ans;
        }
    }

    private static class Optimization2 {
        public int minCost(int[][] grid, int k) {
            int m = grid.length, n = grid[0].length, maxValue = 0;
            for (var row : grid) {
                for (var v : row) maxValue = Math.max(maxValue, v);
            }

            int[] prevMin = new int[maxValue + 2];
            Arrays.fill(prevMin, Integer.MAX_VALUE);
            int[][][] dp = new int[k + 1][m + 1][n + 1];
            for (int i = 0; i <= k; i++) {
                int[] currMin = new int[maxValue + 2];
                Arrays.fill(currMin, Integer.MAX_VALUE);
                for (var row : dp[i]) Arrays.fill(row, Integer.MAX_VALUE / 2);

                dp[i][1][0] = dp[i][0][1] = -grid[0][0];
                for (int x = 1; x <= m; x++) {
                    for (int y = 1; y <= n; y++) {
                        int v = grid[x - 1][y - 1];

                        // 直接移动一步进行转移
                        dp[i][x][y] = Math.min(dp[i][x - 1][y], dp[i][x][y - 1]) + grid[x - 1][y - 1];
                        // 从 >= grid[x - 1][y - 1] 的位置转移
                        dp[i][x][y] = Math.min(dp[i][x][y], prevMin[v]);

                        currMin[grid[x - 1][y - 1]] = Math.min(currMin[v], dp[i][x][y]);
                    }
                }

                // 计算后缀最小值
                for (int x = maxValue; x >= 0; x--) {
                    prevMin[x] = Math.min(prevMin[x + 1], currMin[x]);
                }
            }

            int ans = Integer.MAX_VALUE;
            for (int i = 0; i <= k; i++) ans = Math.min(ans, dp[i][m][n]);
            return ans;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Optimization2", () -> {
            assert new Optimization2().minCost(new int[][]{{19,10}, {23,13}, {16,32}, {38,41}, {30,36}, {53,31}}, 1) == 55;
            assert new Optimization2().minCost(new int[][]{{3,1},{10,4}}, 7) == 4;
            assert new Optimization2().minCost(new int[][]{{1,3,3},{2,5,4},{4,3,5}}, 2) == 7;
            assert new Optimization2().minCost(new int[][]{{1,2},{2,3},{3,4}}, 1) == 9;
        });

        Benchmark.benchmark("Optimization", () -> {
            assert new Optimization().minCost(new int[][]{{19,10}, {23,13}, {16,32}, {38,41}, {30,36}, {53,31}}, 1) == 55;
            assert new Optimization().minCost(new int[][]{{3,1},{10,4}}, 7) == 4;
            assert new Optimization().minCost(new int[][]{{1,3,3},{2,5,4},{4,3,5}}, 2) == 7;
            assert new Optimization().minCost(new int[][]{{1,2},{2,3},{3,4}}, 1) == 9;
        });

        assert new Solution().minCost(new int[][]{{19,10}, {23,13}, {16,32}, {38,41}, {30,36}, {53,31}}, 1) == 55;
        assert new Solution().minCost(new int[][]{{3,1},{10,4}}, 7) == 4;
        assert new Solution().minCost(new int[][]{{1,3,3},{2,5,4},{4,3,5}}, 2) == 7;
        assert new Solution().minCost(new int[][]{{1,2},{2,3},{3,4}}, 1) == 9;
    }

}
