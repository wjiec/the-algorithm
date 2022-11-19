package offer2.p90;

/**
 * 剑指 Offer II 090. 环形房屋偷盗
 *
 * https://leetcode.cn/problems/PzWKhm/
 *
 * 一个专业的小偷，计划偷窃一个环形街道上沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 */

public class Solution {

    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length));
    }

    private int rob(int[] array, int l, int r) {
        int a = array[l], b = Math.max(array[l], array[l + 1]);
        for (int i = l + 2; i < r; i++) {
            int c = Math.max(b, a + array[i]);
            a = b; b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        assert new Solution().rob(new int[]{1}) == 1;
        assert new Solution().rob(new int[]{1,2}) == 2;

        assert new Solution().rob(new int[]{2,3,2}) == 3;
        assert new Solution().rob(new int[]{1,2,3,1}) == 4;
        assert new Solution().rob(new int[]{0}) == 0;
    }

}
