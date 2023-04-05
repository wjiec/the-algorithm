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

public class Solution {

    private int k = 0;
    private int[] sum = null;

    public int mergeStones(int[] stones, int k) {
        this.k = k;
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) return -1;

        int[][][] dp = new int[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        sum = new int[n];
        for (int i = 0, s = 0; i < n; i++) {
            dp[i][i][1] = 0;
            sum[i] = (s += stones[i]);
        }

        return dfs(dp, 0, n - 1, 1);
    }

    private final int INF = Integer.MAX_VALUE / 3;

    private int dfs(int[][][] dp, int l, int r, int t) {
        if (dp[l][r][t] != -1) return dp[l][r][t];
        if (t > r - l + 1) return INF;

        if (t == 1) {
            int ans = dfs(dp, l, r, k);
            if (ans == INF) return dp[l][r][t] = INF;
            return dp[l][r][t] = ans + (sum[r] - (l == 0 ? 0 : sum[l - 1]));
        }

        int ans = INF;
        for (int i = l; i < r; i += k - 1) {
            ans = Math.min(ans, dfs(dp, l, i, 1) + dfs(dp, i + 1, r, t - 1));
        }
        return dp[l][r][t] = ans;
    }

    public static void main(String[] args) {
        assert new Solution().mergeStones(new int[]{3,2,4,1}, 2) == 20;
        assert new Solution().mergeStones(new int[]{3,2,4,1}, 3) == -1;
        assert new Solution().mergeStones(new int[]{3,5,1,2,6}, 3) == 25;
    }

}
