package problem.p1508rangesumofsortedsubarraysums;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1508. Range Sum of Sorted Subarray Sums
 *
 * https://leetcode.cn/problems/range-sum-of-sorted-subarray-sums/
 *
 * You are given the array nums consisting of n positive integers.
 * You computed the sum of all non-empty continuous subarrays from the
 * array and then sorted them in non-decreasing order, creating a new
 * array of n * (n + 1) / 2 numbers.
 *
 * Return the sum of the numbers from index left to index right (indexed
 * from 1), inclusive, in the new array. Since the answer can be a huge
 * number return it modulo 109 + 7.
 */

public class Solution {

    private static class Bucket {
        private int r, v;
        private final int l;
        private final int[] sum;
        public Bucket(int[] sum, int i) {
            this.sum = sum; l = r = i;
            v = sum[r + 1] - sum[l];
        }
        public boolean next() {
            if (++r < sum.length - 1) {
                v = sum[r + 1] - sum[l];
                return true;
            }
            return false;
        }
    }

    public int rangeSum(int[] nums, int n, int left, int right) {
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        PriorityQueue<Bucket> buckets = new PriorityQueue<>(Comparator.comparingInt(v -> v.v));
        for (int i = 0; i < nums.length; i++) {
            buckets.add(new Bucket(sum, i));
        }

        long ans = 0;
        for (int i = 1; i <= right; i++) {
            Bucket bucket = buckets.remove();
            if (i >= left) ans += bucket.v;
            if (bucket.next()) buckets.add(bucket);
        }

        return (int) (ans % 1_000_000_007);
    }

    public static void main(String[] args) {
        assert new Solution().rangeSum(new int[]{1,2,3,4}, 4, 1, 5) == 13;
        assert new Solution().rangeSum(new int[]{1,2,3,4}, 4, 3, 4) == 6;
        assert new Solution().rangeSum(new int[]{1,2,3,4}, 4, 1, 10) == 50;
    }

}
