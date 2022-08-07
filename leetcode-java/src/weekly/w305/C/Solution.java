package weekly.w305.C;

/**
 * 6137. Check if There is a Valid Partition For The Array
 *
 * https://leetcode.cn/contest/weekly-contest-305/problems/check-if-there-is-a-valid-partition-for-the-array/
 *
 * You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous subarrays.
 *
 * We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:
 *
 * The subarray consists of exactly 2 equal elements. For example, the subarray [2,2] is good.
 * The subarray consists of exactly 3 equal elements. For example, the subarray [4,4,4] is good.
 * The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between adjacent
 * elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.
 *
 * Return true if the array has at least one valid partition. Otherwise, return false.
 */

public class Solution {

    public boolean validPartition(int[] nums) {
        boolean[] dp = new boolean[nums.length + 1];
        dp[0] = true;
        // [0, i) 位能不能有效划分
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = dp[i - 2] && (nums[i - 2] == nums[i - 1]);
            if (i != 2 && !dp[i]) {
                dp[i] = dp[i] || (dp[i - 3] && (nums[i - 3] == nums[i - 2]) && (nums[i - 2] == nums[i - 1]));
                dp[i] = dp[i] || (dp[i - 3] && (nums[i - 2] - nums[i - 3] == 1) && (nums[i - 1] - nums[i - 2] == 1));
            }
        }
        return dp[nums.length];
    }

    public static void main(String[] args) {
        assert new Solution().validPartition(new int[]{4,4,4,5,6});
        assert !new Solution().validPartition(new int[]{1,1,1,2});
    }

}
