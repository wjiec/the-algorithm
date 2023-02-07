package weekly.bw97.D;

import common.TODO;

/**
 * 2556. Disconnect Path in a Binary Matrix by at Most One Flip
 *
 * https://leetcode.cn/problems/disconnect-path-in-a-binary-matrix-by-at-most-one-flip/
 *
 * You are given a 0-indexed m x n binary matrix grid. You can move from a cell (row, col)
 * to any of the cells (row + 1, col) or (row, col + 1) that has the value 1.
 *
 * The matrix is disconnected if there is no path from (0, 0) to (m - 1, n - 1).
 *
 * You can flip the value of at most one (possibly none) cell.
 * You cannot flip the cells (0, 0) and (m - 1, n - 1).
 *
 * Return true if it is possible to make the matrix disconnect or false otherwise.
 *
 * Note that flipping a cell changes its value from 0 to 1 or from 1 to 0.
 */

public class Solution {

    private int m = 0, n = 0;

    @TODO
    public boolean isPossibleToCutPath(int[][] grid) {
        m = grid.length; n = grid[0].length;
        return !dfs(grid, 0, 0) || !dfs(grid, 0, 0);
    }

    private boolean dfs(int[][] grid, int x, int y) {
        if (x == m - 1 && y == n - 1) return true;

        grid[x][y] = 0;
        if (x + 1 < m && grid[x + 1][y] == 1 && dfs(grid, x + 1, y)) return true;
        if (y + 1 < n && grid[x][y + 1] == 1 && dfs(grid, x, y + 1)) return true;

        return false;
    }

    public static void main(String[] args) {
        assert new Solution().isPossibleToCutPath(new int[][]{
            {1,1,1,1,1,1},
            {1,0,1,1,1,1},
            {1,1,1,0,1,1},
            {0,0,0,1,1,1}
        });

        assert new Solution().isPossibleToCutPath(new int[][]{
            {1,1,1},
            {1,1,0},
            {1,1,1}
        });

        assert new Solution().isPossibleToCutPath(new int[][]{
            {1,1,1,0,0},
            {1,0,1,0,0},
            {1,1,1,1,1},
            {0,0,1,1,1},
            {0,0,1,1,1}
        });

        assert new Solution().isPossibleToCutPath(new int[][]{
            {1,1,1},
            {0,0,0},
            {1,1,1}
        });

        assert new Solution().isPossibleToCutPath(new int[][]{
            {1,1,1},
            {1,0,0},
            {1,1,1}
        });

        assert !new Solution().isPossibleToCutPath(new int[][]{
            {1,1,1},
            {1,0,1},
            {1,1,1}
        });
    }

}
