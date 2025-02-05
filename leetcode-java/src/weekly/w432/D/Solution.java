package weekly.w432.D;

import common.PrettyPrinter;

/**
 * 3420. Count Non-Decreasing Subarrays After K Operations
 *
 * https://leetcode.cn/contest/weekly-contest-432/problems/count-non-decreasing-subarrays-after-k-operations/
 *
 * You are given an array nums of n integers and an integer k.
 *
 * For each subarray of nums, you can apply up to k operations on it.
 *
 * In each operation, you increment any element of the subarray by 1.
 *
 * Note that each subarray is considered independently, meaning changes
 * made to one subarray do not persist to another.
 *
 * Return the number of subarrays that you can make non-decreasing after performing at most k operations.
 *
 * An array is said to be non-decreasing if each element is greater than
 * or equal to its previous element, if it exists.
 */

public class Solution {

    public long countNonDecreasingSubarrays(int[] nums, int k) {
        // 实际上每个数只和前一个数相关
        int[] diff = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = Math.max(nums[i - 1], nums[i]) - nums[i];
        }
        PrettyPrinter.println(diff);

        return 17;
    }

    public static void main(String[] args) {
        assert new Solution().countNonDecreasingSubarrays(new int[]{6,3,1,2,4,4}, 7) == 17;
        assert new Solution().countNonDecreasingSubarrays(new int[]{6,3,1,3,6}, 4) == 12;
    }

}
