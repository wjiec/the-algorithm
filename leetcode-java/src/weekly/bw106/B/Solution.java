package weekly.bw106.B;

/**
 * 2730. Find the Longest Semi-Repetitive Substring
 *
 * https://leetcode.cn/contest/biweekly-contest-106/problems/find-the-longest-semi-repetitive-substring/
 *
 * You are given a 0-indexed string s that consists of digits from 0 to 9.
 *
 * A string t is called a semi-repetitive if there is at most one consecutive pair of the
 * same digits inside t. For example, 0010, 002020, 0123, 2002, and 54944 are semi-repetitive
 * while 00101022, and 1101234883 are not.
 *
 * Return the length of the longest semi-repetitive substring inside s.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 */

public class Solution {

    public int longestSemiRepetitiveSubstring(String s) {
        int ans = 0, n = s.length();
        if (n == 1) return n;

        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1, d = 0; j < n; j++) {
                if (chars[j] == chars[j - 1]) {
                    if (++d == 2) break;
                }
                ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestSemiRepetitiveSubstring("52233") == 4;
        assert new Solution().longestSemiRepetitiveSubstring("5494") == 4;
        assert new Solution().longestSemiRepetitiveSubstring("1111111") == 2;
    }

}
