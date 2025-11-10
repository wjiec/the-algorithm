package weekly.w467.D;

import common.PrettyPrinter;

/**
 * Q4. Number of Stable Subsequences
 *
 * https://leetcode.cn/contest/weekly-contest-467/problems/number-of-stable-subsequences/
 *
 * You are given an integer array nums.
 *
 * A subsequence is stable if it does not contain three consecutive elements with the same parity
 * when the subsequence is read in order (i.e., consecutive inside the subsequence).
 *
 * Return the number of stable subsequences.
 *
 * Since the answer may be too large, return it modulo 1e9 + 7.
 */

public class Solution {

    public int countStableSubsequences(int[] nums) {
        final int n = nums.length, MOD = 1_000_000_007;
        // 考虑最后一个元素 x 选或者不选
        //  - 不选: 变为在 [0, n - 2] 中有多少个稳定子序列
        //  - 选:
        //      - x 单独作为一个子序列
        //      - x 是偶数
        //          - 可以添加到末尾为奇数的子序列里, 也就是从 [0, n - 2] 里有多少个末尾为奇数的子序列
        //          - 可以添加到末尾为奇数、偶数的子序列里(不能出现连续 3 个偶数), 也就是从 [0, n - 2] 里有多少个末尾为奇数, 偶数的子序列
        //      - x 是奇数
        //          - 可以添加到末尾为偶数的子序列里, 也就是从 [0, n - 2] 里有多少个末尾为偶数的子序列
        //          - 可以添加到末尾为偶数、奇数的子序列里(不能出现连续 3 个奇数), 也就是从 [0, n - 2] 里有多少个末尾为偶数, 奇数的子序列

        // dp[i][j][k] 表示在[0, i), 末尾元素的奇偶性为 j, 且末尾恰好有连续 k 个奇偶性都为 j 的元素
        //  - 也就是第 k + 1 个元素的奇偶性与 x 相反
        long[][][] dp = new long[n + 1][2][3];
        for (int i = 1; i <= n; i++) {
            // 设当前元素 nums[i - 1] 的奇偶性为 x
            int x = nums[i - 1] & 1;
            // 当 j == 1 时, 表示只能从末尾为 x ^ 1 的转移过来; 或者是自己单独作为一个字序列
            dp[i][x][1] = (dp[i - 1][x][1] + dp[i - 1][x ^ 1][1] + dp[i - 1][x ^ 1][2] + 1) % MOD;
            // 当 j == 2 时, 表示可以从末尾为 x 且出现次数为 1 转移过来
            dp[i][x][2] = (dp[i - 1][x][2] + dp[i - 1][x][1]) % MOD;

            // 继承非当前奇偶性的答案
            dp[i][x ^ 1][1] = dp[i - 1][x ^ 1][1];
            dp[i][x ^ 1][2] = dp[i - 1][x ^ 1][2];
        }

        return (int) ((dp[n][0][1] + dp[n][0][2] + dp[n][1][1] + dp[n][1][2]) % MOD);
    }

    public static void main(String[] args) {
        assert new Solution().countStableSubsequences(new int[]{2,3,4,2}) == 14;
    }

}
