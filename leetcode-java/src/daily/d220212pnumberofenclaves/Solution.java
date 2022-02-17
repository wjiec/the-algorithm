package daily.d220212pnumberofenclaves;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1020. Number of Enclaves
 *
 * https://leetcode-cn.com/problems/number-of-enclaves/
 *
 * You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
 *
 * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or
 * walking off the boundary of the grid.
 *
 * Return the number of land cells in grid for which we cannot walk off
 * the boundary of the grid in any number of moves.
 */

public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numEnclaves(int[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    ans += walk(grid, i, j);
                }
            }
        }
        return ans;
    }

    private int walk(int[][] grid, int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        int m = grid.length, n = grid[0].length;

        int count = 0;
        boolean boundary = false;
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] val = queue.remove();

            x = val[0]; y = val[1];
            if (grid[x][y] == 1) {
                count++;
                boundary = boundary || (x == 0 || y == 0 || x == m - 1 || y == n - 1);

                grid[x][y] = 0;
                for (var dir : dirs) {
                    int a = x + dir[0], b = y + dir[1];
                    if (a >= 0 && a < m && b >= 0 && b < n && grid[a][b] == 1) {
                        queue.add(new int[]{a, b});
                    }
                }
            }
        }
        return boundary ? 0 : count;
    }

    public static void main(String[] args) {
        assert new Solution().numEnclaves(new int[][]{
            {0,0,0,1,1,1,0,1,1,1,1,1,0,0,0},
            {1,1,1,1,0,0,0,1,1,0,0,0,1,1,1},
            {1,1,1,0,0,1,0,1,1,1,0,0,0,1,1},
            {1,1,0,1,0,1,1,0,0,0,1,1,0,1,0},
            {1,1,1,1,0,0,0,1,1,1,0,0,0,1,1},
            {1,0,1,1,0,0,1,1,1,1,1,1,0,0,0},
            {0,1,0,0,1,1,1,1,0,0,1,1,1,0,0},
            {0,0,1,0,0,0,0,1,1,0,0,1,0,0,0},
            {1,0,1,0,0,1,0,0,0,0,0,0,1,0,1},
            {1,1,1,0,1,0,1,0,1,1,1,0,0,1,0}
        }) == 27;

        assert new Solution().numEnclaves(new int[][]{{0,0,0,0}, {1,0,1,0}, {0,1,1,0}, {0,0,0,0}}) == 3;
        assert new Solution().numEnclaves(new int[][]{{0,1,1,0}, {0,0,1,0}, {0,0,1,0}, {0,0,0,0}}) == 0;
    }

}
