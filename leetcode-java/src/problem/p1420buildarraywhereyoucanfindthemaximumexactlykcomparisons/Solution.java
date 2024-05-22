package problem.p1420buildarraywhereyoucanfindthemaximumexactlykcomparisons;

import ability.Benchmark;

import java.util.Arrays;

/**
 * 1420. Build Array Where You Can Find The Maximum Exactly K Comparisons
 *
 * https://leetcode.cn/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons
 *
 * You are given three integers n, m and k. Consider the following algorithm to
 * find the maximum element of an array of positive integers:
 *
 * You should build the array arr which has the following properties:
 *
 * arr has exactly n integers.
 * 1 <= arr[i] <= m where (0 <= i < n).
 * After applying the mentioned algorithm to arr, the value search_cost is equal to k.
 *
 * Return the number of ways to build the array arr under the mentioned conditions.
 * As the answer may grow large, the answer must be computed modulo 109 + 7.
 */

public class Solution {

    public int numOfArrays(int n, int m, int k) {
        final int MOD = 1_000_000_007;

        // dp1[i][j] 表示当前已选择最大值为为 i 且递增次数为 j 的方案数
        int[][] dp1 = new int[m + 1][k + 2];
        int[][] dp2 = new int[m + 1][k + 2];
        // 第一位可以任意选择值, 选择之后, 递增次数均为 1
        for (int j = 1; j <= m; j++) dp1[j][1] = 1;

        // 从第二位开始枚举
        for (int i = 2; i <= n; i++) {
            // 当前位可以选择任意值
            for (int j = 1; j <= m; j++) {
                // 枚举前一位的递增次数
                for (int kp = 1; kp <= k && kp <= i; kp++) {
                    // 枚举之前的最大值
                    for (int jx = 1; jx <= m; jx++) {
                        // 当前位选择 j 且前最大数为 jx 的情况下, 计算当前的位的 k
                        int kc = kp + (j > jx ? 1 : 0), jxc = Math.max(jx, j);

                        dp2[jxc][kc] = (dp2[jxc][kc] + dp1[jx][kp]) % MOD;
                    }
                }
            }

            int[][] temp = dp1; dp1 = dp2; dp2 = temp;
            for (var rows : dp2) Arrays.fill(rows, 0);
        }

        int ans = 0;
        // 最后一位可以为任意值, 叠加所有情况
        for (int j = 1; j <= m; j++) ans = (ans + dp1[j][k]) % MOD;

        return ans;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("dp", () -> {
            assert new Solution().numOfArrays(2, 3, 1) == 6;
            assert new Solution().numOfArrays(5, 2, 3) == 0;
            assert new Solution().numOfArrays(9, 1, 1) == 1;
            assert new Solution().numOfArrays(5, 100, 3) == 874379186;
            assert new Solution().numOfArrays(5, 100, 1) == 50333316;
            assert new Solution().numOfArrays(50, 100, 25) == 34549172;
            assert new Solution().numOfArrays(50, 100, 50) == 538992043;
        });
    }

}
