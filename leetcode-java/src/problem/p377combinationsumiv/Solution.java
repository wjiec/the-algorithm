package problem.p377combinationsumiv;

/**
 * 377. Combination Sum IV
 *
 * https://leetcode-cn.com/problems/combination-sum-iv/
 *
 * Given an array of distinct integers nums and a target integer target,
 * return the number of possible combinations that add up to target.
 *
 * The test cases are generated so that the answer can fit in a 32-bit integer.
 */

public class Solution {

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1]; dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (var n : nums) {
                if (n <= i) {
                    dp[i] += dp[i - n];
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
