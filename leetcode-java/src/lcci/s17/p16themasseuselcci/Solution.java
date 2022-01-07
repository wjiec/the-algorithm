package lcci.s17.p16themasseuselcci;

/**
 * 面试题 17.16. 按摩师
 *
 * https://leetcode-cn.com/problems/the-masseuse-lcci/
 *
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
 *
 * 给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 */

public class Solution {

    public int massage(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int ans = 0;
        int[] dp = new int[nums.length + 1]; dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = nums[i - 1] + Math.max(dp[i - 2], i > 2 ? dp[i - 3] : 0);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().massage(new int[]{1,2,3,1}) == 4;
        assert new Solution().massage(new int[]{2,7,9,3,1}) == 12;
        assert new Solution().massage(new int[]{2,1,4,5,3,1,1,3}) == 12;
    }

}
