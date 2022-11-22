package offer2.p104;

/**
 * 剑指 Offer II 104. 排列的数目
 *
 * https://leetcode.cn/problems/D0F0SV/
 *
 * 给定一个由 不同 正整数组成的数组 nums ，和一个目标整数 target 。请从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 数组中的数字可以在一次排列中出现任意次，但是顺序不同的序列被视作不同的组合。
 *
 * 题目数据保证答案符合 32 位整数范围。
 */

public class Solution {

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1]; dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (var num : nums) {
                if (num <= target && i + num <= target) {
                    dp[i + num] += dp[i];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        assert new Solution().combinationSum4(new int[]{1,2,3}, 4) == 7;
        assert new Solution().combinationSum4(new int[]{9}, 3) == 0;
    }

}
