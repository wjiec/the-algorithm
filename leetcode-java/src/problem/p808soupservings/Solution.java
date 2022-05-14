package problem.p808soupservings;

import common.Checker;
import common.TODO;

/**
 * 808. Soup Servings
 *
 * https://leetcode.cn/problems/soup-servings/
 *
 * There are two types of soup: type A and type B. Initially, we have n ml of each type of soup.
 * There are four kinds of operations:
 *
 * Serve 100 ml of soup A and 0 ml of soup B,
 * Serve 75 ml of soup A and 25 ml of soup B,
 * Serve 50 ml of soup A and 50 ml of soup B, and
 * Serve 25 ml of soup A and 75 ml of soup B.
 * When we serve some soup, we give it to someone, and we no longer have it.
 * Each turn, we will choose from the four operations with an equal probability 0.25.
 * If the remaining volume of soup is not enough to complete the operation,
 * we will serve as much as possible. We stop once we no longer
 * have some quantity of both types of soup.
 *
 * Note that we do not have an operation where all 100 ml's of soup B are used first.
 *
 * Return the probability that soup A will be empty first, plus half the probability
 * that A and B become empty at the same time.
 * Answers within 10-5 of the actual answer will be accepted.
 */

public class Solution {

    @TODO(tips = "DP")
    public double soupServings(int n) {
        n = n / 25 + (n % 25 > 0 ? 1 : 0);
        if (n >= 500) return 1.0;

        double[][] memo = new double[n + 1][n + 1];
        for (int i = 0, e = 2 * n; i <= e; ++i) {
            for (int j = 0; j <= n; ++j) {
                int k = i - j;
                if (k < 0 || k > n) continue;

                double ans = 0.0;
                if (j == 0) ans = 1.0;
                if (j == 0 && k == 0) ans = 0.5;
                if (j > 0 && k > 0) {
                    ans = 0.25 * (
                        memo[z(j - 4)][k] + memo[z(j - 3)][z(k - 1)] +
                        memo[z(j - 2)][z(k - 2)] + memo[z(j - 1)][z(k - 3)]);
                }
                memo[j][k] = ans;
            }
        }
        return memo[n][n];
    }

    public int z(int x) { return Math.max(0, x); }

    public static void main(String[] args) {
        assert Checker.check(new Solution().soupServings(50), 0.625);
        assert Checker.check(new Solution().soupServings(100), 0.71875);
    }

}
