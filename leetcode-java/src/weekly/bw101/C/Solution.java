package weekly.bw101.C;

import common.Tag;

import java.util.Arrays;

/**
 * 2607. Make K-Subarray Sums Equal
 *
 * https://leetcode.cn/contest/biweekly-contest-101/problems/make-k-subarray-sums-equal/
 *
 * You are given a 0-indexed integer array arr and an integer k. The array arr is circular.
 * In other words, the first element of the array is the next element of the last element,
 * and the last element of the array is the previous element of the first element.
 *
 * You can do the following operation any number of times:
 *
 * Pick any element from arr and increase or decrease it by 1.
 * Return the minimum number of operations such that the sum of each subarray of length k is equal.
 *
 * A subarray is a contiguous part of the array.
 */

public class Solution {

    @Tag({"斐蜀定理", "周期转换"})
    public long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length, g = gcd(n, k);

        long ans = 0;
        int[] group = new int[n / g];
        for (int i = 0; i < g; i++) {
            int len = 0;
            for (int j = i; j < n; j += g) {
                group[len++] = arr[j];
            }

            Arrays.sort(group);
            int mid = group[len / 2];
            for (int j = 0; j < len; j++) {
                ans += Math.abs(mid - group[j]);
            }
        }
        return ans;
    }

    private int gcd(int a, int b) { return a % b == 0 ? b : gcd(b, a % b); }

    public static void main(String[] args) {
        assert new Solution().makeSubKSumEqual(new int[]{6,2,8,5,7,10}, 4) == 10;
        assert new Solution().makeSubKSumEqual(new int[]{8,2,5,9,8,10}, 2) == 11;
        assert new Solution().makeSubKSumEqual(new int[]{2, 10, 9}, 1) == 8;
        assert new Solution().makeSubKSumEqual(new int[]{1, 10}, 1) == 9;

        assert new Solution().makeSubKSumEqual(new int[]{1,4,1,3}, 2) == 1;
        assert new Solution().makeSubKSumEqual(new int[]{2,5,5,7}, 3) == 5;
    }

}
