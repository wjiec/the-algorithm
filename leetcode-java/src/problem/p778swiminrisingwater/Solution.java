package problem.p778swiminrisingwater;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 778. Swim in Rising Water
 *
 * https://leetcode.cn/problems/swim-in-rising-water
 *
 * You are given an n x n integer matrix grid where each value grid[i][j]
 * represents the elevation at that point (i, j).
 *
 * The rain starts to fall. At time t, the depth of the water everywhere is t.
 * You can swim from a square to another 4-directionally adjacent square if and
 * only if the elevation of both squares individually are at most t.
 *
 * You can swim infinite distances in zero time. Of course, you must stay
 * within the boundaries of the grid during your swim.
 *
 * Return the least time until you can reach the bottom right square (n - 1, n - 1)
 * if you start at the top left square (0, 0).
 */

public class Solution {

    public int swimInWater(int[][] grid) {
        int l = grid[0][0], r = 2510, ans = 0;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(grid, mid)) {
                ans = mid; r = mid;
            } else l = mid + 1;
        }
        return ans;
    }

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private boolean check(int[][] grid, int v) {
        int n = grid.length;
        boolean[][] seen = new boolean[n][n];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        seen[0][0] = true;

        while (!queue.isEmpty()) {
            var curr = queue.remove();
            if (curr[0] == n - 1 && curr[1] == n - 1) return true;

            for (var dir : dirs) {
                int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                if (dx >= 0 && dx < n && dy >= 0 && dy < n && !seen[dx][dy] && grid[dx][dy] <= v) {
                    queue.add(new int[]{dx, dy});
                    seen[dx][dy] = true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
    }

}
