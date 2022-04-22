package problem.p256painthouse;

/**
 * 256. Paint House
 *
 * https://leetcode-cn.com/problems/paint-house/
 *
 * There is a row of n houses, where each house can be painted one of three colors: red, blue, or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.
 *
 * For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is
 * the cost of painting house 1 with color green, and so on...
 *
 * Return the minimum cost to paint all houses.
 */

public class Solution {

    public int minCost(int[][] costs) {
        int[][] dp = new int[costs.length + 1][3];
        for (int i = 1; i <= costs.length; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i - 1][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i - 1][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i - 1][2];
        }
        return Math.min(dp[costs.length][0], Math.min(dp[costs.length][1], dp[costs.length][2]));
    }

    public static void main(String[] args) {
        assert new Solution().minCost(new int[][]{{17,2,17},{16,16,5},{14,3,19}}) == 10;
        assert new Solution().minCost(new int[][]{{7,6,2}}) == 2;
    }

}
