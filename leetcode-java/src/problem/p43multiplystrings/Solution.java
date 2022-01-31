package problem.p43multiplystrings;

import java.util.ArrayList;
import java.util.List;

/**
 * 43. Multiply Strings
 *
 * https://leetcode-cn.com/problems/multiply-strings/
 *
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

public class Solution {

    private static class NumberWithCarry {
        private final int carry;
        private String number;
        public NumberWithCarry(String n, int c) { number = n; carry = c; }
        public String toString() { return number + "(" + carry + ")"; }
    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        if (num1.equals("1")) return num2;
        if (num2.equals("1")) return num1;

        List<String> list = new ArrayList<>();
        for (int i = num2.length() - 1; i >= 0; i--) {
            list.add(multiply(num1, num2.charAt(i) - '0'));

        }

        if (list.size() == 1) {
            return new StringBuilder(list.get(0)).reverse().toString();
        }

        NumberWithCarry nwc = new NumberWithCarry(list.get(0), 0);
        for (int i = 1; i < list.size(); i++) {
            nwc = shiftAdd(nwc, list.get(i), i);
        }

        if (nwc.carry != 0) nwc.number = nwc.number + nwc.carry;
        return new StringBuilder(nwc.number).reverse().toString();
    }

    private NumberWithCarry shiftAdd(NumberWithCarry a, String b, int offset) {
        int carry = 0, idx = 0;
        StringBuilder sb = new StringBuilder(a.number.substring(0, offset));
        for (int i = offset; i < a.number.length(); i++) {
            int v = (a.number.charAt(i) - '0') + (b.charAt(idx++) - '0') + carry;
            sb.append(v % 10);
            carry = v / 10;
        }
        carry += a.carry;

        while (idx < b.length()) {
            int v = carry + (b.charAt(idx++) - '0');
            sb.append(v % 10);
            carry = v / 10;
        }

        System.out.printf("  a = %s\n+ b = %s%s\n----------------------\n      %s(%d)\n\n",
            a, " ".repeat(offset), b, sb, carry);

        return new NumberWithCarry(sb.toString(), carry);
    }

    private String multiply(String num, int bit) {
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = num.length() - 1; i >= 0; i--) {
            int v = bit * (num.charAt(i) - '0') + carry;
            sb.append(v % 10);
            carry = v / 10;
        }
        if (carry != 0) sb.append(carry);

        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().multiply("140", "721").equals("100940");
        assert new Solution().multiply("65539", "83314").equals("5460316246");
        assert new Solution().multiply("123456789", "987654321").equals("121932631112635269");

        assert new Solution().multiply("2", "3").equals("6");
        assert new Solution().multiply("123", "456").equals("56088");
    }

}
