package problem.p2466countwaystobuildgoodstrings;

/**
 * 6238. Count Ways To Build Good Strings
 *
 * https://leetcode.cn/problems/count-ways-to-build-good-strings/
 *
 * Given the integers zero, one, low, and high, we can construct a string by starting
 * with an empty string, and then at each step perform either of the following:
 *
 * Append the character '0' zero times.
 * Append the character '1' one times.
 * This can be performed any number of times.
 *
 * A good string is a string constructed by the above process having a length
 * between low and high (inclusive).
 *
 * Return the number of different good strings that can be constructed satisfying
 * these properties. Since the answer can be large, return it modulo 109 + 7.
 */

public class Solution {

    public int countGoodStrings(int low, int high, int zero, int one) {
        int MOD = 1_000_000_007, ans = 0;
        int[] dp = new int[high + 1]; dp[0] = 1;
        for (int i = 1; i <= high; i++) {
            if (i - one >= 0) dp[i] = (dp[i] + dp[i - one]) % MOD;
            if (i - zero >= 0) dp[i] = (dp[i] + dp[i - zero]) % MOD;
            if (i >= low && i <= high) ans = (ans + dp[i]) % MOD;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countGoodStrings(3, 3, 1, 1) == 8;
        assert new Solution().countGoodStrings(2, 3, 1, 2) == 5;
    }

}
