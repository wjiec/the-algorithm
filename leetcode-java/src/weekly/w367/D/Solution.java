package weekly.w367.D;

import common.PrettyPrinter;

/**
 * 2906. Construct Product Matrix
 *
 * https://leetcode.cn/contest/weekly-contest-367/problems/construct-product-matrix/
 *
 * Given a 0-indexed 2D integer matrix grid of size n * m, we define a 0-indexed 2D
 * matrix p of size n * m as the product matrix of grid if the following condition is met:
 *
 * Each element p[i][j] is calculated as the product of all elements in grid except
 * for the element grid[i][j]. This product is then taken modulo 12345.
 *
 * Return the product matrix of grid.
 */

public class Solution {

    public int[][] constructProductMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m][n];

        long suffix = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                ans[i][j] = (int) suffix;
                suffix = (suffix * grid[i][j]) % 12345;
            }
        }

        long prefix = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = (int) ((ans[i][j] * prefix) % 12345);
                prefix = (prefix * grid[i][j]) % 12345;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().constructProductMatrix(new int[][]{
            {4,3,9},
            {3,9,10},
            {9,7,8},
            {8,4,7},
            {6,1,3}
        }));
    }

}
