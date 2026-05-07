package weekly.w490.B;

/**
 * Q2. Check Digitorial Permutation
 *
 * https://leetcode.cn/contest/weekly-contest-490/problems/check-digitorial-permutation/
 *
 * You are given an integer n.
 *
 * A number is called digitorial if the sum of the factorials of its digits is equal to the number itself.
 *
 * Determine whether any permutation of n (including the original order) forms a digitorial number.
 *
 * Return true if such a permutation exists, otherwise return false.
 *
 * Note:
 *
 * The factorial of a non-negative integer x, denoted as x!, is the
 * product of all positive integers less than or equal to x, and 0! = 1.
 *
 * A permutation is a rearrangement of all the digits of a number that does not start with zero.
 * Any arrangement starting with zero is invalid.
 */

public class Solution {

    public boolean isDigitorialPermutation(int n) {
        long[] fib = new long[10]; fib[0] = 1;
        for (int i = 1; i < fib.length; i++) fib[i] = fib[i - 1] * i;

        long sum = 0;
        for (var v = n; v != 0; v /= 10) {
            sum += fib[v % 10];
        }

        int[] d1 = digits(n), d2 = digits(sum);
        for (int i = 0; i < 10; i++) {
            if (d1[i] != d2[i]) return false;
        }
        return true;
    }

    private int[] digits(long n) {
        int[] cnt = new int[10];
        for (var v = n; v != 0; v /= 10) cnt[(int) (v % 10)]++;
        return cnt;
    }

    public static void main(String[] args) {
    }

}
