package weekly.w391.A;

/**
 * 3099. Harshad Number
 *
 * https://leetcode.cn/contest/weekly-contest-391/problems/harshad-number/
 *
 * An integer divisible by the sum of its digits is said to be a Harshad number.
 *
 * You are given an integer x.
 *
 * Return the sum of the digits of x if x is a Harshad number, otherwise, return -1.
 */

public class Solution {

    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int div = 0;
        for (int a = x; a != 0; a /= 10) div += a % 10;
        return x % div == 0 ? div : -1;
    }

    public static void main(String[] args) {
    }

}
