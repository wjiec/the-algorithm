package weekly.bw166.B;

import java.util.Arrays;

/**
 * Q2. Climbing Stairs II
 *
 * https://leetcode.cn/contest/biweekly-contest-166/problems/climbing-stairs-ii/
 *
 * You are climbing a staircase with n + 1 steps, numbered from 0 to n.
 *
 * You are also given a 1-indexed integer array costs of length n, where costs[i] is the cost of step i.
 *
 * From step i, you can jump only to step i + 1, i + 2, or i + 3. The cost of jumping
 * from step i to step j is defined as: costs[j] + (j - i)^2
 *
 * You start from step 0 with cost = 0.
 *
 * Return the minimum total cost to reach step n.
 */

public class Solution {

    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE); dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= 3 && i + j <= n; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + costs[i + j - 1] + j * j);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        assert new Solution().climbStairs(4, new int[]{1, 2, 3, 4}) == 13;
        assert new Solution().climbStairs(4, new int[]{5, 1, 6, 2}) == 11;
        assert new Solution().climbStairs(3, new int[]{9, 8, 3}) == 12;
    }

}
