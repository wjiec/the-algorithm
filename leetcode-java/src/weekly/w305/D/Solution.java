package weekly.w305.D;

/**
 * 6138. Longest Ideal Subsequence
 *
 * https://leetcode.cn/contest/weekly-contest-305/problems/longest-ideal-subsequence/
 *
 * You are given a string s consisting of lowercase letters and an integer k.
 * We call a string t ideal if the following conditions are satisfied:
 *
 * t is a subsequence of the string s.
 * The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
 * Return the length of the longest ideal string.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters
 * without changing the order of the remaining characters.
 *
 * Note that the alphabet order is not cyclic. For example, the absolute difference in the
 * alphabet order of 'a' and 'z' is 25, not 1.
 */

public class Solution {

    public int longestIdealString(String s, int k) {
        int[] dp = new int[128];
        for (var c : s.toCharArray()) {
            int count = 0;
            char start = (char) Math.max('a', c - k);
            char end = (char) Math.min('z', c + k);
            for (char i = start; i <= end; i++) {
                count = Math.max(count, dp[i]);
            }
            dp[c] = count + 1;
        }

        int ans = 0;
        for (int i = 'a'; i <= 'z'; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
