package weekly.bw143.A;

/**
 * 3345. Smallest Divisible Digit Product I
 *
 * https://leetcode.cn/contest/biweekly-contest-143/problems/smallest-divisible-digit-product-i/
 *
 * You are given two integers n and t. Return the smallest number greater than or
 * equal to n such that the product of its digits is divisible by t.
 */

public class Solution {

    public int smallestNumber(int n, int t) {
        while (product(n) % t != 0) n++;
        return n;
    }

    private int product(int n) {
        int ans = 1;
        for (; n != 0; n /= 10) ans *= n % 10;
        return ans;
    }

    public static void main(String[] args) {
    }

}
