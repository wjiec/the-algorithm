package daily.d221014p940distinctsubsequencesii;

import common.TODO;
import common.Template;

import java.util.Arrays;

/**
 * 940. Distinct Subsequences II
 *
 * https://leetcode.cn/problems/distinct-subsequences-ii/
 *
 * Given a string s, return the number of distinct non-empty subsequences of s.
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 * A subsequence of a string is a new string that is formed from the original string
 * by deleting some (can be none) of the characters without disturbing the relative
 * positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde"
 * while "aec" is not.
 */

public class Solution {

    @TODO(tips = "DP")
    @Template("不同子序列数量")
    public int distinctSubseqII(String s) {
        int[] last = new int[128];
        Arrays.fill(last, -1);

        int[] dp = new int[s.length()];
        Arrays.fill(dp, 1);

        int MOD = 1_000_000_007;
        for (int i = 0; i < s.length(); i++) {
            for (int c = 'a'; c <= 'z'; c++) {
                if (last[c] != -1) {
                    dp[i] = (dp[i] + dp[last[c]]) % MOD;
                }
            }
            last[s.charAt(i)] = i;
        }

        int ans = 0;
        for (int c = 'a'; c <= 'z'; c++) {
            if (last[c] != -1) {
                ans = (ans + dp[last[c]]) % MOD;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().distinctSubseqII("abc") == 7;
        assert new Solution().distinctSubseqII("aba") == 6;
        assert new Solution().distinctSubseqII("aaa") == 3;
    }

}
