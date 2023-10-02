package weekly.w365.B;

/**
 * 2874. Maximum Value of an Ordered Triplet II
 *
 * https://leetcode.cn/contest/weekly-contest-365/problems/maximum-value-of-an-ordered-triplet-ii/
 *
 * You are given a 0-indexed integer array nums.
 *
 * Return the maximum value over all triplets of indices (i, j, k) such that i < j < k.
 * If all such triplets have a negative value, return 0.
 *
 * The value of a triplet of indices (i, j, k) is equal to (nums[i] - nums[j]) * nums[k].
 */

public class Solution {

    public long maximumTripletValue(int[] nums) {
        int[] maxSuffix = new int[nums.length + 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            maxSuffix[i] = Math.max(maxSuffix[i + 1], nums[i]);
        }

        long ans = 0, preMax = nums[0];
        for (int j = 1; j < nums.length; j++) {
            ans = Math.max(ans, (preMax - nums[j]) * maxSuffix[j + 1]);
            preMax = Math.max(preMax, nums[j]);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
