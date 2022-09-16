package problem.p1888minimumnumberofflipstomakethebinarystringalternating;

import common.Tag;

/**
 * 1888. Minimum Number of Flips to Make the Binary String Alternating
 *
 * https://leetcode.cn/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/
 *
 * You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:
 *
 * Type-1: Remove the character at the start of the string s and append it to the end of the string.
 * Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
 * Return the minimum number of type-2 operations you need to perform such that s becomes alternating.
 *
 * The string is called alternating if no two adjacent characters are equal.
 *
 * For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 */

public class Solution {

    @Tag({"字符串翻转", "字符串轮转"})
    public int minFlips(String s) {
        char[] map = new char[]{'0', '1'};
        char[] chars = s.toCharArray();
        int first0 = 0, n = chars.length;
        for (int i = 0; i < n; i++) {
            if (map[i % 2] != chars[i]) first0++;
        }

        int ans = Math.min(first0, n - first0);
        for (int i = 0; i < n; i++) {
            first0 -= (chars[i] != map[i % 2] ? 1 : 0);
            first0 += (chars[i] != map[(i + n) % 2] ? 1 : 0);
            ans = Math.min(ans, Math.min(first0, n - first0));
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minFlips("001000000010") == 4;
        assert new Solution().minFlips("01001001101") == 2;

        assert new Solution().minFlips("111000") == 2;
        assert new Solution().minFlips("010") == 0;
        assert new Solution().minFlips("1110") == 1;
    }

}
