package problem.p746mincostclimbingstairs;


/**
 * 746. Min Cost Climbing Stairs
 *
 * https://leetcode-cn.com/problems/min-cost-climbing-stairs/
 *
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase.
 * Once you pay the cost, you can either climb one or two steps.
 *
 * You can either start from the step with index 0, or the step with index 1.
 *
 * Return the minimum cost to reach the top of the floor.
 */

public class Solution {

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1]; dp[0] = Math.min(cost[0], cost[1]);
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1], i > 1 ? dp[i - 2] : 0) + (i == n ? 0 : cost[i]);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        assert new Solution().minCostClimbingStairs(new int[]{10,15,20}) == 15;
        assert new Solution().minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}) == 6;
    }

}
