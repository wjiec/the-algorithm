package weekly.w425.C;

import java.util.HashMap;
import java.util.Map;

/**
 * 3366. Minimum Array Sum
 *
 * https://leetcode.cn/contest/weekly-contest-425/problems/minimum-array-sum/
 *
 * You are given an integer array nums and three integers k, op1, and op2.
 *
 * You can perform the following operations on nums:
 *
 * Operation 1: Choose an index i and divide nums[i] by 2, rounding up to the nearest whole number.
 * You can perform this operation at most op1 times, and not more than once per index.
 *
 * Operation 2: Choose an index i and subtract k from nums[i], but only if nums[i] is greater than
 * or equal to k. You can perform this operation at most op2 times, and not more than once per index.
 *
 * Note: Both operations can be applied to the same index, but at most once each.
 *
 * Return the minimum possible sum of all elements in nums after performing any number of operations.
 */

public class Solution {

    public int minArraySum(int[] nums, int k, int op1, int op2) {
        return dfs(nums, 0, k, op1, op2);
    }

    private final Map<Integer, Integer> memo = new HashMap<>();

    private int dfs(int[] nums, int i, int k, int op1, int op2) {
        if (i == nums.length) return 0;

        int key = (i << 20) | (op1 << 10) | op2;
        if (memo.containsKey(key)) return memo.get(key);

        int ans = nums[i] + dfs(nums, i + 1, k, op1, op2);
        // 当前位置可以执行 op1
        if (op1 > 0) {
            int after = (nums[i] + 1) / 2;
            ans = Math.min(ans, after + dfs(nums, i + 1, k, op1 - 1, op2));
        }

        // 当前位置可以执行 op2
        if (op2 > 0 && nums[i] >= k) {
            int after = nums[i] - k;
            ans = Math.min(ans, after + dfs(nums, i + 1, k, op1, op2 - 1));
        }

        // 当前位置可以执行 op1 和 op2
        if (op1 > 0 && op2 > 0 && nums[i] >= k) {
            int after = (nums[i] - k + 1) / 2;
            // 检查是否可以先执行 op1 再执行 op2
            if ((nums[i] + 1) / 2 >= k) after = Math.min(after, (nums[i] + 1) / 2 - k);
            ans = Math.min(ans, after + dfs(nums, i + 1, k, op1 - 1, op2 - 1));
        }

        memo.put(key, ans);
        return ans;
    }

    private static class Iteration {
        public int minArraySum(int[] nums, int k, int op1, int op2) {
            // dp[i][j][k] 表示在 [0, i) 中使用 i 次 op1 和 k 次 op2 可以得到的最小数组和是多少
            //  - 初始条件下, 如果没有任何数, 不管 op1 和 op2 是多少, dp[0][j][k] 的值都是 0
            int[][][] dp = new int[nums.length + 1][op1 + 1][op2 + 1];
            for (int i = 1; i <= nums.length; i++) {
                int v = nums[i - 1];

                for (int j = 0; j <= op1; j++) {
                    for (int kk = 0; kk <= op2; kk++) {
                        dp[i][j][kk] = dp[i - 1][j][kk] + v;
                        if (j - 1 >= 0) dp[i][j][kk] = Math.min(dp[i][j][kk], dp[i - 1][j - 1][kk] + (v + 1) / 2);
                        if (v >= k && kk - 1 >= 0) dp[i][j][kk] = Math.min(dp[i][j][kk], dp[i - 1][j][kk - 1] + (v - k));
                        if (v >= k && j - 1 >= 0 && kk - 1 >= 0) dp[i][j][kk] = Math.min(dp[i][j][kk], dp[i - 1][j - 1][kk - 1] + (v - k + 1) / 2);
                        // 如果两个都执行的话, 有以下可选的方案:
                        //  - 先执行 op1, 再执行 op2: (v / 2) - k
                        //  - 先执行 op2, 再执行 op1: (v - k) / 2, 实际上等于 v / 2 - k / 2
                        // 这种情况下, 肯定是 [先执行 op1, 再执行 op2] 得到的值更小
                        if ((v + 1) / 2 >= k && j - 1 >= 0 && kk - 1 >= 0) dp[i][j][kk] = Math.min(dp[i][j][kk], dp[i - 1][j - 1][kk - 1] + (v + 1) / 2 - k);
                    }
                }
            }

            return dp[nums.length][op1][op2];
        }
    }

    public static void main(String[] args) {
        assert new Iteration().minArraySum(new int[]{2,8,3,19,3}, 3, 1, 1) == 23;
        assert new Iteration().minArraySum(new int[]{2,4,3}, 3, 2, 1) == 3;
        assert new Iteration().minArraySum(new int[]{6,8,3}, 8, 1, 3) == 6;

        assert new Solution().minArraySum(new int[]{2,8,3,19,3}, 3, 1, 1) == 23;
        assert new Solution().minArraySum(new int[]{2,4,3}, 3, 2, 1) == 3;
        assert new Solution().minArraySum(new int[]{6,8,3}, 8, 1, 3) == 6;
    }

}
