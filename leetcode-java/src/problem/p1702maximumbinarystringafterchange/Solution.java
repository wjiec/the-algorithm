package problem.p1702maximumbinarystringafterchange;

/**
 * 1702. Maximum Binary String After Change
 *
 * https://leetcode.cn/problems/maximum-binary-string-after-change/
 *
 * You are given a binary string binary consisting of only 0's or 1's.
 * You can apply each of the following operations any number of times:
 *
 * Operation 1: If the number contains the substring "00", you can replace it with "10".
 * For example, "00010" -> "10010"
 *
 * Operation 2: If the number contains the substring "10", you can replace it with "01".
 * For example, "00010" -> "00001"
 *
 * Return the maximum binary string you can obtain after any number of operations. Binary string x is
 * greater than binary string y if x's decimal representation is greater than y's decimal representation.
 */

public class Solution {

    public String maximumBinaryString(String binary) {
        char[] chars = binary.toCharArray();
        int zero = 0, first = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                if (first == -1) first = i;
                zero++; chars[i] = '1';
            }
        }
        if (zero < 2) return binary;

        chars[first + zero - 1] = '0';
        return new String(chars);
    }

    public static void main(String[] args) {
        assert new Solution().maximumBinaryString("000110").equals("111011");
        assert new Solution().maximumBinaryString("01").equals("01");
    }

}
