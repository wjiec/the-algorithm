package weekly.w475.D;

import common.PrettyPrinter;

/**
 * Q4. Maximize Cyclic Partition Score
 *
 * https://leetcode.cn/contest/weekly-contest-475/problems/maximize-cyclic-partition-score/
 *
 * You are given a cyclic array nums and an integer k.
 *
 * Partition nums into at most k subarrays. As nums is cyclic, these subarrays may wrap around
 * from the end of the array back to the beginning.
 *
 * The range of a subarray is the difference between its maximum and minimum values.
 * The score of a partition is the sum of subarray ranges.
 *
 * Return the maximum possible score among all cyclic partitions.
 */

public class Solution {

    public long maximumScore(int[] nums, int k) {
        int[] dup = new int[nums.length << 1];
        System.arraycopy(nums, 0, dup, 0, nums.length);
        System.arraycopy(nums, 0, dup, nums.length, nums.length);
        PrettyPrinter.println(dup);

        // 可以划分为 [1, k] 个子数组

        return 1;
    }

    public static void main(String[] args) {
        assert new Solution().maximumScore(new int[]{1, 2, 3, 3}, 2) == 3;
        assert new Solution().maximumScore(new int[]{1, 2, 3, 3}, 1) == 2;
        assert new Solution().maximumScore(new int[]{1, 2, 3, 3}, 4) == 3;
    }

}
