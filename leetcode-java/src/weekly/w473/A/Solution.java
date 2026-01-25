package weekly.w473.A;

/**
 * Q1. Remove Zeros in Decimal Representation
 *
 * https://leetcode.cn/contest/weekly-contest-473/problems/remove-zeros-in-decimal-representation/
 *
 * You are given a positive integer n.
 *
 * Return the integer obtained by removing all zeros from the decimal representation of n.
 */

public class Solution {

    public long removeZeros(long n) {
        return Long.parseLong(String.valueOf(n).replaceAll("0", ""));
    }

    public static void main(String[] args) {
    }

}
