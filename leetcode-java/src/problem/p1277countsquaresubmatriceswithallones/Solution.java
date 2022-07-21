package problem.p1277countsquaresubmatriceswithallones;

/**
 * 1277. Count Square Submatrices with All Ones
 *
 * https://leetcode.cn/problems/count-square-submatrices-with-all-ones/
 *
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 */

public class Solution {

    public int countSquares(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, ans = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
                ans += dp[i][j];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countSquares(new int[][]{
            {0,1,1,1},
            {1,1,1,1},
            {0,1,1,1}
        }) == 15;

        assert new Solution().countSquares(new int[][]{
            {1,0,1},
            {1,1,0},
            {1,1,0}
        }) == 7;
    }

}
