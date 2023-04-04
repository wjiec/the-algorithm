package daily.d230404p1000minimumcosttomergestones;

import java.util.Arrays;

/**
 * 1000. Minimum Cost to Merge Stones
 *
 * https://leetcode.cn/problems/minimum-cost-to-merge-stones/
 *
 * There are n piles of stones arranged in a row. The ith pile has stones[i] stones.
 *
 * A move consists of merging exactly k consecutive piles into one pile, and the
 * cost of this move is equal to the total number of stones in these k piles.
 *
 * Return the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.
 */


/*
有 N 堆石头排成一排，第 i 堆中有 stones[i] 块石头。

每次移动（move）需要将连续的 K 堆石头合并为一堆，而这个移动的成本为这 K 堆石头的总数。

找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。
 */

public class Solution {

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) return -1;

        int[][][] dp = new int[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int[] sum = new int[n];
        for (int i = 0, s = 0; i < n; i++) {
            dp[i][i][1] = 0;
            sum[i] = (s + stones[i]);
        }

        return dfs(dp, sum, 0, n - 1, 1, k);
    }

    private final int INF = Integer.MAX_VALUE / 3;

    private int dfs(int[][][] dp, int[] sum, int l, int r, int c, int k) {
        if (dp[l][r][c] != -1) return dp[l][r][c];
        if (c > r - l + 1) return INF;
        if (c == 1) {
            int ans = dfs(dp, sum, l, r, k, k);
            if (ans == INF) return dp[l][r][c] = INF;
            return dp[l][r][c] + (sum[r] - (l == 0 ? 0 : sum[l - 1]));
        }

        int ans = INF;
        for (int x = l; x < r; x += k - 1) {
            ans = Math.min(ans, dfs(dp, sum, l, x, 1, k) + dfs(dp, sum, x + 1, r, c - 1, k));
        }
        return dp[l][r][c] = ans;
    }

    public static void main(String[] args) {
        assert new Solution().mergeStones(new int[]{3,2,4,1}, 2) == 20;
        assert new Solution().mergeStones(new int[]{3,2,4,1}, 3) == -1;
        assert new Solution().mergeStones(new int[]{3,5,1,2,6}, 3) == 25;
    }

}
