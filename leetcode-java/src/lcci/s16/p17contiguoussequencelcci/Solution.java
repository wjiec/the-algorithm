package lcci.s16.p17contiguoussequencelcci;

/**
 * 面试题 16.17. 连续数列
 *
 * https://leetcode-cn.com/problems/contiguous-sequence-lcci/
 *
 * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
 */

public class Solution {

    public int maxSubArray(int[] nums) {
        int ans = nums[0], curr = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr += nums[i];
            if (curr < nums[i]) {
                curr = nums[i];
            }
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}) == 6;
    }

}
