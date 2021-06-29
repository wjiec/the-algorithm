package problem.p343integerbreak;

/**
 * 343. Integer Break
 *
 * https://leetcode-cn.com/problems/integer-break/
 *
 * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
 *
 * Return the maximum product you can get.
 */

public class Solution {

    public int integerBreak(int n) {
        if (n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if (b == 0) return (int) Math.pow(3, a);
        if (b == 1) return (int) Math.pow(3, a - 1) * 4;
        return (int) Math.pow(3, a) * 2;
    }

    public static void main(String[] args) {
        assert new Solution().integerBreak(2) == 1;
        assert new Solution().integerBreak(10) == 36;
    }

}
