package weekly.w421.C;

import common.PrettyPrinter;

import java.util.HashMap;
import java.util.Map;

import static ability.Ability.Math.gcd;

/**
 * 3336. Find the Number of Subsequences With Equal GCD
 *
 * https://leetcode.cn/contest/weekly-contest-421/problems/find-the-number-of-subsequences-with-equal-gcd/
 *
 * You are given an integer array nums.
 *
 * Your task is to find the number of pairs of non-empty subsequences (seq1, seq2) of nums
 * that satisfy the following conditions:
 *
 * The subsequences seq1 and seq2 are disjoint, meaning no index of nums is common between them.
 * The GCD of the elements of seq1 is equal to the GCD of the elements of seq2.
 * Return the total number of such pairs.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 */

public class Solution {

    // 对于一个数来说, 他只有可能是以下三种情况
    //  - 不在任何一个子序列中
    //  - 在第一个子序列中
    //  - 在第二个子序列中
    public int subsequencePairCount(int[] nums) {
        // 如果什么数都不选, 最后 a = b = 0 的情况需要排除掉
        return (dfs(nums, 0, 0, 0) - 1 + MOD) % MOD;
    }

    private final int MOD = 1_000_000_007;
    private final Map<Integer, Integer> memo = new HashMap<>();

    // 返回值表示当前在位置 i, 同时第一个子序列的最大公约数是 a, 第
    // 二个子序列的最大公约数是 b 的方案数
    private int dfs(int[] nums, int i, int a, int b) {
        if (i == nums.length) return a == b ? 1 : 0;

        int key = (i << 16) | (a << 8) | b;
        if (memo.containsKey(key)) return memo.get(key);

        int ans = dfs(nums, i + 1, a, b); // 选择是直接跳过当前数
        ans = (ans + dfs(nums, i + 1, gcd(a, nums[i]), b)) % MOD; // 选择加入第一个子序列
        ans = (ans + dfs(nums, i + 1, a, gcd(b, nums[i]))) % MOD; // 选择加入第二个子序列

        memo.put(key, ans);
        return ans;
    }

    private static class Recurrence {
        public int subsequencePairCount(int[] nums) {
            int MOD = 1_000_000_007;
            int max = 0, n = nums.length;
            for (var v : nums) max = Math.max(max, v);

            int[][][] dp = new int[n + 1][max + 1][max + 1];
            dp[0][0][0] = 1;

            // 枚举所有的数
            for (int i = 1; i <= n; i++) {
                int x = nums[i - 1];

                // 枚举第一个子序列可能的值
                for (int a = 0; a <= max; a++) {
                    // 枚举第二个子序列可能的值
                    for (int b = 0; b <= max; b++) {
                        dp[i][a][b] = (dp[i][a][b] + dp[i - 1][a][b]) % MOD;
                        dp[i][gcd(a, x)][b] = (dp[i][gcd(a, x)][b] + dp[i - 1][a][b]) % MOD;
                        dp[i][a][gcd(b, x)] = (dp[i][a][gcd(b, x)] + dp[i - 1][a][b]) % MOD;
                    }
                }
            }

            PrettyPrinter.println(dp);

            int ans = 0;
            for (int j = 1; j <= max; j++) ans = (ans + dp[n][j][j]) % MOD;
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Recurrence().subsequencePairCount(new int[]{1, 2, 3, 4}) == 10;
        assert new Recurrence().subsequencePairCount(new int[]{10, 20, 30}) == 2;
    }

}
