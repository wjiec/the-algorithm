package weekly.w438.A;

/**
 * 3461. Check If Digits Are Equal in String After Operations I
 *
 * https://leetcode.cn/contest/weekly-contest-438/problems/check-if-digits-are-equal-in-string-after-operations-i/
 *
 * You are given a string s consisting of digits. Perform the following
 * operation repeatedly until the string has exactly two digits:
 *
 * For each pair of consecutive digits in s, starting from the first digit, calculate a new digit
 * as the sum of the two digits modulo 10.
 * Replace s with the sequence of newly calculated digits, maintaining the order in which they are computed.
 *
 * Return true if the final two digits in s are the same; otherwise, return false.
 */

public class Solution {

    public boolean hasSameDigits(String s) {
        if (s.length() == 2) return s.charAt(0) == s.charAt(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            int a = s.charAt(i - 1) - '0', b = s.charAt(i) - '0';
            sb.append((a + b) % 10);
        }
        return hasSameDigits(sb.toString());
    }

    public static void main(String[] args) {
    }

}
