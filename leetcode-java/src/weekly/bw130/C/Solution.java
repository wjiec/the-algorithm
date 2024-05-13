package weekly.bw130.C;

import java.util.Arrays;

/**
 * 100289. Minimum Substring Partition of Equal Character Frequency
 *
 * https://leetcode.cn/contest/biweekly-contest-130/problems/minimum-substring-partition-of-equal-character-frequency/
 *
 * Given a string s, you need to partition it into one or more balanced substrings.
 * For example, if s == "ababcc" then ("abab", "c", "c"), ("ab", "abc", "c"), and ("ababcc") are all
 * valid partitions, but ("a", "bab", "cc"), ("aba", "bc", "c"), and ("ab", "abcc") are not.
 *
 * Return the minimum number of substrings that you can partition s into.
 *
 * Note: A balanced string is a string where each character in the string occurs the same number of times.
 */

public class Solution {

    public int minimumSubstringsInPartition(String s) {
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length + 1];
        Arrays.fill(dp, chars.length); dp[0] = 0;
        for (int i = 0; i <= chars.length; i++) {
            int[] freq = new int[26];
            for (int j = i + 1; j <= chars.length; j++) {
                freq[chars[j - 1] - 'a']++;
                if (balanced(freq)) dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[chars.length];
    }

    private boolean balanced(int[] freq) {
        int val = 0;
        for (var v : freq) {
            if (v != 0 && val == 0) val = v;
            if (v != 0 && v != val) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().minimumSubstringsInPartition("fabccddg") == 3;
        assert new Solution().minimumSubstringsInPartition("abababaccddb") == 2;
    }

}
