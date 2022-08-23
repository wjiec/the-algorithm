package problem.p1594maximumnonnegativeproductinamatrix;

/**
 * 1594. Maximum Non Negative Product in a Matrix
 *
 * https://leetcode.cn/problems/maximum-non-negative-product-in-a-matrix/
 *
 * You are given a m x n matrix grid. Initially, you are located at the top-left
 * corner (0, 0), and in each step, you can only move right or down in the matrix.
 *
 * Among all possible paths starting from the top-left corner (0, 0) and ending in the
 * bottom-right corner (m - 1, n - 1), find the path with the maximum non-negative product.
 *
 * The product of a path is the product of all integers in the grid cells visited along the path.
 *
 * Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative, return -1.
 *
 * Notice that the modulo is performed after getting the maximum product.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] po = new long[m][n], ne = new long[m][n];

        po[0][0] = ne[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) po[0][i] = ne[0][i] = po[0][i - 1] * grid[0][i];
        for (int i = 1; i < m; i++) po[i][0] = ne[i][0] = po[i - 1][0] * grid[i][0];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] >= 0) {
                    po[i][j] = Math.max(po[i - 1][j] * grid[i][j], po[i][j - 1] * grid[i][j]);
                    ne[i][j] = Math.min(ne[i - 1][j] * grid[i][j], ne[i][j - 1] * grid[i][j]);
                } else {
                    po[i][j] = Math.max(ne[i - 1][j] * grid[i][j], ne[i][j - 1] * grid[i][j]);
                    ne[i][j] = Math.min(po[i - 1][j] * grid[i][j], po[i][j - 1] * grid[i][j]);
                }
            }
        }
        return Math.max((int) (po[m - 1][n - 1] % 1_000_000_007), -1);
    }

    public static void main(String[] args) {
        assert new Solution().maxProductPath(new int[][]{
            {-1,-2,-3},
            {-2,-3,-3},
            {-3,-3,-2}
        }) == -1;

        assert new Solution().maxProductPath(new int[][]{
            {1,-2,1},
            {1,-2,1},
            {3,-4,1}
        }) == 8;

        assert new Solution().maxProductPath(new int[][]{
            {1, 3},
            {0,-4}
        }) == 0;

        assert new Solution().maxProductPath(new int[][]{
            { 1, 4,4,0},
            {-2, 0,0,1},
            { 1,-1,1,1}
        }) == 2;
    }

}
