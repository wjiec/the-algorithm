package weekly.w299.A;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 6101. Check if Matrix Is X-Matrix
 *
 * https://leetcode.cn/contest/weekly-contest-299/problems/check-if-matrix-is-x-matrix/
 *
 * A square matrix is said to be an X-Matrix if both of the following conditions hold:
 *
 * All the elements in the diagonals of the matrix are non-zero.
 * All other elements are 0.
 * Given a 2D integer array grid of size n x n representing a square matrix, return true
 * if grid is an X-Matrix. Otherwise, return false.
 */

public class Solution {

    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        Set<Point> points = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (grid[i][i] == 0) return false;
            points.add(new Point(i, i));
        }

        for (int i = 0; i < grid.length; i++) {
            if (grid[i][n - i - 1] == 0) return false;
            points.add(new Point(i, n - i - 1));
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!points.contains(new Point(i, j))) {
                    if (grid[i][j] != 0) return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
