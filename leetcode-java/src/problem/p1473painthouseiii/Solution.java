package problem.p1473painthouseiii;

import java.util.Arrays;

/**
 * 1473. Paint House III
 *
 * https://leetcode.cn/problems/paint-house-iii/
 *
 * There is a row of m houses in a small city, each house must be painted with
 * one of the n colors (labeled from 1 to n), some houses that have been painted
 * last summer should not be painted again.
 *
 * A neighborhood is a maximal group of continuous houses that are painted with the same color.
 *
 * For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods [{1}, {2,2}, {3,3}, {2}, {1,1}].
 * Given an array houses, an m x n matrix cost and an integer target where:
 *
 * houses[i]: is the color of the house i, and 0 if the house is not painted yet.
 * cost[i][j]: is the cost of paint the house i with the color j + 1.
 *
 * Return the minimum cost of painting all the remaining houses in such a way that
 * there are exactly target neighborhoods. If it is not possible, return -1.
 */

public class Solution {

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        final int INF = Integer.MAX_VALUE / 2;
        // dp[i][j][k] 表示将前 i 个房屋涂成 j 颜色构成 k 个街区的最小成本
        int[][][] dp = new int[m + 1][n + 1][target + 1];
        for (int i = 1; i <= m; i++) for (var row : dp[i]) Arrays.fill(row, INF);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (houses[i - 1] != 0 && houses[i - 1] != j) continue;

                for (int k = 1; k <= target; k++) {
                    // 从前一个房子的 jj 颜色转移到当前颜色 j
                    for (int jj = 1; jj <= n; jj++) {
                        if (j == jj) dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][k]);
                        else dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][jj][k - 1]);
                    }
                    // 如果有办法转移, 则加上转移的成本
                    if (dp[i][j][k] < INF && houses[i - 1] == 0) {
                        dp[i][j][k] += cost[i - 1][j - 1];
                    }
                    // 下一轮房子的数量不足以形成 k 个街区
                    if (k == i) break;
                }
            }
        }

        int ans = INF;
        for (int j = 1; j <= n; j++) {
            ans = Math.min(ans, dp[m][j][target]);
        }
        return ans >= INF ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minCost(new int[]{0,0,0,1}, new int[][]{{1,5},{4,1},{1,3},{4,4}}, 4, 2, 4) == 12;

        assert new Solution().minCost(new int[]{0,0,0,0,0}, new int[][]{{1,10},{10,1},{10,1},{1,10},{5,1}}, 5, 2, 3) == 9;
        assert new Solution().minCost(new int[]{0,2,1,2,0}, new int[][]{{1,10},{10,1},{10,1},{1,10},{5,1}}, 5, 2, 3) == 11;
        assert new Solution().minCost(new int[]{0,0,0,0,0}, new int[][]{{1,10},{10,1},{1,10},{10,1},{1,10}}, 5, 2, 5) == 5;
        assert new Solution().minCost(new int[]{3,1,2,3}, new int[][]{{1,1,1},{1,1,1},{1,1,1},{1,1,1}}, 4, 3, 3) == -1;
    }

}
