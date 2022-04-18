package problem.p161oneeditdistance;

/**
 * 161. One Edit Distance
 *
 * https://leetcode-cn.com/problems/one-edit-distance/
 *
 * Given two strings s and t, return true if they are both one edit distance apart,
 * otherwise return false.
 *
 * A string s is said to be one distance apart from a string t if you can:
 *
 * Insert exactly one character into s to get t.
 * Delete exactly one character from s to get t.
 * Replace exactly one character of s with a different character to get t.
 */

public class Solution {

    public boolean isOneEditDistance(String s, String t) {
        int a = s.length(), b = t.length();
        if (a < b) return isOneEditDistance(t, s);
        if (a - b > 1) return false;

        int diff = 0;
        for (int i = 0, j = 0; i < a && j < b; i++) {
            if (s.charAt(i) != t.charAt(j)) {
                if (++diff > 1) return false;
                if (a != b) continue;
            }

            j++;
        }
        return diff == 1 || a != b;
    }

    public static void main(String[] args) {
        assert new Solution().isOneEditDistance("cb", "ab");

        assert new Solution().isOneEditDistance("ab", "acb");
        assert !new Solution().isOneEditDistance("", "");
        assert new Solution().isOneEditDistance("a", "ab");
        assert new Solution().isOneEditDistance("ab", "b");
        assert new Solution().isOneEditDistance("", "a");
        assert !new Solution().isOneEditDistance("aa", "acc");
    }

}
