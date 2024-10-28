package weekly.bw142.C;

/**
 * 3332. Maximum Points Tourist Can Earn
 *
 * https://leetcode.cn/contest/biweekly-contest-142/problems/maximum-points-tourist-can-earn/
 *
 * You are given two integers, n and k, along with two 2D integer arrays, stayScore and travelScore.
 *
 * A tourist is visiting a country with n cities, where each city is directly connected to every other city.
 * The tourist's journey consists of exactly k 0-indexed days, and they can choose any city as their starting point.
 *
 * Each day, the tourist has two choices:
 *
 * Stay in the current city: If the tourist stays in their current city curr
 * during day i, they will earn stayScore[i][curr] points.
 *
 * Move to another city: If the tourist moves from their current city curr to
 * city dest, they will earn travelScore[curr][dest] points.
 *
 * Return the maximum possible points the tourist can earn.
 */

public class Solution {

    public int maxScore(int n, int k, int[][] stayScore, int[][] travelScore) {
        // dp[i][j] 表示第 i 天的时候在城市 j 的最大点数
        int[][] dp = new int[k + 1][n];

        // 枚举天数
        for (int i = 1; i <= k; i++) {
            // 枚举前一天在的地方
            for (int pj = 0; pj < n; pj++) {
                // 枚举今天在的地方
                for (int cj = 0; cj < n; cj++) {
                    // 选择停留
                    if (pj == cj) dp[i][cj] = Math.max(dp[i][cj], dp[i - 1][pj] + stayScore[i - 1][cj]);
                    else dp[i][cj] = Math.max(dp[i][cj], dp[i - 1][pj] + travelScore[pj][cj]);
                }
            }
        }

        int ans = 0;
        for (int j = 0; j < n; j++) ans = Math.max(ans, dp[k][j]);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxScore(2, 1, new int[][]{{1,1}}, new int[][]{{0,1},{6,0}}) == 6;
    }

}
