package problem.p695maxareaofisland;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 695. Max Area of Island
 *
 * https://leetcode-cn.com/problems/max-area-of-island/
 *
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all
 * four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 */

public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    ans = Math.max(ans, bfs(grid, i, j));
                }
            }
        }
        return ans;
    }

    private int bfs(int[][] grid, int x, int y) {
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        for (queue.add(new int[]{x, y}); !queue.isEmpty(); ) {
            count++;
            int[] curr = queue.remove();

            x = curr[0]; y = curr[1];
            grid[x][y] = 0;
            for (var dir : dirs) {
                int a = x + dir[0], b = y + dir[1];
                if (a >= 0 && a < grid.length && b >= 0 && b < grid[0].length && grid[a][b] == 1) {
                    queue.add(new int[]{a, b});
                    grid[a][b] = 0;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        assert new Solution().maxAreaOfIsland(new int[][]{
            {1,1,0,0,0},
            {1,1,0,0,0},
            {0,0,0,1,1},
            {0,0,0,1,1}
        }) == 4;

        assert new Solution().maxAreaOfIsland(new int[][]{
            {0,0,1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,1,0,0,0,0,0,0,0,0},
            {0,1,0,0,1,1,0,0,1,0,1,0,0},
            {0,1,0,0,1,1,0,0,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0}
        }) == 6;

        assert new Solution().maxAreaOfIsland(new int[][]{
            {0,0,0,0,0,0,0,0}
        }) == 0;
    }

}
