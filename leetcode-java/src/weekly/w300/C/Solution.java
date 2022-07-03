package weekly.w300.C;

/**
 * 6109. Number of People Aware of a Secret
 *
 * https://leetcode.cn/contest/weekly-contest-300/problems/number-of-people-aware-of-a-secret/
 *
 * On day 1, one person discovers a secret.
 *
 * You are given an integer delay, which means that each person will share the secret with a new person
 * every day, starting from delay days after discovering the secret.
 *
 * You are also given an integer forget, which means that each person will forget the secret forget days
 * after discovering it. A person cannot share the secret on the same day
 * they forgot it, or on any day afterwards.
 *
 * Given an integer n, return the number of people who know the secret at the end of day n.
 * Since the answer may be very large, return it modulo 109 + 7.
 */

public class Solution {

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int MOD = 1_000_000_007;
        long[] dp = new long[n + 1]; dp[1] = 1;
        for (int i = 1; i + delay <= n; i++) {
            for (int j = i + delay, e = Math.min(n, i + forget - 1); j <= e; j++) {
                dp[j] = (dp[j] + dp[i]) % MOD;
            }
        }

        long ans = 0;
        for (int i = n; i > n - forget; i--) {
            ans = (ans + dp[i]) % MOD;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().peopleAwareOfSecret(6, 2, 4) == 5;
        assert new Solution().peopleAwareOfSecret(4, 1, 3) == 6;
    }

}
