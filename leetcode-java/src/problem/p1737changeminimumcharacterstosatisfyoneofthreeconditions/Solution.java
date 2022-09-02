package problem.p1737changeminimumcharacterstosatisfyoneofthreeconditions;

import common.Tag;

/**
 * 1737. Change Minimum Characters to Satisfy One of Three Conditions
 *
 * https://leetcode.cn/problems/change-minimum-characters-to-satisfy-one-of-three-conditions/
 *
 * You are given two strings a and b that consist of lowercase letters. In one operation, you can
 * change any character in a or b to any lowercase letter.
 *
 * Your goal is to satisfy one of the following three conditions:
 *
 * Every letter in a is strictly less than every letter in b in the alphabet.
 * Every letter in b is strictly less than every letter in a in the alphabet.
 * Both a and b consist of only one distinct letter.
 * Return the minimum number of operations needed to achieve your goal.
 */

public class Solution {

    @Tag({"字母", "转换并满足条件"})
    public int minCharacters(String a, String b) {
        int l1 = a.length(), l2 = b.length();
        int[] s1 = new int[128], s2 = new int[128];
        for (var c : a.toCharArray()) s1[c]++;
        for (var c : b.toCharArray()) s2[c]++;

        int ans = Integer.MAX_VALUE;
        for (char c = 'a'; c <= 'z'; c++) {
            ans = Math.min(ans, (l1 - s1[c]) + (l2 - s2[c]));

            ans = Math.min(ans, lessThan(s1, s2, c));
            ans = Math.min(ans, lessThan(s2, s1, c));
        }
        return ans;
    }

    // 使得 s2 中的字符都大于等于 c 时, 同时 s1 中的字符严格小于 c
    private int lessThan(int[] s1, int[] s2, char c) {
        // 如果 c == 'a' 则无法将 s1 中的字母变为小于 'a' 的字母
        if (c == 'a') return Integer.MAX_VALUE;

        int ans = 0;
        // s2 中的字符都大于等于 c
        for (char i = 'a'; i < c; i++) ans += s2[i];
        // s1 中的字符都小于 c
        for (char i = c; i <= 'z'; i++) ans += s1[i];

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minCharacters("otpaynexxlngyrdmand", "twtyifiiundfejxfktclktjnqrmycnqmxhxfitnlasyuwotjguerenjjnpkaajsnnraopdnambfccwthppimijghg") == 19;
        assert new Solution().minCharacters("a", "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz") == 2;

        assert new Solution().minCharacters("aba", "caa") == 2;
        assert new Solution().minCharacters("dabadd", "cda") == 3;
    }

}
