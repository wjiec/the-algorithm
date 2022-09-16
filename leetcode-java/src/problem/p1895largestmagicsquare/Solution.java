package problem.p1895largestmagicsquare;

/**
 * 1895. Largest Magic Square
 *
 * https://leetcode.cn/problems/largest-magic-square/
 *
 * A k x k magic square is a k x k grid filled with integers such that every row sum, every
 * column sum, and both diagonal sums are all equal. The integers in the magic square do not
 * have to be distinct. Every 1 x 1 grid is trivially a magic square.
 *
 * Given an m x n integer grid, return the size (i.e., the side length k) of the largest
 * magic square that can be found within this grid.
 */

public class Solution {

    private int[][] rows, cols;

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        rows = new int[m + 1][n + 1];
        cols = new int[m + 1][n + 1];

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                rows[i][j] = rows[i][j - 1] + grid[i - 1][j - 1];
                cols[i][j] = cols[i - 1][j] + grid[i - 1][j - 1];
            }
        }

        for (int k = Math.min(m, n); k > 1; --k) {
            for (int lx = 0; lx + k - 1 < m; ++lx) {
                for (int ly = 0; ly + k - 1 < n; ++ly) {
                    int rx = lx + k - 1, ry = ly + k - 1;
                    if (check(grid, lx, ly, rx, ry)) {
                        return k;
                    }
                }
            }
        }
        return 1;
    }

    private boolean check(int[][] grid, int x1, int y1, int x2, int y2) {
        int sum = rows[x1 + 1][y2 + 1] - rows[x1 + 1][y1];
        for (int i = x1 + 1; i <= x2; ++i) {
            if (rows[i + 1][y2 + 1] - rows[i + 1][y1] != sum) {
                return false;
            }
        }

        for (int j = y1; j <= y2; ++j) {
            if (cols[x2 + 1][j + 1] - cols[x1][j + 1] != sum) {
                return false;
            }
        }

        int s1 = 0;
        for (int i = x1, j = y1; i <= x2; ++i, ++j) {
            s1 += grid[i][j];
        }
        if (s1 != sum) return false;

        int s2 = 0;
        for (int i = x1, j = y2; i <= x2; ++i, --j) {
            s2 += grid[i][j];
        }

        return s2 == sum;
    }

    public static void main(String[] args) {
        assert new Solution().largestMagicSquare(new int[][]{{7,1,4,5,6},{2,5,1,6,4},{1,5,4,3,2},{1,2,7,3,4}}) == 3;
        assert new Solution().largestMagicSquare(new int[][]{{5,1,3,1},{9,3,3,1},{1,3,3,8}}) == 2;
    }

}
