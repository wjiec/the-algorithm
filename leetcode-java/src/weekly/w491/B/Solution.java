package weekly.w491.B;

import java.util.Arrays;

/**
 * Q2. Minimum Cost to Split into Ones
 *
 * https://leetcode.cn/contest/weekly-contest-491/problems/minimum-cost-to-split-into-ones/
 *
 * You are given an integer n.
 *
 * In one operation, you may split an integer x into two positive integers a and b such that a + b = x.
 *
 * The cost of this operation is a * b.
 *
 * Return an integer denoting the minimum total cost required to split the integer n into n ones.
 */

public class Solution {

    public int minCost(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE); dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            for (int a = 1; a <= i / 2; a++) {
                dp[i] = Math.min(dp[i], dp[a] + dp[i - a] + a * (i - a));
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        assert new Solution().minCost(3) == 3;
        assert new Solution().minCost(4) == 6;
        assert new Solution().minCost(4) == 6;
    }

}
