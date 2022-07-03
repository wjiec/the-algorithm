package weekly.w300.D;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 6110. Number of Increasing Paths in a Grid
 *
 * https://leetcode.cn/contest/weekly-contest-300/problems/number-of-increasing-paths-in-a-grid/
 *
 * You are given an m x n integer matrix grid, where you can move from a cell to
 * any adjacent cell in all 4 directions.
 *
 * Return the number of strictly increasing paths in the grid such that you can start from
 * any cell and end at any cell. Since the answer may be very large, return it modulo 109 + 7.
 *
 * Two paths are considered different if they do not have exactly the same sequence of visited cells.
 */

public class Solution {

    private final int MOD = 1_000_000_007;
    private final Map<Point, Long> memos = new HashMap<>();
    private final int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public int countPaths(int[][] grid) {
        long ans = 0;
        int m = grid.length, n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = (ans + dfs(grid, i, j)) % MOD;
            }
        }
        return (int)ans;
    }

    private long dfs(int[][] grid, int x, int y) {
        Point p = new Point(x, y);
        if (memos.containsKey(p)) return memos.get(p);

        long ans = 1;
        int m = grid.length, n = grid[0].length;
        for (int[] dir : dirs) {
            int a = x + dir[0], b = y + dir[1];
            if (a >= 0 && a < m && b >= 0 && b < n && grid[a][b] > grid[x][y]) {
                ans = (ans + dfs(grid, a, b)) % MOD;
            }
        }
        memos.put(p, ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countPaths(new int[][]{{1,1},{3,4}}) == 8;
        assert new Solution().countPaths(new int[][]{{1},{2}}) == 3;
    }

}
