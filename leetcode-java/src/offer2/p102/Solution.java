package offer2.p102;

/**
 * 剑指 Offer II 102. 加减的目标值
 *
 * https://leetcode.cn/problems/YaVDxD/
 *
 * 给定一个正整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 */

public class Solution {

    private int ans = 0;

    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, 0, target);
        return ans;
    }

    private void dfs(int[] array, int idx, int target) {
        if (idx == array.length) {
            if (target == 0) ans++;
            return;
        }

        dfs(array, idx + 1, target - array[idx]);
        dfs(array, idx + 1, target + array[idx]);
    }

    private static class DynamicProgramming {
        public int findTargetSumWays(int[] nums, int target) {
            int sum = -target;
            for (var v : nums) sum += v;
            if (sum < 0 || sum % 2 != 0) return 0;

            int n = nums.length, half = sum / 2;
            int[][] dp = new int[n + 1][half + 1];
            dp[0][0] = 1;

            for (int i = 1; i <= n; i++) {
                int v = nums[i - 1];
                for (int j = 0; j <= half; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= v) dp[i][j] += dp[i - 1][j - v];
                }
            }

            return dp[n][half];
        }
    }

    public static void main(String[] args) {
        assert new Solution().findTargetSumWays(new int[]{1,1,1,1,1}, 3) == 5;
        assert new Solution().findTargetSumWays(new int[]{1}, 1) == 1;

        assert new DynamicProgramming().findTargetSumWays(new int[]{1,1,1,1,1}, 3) == 5;
        assert new DynamicProgramming().findTargetSumWays(new int[]{1}, 1) == 1;
    }

}
