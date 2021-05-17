package problem.p263uglynumber;

/**
 * 263. Ugly Number
 *
 * https://leetcode-cn.com/problems/ugly-number/
 *
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return true if n is an ugly number.
 */

public class Solution {

    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }

        return n == 1;
    }

    public static void main(String[] args) {
        assert new Solution().isUgly(1);
        assert !new Solution().isUgly(14);
        assert new Solution().isUgly(8);
        assert new Solution().isUgly(6);
        assert !new Solution().isUgly(-2147483648);
    }

}
