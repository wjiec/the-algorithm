package problem.p840magicsquaresingrid;

/**
 * 840. Magic Squares In Grid
 *
 * https://leetcode.cn/problems/magic-squares-in-grid/
 *
 * A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column,
 * and both diagonals all have the same sum.
 *
 * Given a row x col grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).
 */

public class Solution {

    public int numMagicSquaresInside(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m < 3 || n < 3) return 0;

        int ans = 0;
        for (int i = 0; i < m - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                if (grid[i + 1][j + 1] != 5) continue;

                if (isMagic(
                    grid[i    ][j], grid[i    ][j + 1], grid[i    ][j + 2],
                    grid[i + 1][j], grid[i + 1][j + 1], grid[i + 1][j + 2],
                    grid[i + 2][j], grid[i + 2][j + 1], grid[i + 2][j + 2]
                )) ans++;
            }
        }

        return ans;
    }

    private boolean isMagic(int a, int b, int c,
                            int d, int e, int f,
                            int g, int h, int i) {
        int v = 1 << a | 1 << b | 1 << c
            |   1 << d | 1 << e | 1 << f
            |   1 << g | 1 << h | 1 << i;
        if ((v ^ ((1 << 10) - 2)) != 0) return false;

        return a + b + c == 15
            && d + e + f == 15
            && g + h + i == 15
            && a + d + g == 15
            && b + e + h == 15
            && c + f + i == 15
            && a + e + i == 15
            && c + e + g == 15;
    }

    public static void main(String[] args) {
        assert new Solution().numMagicSquaresInside(new int[][]{
            {5,5,5},
            {5,5,5},
            {5,5,5},
        }) == 0;

        assert new Solution().numMagicSquaresInside(new int[][]{
            {4,3,8,4},
            {9,5,1,9},
            {2,7,6,2}
        }) == 1;

        assert new Solution().numMagicSquaresInside(new int[][]{
            {8}
        }) == 0;
    }

}
