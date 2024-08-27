package problem.p972equalrationalnumbers;

/**
 * 972. Equal Rational Numbers
 *
 * https://leetcode.cn/problems/equal-rational-numbers/
 *
 * Given two strings s and t, each of which represents a non-negative rational number, return
 * true if and only if they represent the same number. The strings may use parentheses to denote
 * the repeating part of the rational number.
 *
 * A rational number can be represented using up to three parts: <IntegerPart>, <NonRepeatingPart>,
 * and a <RepeatingPart>. The number will be represented in one of the following three ways:
 *
 * <IntegerPart>
 * For example, 12, 0, and 123.
 * <IntegerPart><.><NonRepeatingPart>
 * For example, 0.5, 1., 2.12, and 123.0001.
 * <IntegerPart><.><NonRepeatingPart><(><RepeatingPart><)>
 *
 * For example, 0.1(6), 1.(9), 123.00(1212).
 * The repeating portion of a decimal expansion is conventionally denoted within a pair of round brackets.
 *
 * For example:
 * 1/6 = 0.16666666... = 0.1(6) = 0.1666(6) = 0.166(66).
 */

public class Solution {

    public boolean isRationalEqual(String s, String t) {
        return Math.abs(expand(s.toCharArray()) - expand(t.toCharArray())) < 0.00000000001;
    }

    private double expand(char[] chars) {
        char[] ans = new char[18]; boolean dot = false;
        for (int i = 0, lp = 0, j = 0; j < ans.length; i++) {
            if (i < chars.length) {
                switch (chars[i]) {
                    case '(' -> lp = i;
                    case ')' -> i = lp;
                    default -> ans[j++] = chars[i];
                }
            } else ans[j++] = !dot ? '.' : '0';
            dot = dot || ans[j - 1] == '.';
        }

        return Double.parseDouble(new String(ans));
    }

    public static void main(String[] args) {
        assert new Solution().isRationalEqual("0.(52)", "0.5(25)");
        assert new Solution().isRationalEqual("0.1666(6)", "0.166(66)");
        assert new Solution().isRationalEqual("0.9(9)", "1.");
        assert new Solution().isRationalEqual("1.0", "1");
    }

}
