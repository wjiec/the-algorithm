package weekly.w455.B;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * Q2. Inverse Coin Change
 *
 * https://leetcode.cn/contest/weekly-contest-455/problems/inverse-coin-change/
 *
 * You are given a 1-indexed integer array numWays, where numWays[i] represents the number of ways
 * to select a total amount i using an infinite supply of some fixed coin denominations.
 *
 * Each denomination is a positive integer with value at most numWays.length.
 *
 * However, the exact coin denominations have been lost. Your task is to recover
 * the set of denominations that could have resulted in the given numWays array.
 *
 * Return a sorted array containing unique integers which represents this set of denominations.
 *
 * If no such set exists, return an empty array.
 */

public class Solution {

    // numWays[i] 表示凑出金额 i 的方法数
    //  - 如果方法数为 0, 则说明没有这个面值的硬币
    //  - 如果方法数为 1, 则说明恰好有 i + 1 这个面值的硬币
    public List<Integer> findCoins(int[] numWays) {
        // 尝试找到一个可行的组合
        int[] ans = new int[numWays.length + 1];
        dfs(numWays, 1, ans);

        List<Integer> copy = new ArrayList<>();
        for (int i = 0; i < coinsLen; i++) copy.add(ans[i]);
        return copy;
    }

    private int coinsLen = 0;

    private boolean dfs(int[] numWays, int money, int[] coins) {
        if (money > numWays.length) return true;

        int ways = numWays[money - 1];
        // 没有凑成金额 money 的方案
        if (ways == 0) {
            if (check(coins, money) != 0) return false;
            return dfs(numWays, money + 1, coins);
        }

        // 先检查是否能通过现有的硬币凑成金额 money
        if (check(coins, money) == ways) {
            if (dfs(numWays, money + 1, coins)) return true;
        }

        // 否则我们需要尝试增加新的硬币
        //  - 我们增加的硬币只能是 <= i 的, 而且重复添加没有意义
        int start = coinsLen == 0 ? 1 : Math.max(coins[coinsLen - 1] + 1, money);
        for (int newCoin = start; newCoin <= money; newCoin++) {
            if (numWays[newCoin - 1] == 0) continue;

            coins[coinsLen++] = newCoin;
            if (check(coins, money) == ways) {
                if (dfs(numWays, money + 1, coins)) return true;
            }
            coinsLen--;
        }
        return false;
    }

    // 检查通过 coins 凑出金额 target 的方法数
    private int check(int[] coins, int target) {
        int[] dp = new int[target + 1]; dp[0] = 1;
        for (int j = 0; j < coinsLen; j++) {
            int coin = coins[j];
            for (int i = coin; i <= target; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().findCoins(new int[]{0,1,0,2,0,3,0,4,0,5}));
        PrettyPrinter.println(new Solution().findCoins(new int[]{1,2,2,3,4}));
        PrettyPrinter.println(new Solution().findCoins(new int[]{1,2,3,4,15}));
    }

}
