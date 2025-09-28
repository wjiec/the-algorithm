package weekly.w464.A;

/**
 * Q1. GCD of Odd and Even Sums
 *
 * https://leetcode.cn/contest/weekly-contest-464/problems/gcd-of-odd-and-even-sums/
 *
 * You are given an integer n. Your task is to compute the GCD (greatest common divisor) of two values:
 *
 * sumOdd: the sum of the first n odd numbers.
 *
 * sumEven: the sum of the first n even numbers.
 *
 * Return the GCD of sumOdd and sumEven.
 */

public class Solution {

    public int gcdOfOddEvenSums(int n) {
        int sumOdd = 0, sumEven = 0;
        for (int i = 0, odd = 1, even = 2; i < n; i++, odd += 2, even += 2) {
            sumOdd += odd; sumEven += even;
        }

        while (sumOdd % sumEven != 0) {
            int temp = sumOdd % sumEven;
            sumOdd = sumEven; sumEven = temp;
        }
        return sumEven;
    }

    private static class Optimization {
        public int gcdOfOddEvenSums(int n) {
            // 奇数和也就是从 1 开始的等差数列之和, 偶数和也就是从 1 开始的等差数列之和
            //  - 根据等差数列之和 S = n * (a1 + an) / 2
            //      - 奇数数列 a1 = 1, an = 2 * n - 1
            //      - 偶数数列 a1 = 2, an = 2 * n
            //
            // S1 = n / 2 * (1 + (2 * n - 1))
            //    = n / 2 * (2 * n)
            //    = n / 2 * 2 * n
            //    = n * n
            //
            // S2 = n / 2 * (2 + (2 * n))
            //    = n / 2 * (2 * (n + 1))
            //    = n / 2 * 2 * (n + 1)
            //    = n * (n + 1)
            //
            // 公因式为 n, gcd(n, n + 1) == 1
            return n;
        }
    }

    public static void main(String[] args) {
        assert new Optimization().gcdOfOddEvenSums(4) == 4;
        assert new Optimization().gcdOfOddEvenSums(5) == 5;

        assert new Solution().gcdOfOddEvenSums(4) == 4;
        assert new Solution().gcdOfOddEvenSums(5) == 5;
    }

}
