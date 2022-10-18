package problem.p2165smallestvalueoftherearrangednumber;

/**
 * 2165. Smallest Value of the Rearranged Number
 *
 * https://leetcode.cn/problems/smallest-value-of-the-rearranged-number/
 *
 * You are given an integer num. Rearrange the digits of num such that its
 * value is minimized and it does not contain any leading zeros.
 *
 * Return the rearranged number with minimal value.
 *
 * Note that the sign of the number does not change after rearranging the digits.
 */

public class Solution {

    public long smallestNumber(long num) {
        String s = String.valueOf(num);
        int[] count = new int[10];
        for (var c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                count[c - '0']++;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (num < 0) {
            sb.append("-");
            for (int i = count.length - 1; i >= 0; i--) {
                sb.append(("" + i).repeat(count[i]));
            }
        } else {
            for (int i = 1; i < count.length; i++) {
                if (count[i] != 0) {
                    sb.append((char) ('0' + i));
                    count[i]--; break;
                }
            }
            for (int i = 0; i < count.length; i++) {
                sb.append(("" + i).repeat(count[i]));
            }
        }

        return Long.parseLong(sb.toString());
    }

    public static void main(String[] args) {
        assert new Solution().smallestNumber(310) == 103;
        assert new Solution().smallestNumber(-7605) == -7650;
    }

}
