package weekly.w487.A;

/**
 * Q1. Count Monobit Integers
 *
 * https://leetcode.cn/contest/weekly-contest-487/problems/count-monobit-integers/
 *
 * You are given an integer n.
 *
 * An integer is called Monobit if all bits in its binary representation are the same.
 *
 * Return the count of Monobit integers in the range [0, n] (inclusive).
 */

public class Solution {

    public int countMonobit(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            if ((i & (i + 1)) == 0) ans++;
        }
        return ans;
    }

    private static class Optimization {
        public int countMonobit(int n) {
            return 32 - Integer.numberOfLeadingZeros(n + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(32 - Integer.numberOfLeadingZeros(4));
    }

}
