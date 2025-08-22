package weekly.bw161.A;

import java.util.Arrays;

/**
 * Q1. Split Array by Prime Indices
 *
 * https://leetcode.cn/contest/biweekly-contest-161/problems/split-array-by-prime-indices/
 *
 * You are given an integer array nums.
 *
 * Split nums into two arrays A and B using the following rule:
 *
 * Elements at prime indices in nums must go into array A.
 * All other elements must go into array B.
 * Return the absolute difference between the sums of the two arrays: |sum(A) - sum(B)|.
 *
 * Note: An empty array has a sum of 0.
 */

public class Solution {

    public long splitArray(int[] nums) {
        boolean[] primes = new boolean[nums.length + 1];
        Arrays.fill(primes, true); primes[0] = primes[1] = false;
        for (int i = 2; i < primes.length; i++) {
            if (!primes[i]) continue;
            for (int j = i + i; j < primes.length; j += i) {
                primes[j] = false;
            }
        }

        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += nums[i] * (primes[i] ? 1 : -1);
        }
        return Math.abs(ans);
    }

    public static void main(String[] args) {
        assert new Solution().splitArray(new int[]{-1, 5, 7, 0}) == 3;
    }

}
