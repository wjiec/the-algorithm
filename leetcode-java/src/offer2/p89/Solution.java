package offer2.p89;

/**
 * 剑指 Offer II 089. 房屋偷盗
 *
 * https://leetcode.cn/problems/Gu0c2T/
 *
 * 一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响小偷偷窃的唯一制约因素就是
 * 相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */

public class Solution {

    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1]; dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[nums.length];
    }

    public static void main(String[] args) {
        assert new Solution().rob(new int[]{1,2,3,1}) == 4;
        assert new Solution().rob(new int[]{2,7,9,3,1}) == 12;
    }

}
