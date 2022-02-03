package problem.p50powxn;

import common.Checker;

/**
 * 50. Pow(x, n)
 *
 * https://leetcode-cn.com/problems/powx-n/
 *
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 */

public class Solution {

    public double myPow(double x, int n) {
        return n >= 0 ? quickMultiply(x, n) : (1.0 / quickMultiply(x, n));
    }

    private double quickMultiply(double x, long n) {
        if (n == 0) return 1;

        double v = quickMultiply(x, n / 2);
        return v * v * (n % 2 == 0 ? 1 : x);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().myPow(2, 10), 1024.0);
        assert Checker.check(new Solution().myPow(2.1, 3), 9.261);
        assert Checker.check(new Solution().myPow(2, -2), 0.25);
    }

}
