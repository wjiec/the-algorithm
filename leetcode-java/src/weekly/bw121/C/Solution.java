package weekly.bw121.C;

import java.util.Arrays;

/**
 * 2998. Minimum Number of Operations to Make X and Y Equal
 *
 * https://leetcode.cn/contest/biweekly-contest-121/problems/minimum-number-of-operations-to-make-x-and-y-equal/
 *
 * You are given two positive integers x and y.
 *
 * In one operation, you can do one of the four following operations:
 *
 * Divide x by 11 if x is a multiple of 11.
 * Divide x by 5 if x is a multiple of 5.
 * Decrement x by 1.
 * Increment x by 1.
 *
 * Return the minimum number of operations required to make x and y equal.
 */

public class Solution {

    public int minimumOperationsToMakeEqual(int x, int y) {
        int[] dp = new int[Math.max(x, y) + 20];
        Arrays.fill(dp, -1);
        for (int i = 0; i < dp.length; i++) dp[i] = Math.abs(y - i);

        for (int i = y + 1; i < dp.length; i++) {
            if (i % 11 == 0 && dp[i / 11] + 1 < dp[i]) {
                dp[i] = dp[i / 11] + 1;
            }
            if (i % 5 == 0 && dp[i / 5] + 1 < dp[i]) {
                dp[i] = dp[i / 5] + 1;
            }

            for (int j = -11; j <= 11; j++) {
                if ((i + j) >= 0 && (i + j) < dp.length) {
                    dp[i] = Math.min(dp[i], dp[i + j] + Math.abs(j));
                    dp[i + j] = Math.min(dp[i + j], dp[i] + Math.abs(j));
                }
            }
        }

        return dp[x];
    }

    public static void main(String[] args) {
        assert new Solution().minimumOperationsToMakeEqual(26, 1) == 3;
        assert new Solution().minimumOperationsToMakeEqual(54, 2) == 4;
        assert new Solution().minimumOperationsToMakeEqual(25, 30) == 5;
    }

}
