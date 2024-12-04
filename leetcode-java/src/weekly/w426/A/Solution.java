package weekly.w426.A;

/**
 * 3370. Smallest Number With All Set Bits
 *
 * https://leetcode.cn/contest/weekly-contest-426/problems/smallest-number-with-all-set-bits/
 *
 * You are given a positive number n.
 *
 * Return the smallest number x greater than or equal to n, such that the binary representation of x
 * contains only set bits.
 *
 * A set bit refers to a bit in the binary representation of a number that has a value of 1.
 */

public class Solution {

    public int smallestNumber(int n) {
        int ans = 1;
        while (ans <= n) ans <<= 1;
        return ans - 1;
    }

    public static void main(String[] args) {
    }

}
