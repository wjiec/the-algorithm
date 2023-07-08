package problem.p2290minimumobstacleremovaltoreachcorner;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 2290. Minimum Obstacle Removal to Reach Corner
 *
 * https://leetcode.cn/problems/minimum-obstacle-removal-to-reach-corner/
 *
 * You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:
 *
 * 0 represents an empty cell,
 * 1 represents an obstacle that may be removed.
 * You can move up, down, left, or right from and to an empty cell.
 *
 * Return the minimum number of obstacles to remove so you can move from
 * the upper left corner (0, 0) to the lower right corner (m - 1, n - 1).
 */

public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] distance = new int[m][n];
        for (var row : distance) Arrays.fill(row, Integer.MAX_VALUE);
        distance[0][0] = 0;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int x = curr[0], y = curr[1];
            for (var dir : dirs) {
                int dx = x + dir[0], dy = y + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n) {
                    if (distance[x][y] + grid[dx][dy] < distance[dx][dy]) {
                        distance[dx][dy] = distance[x][y] + grid[dx][dy];
                        if (grid[dx][dy] == 0) queue.addFirst(new int[]{dx, dy});
                        else queue.addLast(new int[]{dx, dy});
                    }
                }
            }
        }
        return distance[m - 1][n - 1];
    }

    public static void main(String[] args) {
    }

}
