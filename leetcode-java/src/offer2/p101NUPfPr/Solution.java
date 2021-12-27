package offer2.p101NUPfPr;

import java.util.Arrays;

/**
 * 剑指 Offer II 101. 分割等和子集
 *
 * 给定一个非空的正整数数组 nums ，请判断能否将这些数字分成元素和相等的两部分。
 */

public class Solution {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (var n : nums) sum += n;
        if (sum % 2 != 0) return false;

        int half = sum / 2;
        boolean[] dp = new boolean[half + 1]; dp[0] = true;
        for (int num : nums) {
            for (int j = half; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[half];
    }

    public static void main(String[] args) {
        assert new Solution().canPartition(new int[]{2,2,1,1});

        assert new Solution().canPartition(new int[]{1,5,11,5});
        assert !new Solution().canPartition(new int[]{1,2,3,5});
    }

}
