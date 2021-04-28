package problem.p67addbinary;

/**
 * 67. Add Binary
 *
 * https://leetcode-cn.com/problems/add-binary/
 *
 * Given two binary strings a and b, return their sum as a binary string.
 */

public class Solution {

    public String addBinary(String a, String b) {
        byte carry = 0;
        char []cs = new char[Math.max(a.length(), b.length())];
        int l = a.length() - 1, r = b.length() - 1;
        for (; l >= 0 && r >= 0; --l, --r) {
            switch (a.charAt(l) + b.charAt(r) + carry) {
                case '`': // 0
                    cs[Math.max(l, r)] = '0'; break;
                case 'a': // 1
                    carry = 0;
                    cs[Math.max(l, r)] = '1'; break;
                case 'b': // 2
                    carry = 1;
                    cs[Math.max(l, r)] = '0'; break;
                case 'c': // 3
                    carry = 1;
                    cs[Math.max(l, r)] = '1'; break;
            }
        }

        for (; l >= 0; --l) {
            cs[l] = a.charAt(l);
            if (carry == 1 && cs[l] == '1') {
                cs[l] = '0';
                continue;
            } else if (carry == 1) {
                cs[l] = '1';
            }

            carry = 0;
        }

        for (; r >= 0; --r) {
            cs[r] = b.charAt(r);
            if (carry == 1 && cs[r] == '1') {
                cs[r] = '0';
                continue;
            } else if (carry == 1) {
                cs[r] = '1';
            }

            carry = 0;
        }

        return carry == 1 ? "1" + new String(cs) : new String(cs);
    }

    public static void main(String[] args) {
        assert new Solution().addBinary("0", "0").equals("0");
        assert new Solution().addBinary("1", "0").equals("1");
        assert new Solution().addBinary("11", "1").equals("100");
        assert new Solution().addBinary("1101", "1").equals("1110");
        assert new Solution().addBinary("1010", "1011").equals("10101");
        assert new Solution().addBinary("1111111111", "1").equals("10000000000");
    }

}
