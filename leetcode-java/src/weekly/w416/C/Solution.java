package weekly.w416.C;

/**
 * 3297. Count Substrings That Can Be Rearranged to Contain a String I
 *
 * https://leetcode.cn/contest/weekly-contest-416/problems/count-substrings-that-can-be-rearranged-to-contain-a-string-i/
 *
 * You are given two strings word1 and word2.
 *
 * A string x is called valid if x can be rearranged to have word2 as a prefix.
 *
 * Return the total number of valid substrings of word1.
 *
 * Note that the memory limits in this problem are smaller than usual, so you must
 * implement a solution with a linear runtime complexity.
 * @noinspection DuplicatedCode
 */

public class Solution {

    public long validSubstringCount(String word1, String word2) {
        int[] need = new int[26];
        for (var c : word2.toCharArray()) need[c - 'a']++;

        long ans = 0;
        int[] curr = new int[26];
        char[] chars = word1.toCharArray();
        for (int l = 0, r = 0; l < chars.length; l++) {
            // 找到满足条件的最近的索引r
            while (r <= chars.length && !satisfied(need, curr)) {
                curr[chars[r++] - 'a']++;
            }

            if (satisfied(need, curr)) ans += chars.length - r + 1;
            curr[chars[l] - 'a']--;
        }
        return ans;
    }

    private boolean satisfied(int[] need, int[] curr) {
        for (int i = 0; i < need.length; i++) {
            if (need[i] > curr[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().validSubstringCount("dcbdcdccb", "cdd") == 18;

        assert new Solution().validSubstringCount("bcca", "abc") == 1;
        assert new Solution().validSubstringCount("abcabc", "abc") == 10;
        assert new Solution().validSubstringCount("abcabc", "aaabc") == 0;
    }

}
