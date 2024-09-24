package weekly.w416.D;

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
 */

public class Solution {

    public long validSubstringCount(String word1, String word2) {
        int matched = 0;
        int[] counter = new int[128];
        for (var c : word2.toCharArray()) {
            if (counter[c]++ == 0) matched++;
        }

        long ans = 0;
        char[] chars = word1.toCharArray();
        for (int l = 0, r = 0; r < chars.length; r++) {
            if (--counter[chars[r]] == 0) matched--;

            // 找到第一个不满足条件的位置, 此时从 [0, l - 1] 都是满足要求的左端点,
            // 合法字符串的右端点为 r, 可以做到不重不漏
            while (matched == 0) {
                if (counter[chars[l++]]++ == 0) matched++;
            }

            ans += l; // 从 [0, l - 1] 的个数右 (l - 1) + 1
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().validSubstringCount("dcbdcdccb", "cdd") == 18;

        assert new Solution().validSubstringCount("bcca", "abc") == 1;
        assert new Solution().validSubstringCount("abcabc", "abc") == 10;
        assert new Solution().validSubstringCount("abcabc", "aaabc") == 0;
    }

}
