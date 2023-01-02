package problem.p2523closestprimenumbersinrange;

import common.Checker;

/**
 * 2523. Closest Prime Numbers in Range
 *
 * https://leetcode.cn/problems/closest-prime-numbers-in-range/
 *
 * Given two positive integers left and right, find the two integers num1 and num2 such that:
 *
 * left <= nums1 < nums2 <= right .
 *
 * nums1 and nums2 are both prime numbers.
 *
 * nums2 - nums1 is the minimum amongst all other pairs satisfying the above conditions.
 * Return the positive integer array ans = [nums1, nums2]. If there are multiple pairs
 * satisfying these conditions, return the one with the minimum nums1 value or [-1, -1]
 * if such numbers do not exist.
 *
 * A number greater than 1 is called prime if it is only divisible by 1 and itself.
 */

public class Solution {

    public int[] closestPrimes(int left, int right) {
        int pLen = 0, pMax = Math.min(10_0000, right / 2);
        int[] primes = new int[pMax];
        boolean[] skipped = new boolean[right + 1];
        for (int i = 2; i <= right; i++) {
            if (!skipped[i]) primes[pLen++] = i;
            for (int j = 0; j < pLen && i * primes[j] <= right; j++) {
                skipped[i * primes[j]] = true;
                if (i % primes[j] == 0) break;
            }
        }

        int mi = Integer.MAX_VALUE, l = -1, r = -1;
        for (int i = 1; i < pLen; i++) {
            if (primes[i - 1] < left) continue;
            if (primes[i] - primes[i - 1] < mi) {
                l = primes[i - 1]; r = primes[i]; mi = r - l;
            }
        }
        return new int[]{l, r};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().closestPrimes(10, 19), new int[]{11, 13});
        assert Checker.check(new Solution().closestPrimes(4, 6), new int[]{-1, -1});
    }

}
