package weekly.w378.A;

/**
 * 2980. Check if Bitwise OR Has Trailing Zeros
 *
 * https://leetcode.cn/contest/weekly-contest-378/problems/check-if-bitwise-or-has-trailing-zeros/
 *
 * You are given an array of positive integers nums.
 *
 * You have to check if it is possible to select two or more elements in the array
 * such that the bitwise OR of the selected elements has at least one trailing zero
 * in its binary representation.
 *
 * For example, the binary representation of 5, which is "101", does not have any
 * trailing zeros, whereas the binary representation of 4, which is "100", has two trailing zeros.
 *
 * Return true if it is possible to select two or more elements whose
 * bitwise OR has trailing zeros, return false otherwise.
 */

public class Solution {

    public boolean hasTrailingZeros(int[] nums) {
        int even = 0;
        for (var v : nums) if (v % 2 == 0) even++;
        return even >= 2;
    }

    public static void main(String[] args) {
    }

}
