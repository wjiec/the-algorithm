package problem.p463islandperimeter;

/**
 * 463. Island Perimeter
 *
 * https://leetcode-cn.com/problems/island-perimeter/
 *
 * You are given row x col grid representing a map where grid[i][j] = 1
 * represents land and grid[i][j] = 0 represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally).
 * The grid is completely surrounded by water,
 * and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island.
 * One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100.
 * Determine the perimeter of the island.
 */

public class Solution {

    public int islandPerimeter(int[][] grid) {
        int cnt = 0, rows = grid.length, cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    cnt += 4;

                    // top
                    if (i > 0 && grid[i - 1][j] == 1) {
                        cnt -= 2;
                    }

                    // left
                    if (j > 0 && grid[i][j - 1] == 1) {
                        cnt -= 2;
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        assert new Solution().islandPerimeter(new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0}, {1,1,0,0}}) == 16;
        assert new Solution().islandPerimeter(new int[][]{{1}}) == 4;
        assert new Solution().islandPerimeter(new int[][]{{1,0}}) == 4;
    }

}
