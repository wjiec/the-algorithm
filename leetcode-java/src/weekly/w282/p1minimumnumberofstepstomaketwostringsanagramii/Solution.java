package weekly.w282.p1minimumnumberofstepstomaketwostringsanagramii;

/**
 * 6009. Minimum Number of Steps to Make Two Strings Anagram II
 *
 * https://leetcode-cn.com/contest/weekly-contest-282/problems/minimum-number-of-steps-to-make-two-strings-anagram-ii/
 *
 * You are given two strings s and t. In one step, you can append any character to either s or t.
 *
 * Return the minimum number of steps to make s and t anagrams of each other.
 *
 * An anagram of a string is a string that contains the same characters with a different (or the same) ordering.
 */

public class Solution {

    public int minSteps(String s, String t) {
        int[] chars = new int[128];
        for (var c : s.toCharArray()) chars[c]++;
        for (var c : t.toCharArray()) chars[c]--;

        int ans = 0;
        for (var n : chars) ans += Math.abs(n);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minSteps("leetcode", "coats") == 7;
        assert new Solution().minSteps("night", "thing") == 0;
    }

}
