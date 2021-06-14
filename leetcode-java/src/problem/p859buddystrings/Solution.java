package problem.p859buddystrings;

/**
 * 859. Buddy Strings
 *
 * https://leetcode-cn.com/problems/buddy-strings/
 *
 * Given two strings s and goal, return true if you can swap two letters
 * in s so the result is equal to goal, otherwise, return false.
 *
 * Swapping letters is defined as taking two indices i and j (0-indexed)
 * such that i != j and swapping the characters at s[i] and s[j].
 *
 * For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 */

public class Solution {

    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) return false;
        if (s.equals(goal)) {
            boolean[] chars = new boolean[26];
            for (var c : goal.toCharArray()) {
                if (chars[c - 'a']) return true;
                chars[c - 'a'] = true;
            }
            return false;
        }

        int[] diff = new int[4]; // cnt, first, second, pad
        for (int i = 0, l = s.length(); i < l; i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                diff[++diff[0]] = i;
                if (diff[0] > 2) return false;
            }
        }
        return diff[0] == 2 && s.charAt(diff[1]) == goal.charAt(diff[2])
            && s.charAt(diff[2]) == goal.charAt(diff[1]);
    }

    public static void main(String[] args) {
        assert !new Solution().buddyStrings("aaaaaaaab", "aaaaaaaac");
        assert !new Solution().buddyStrings("abc", "acd");
        assert !new Solution().buddyStrings("abcaa", "abcbb");
        assert new Solution().buddyStrings("ab", "ba");
        assert !new Solution().buddyStrings("ab", "ab");
        assert new Solution().buddyStrings("aa", "aa");
        assert new Solution().buddyStrings("aaaaaaabc", "aaaaaaacb");
        assert !new Solution().buddyStrings("", "aa");
    }

}
