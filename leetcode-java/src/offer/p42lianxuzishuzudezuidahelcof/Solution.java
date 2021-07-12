package offer.p42lianxuzishuzudezuidahelcof;

import java.util.Arrays;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 *
 * https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 *
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 */

public class Solution {

    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int[] dp = new int[nums.length]; dp[0] = nums[0];
        for (int i = 1, l = nums.length; i < l; i++) {
            if (dp[i - 1] < 0) dp[i] = nums[i];
            else dp[i] = dp[i - 1] + nums[i];

            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}) == 6;
    }

}
