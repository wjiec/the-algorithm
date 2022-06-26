package weekly.w299.B;

import java.util.Arrays;

/**
 * 6100. Count Number of Ways to Place Houses
 *
 * https://leetcode.cn/contest/weekly-contest-299/problems/count-number-of-ways-to-place-houses/
 *
 * There is a street with n * 2 plots, where there are n plots on each side of the street. The plots
 * on each side are numbered from 1 to n. On each plot, a house can be placed.
 *
 * Return the number of ways houses can be placed such that no two houses are adjacent to each
 * other on the same side of the street. Since the answer may be very large, return it modulo 109 + 7.
 *
 * Note that if a house is placed on the ith plot on one side of the street, a house can also be
 * placed on the ith plot on the other side of the street.
 */

public class Solution {

    public int countHousePlacements(int n) {
        int MOD = 1_000_000_007;
        // 0 - no set
        // 1 - up
        // 2 - down
        // 3 - up and down
        int[][] dp = new int[n][4];
        Arrays.fill(dp[0], 1);

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][0] = (dp[i][0] + dp[i - 1][1]) % MOD;
            dp[i][0] = (dp[i][0] + dp[i - 1][2]) % MOD;
            dp[i][0] = (dp[i][0] + dp[i - 1][3]) % MOD;

            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;

            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;

            dp[i][3] = dp[i - 1][0];
        }

        int ans = 0;
        ans = (ans + dp[n - 1][0]) % MOD;
        ans = (ans + dp[n - 1][1]) % MOD;
        ans = (ans + dp[n - 1][2]) % MOD;
        ans = (ans + dp[n - 1][3]) % MOD;

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countHousePlacements(1) == 4;
        assert new Solution().countHousePlacements(2) == 9;
        assert new Solution().countHousePlacements(3) == 25;
    }

}
