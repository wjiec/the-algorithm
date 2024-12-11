package weekly.w427.C;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 3381. Maximum Subarray Sum With Length Divisible by K
 *
 * https://leetcode.cn/contest/weekly-contest-427/problems/maximum-subarray-sum-with-length-divisible-by-k/
 *
 * You are given an array of integers nums and an integer k.
 *
 * Return the maximum sum of a non-empty subarray of nums, such that the size of the subarray is divisible by k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

/** @noinspection unchecked*/
public class Solution {

    public long maxSubarraySum(int[] nums, int k) {
        // 首先求一次前缀和 sum[i] = sum[i - 1] + nums[i]
        // 然后根据下标对 k 取整并分组, 组内找到最大的两个数之差
        long sum = 0, ans = Long.MIN_VALUE;
        TreeSet<Long>[] groups = new TreeSet[k];
        Arrays.setAll(groups, i -> new TreeSet<>());
        for (int i = 0; i <= nums.length; i++) {
            if (i > 0) sum += nums[i - 1];

            var curr = groups[i % k];
            if (!curr.isEmpty()) ans = Math.max(ans, Math.max(sum - curr.first(), sum - curr.last()));
            groups[i % k].add(sum);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
