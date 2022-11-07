package problem.p2461maximumsumofdistinctsubarrayswithlengthk;

import java.util.HashMap;
import java.util.Map;

/**
 * 2461. Maximum Sum of Distinct Subarrays With Length K
 *
 * https://leetcode.cn/problems/maximum-sum-of-distinct-subarrays-with-length-k/
 *
 * You are given an integer array nums and an integer k. Find the maximum subarray sum of all
 * the subarrays of nums that meet the following conditions:
 *
 * The length of the subarray is k, and
 * All the elements of the subarray are distinct.
 * Return the maximum subarray sum of all the subarrays that meet the conditions.
 * If no subarray meets the conditions, return 0.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public long maximumSubarraySum(int[] nums, int k) {
        long curr = 0, ans = 0; int n = nums.length;
        Map<Integer, Integer> unique = new HashMap<>();
        for (int l = 0, r = 0; r < n; r++) {
            curr += nums[r];
            unique.merge(nums[r], 1, Integer::sum);

            for (; r - l + 1 > k; l++) {
                curr -= nums[l];
                unique.merge(nums[l], -1, (old, val) -> (old + val == 0 ? null : old + val));
            }

            if (r - l + 1 == k && unique.size() == k) ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumSubarraySum(new int[]{1,1,1,7,8,9}, 3) == 24;
        assert new Solution().maximumSubarraySum(new int[]{1,5,4,2,9,9,9}, 3) == 15;
        assert new Solution().maximumSubarraySum(new int[]{4,4,4}, 3) == 0;
    }

}
