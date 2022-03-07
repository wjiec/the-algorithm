package problem.p200numberofislands;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 200. Number of Islands
 *
 * https://leetcode-cn.com/problems/number-of-islands/
 *
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent
 * lands horizontally or vertically.
 *
 * You may assume all four edges of the grid are all surrounded by water.
 */

public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numIslands(char[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    ans++; bfsMark(grid, i, j);
                }
            }
        }
        return ans;
    }

    private void bfsMark(char[][] grid, int x, int y) {
        grid[x][y] = '.';
        Queue<int[]> queue = new ArrayDeque<>();
        for (queue.add(new int[]{x, y}); !queue.isEmpty(); ) {
            int[] curr = queue.remove();
            for (var dir : dirs) {
                int a = dir[0] + curr[0], b = dir[1] + curr[1];
                if (a >= 0 && a < grid.length && b >= 0 && b < grid[0].length && grid[a][b] == '1') {
                    grid[a][b] = '.';
                    queue.add(new int[]{a, b});
                }
            }
        }
    }

    public static void main(String[] args) {
        assert new Solution().numIslands(new char[][]{
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        }) == 1;

        assert new Solution().numIslands(new char[][]{
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        }) == 3;
    }

}
