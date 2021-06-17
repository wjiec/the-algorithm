package daily.d210617p65validnumber;

/**
 * 65. Valid Number
 *
 * https://leetcode-cn.com/problems/valid-number/
 *
 * A valid number can be split up into these components (in order):
 *
 * A decimal number or an integer.
 * (Optional) An 'e' or 'E', followed by an integer.
 * A decimal number can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One of the following formats:
 * One or more digits, followed by a dot '.'.
 * One or more digits, followed by a dot '.', followed by one or more digits.
 * A dot '.', followed by one or more digits.
 * An integer can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One or more digits.
 *
 * For example, all the following are valid numbers:
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"],
 * while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].
 *
 * Given a string s, return true if s is a valid number.
 */

public class Solution {

    public boolean isNumber(String s) {
        if (s.contains("e") || s.contains("E")) {
            if (s.matches(".*[eE].*[eE].*")) return false;
            String[] es = s.split("[eE]");
            if (es.length != 2) return false;
            return (isInteger(es[0]) || isFloat(es[0])) && isInteger(es[1]);
        }
        return s.contains(".") ? isFloat(s) : isInteger(s);
    }

    private boolean isInteger(String s) {
        return s.matches("[+\\-]?\\d+");
    }

    private boolean isFloat(String s) {
        return s.matches("[+\\-]?((\\d+\\.)|(\\d+\\.\\d+)|(\\.\\d+))");
    }

    public static void main(String[] args) {
        assert !new Solution().isNumber("7e69e");
        assert new Solution().isNumber("2");
        assert new Solution().isNumber("0089");
        assert new Solution().isNumber("-0.1");
        assert new Solution().isNumber("+3.14");
        assert new Solution().isNumber("4.");
        assert new Solution().isNumber("-.9");
        assert new Solution().isNumber("2e10");
        assert new Solution().isNumber("-90E3");
        assert new Solution().isNumber("3e+7");
        assert new Solution().isNumber("+6e-1");
        assert new Solution().isNumber("53.5e93");
        assert new Solution().isNumber("-123.456e789");
        assert !new Solution().isNumber("abc");
        assert !new Solution().isNumber("1a");
        assert !new Solution().isNumber("1e");
        assert !new Solution().isNumber("e3");
        assert !new Solution().isNumber("99e2.5");
        assert !new Solution().isNumber("--6");
        assert !new Solution().isNumber("-+3");
        assert !new Solution().isNumber("95a54e53");
    }

}
