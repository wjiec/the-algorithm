package problem.p2245maximumtrailingzerosinacorneredpath;

/**
 * 2245. Maximum Trailing Zeros in a Cornered Path
 *
 * https://leetcode.cn/problems/maximum-trailing-zeros-in-a-cornered-path/
 *
 * You are given a 2D integer array grid of size m x n, where each cell contains a positive integer.
 *
 * A cornered path is defined as a set of adjacent cells with at most one turn.
 * More specifically, the path should exclusively move either horizontally or vertically up
 * to the turn (if there is one), without returning to a previously visited cell.
 * After the turn, the path will then move exclusively in the alternate direction: move vertically
 * if it moved horizontally, and vice versa, also without returning to a previously visited cell.
 *
 * The product of a path is defined as the product of all the values in the path.
 *
 * Return the maximum number of trailing zeros in the product of a cornered path found in grid.
 *
 * Note:
 * Horizontal movement means moving in either the left or right direction.
 * Vertical movement means moving in either the up or down direction.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final static int[][] c25 = new int[1001][2];

    static {
        // 预处理：递推算出每个数的因子 2 的个数和因子 5 的个数
        for (var i = 2; i <= 1000; i++) {
            if (i % 2 == 0) c25[i][0] = c25[i / 2][0] + 1;
            if (i % 5 == 0) c25[i][1] = c25[i / 5][1] + 1;
        }
    }

    public int maxTrailingZeros(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        var s = new int[m][n + 1][2];
        for (var i = 0; i < m; i++)
            for (var j = 0; j < n; j++) {
                s[i][j + 1][0] = s[i][j][0] + c25[grid[i][j]][0]; // 每行的因子 2 的前缀和
                s[i][j + 1][1] = s[i][j][1] + c25[grid[i][j]][1]; // 每行的因子 5 的前缀和
            }

        for (var j = 0; j < n; j++) {
            for (int i = 0, s2 = 0, s5 = 0; i < m; i++) { // 从上往下，枚举左拐还是右拐
                s2 += c25[grid[i][j]][0]; s5 += c25[grid[i][j]][1];
                ans = Math.max(ans, Math.max(Math.min(s2 + s[i][j][0], s5 + s[i][j][1]),
                    Math.min(s2 + s[i][n][0] - s[i][j + 1][0], s5 + s[i][n][1] - s[i][j + 1][1])));
            }
            for (int i = m - 1, s2 = 0, s5 = 0; i >= 0; i--) { // 从下往上，枚举左拐还是右拐
                s2 += c25[grid[i][j]][0]; s5 += c25[grid[i][j]][1];
                ans = Math.max(ans, Math.max(Math.min(s2 + s[i][j][0], s5 + s[i][j][1]),
                    Math.min(s2 + s[i][n][0] - s[i][j + 1][0], s5 + s[i][n][1] - s[i][j + 1][1])));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxTrailingZeros(new int[][]{
            {23,17,15,3,20},
            {8,1,20,27,11},
            {9,4,6,2,21},
            {40,9,1,10,6},
            {22,7,4,5,3}
        }) == 3;

        assert new Solution().maxTrailingZeros(new int[][]{
            {4,3,2},
            {7,6,1},
            {8,8,8}
        }) == 0;
    }

}
