package problem.p482licensekeyformatting;

/**
 * 482. License Key Formatting
 *
 * https://leetcode-cn.com/problems/license-key-formatting/
 *
 * You are given a license key represented as a string s that consists of only alphanumeric characters and dashes.
 * The string is separated into n + 1 groups by n dashes. You are also given an integer k.
 *
 * We want to reformat the string s such that each group contains exactly k characters,
 * except for the first group, which could be shorter than k but still must contain at least one character.
 *
 * Furthermore, there must be a dash inserted between two groups,
 * and you should convert all lowercase letters to uppercase.
 *
 * Return the reformatted license key.
 */

public class Solution {

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1, j = 0; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '-') {
                continue;
            }

            if (c >= 'a' && c <= 'z') {
                c -= 32;
            }

            sb.append(c);
            if (++j % k == 0) {
                sb.append('-');
            }
        }

        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        assert new Solution().licenseKeyFormatting("---", 3).equals("");
        assert new Solution().licenseKeyFormatting("--a-a-a-a--", 2).equals("AA-AA");
        assert new Solution().licenseKeyFormatting("5F3Z-2e-9-w", 4).equals("5F3Z-2E9W");
        assert new Solution().licenseKeyFormatting("2-5g-3-J", 2).equals("2-5G-3J");
    }

}
