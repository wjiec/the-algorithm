package weekly.bw117.C;

/**
 * 2930. Number of Strings Which Can Be Rearranged to Contain Substring
 *
 * https://leetcode.cn/contest/biweekly-contest-117/problems/number-of-strings-which-can-be-rearranged-to-contain-substring/
 *
 * You are given an integer n.
 *
 * A string s is called good if it contains only lowercase English characters and
 * it is possible to rearrange the characters of s such that the new string
 * contains "leet" as a substring.
 *
 * For example:
 *
 * The string "lteer" is good because we can rearrange it to form "leetr" .
 * "letl" is not good because we cannot rearrange it to contain "leet" as a substring.
 * Return the total number of good strings of length n.
 *
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

public class Solution {

    public int stringCount(int n) {
        long ans = pow(26, n)
            - pow(25, n - 1) * (75 + n)
            + pow(24, n - 1) * (72 + n * 2L)
            - pow(23, n - 1) * (23 + n);
        return (int) (((ans % MOD) + MOD) % MOD);
    }

    final long MOD = 1_000_000_007;

    private long pow(long x, long k) {
        long ans = 1, base = x;
        for (int i = 0; i < 31; i++) {
            if ((k & (1 << i)) != 0) {
                ans = (ans * base) % MOD;
            }
            base = (base * base) % MOD;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().stringCount(7) == 6058192;
    }

}
