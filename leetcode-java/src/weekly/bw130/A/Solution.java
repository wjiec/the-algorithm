package weekly.bw130.A;

/**
 * 100299. Check if Grid Satisfies Conditions
 *
 * https://leetcode.cn/contest/biweekly-contest-130/problems/check-if-grid-satisfies-conditions/
 *
 * You are given a 2D matrix grid of size m x n. You need to check if each cell grid[i][j] is:
 *
 * Equal to the cell below it, i.e. grid[i][j] == grid[i + 1][j] (if it exists).
 * Different from the cell to its right, i.e. grid[i][j] != grid[i][j + 1] (if it exists).
 *
 * Return true if all the cells satisfy these conditions, otherwise, return false.
 */

public class Solution {

    public boolean satisfiesConditions(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < m && grid[i][j] != grid[i + 1][j]) return false;
                if (j + 1 < n && grid[i][j] == grid[i][j + 1]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
