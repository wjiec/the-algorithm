package problem.p1392longesthappyprefix;

/**
 * 1392. Longest Happy Prefix
 *
 * https://leetcode.cn/problems/longest-happy-prefix/
 *
 * A string is called a happy prefix if is a non-empty prefix which is also a suffix (excluding itself).
 *
 * Given a string s, return the longest happy prefix of s. Return an empty string "" if no such prefix exists.
 */

public class Solution {

    public String longestPrefix(String s) {
        int[] next = new int[s.length()];
        for (int i = 1, j = 0; i < s.length(); ) {
            if (s.charAt(i) == s.charAt(j)) {
                next[i++] = ++j;
            } else {
                if (j != 0) j = next[j - 1];
                else next[i++] = 0;
            }
        }

        return s.substring(0, next[s.length() - 1]);
    }

    public static void main(String[] args) {
        assert new Solution().longestPrefix("bba").equals("");

        assert new Solution().longestPrefix("level").equals("l");
        assert new Solution().longestPrefix("ababab").equals("abab");
    }

}
