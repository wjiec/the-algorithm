package problem.p1922countgoodnumbers;

import ability.Ability;

/**
 * 1922. Count Good Numbers
 *
 * https://leetcode.cn/problems/count-good-numbers/
 *
 * A digit string is good if the digits (0-indexed) at even indices are even and the digits
 * at odd indices are prime (2, 3, 5, or 7).
 *
 * For example, "2582" is good because the digits (2 and 8) at even positions are even and
 * the digits (5 and 2) at odd positions are prime. However, "3245" is not good because 3 is
 * at an even index but is not even.
 *
 * Given an integer n, return the total number of good digit strings of length n.
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * A digit string is a string consisting of digits 0 through 9 that may contain leading zeros.
 */

public class Solution {

    public int countGoodNumbers(long n) {
        long MOD = 1_000_000_007;
        // [0, 2, 4, 6, 8] * [2, 3, 5, 7] = 20
        return (int) ((Ability.Math.pow(20, n / 2, MOD) * (n % 2 == 1 ? 5 : 1)) % MOD);
    }

    public static void main(String[] args) {
        assert new Solution().countGoodNumbers(1) == 5;
        assert new Solution().countGoodNumbers(4) == 400;
        assert new Solution().countGoodNumbers(50) == 564908303;
    }

}
