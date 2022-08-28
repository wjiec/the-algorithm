package problem.p2386findtheksumofanarray;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2386. Find the K-Sum of an Array
 *
 * https://leetcode.cn/problems/find-the-k-sum-of-an-array/
 *
 * You are given an integer array nums and a positive integer k.
 * You can choose any subsequence of the array and sum all of its elements together.
 *
 * We define the K-Sum of the array as the kth largest subsequence sum that
 * can be obtained (not necessarily distinct).
 *
 * Return the K-Sum of the array.
 *
 * A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining elements.
 *
 * Note that the empty subsequence is considered to have a sum of 0.
 */

public class Solution {

    public long kSum(int[] nums, int k) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) sum += nums[i];
            else nums[i] = -nums[i];
        }

        // [sum, index]
        PriorityQueue<long[]> q = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));
        q.add(new long[]{sum, 0});

        Arrays.sort(nums);
        while (--k > 0) {
            long[] curr = q.remove();
            int i = (int) curr[1];
            if (i < nums.length) {
                q.add(new long[]{curr[0] - nums[i], i + 1});
                if (i != 0) q.add(new long[]{curr[0] - nums[i] + nums[i - 1], i + 1});
            }
        }
        return q.remove()[0];
    }

    public static void main(String[] args) {
        assert new Solution().kSum(new int[]{1000,1001,1002,1003,1004,1005,1006,1007,1008,1009}, 10) == 9037;

        assert new Solution().kSum(new int[]{2,4,-2}, 5) == 2;
        assert new Solution().kSum(new int[]{1,-2,3,4,-10,12}, 16) == 10;
    }

}
