package offer2.p2JFETK5;

/**
 * 剑指 Offer II 002. 二进制加法
 *
 * https://leetcode-cn.com/problems/JFETK5/
 *
 * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 */

public class Solution {

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        boolean carry = false;
        int i = a.length() - 1, j = b.length() - 1;
        for (; i >= 0 && j >= 0; i--, j--) {
            char x = a.charAt(i), y = b.charAt(j);
            if (x == '1' && y == '1') {
                sb.append(carry ? '1' : '0');
                carry = true;
            } else if (x == '1' || y == '1') {
                sb.append(carry ? '0' : '1');
            } else {
                sb.append(carry ? '1' : '0');
                carry = false;
            }
        }

        for (; i >= 0; i--) {
            if (a.charAt(i) == '1') {
                sb.append(carry ? '0' : '1');
            } else {
                sb.append(carry ? '1' : '0');
                carry = false;
            }
        }

        for (; j >= 0; j--) {
            if (b.charAt(j) == '1') {
                sb.append(carry ? '0' : '1');
            } else {
                sb.append(carry ? '1' : '0');
                carry = false;
            }
        }

        if (carry) sb.append('1');

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        assert new Solution().addBinary("11", "10").equals("101");
        assert new Solution().addBinary("1010", "1011").equals("10101");
        assert new Solution().addBinary("111111", "1").equals("1000000");
    }

}
