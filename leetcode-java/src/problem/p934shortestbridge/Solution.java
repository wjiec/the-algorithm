package problem.p934shortestbridge;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 934. Shortest Bridge
 *
 * https://leetcode.cn/problems/shortest-bridge/
 *
 * You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
 *
 * An island is a 4-directionally connected group of 1's not connected to any other 1's.
 * There are exactly two islands in grid.
 *
 * You may change 0's to 1's to connect the two islands to form one island.
 *
 * Return the smallest number of 0's you must flip to connect the two islands.
 */

public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int shortestBridge(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return shortestBridge(grid, i, j);
                }
            }
        }
        return 0;
    }

    private int shortestBridge(int[][] grid, int x, int y) {
        grid[x][y] = -1;

        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        Queue<int[]> island = new ArrayDeque<>();
        for (queue.add(new int[]{x, y}); !queue.isEmpty(); ) {
            int[] curr = queue.remove();
            island.add(new int[]{curr[0], curr[1], 0});

            for (var dir : dirs) {
                int a = curr[0] + dir[0], b = curr[1] + dir[1];
                if (a >= 0 && a < m && b >= 0 && b < n && grid[a][b] == 1) {
                    grid[a][b] = -1;
                    queue.add(new int[]{a, b});
                }
            }
        }

        while (!island.isEmpty()) {
            int[] curr = island.remove();
            for (var dir : dirs) {
                int a = curr[0] + dir[0], b = curr[1] + dir[1];
                if (a >= 0 && a < m && b >= 0 && b < n) {
                    if (grid[a][b] == 0) {
                        grid[a][b] = -1;
                        island.add(new int[]{a, b, curr[2] + 1});
                    } else if (grid[a][b] == 1) return curr[2];
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        assert new Solution().shortestBridge(new int[][]{
            {0,1},
            {1,0}
        }) == 1;

        assert new Solution().shortestBridge(new int[][]{
            {0,1,0},
            {0,0,0},
            {0,0,1}
        }) == 2;

        assert new Solution().shortestBridge(new int[][]{
            {1,1,1,1,1},
            {1,0,0,0,1},
            {1,0,1,0,1},
            {1,0,0,0,1},
            {1,1,1,1,1}
        }) == 1;
    }

}
