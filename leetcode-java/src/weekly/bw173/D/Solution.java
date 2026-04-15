package weekly.bw173.D;

import ability.Benchmark;

import java.util.Arrays;

/**
 * Q4. Count Routes to Climb a Rectangular Grid
 *
 * https://leetcode.cn/contest/biweekly-contest-173/problems/count-routes-to-climb-a-rectangular-grid/
 *
 * You are given a string array grid of size n, where each string grid[i] has length m.
 *
 * The character grid[i][j] is one of the following symbols:
 *
 * '.': The cell is available.
 * '#': The cell is blocked.
 * You want to count the number of different routes to climb grid. Each route must start
 * from any cell in the bottom row (row n - 1) and end in the top row (row 0).
 *
 * However, there are some constraints on the route.
 *
 * You can only move from one available cell to another available cell.
 *
 * The Euclidean distance of each move is at most d, where d is an integer parameter given to you.
 *  The Euclidean distance between two cells (r1, c1), (r2, c2) is sqrt((r1 - r2)2 + (c1 - c2)2).
 *
 * Each move either stays on the same row or moves to the row directly above (from row r to r - 1).
 *
 * You cannot stay on the same row for two consecutive turns. If you stay on the same row
 * in a move (and this move is not the last move), your next move must go to the row above.
 *
 * Return an integer denoting the number of such routes. Since the answer may be very large,
 * return it modulo 1e9 + 7.
 */

public class Solution {

    public int numberOfRoutes(String[] grid, int d) {
        int m = grid.length, n = grid[0].length();
        char[][] chars = new char[m][];
        for (int i = 0; i < m; i++) chars[i] = grid[i].toCharArray();

        memo = new int[m][n][2];
        for (var mat : memo) for (var r : mat) Arrays.fill(r, -1);

        long ans = 0;
        for (int j = 0; j < n; j++) {
            if (chars[m - 1][j] == '.') {
                ans += dfs(chars, m - 1, j, true, d);
            }
        }
        return (int) (ans % 1_000_000_007);
    }

    private int[][][] memo = null;

    // 当前在的位置是 (i, j), chance 表示此时是否还有机会移动的同一行
    private int dfs(char[][] grid, int i, int j, boolean chance, int d) {
        if (i == 0 && !chance) return 1;
        if (memo[i][j][chance ? 1 : 0] != -1) return memo[i][j][chance ? 1 : 0];

        long ans = i == 0 ? 1 : 0; int n = grid[0].length;
        // 移动到的位置要么是 (i - 1, j') 要么是 (i, j')
        //  - 需要满足 (i' - i)^2 + (j' - j)^2 <= d * d
        //      - 移动到上一行的话, 就是 (j' - j)^2 <= d * d - 1
        //          - j' - j <= sqrt(d * d - 1)
        //      - 移动到同一行的话, 就是 (j' - j)^2 <= d * d
        //          - j' <= d

        // 移动到上一行
        if (i > 0 && grid[i - 1][j] == '.') ans += dfs(grid, i - 1, j, true, d);
        if (i > 0) {
            for (int k = (int) Math.sqrt(d * d - 1); k > 0; k--) {
                if (j - k >= 0 && grid[i - 1][j - k] == '.') ans += dfs(grid, i - 1, j - k, true, d);
                if (j + k < n && grid[i - 1][j + k] == '.') ans += dfs(grid, i - 1, j + k, true, d);
            }
        }
        // 如果能移动到同一行的话
        if (chance) {
            for (int k = d; k > 0; k--) {
                if (j - k >= 0 && grid[i][j - k] == '.') ans += dfs(grid, i, j - k, false, d);
                if (j + k < n && grid[i][j + k] == '.') ans += dfs(grid, i, j + k, false, d);
            }
        }
        return memo[i][j][chance ? 1 : 0] = (int) (ans % 1_000_000_007);
    }

    private static class Iteration {
        private static final int MOD = 1_000_000_007;
        public int numberOfRoutes(String[] grid, int d) {
            int n = grid[0].length(), dx = (int) Math.sqrt(d * d - 1);

            int[] curr = new int[n]; Arrays.fill(curr, 1); // 初始化
            // 反向枚举所有行
            for (int i = 0; i < grid.length; i++) {
                char[] chars = grid[i].toCharArray();

                int[] sum = new int[n + 1];
                // 前一行的前缀和用于计算往上走可以走到的位置
                for (int j = 0; j < n; j++) sum[j + 1] = (sum[j] + curr[j]) % MOD;

                int[] next = new int[n];
                // 当前行只有为 . 的位置才可以到达上一行, 如果是第一行的话
                for (int j = 0; j < n; j++) {
                    if (chars[j] == '#') continue;
                    // 上一行能到达两边的距离是 [j - dx, j + dx]
                    if (i == 0) next[j] = curr[j]; // 第一行无法在往上走了
                    else next[j] = (sum[Math.min(j + dx + 1, n)] - sum[Math.max(j - dx, 0)] + MOD) % MOD;
                }

                // 叠加上走同一行的逻辑
                sum[0] = 0;
                for (int j = 0; j < n; j++) sum[j + 1] = (sum[j] + next[j]) % MOD;
                for (int j = 0; j < n; j++) {
                    if (chars[j] == '#') continue;
                    // 同一行能到达两边的距离是 [j - d, j + d], 会额外多加一个 next[j], 需要去掉
                    next[j] = (sum[Math.min(j + d + 1, n)] - sum[Math.max(j - d, 0)] + MOD) % MOD;
                }

                curr = next;
            }

            int ans = 0;
            for (var v : curr) ans = (ans + v) % MOD;
            return ans;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Iteration", () -> {
            assert new Iteration().numberOfRoutes(new String[]{"..","#."}, 1) == 2;
            assert new Iteration().numberOfRoutes(new String[]{"..","#."}, 2) == 4;
            assert new Iteration().numberOfRoutes(new String[]{"#"}, 750) == 0;
            assert new Iteration().numberOfRoutes(new String[]{".."}, 1) == 4;
        });

        assert new Solution().numberOfRoutes(new String[]{"..","#."}, 1) == 2;
        assert new Solution().numberOfRoutes(new String[]{"..","#."}, 2) == 4;
        assert new Solution().numberOfRoutes(new String[]{"#"}, 750) == 0;
        assert new Solution().numberOfRoutes(new String[]{".."}, 1) == 4;
    }

}
