package lcci.s8.p11coinlcci;

import common.PrettyPrinter;

/**
 * 面试题 08.11. 硬币
 *
 * https://leetcode.cn/problems/coin-lcci/
 *
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。
 * (结果可能会很大，你需要将结果模上1000000007)
 */

public class Solution {

    public int waysToChange(int n) {
        final int MOD = 1_000_000_007;
        final int[] coins = new int[]{1, 5, 10, 25};
        int[] dp = new int[n + 1]; dp[0] = 1;
        for(int coin : coins) {
            for(int i = coin; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % MOD;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        assert new Solution().waysToChange(5) == 2;
        assert new Solution().waysToChange(10) == 4;
    }

}
