package weekly.w423.D;

import java.util.HashMap;
import java.util.Map;

/**
 * 3352. Count K-Reducible Numbers Less Than N
 *
 * https://leetcode.cn/contest/weekly-contest-423/problems/count-k-reducible-numbers-less-than-n/
 *
 * You are given a binary string s representing a number n in its binary form.
 *
 * You are also given an integer k.
 *
 * An integer x is called k-reducible if performing the following operation at most k times reduces it to 1:
 *
 * Replace x with the count of set bits in its binary representation.
 * For example, the binary representation of 6 is "110". Applying the operation once
 * reduces it to 2 (since "110" has two set bits). Applying the operation again to 2 (binary "10")
 * reduces it to 1 (since "10" has one set bit).
 *
 * Return an integer denoting the number of positive integers less than n that are k-reducible.
 *
 * Since the answer may be too large, return it modulo 109 + 7.
 */

public class Solution {

    public int countKReducibleNumbers(String s, int k) {
        return dfs(s.toCharArray(), 0, 0, true, k - 1);
    }

    private final Map<Integer, Integer> memo = new HashMap<>();

    private int dfs(char[] bits, int i, int ones, boolean limited, int k) {
        // 要求小于 n, 所以这里在判断的时候不能出现全是 limited 的情况(也就是和 n 一样)
        if (i == bits.length) return !limited && reducible(ones, k) ? 1 : 0;

        int key = (i << 16) | ones;
        if (!limited && memo.containsKey(key)) return memo.get(key);

        long ans = 0;
        if (!limited || bits[i] == '1') {
            ans += dfs(bits, i + 1, ones, false, k); // use 0
            ans += dfs(bits, i + 1, ones + 1, limited && bits[i] == '1', k); // use 1
        } else ans += dfs(bits, i + 1, ones, true, k); // use 0 only

        ans %= 1_000_000_007;
        if (!limited) memo.put(key, (int) ans);
        return (int) ans;
    }

    private final Boolean[] isReducible = new Boolean[1000];

    private boolean reducible(int n, int k) {
        if (isReducible[n] != null) return isReducible[n];

        for (int i = 0, v = n; i < k; i++) {
            if ((v = Integer.bitCount(v)) == 1) {
                return isReducible[n] = true;
            }
        }

        return isReducible[n] = (n == 1);
    }

    private static class Iteration {
        public int countKReducibleNumbers(String s, int k) {
            final int n = s.length(), MOD = 1_000_000_007;
            int[][][] dp = new int[n + 1][n + 2][2];
            for (int j = 1; j <= n; j++) {
                int v = j, c = k - 1;
                while (--c >= 0) v = Integer.bitCount(v);
                dp[n][j][0] = v == 1 ? 1 : 0;
            }

            for (int i = n - 1; i >= 0; i--) {
                char curr = s.charAt(i);
                for (int j = 0; j <= n; j++) {
                    // unlimited
                    dp[i][j][0] = (dp[i + 1][j][0] + dp[i + 1][j + 1][0]) % MOD;
                    // limited
                    dp[i][j][1] = (dp[i + 1][j][curr == '0' ? 1 : 0] + (curr == '1' ? dp[i + 1][j + 1][1] : 0)) % MOD;
                }
            }

            return dp[0][0][1]; // 递归入口
        }
    }

    public static void main(String[] args) {
        assert new Solution().countKReducibleNumbers("1000", 2) == 6;
        assert new Solution().countKReducibleNumbers("111", 1) == 3;
        assert new Solution().countKReducibleNumbers("1", 3) == 0;

        assert new Iteration().countKReducibleNumbers("1000", 2) == 6;
        assert new Iteration().countKReducibleNumbers("111", 1) == 3;
        assert new Iteration().countKReducibleNumbers("1", 3) == 0;
    }

}
