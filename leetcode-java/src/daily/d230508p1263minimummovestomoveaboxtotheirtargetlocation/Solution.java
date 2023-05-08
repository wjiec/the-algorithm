package daily.d230508p1263minimummovestomoveaboxtotheirtargetlocation;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 1263. Minimum Moves to Move a Box to Their Target Location
 *
 * https://leetcode.cn/problems/minimum-moves-to-move-a-box-to-their-target-location/
 *
 * A storekeeper is a game in which the player pushes boxes around
 * in a warehouse trying to get them to target locations.
 *
 * The game is represented by an m x n grid of characters grid where each element is a wall, floor, or box.
 *
 * Your task is to move the box 'B' to the target position 'T' under the following rules:
 *
 * The character 'S' represents the player. The player can move up, down, left, right
 * in grid if it is a floor (empty cell).
 * The character '.' represents the floor which means a free cell to walk.
 * The character '#' represents the wall which means an obstacle (impossible to walk there).
 * There is only one box 'B' and one target cell 'T' in the grid.
 * The box can be moved to an adjacent free cell by standing next to the box
 * and then moving in the direction of the box. This is a push.
 * The player cannot walk through the box.
 * Return the minimum number of pushes to move the box to the target. If there is no
 * way to reach the target, return -1.
 */

public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int minPushBox(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int sx = -1, sy = -1, bx = -1, by = -1; // 玩家、箱子的初始位置
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (grid[x][y] == 'S') {
                    sx = x; sy = y;
                } else if (grid[x][y] == 'B') {
                    bx = x; by = y;
                }
            }
        }

        int[][] dp = new int[m * n][m * n];
        for (int i = 0; i < m * n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[sx * n + sy][bx * n + by] = 0; // 初始状态的推动次数为 0

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx * n + sy, bx * n + by});

        while (!queue.isEmpty()) {
            Queue<int[]> nextQueue = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int s1 = arr[0], b1 = arr[1];
                int sx1 = s1 / n, sy1 = s1 % n, bx1 = b1 / n, by1 = b1 % n;
                if (grid[bx1][by1] == 'T') return dp[s1][b1]; // 箱子已被推到目标处

                for (var dir : dirs) {
                    int sx2 = sx1 + dir[0], sy2 = sy1 + dir[1], s2 = sx2 * n + sy2;
                    if (!ok(grid, m, n, sx2, sy2)) continue; // 玩家位置不合法
                    if (bx1 == sx2 && by1 == sy2) { // 推动箱子
                        int bx2 = bx1 + dir[0], by2 = by1 + dir[1], b2 = bx2 * n + by2;
                        // 箱子位置不合法 或 状态已访问
                        if (!ok(grid, m, n, bx2, by2) || dp[s2][b2] <= dp[s1][b1] + 1) continue;

                        dp[s2][b2] = dp[s1][b1] + 1;
                        nextQueue.add(new int[]{s2, b2});
                    } else {
                        // 状态已访问
                        if (dp[s2][b1] <= dp[s1][b1]) continue;

                        dp[s2][b1] = dp[s1][b1];
                        queue.add(new int[]{s2, b1});
                    }
                }
            }
            queue = nextQueue;
        }
        return -1;
    }

    public boolean ok(char[][] grid, int m, int n, int x, int y) { // 不越界且不在墙上
        return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != '#';
    }

    public static void main(String[] args) {
    }

}
