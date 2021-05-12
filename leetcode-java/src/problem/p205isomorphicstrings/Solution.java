package problem.p205isomorphicstrings;

/**
 * 205. Isomorphic Strings
 *
 * https://leetcode-cn.com/problems/isomorphic-strings/
 *
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 */

public class Solution {

    public boolean isIsomorphic(String s, String t) {
        return doIsomorphic(s, t) && doIsomorphic(t, s);
    }

    private boolean doIsomorphic(String s, String t) {
        char[] chars = new char[255];
        for (int i = 0; i < s.length(); i++) {
            if (chars[s.charAt(i)] == 0) {
                chars[s.charAt(i)] = t.charAt(i);
            } else if (chars[s.charAt(i)] != t.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().isIsomorphic("egg", "add");
        assert !new Solution().isIsomorphic("foo", "bar");
        assert new Solution().isIsomorphic("paper", "title");
        assert !new Solution().isIsomorphic("bcda", "baba");
    }

}
