package weekly.w481.A;

/**
 * Q1. Mirror Distance of an Integer
 *
 * https://leetcode.cn/contest/weekly-contest-481/problems/mirror-distance-of-an-integer/
 *
 * You are given an integer n.
 *
 * Define its mirror distance as: abs(n - reverse(n)) where reverse(n) is the integer
 * formed by reversing the digits of n.
 *
 * Return an integer denoting the mirror distance of n.
 *
 * abs(x) denotes the absolute value of x.
 */

public class Solution {

    public int mirrorDistance(int n) {
        int reversed = 0;
        for (int v = n; v != 0; v /= 10) reversed = reversed * 10 + v % 10;
        return Math.abs(n - reversed);
    }

    public static void main(String[] args) {
    }

}
