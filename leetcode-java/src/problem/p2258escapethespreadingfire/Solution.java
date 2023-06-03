package problem.p2258escapethespreadingfire;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 2258. Escape the Spreading Fire
 *
 * https://leetcode.cn/problems/escape-the-spreading-fire/
 *
 * You are given a 0-indexed 2D integer array grid of size m x n which represents a field.
 *
 * Each cell has one of three values:
 *
 * 0 represents grass,
 * 1 represents fire,
 * 2 represents a wall that you and fire cannot pass through.
 *
 * You are situated in the top-left cell, (0, 0), and you want to travel to the safehouse at the
 * bottom-right cell, (m - 1, n - 1). Every minute, you may move to an adjacent grass cell.
 *
 * After your move, every fire cell will spread to all adjacent cells that are not walls.
 *
 * Return the maximum number of minutes that you can stay in your initial position before moving
 * while still safely reaching the safehouse. If this is impossible, return -1.
 * If you can always reach the safehouse regardless of the minutes stayed, return 109.
 *
 * Note that even if the fire spreads to the safehouse immediately after you have
 * reached it, it will be counted as safely reaching the safehouse.
 *
 * A cell is adjacent to another cell if the former is directly north, east, south, or
 * west of the latter (i.e., their sides are touching).
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int maximumMinutes(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] fires = new int[m][n];
        for (var row : fires) Arrays.fill(row, -1);
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fires[i][j] = 0;
                    queue.add(new int[]{i, j, 0});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            for (var dir : dirs) {
                int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && fires[dx][dy] == -1 && grid[dx][dy] == 0) {
                    queue.add(new int[]{dx, dy, fires[dx][dy] = curr[2] + 1});
                }
            }
        }

        int[][] steps = new int[m][n];
        for (var row : steps) Arrays.fill(row, Integer.MAX_VALUE);
        steps[0][0] = 0; queue.add(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            for (var dir : dirs) {
                int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && grid[dx][dy] == 0 && steps[dx][dy] > curr[2]) {
                    queue.add(new int[]{dx, dy, steps[dx][dy] = curr[2] + 1});
                }
            }
        }
        if (steps[m - 1][n - 1] == Integer.MAX_VALUE) return -1;

        int l = 0, r = 10001, ans = 0;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(fires, steps, 0, 0, mid)) {
                ans = mid; l = mid + 1;
            } else r = mid;
        }
        if (ans == 0) return check(fires, steps, 0, 0, 0) ? 0 : -1;
        return ans >= 10000 ? 1_000_000_000 : ans;
    }

    private boolean check(int[][] fires, int[][] steps, int x, int y, int delta) {
        int m = fires.length, n = fires[0].length;
        if (fires[x][y] != -1 && steps[x][y] + delta >= fires[x][y]) {
            return steps[x][y] + delta == fires[x][y] && x == m - 1 && y == n - 1;
        }
        if (x == m - 1 && y == n - 1) return true;
        for (var dir : dirs) {
            int dx = x + dir[0], dy = y + dir[1];
            if (dx >= 0 && dx < m && dy >= 0 && dy < n && steps[dx][dy] == steps[x][y] + 1) {
                if (check(fires, steps, dx, dy, delta)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // {}
        assert new Solution().maximumMinutes(new int[][]{
            {0,2,0,0,1},
            {0,2,0,2,2},
            {0,2,0,0,0},
            {0,0,2,2,0},
            {0,0,0,0,0}
        }) == 0;

        assert new Solution().maximumMinutes(new int[][]{
            {0,2,0,0,0,0,0},
            {0,0,0,2,2,1,0},
            {0,2,0,0,1,2,0},
            {0,0,2,2,2,0,2},
            {0,0,0,0,0,0,0},
        }) == 3;

        assert new Solution().maximumMinutes(new int[][]{
            {0,0,0,0},
            {0,1,2,0},
            {0,2,0,0}
        }) == -1;

        assert new Solution().maximumMinutes(new int[][]{
            {0,0,0},
            {2,2,0},
            {1,2,0}
        }) == 1000000000;
    }

}
