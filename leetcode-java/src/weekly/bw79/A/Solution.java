package weekly.bw79.A;

/**
 * 6083. Check if Number Has Equal Digit Count and Digit Value
 *
 * https://leetcode.cn/contest/biweekly-contest-79/problems/check-if-number-has-equal-digit-count-and-digit-value/
 *
 * You are given a 0-indexed string num of length n consisting of digits.
 *
 * Return true if for every index i in the range 0 <= i < n,
 * the digit i occurs num[i] times in num, otherwise return false.
 */

public class Solution {

    public boolean digitCount(String num) {
        int[] digits = new int[10];
        for (var c : num.toCharArray()) digits[c - '0']++;
        for (int i = 0; i < num.length(); i++) {
            int n = num.charAt(i) - '0';
            if (digits[i] != n) return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
