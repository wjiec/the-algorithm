package problem.p221maximalsquare;

/**
 * 221. Maximal Square
 *
 * https://leetcode-cn.com/problems/maximal-square/
 *
 * Given an m x n binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 */

public class Solution {

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n]; int edge = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != '0') {
                    if (i != 0 && j != 0) {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    } else dp[i][j] = 1;

                    edge = Math.max(edge, dp[i][j]);
                }
            }
        }
        return edge *edge;
    }

    public static void main(String[] args) {
        assert new Solution().maximalSquare(new char[][]{
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        }) == 4;

        assert new Solution().maximalSquare(new char[][]{
            {'0','1'},
            {'1','0'}
        }) == 1;

        assert new Solution().maximalSquare(new char[][]{
            {'0'}
        }) == 0;
    }

}
