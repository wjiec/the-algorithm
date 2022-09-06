package problem.p276paintfence;

import common.Tag;

/**
 * 276. Paint Fence
 *
 * https://leetcode.cn/problems/paint-fence/
 *
 * You are painting a fence of n posts with k different colors.
 * You must paint the posts following these rules:
 *
 * Every post must be painted exactly one color.
 * There cannot be three or more consecutive posts with the same color.
 * Given the two integers n and k, return the number of ways you can paint the fence.
 */

public class Solution {

    @Tag({"涂色", "最多两个相同"})
    public int numWays(int n, int k) {
        int[] dp = new int[n + 1];
        dp[1] = k; if (n > 1) dp[2] = k * k;
        for (int i = 3; i <= n; i++) {
            // 可以涂与 i-1 个栅栏不同的颜色
            dp[i] = dp[i - 1] * (k - 1);
            // 涂与 i-1 个栅栏相同的颜色, 但是
            // 需要 i-1 与 i-2 栅栏的颜色不相同
            dp[i] += dp[i - 2] * (k - 1);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        assert new Solution().numWays(3, 2) == 6;
        assert new Solution().numWays(1, 1) == 1;
        assert new Solution().numWays(7, 2) == 42;
    }

}
