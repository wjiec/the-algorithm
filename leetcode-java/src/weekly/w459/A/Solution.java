package weekly.w459.A;

/**
 * Q1. Check Divisibility by Digit Sum and Product
 *
 * https://leetcode.cn/contest/weekly-contest-459/problems/check-divisibility-by-digit-sum-and-product/
 *
 * You are given a positive integer n. Determine whether n is divisible by the sum of the following two values:
 *
 * The digit sum of n (the sum of its digits).
 *
 * The digit product of n (the product of its digits).
 *
 * Return true if n is divisible by this sum; otherwise, return false
 */

public class Solution {

    public boolean checkDivisibility(int n) {
        int sum = 0, mul = 1;
        for (int v = n; v != 0; v /= 10) {
            sum += v % 10;
            mul *= v % 10;
        }
        return n % (sum + mul) == 0;
    }

    public static void main(String[] args) {
    }

}
