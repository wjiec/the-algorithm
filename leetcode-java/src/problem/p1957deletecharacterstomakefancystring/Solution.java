package problem.p1957deletecharacterstomakefancystring;

/**
 * 1957. Delete Characters to Make Fancy String
 *
 * https://leetcode-cn.com/problems/delete-characters-to-make-fancy-string/
 *
 * A fancy string is a string where no three consecutive characters are equal.
 *
 * Given a string s, delete the minimum possible number of characters from s to make it fancy.
 *
 * Return the final string after the deletion. It can be shown that the answer will always be unique.
 */

public class Solution {

    public String makeFancyString(String s) {
        if (s.length() < 3) return s;

        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append(chars[0]).append(chars[1]);
        for (int i = 2; i < chars.length; i++) {
            if (chars[i] != chars[i - 1] || chars[i] != chars[i - 2]) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().makeFancyString("leeetcode").equals("leetcode");
        assert new Solution().makeFancyString("aaabaaaa").equals("aabaa");
        assert new Solution().makeFancyString("aab").equals("aab");
    }

}
