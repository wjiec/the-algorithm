package weekly.w364.A;

/**
 * 2864. Maximum Odd Binary Number
 *
 * https://leetcode.cn/contest/weekly-contest-364/problems/maximum-odd-binary-number/
 *
 * You are given a binary string s that contains at least one '1'.
 *
 * You have to rearrange the bits in such a way that the resulting binary
 * number is the maximum odd binary number that can be created from this combination.
 *
 * Return a string representing the maximum odd binary number that can be created from the given combination.
 *
 * Note that the resulting string can have leading zeros.
 */

public class Solution {

    public String maximumOddBinaryNumber(String s) {
        int one = 0, zero = 0;
        for (var c : s.toCharArray()) {
            if (c == '0') zero++;
            else one++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("1".repeat(Math.max(0, one - 1)));
        sb.append("0".repeat(zero));
        sb.append("1");

        return sb.toString();
    }

    public static void main(String[] args) {
    }

}
