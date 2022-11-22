package offer2.p103;

import java.util.Arrays;

/**
 * 剑指 Offer II 103. 最少的硬币数目
 *
 * https://leetcode.cn/problems/gaM7Ch/
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final int INF = Integer.MAX_VALUE / 2;

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i <= amount; i++) {
            if (dp[i] == INF) continue;
            for (var coin : coins) {
                if (coin > amount) continue;
                int next = i + coin;
                if (next <= amount) {
                    dp[next] = Math.min(dp[next], dp[i] + 1);
                }
            }
        }
        return dp[amount] == INF ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        assert new Solution().coinChange(new int[]{1, 2147483647}, 2) == 2;

        assert new Solution().coinChange(new int[]{1, 2, 5}, 11) == 3;
        assert new Solution().coinChange(new int[]{2}, 3) == -1;
        assert new Solution().coinChange(new int[]{1}, 0) == 0;
        assert new Solution().coinChange(new int[]{1}, 1) == 1;
        assert new Solution().coinChange(new int[]{1}, 2) == 2;
    }

}
