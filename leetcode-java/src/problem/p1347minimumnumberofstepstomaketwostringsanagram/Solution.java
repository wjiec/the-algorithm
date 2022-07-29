package problem.p1347minimumnumberofstepstomaketwostringsanagram;

/**
 * 1347. Minimum Number of Steps to Make Two Strings Anagram
 *
 * https://leetcode.cn/problems/minimum-number-of-steps-to-make-two-strings-anagram/
 *
 * You are given two strings of the same length s and t. In one step you can choose
 * any character of t and replace it with another character.
 *
 * Return the minimum number of steps to make t an anagram of s.
 *
 * An Anagram of a string is a string that contains the same characters
 * with a different (or the same) ordering.
 */

public class Solution {

    public int minSteps(String s, String t) {
        int[] a = new int[128], b = new int[128];
        for (var c : s.toCharArray()) a[c]++;
        for (var c : t.toCharArray()) b[c]++;

        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < b[i]) ans += b[i] - a[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minSteps("bab", "aba") == 1;
        assert new Solution().minSteps("leetcode", "practice") == 5;
        assert new Solution().minSteps("anagram", "mangaar") == 0;
    }

}
