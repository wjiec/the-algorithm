package problem.p392issubsequence;

/**
 * 392. Is Subsequence
 *
 * https://leetcode-cn.com/problems/is-subsequence/
 *
 * Given two strings s and t, check if s is a subsequence of t.
 *
 * A subsequence of a string is a new string that is formed from the original string by
 * deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
 * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 */

public class Solution {

    public boolean isSubsequence(String s, String t) {
        int l = 0, sz1 = s.length(), sz2 = t.length();
        if (sz1 == 0 || sz2 == 0) {
            return sz1 == 0;
        }

        for (int i = 0; i < sz2; i++) {
            if (s.charAt(l) == t.charAt(i)) {
                l++;
            }
            if (l == sz1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().isSubsequence("abc", "ahbgdc");
        assert new Solution().isSubsequence("abc", "ahbgdc");
        assert new Solution().isSubsequence("", "ahbgdc");
    }

}
