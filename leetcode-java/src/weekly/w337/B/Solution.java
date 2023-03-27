package weekly.w337.B;

/**
 * 2596. Check Knight Tour Configuration
 *
 * https://leetcode.cn/problems/check-knight-tour-configuration/
 *
 * There is a knight on an n x n chessboard. In a valid configuration, the knight
 * starts at the top-left cell of the board and visits every cell on the board exactly once.
 *
 * You are given an n x n integer matrix grid consisting of distinct integers
 * from the range [0, n * n - 1] where grid[row][col] indicates that the
 * cell (row, col) is the grid[row][col]th cell that the knight visited.
 *
 * The moves are 0-indexed.
 *
 * Return true if grid represents a valid configuration of the knight's movements or false otherwise.
 *
 * Note that a valid knight move consists of moving two squares vertically and one square
 * horizontally, or two squares horizontally and one square vertically.
 *
 * The figure below illustrates all the possible eight moves of a knight from some cell.
 */

public class Solution {

    private final int[][] dir8 = new int[][]{
        {2, 1}, {2, -1},
        {-2, 1}, {-2, -1},
        {1, 2}, {1, -2},
        {-1, 2}, {-1, -2}
    };

    public boolean checkValidGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] != 0) return false;

        int x = 0, y = 0, c = m * n;
        while (true) {
            int curr = grid[x][y];
            if (curr == c - 1) break;
            boolean found = false;
            for (var dir : dir8) {
                int dx = x + dir[0], dy = y + dir[1];
                found = dx >= 0 && dx < m && dy >= 0 && dy < n && grid[dx][dy] == curr + 1;
                if (found) { x = dx; y = dy; break; }
            }
            if (!found) return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
