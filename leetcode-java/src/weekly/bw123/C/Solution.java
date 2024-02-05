package weekly.bw123.C;

import java.util.HashMap;
import java.util.Map;

/**
 * 3026. Maximum Good Subarray Sum
 *
 * https://leetcode.cn/contest/biweekly-contest-123/problems/maximum-good-subarray-sum/
 *
 * You are given an array nums of length n and a positive integer k.
 *
 * A subarray of nums is called good if the absolute difference between its
 * first and last element is exactly k, in other words, the subarray
 * nums[i..j] is good if |nums[i] - nums[j]| == k.
 *
 * Return the maximum sum of a good subarray of nums. If there are no good subarrays, return 0.
 */

public class Solution {

    public long maximumSubarraySum(int[] nums, int k) {
        long ans = Long.MIN_VALUE, sum = 0;
        // {k: v} 是当值为k时的最小和是v
        Map<Long, Long> minSum = new HashMap<>();
        for (var v : nums) {
            // |a[i] - a[j]| = k
            //      -> a[i] = a[j] + k
            //      -> a[i] = a[j] - k
            long l = v - k, r = v + k;

            if (minSum.containsKey(l)) ans = Math.max(ans, sum + v - minSum.get(l));
            if (minSum.containsKey(r)) ans = Math.max(ans, sum + v - minSum.get(r));
            minSum.merge((long) v, sum, Long::min); sum += v;
        }
        return ans == Long.MIN_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maximumSubarraySum(new int[]{1,5}, 2));
        System.out.println(new Solution().maximumSubarraySum(new int[]{1,2,3,4,5,6}, 1));
        System.out.println(new Solution().maximumSubarraySum(new int[]{-999999998,-999999999,-999999999,-1000000000}, 2));
    }

}
