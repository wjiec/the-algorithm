package weekly.w477.A;

/**
 * Q1. Concatenate Non-Zero Digits and Multiply by Sum I
 *
 * https://leetcode.cn/contest/weekly-contest-477/problems/concatenate-non-zero-digits-and-multiply-by-sum-i/
 *
 * You are given an integer n.
 *
 * Form a new integer x by concatenating all the non-zero digits of n in their original order.
 *
 * If there are no non-zero digits, x = 0.
 *
 * Let sum be the sum of digits in x.
 *
 * Return an integer representing the value of x * sum.
 */

public class Solution {

    public long sumAndMultiply(int n) {
        long x = 0, sum = 0;
        for (var c : String.valueOf(n).toCharArray()) {
            if (c != '0') {
                x = x * 10 + (c - '0');
                sum += c - '0';
            }
        }
        return x * sum;
    }

    public static void main(String[] args) {
    }

}
