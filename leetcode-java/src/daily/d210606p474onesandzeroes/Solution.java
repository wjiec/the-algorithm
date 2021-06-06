package daily.d210606p474onesandzeroes;

/**
 * 474. Ones and Zeroes
 *
 * https://leetcode-cn.com/problems/ones-and-zeroes/
 *
 * You are given an array of binary strings strs and two integers m and n.
 *
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 *
 * A set x is a subset of a set y if all elements of x are also elements of y.
 */

public class Solution {

    // @TODO dp
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            int one = countOne(strs[i - 1]), zero = strs[i - 1].length() - one;
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zero && k >= one) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zero][k - one] + 1);
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    private int countOne(String s) {
        int one = 0;
        for (var c : s.toCharArray()) {
            one += c - '0';
        }
        return one;
    }

    public static void main(String[] args) {
        assert new Solution().findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3) == 4;
        assert new Solution().findMaxForm(new String[]{"10", "0", "1"}, 1, 1) == 2;
    }

}
