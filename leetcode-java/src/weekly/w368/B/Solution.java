package weekly.w368.B;

import java.util.TreeMap;

/**
 * 2909. Minimum Sum of Mountain Triplets II
 *
 * https://leetcode.cn/contest/weekly-contest-368/problems/minimum-sum-of-mountain-triplets-ii/
 *
 * You are given a 0-indexed array nums of integers.
 *
 * A triplet of indices (i, j, k) is a mountain if:
 *
 * i < j < k
 * nums[i] < nums[j] and nums[k] < nums[j]
 * Return the minimum possible sum of a mountain triplet of nums. If no such triplet exists, return -1.
 */

public class Solution {

    public int minimumSum(int[] nums) {
        TreeMap<Integer, Integer> suffix = new TreeMap<>();
        for (var v : nums) suffix.merge(v, 1, Integer::sum);

        int ans = Integer.MAX_VALUE;
        TreeMap<Integer, Integer> prefix = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            suffix.merge(nums[i], -1, (a, b) -> a + b == 0 ? null : (a + b));
            if (!prefix.isEmpty() && !suffix.isEmpty() && prefix.firstKey() < nums[i] && suffix.firstKey() < nums[i]) {
                ans = Math.min(ans, nums[i] + prefix.firstKey() + suffix.firstKey());
            }
            prefix.merge(nums[i], 1, Integer::sum);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
    }

}
