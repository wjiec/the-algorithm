package problem.p400nthdigit;

/**
 * 400. Nth Digit
 *
 * https://leetcode-cn.com/problems/nth-digit/
 *
 * Given an integer n, return the nth digit of the
 * infinite integer sequence [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...].
 */

public class Solution {

    public int findNthDigit(int n) {
        long base = 1, bits = 1, count = 9;
        while (count < n) {
            n -= count;

            bits += 1;
            base *= 10;
            count = bits * base * 9;
        }

        return Long.toString(base + (n - 1) / bits).charAt((int) ((n - 1) % bits)) - '0';
    }

    public static void main(String[] args) {
        assert new Solution().findNthDigit(3) == 3;
        assert new Solution().findNthDigit(11) == 0;
    }

}
