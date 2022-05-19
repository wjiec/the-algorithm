package problem.p837new21game;

import common.Checker;
import common.TODO;

/**
 * 837. New 21 Game
 *
 * https://leetcode.cn/problems/new-21-game/
 *
 * Alice plays the following game, loosely based on the card game "21".
 *
 * Alice starts with 0 points and draws numbers while she has less than k points.
 * During each draw, she gains an integer number of points randomly from the range [1, maxPts],
 * where maxPts is an integer. Each draw is independent and the outcomes have equal probabilities.
 *
 * Alice stops drawing numbers when she gets k or more points.
 *
 * Return the probability that Alice has n or fewer points.
 *
 * Answers within 10-5 of the actual answer are considered accepted.
 */

public class Solution {

    @TODO(tips = "DP")
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0) return 1.0;

        double[] dp = new double[k + maxPts];
        for (int i = k; i <= n && i < k + maxPts; i++) dp[i] = 1.0;

        dp[k - 1] = 1.0 * Math.min(n - k + 1, maxPts) / maxPts;
        for (int i = k - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] - (dp[i + maxPts + 1] - dp[i + 1]) / maxPts;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().new21Game(10, 1, 10), 1.0);
        assert Checker.check(new Solution().new21Game(6, 1, 10), 0.6);
        assert Checker.check(new Solution().new21Game(21, 17, 10), 0.73278);
    }

}
