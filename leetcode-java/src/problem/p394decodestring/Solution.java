package problem.p394decodestring;

/**
 * 394. Decode String
 *
 * https://leetcode-cn.com/problems/decode-string/
 *
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside
 * the square brackets is being repeated exactly k times.
 *
 * Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; there are no extra white spaces,
 * square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and
 * that digits are only for those repeat numbers, k.
 *
 * For example, there will not be input like 3a or 2[4].
 */

public class Solution {

    public String decodeString(String s) {
        return decodeString(s.toCharArray(), 0);
    }

    private String decodeString(char[] chars, int i) {
        if (i >= chars.length) return "";

        StringBuilder sb = new StringBuilder();
        while (i < chars.length && !Character.isDigit(chars[i])) sb.append(i++);

        int count = 0;
        while (i < chars.length && Character.isDigit(chars[i])) count = count * 10 + (chars[i++] - '0');
        if (count != 0) sb.append(decodeString(chars, i + 1).repeat(count));

        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().decodeString("3[a]2[bc]").equals("aaabcbc");
        assert new Solution().decodeString("3[a2[c]]").equals("accaccacc");
        assert new Solution().decodeString("2[abc]3[cd]ef").equals("abcabccdcdcdef");
        assert new Solution().decodeString("abc3[cd]xyz").equals("abccdcdcdxyz");
    }

}
