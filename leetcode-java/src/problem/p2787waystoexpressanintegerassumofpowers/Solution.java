package problem.p2787waystoexpressanintegerassumofpowers;

import ability.Benchmark;

import java.util.ArrayList;
import java.util.List;

/**
 * 2787. Ways to Express an Integer as Sum of Powers
 *
 * https://leetcode.cn/problems/ways-to-express-an-integer-as-sum-of-powers/
 *
 * Given two positive integers n and x.
 *
 * Return the number of ways n can be expressed as the sum of the xth power of unique positive
 * integers, in other words, the number of sets of unique integers [n1, n2, ..., nk] where
 * n = n1x + n2x + ... + nkx.
 *
 * Since the result can be very large, return it modulo 109 + 7.
 *
 * For example, if n = 160 and x = 3, one way to express n is n = 23 + 33 + 53.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static final int MOD = 1_000_000_007;

    // 互不相同的正整数的 x 次幂之和为 n 的方案数
    public int numberOfWays(int n, int x) {
        List<Integer> powers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if ((long) Math.pow(i, x) > n) break;
            powers.add((int) Math.pow(i, x));
        }

        // powers 中最多为300个, 最少为 1 个
        // 答案即从 powers 中选出 k 个数且和为 n 的方案数

        // dp[i][j] 表示使用 [0, i] 且 i 必须使用的和为 j 的方案数
        long[][] dp = new long[powers.size() + 1][n + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= powers.size(); i++) {
            int curr = powers.get(i - 1);
            // 枚举加上 curr 之后的结果值
            for (int j = curr; j <= n; j++) {
                // 从前面的状态进行转移
                for (int k = 0; k < i; k++) {
                    dp[i][j] += dp[k][j - curr];
                }
            }
        }

        long ans = 0;
        for (var row : dp) ans += row[n];
        return (int) (ans % MOD);
    }

    private static class StateCompress {

        public int numberOfWays(int n, int x) {
            List<Integer> powers = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if ((long) Math.pow(i, x) > n) break;
                powers.add((int) Math.pow(i, x));
            }

            // dp[i][j] 表示使用 [0, i] 且 i 必须使用的和为 j 的方案数
            long[] dp = new long[n + 1]; dp[0] = 1;
            for (int i = 1; i <= powers.size(); i++) {
                int curr = powers.get(i - 1);
                for (int j = n; j >= curr; j--) {
                    dp[j] += dp[j - curr];
                }
            }

            return (int) (dp[n] % 1_000_000_007);
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().numberOfWays(10, 2) == 1;
            assert new Solution().numberOfWays(4, 1) == 2;
        });

        Benchmark.benchmark("compress", () -> {
            assert new StateCompress().numberOfWays(10, 2) == 1;
            assert new StateCompress().numberOfWays(4, 1) == 2;
        });
    }

}
