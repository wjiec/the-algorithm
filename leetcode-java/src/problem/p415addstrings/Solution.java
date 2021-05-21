package problem.p415addstrings;

/**
 * 415. Add Strings
 *
 * https://leetcode-cn.com/problems/add-strings/
 *
 * Given two non-negative integers, num1 and num2 represented as string,
 * return the sum of num1 and num2 as a string.
 *
 * You must solve the problem without using any built-in library
 * for handling large integers (such as BigInteger).
 * You must also not convert the inputs to integers directly.
 */

public class Solution {

    public String addStrings(String num1, String num2) {
        int sz1 = num1.length(), sz2 = num2.length(), carry = 0, i = sz1 - 1, j = sz2 - 1;
        StringBuilder sb = new StringBuilder();
        for (; i >= 0 && j >= 0; i--, j--) {
            int v = (num1.charAt(i) - '0') + (num2.charAt(j) - '0') + carry;
            carry = v >= 10 ? 1 : 0;
            sb.append((char) ('0' + (v % 10)));
        }

        for (; i >= 0; i--) {
            int v = (num1.charAt(i) - '0') + carry;
            carry = v >= 10 ? 1 : 0;
            sb.append((char) ('0' + (v % 10)));
        }

        for (; j >= 0; j--) {
            int v = (num2.charAt(j) - '0') + carry;
            carry = v >= 10 ? 1 : 0;
            sb.append((char) ('0' + (v % 10)));
        }

        if (carry == 1) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        assert new Solution().addStrings("11", "123").equals("134");
        assert new Solution().addStrings("456", "77").equals("533");
        assert new Solution().addStrings("0", "0").equals("0");
        assert new Solution().addStrings("99", "901").equals("1000");
        assert new Solution().addStrings("555", "445").equals("1000");
    }

}
