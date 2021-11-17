package problem.p1844replacealldigitswithcharacters;

/**
 * 1844. Replace All Digits with Characters
 *
 * https://leetcode-cn.com/problems/replace-all-digits-with-characters/
 *
 * You are given a 0-indexed string s that has lowercase English letters
 * in its even indices and digits in its odd indices.
 *
 * There is a function shift(c, x), where c is a character and x is a digit,
 * that returns the xth character after c.
 *
 * For example, shift('a', 5) = 'f' and shift('x', 0) = 'x'.
 * For every odd index i, you want to replace the digit s[i] with shift(s[i-1], s[i]).
 *
 * Return s after replacing all digits. It is guaranteed that shift(s[i-1], s[i]) will never exceed 'z'.
 */

public class Solution {

    public String replaceDigits(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) sb.append(s.charAt(i));
            else sb.append((char) (s.charAt(i - 1) + (s.charAt(i) - '0')));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().replaceDigits("a1c1e1").equals("abcdef");
        assert new Solution().replaceDigits("a1b2c3d4e").equals("abbdcfdhe");
    }

}
