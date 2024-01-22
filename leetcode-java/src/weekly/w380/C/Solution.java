package weekly.w380.C;

/**
 * 3007. Maximum Number That Sum of the Prices Is Less Than or Equal to K
 *
 * https://leetcode.cn/contest/weekly-contest-380/problems/maximum-number-that-sum-of-the-prices-is-less-than-or-equal-to-k/
 *
 * You are given an integer k and an integer x.
 *
 * Consider s is the 1-indexed binary representation of an integer num.
 * The price of a number num is the number of i's such that i % x == 0 and s[i] is a set bit.
 *
 * Return the greatest integer num such that the sum of prices of all numbers
 * from 1 to num is less than or equal to k.
 *
 * Note:
 *
 * In the binary representation of a number set bit is a bit of value 1.
 * The binary representation of a number will be indexed from right to left.
 *
 * For example, if s == 11100, s[4] == 1 and s[2] == 0.
 */

public class Solution {

    public long findMaximumNumber(long k, int x) {
        long num = 0, prev = 0;
        for (long i = 63 - Long.numberOfLeadingZeros((k + 1) << x); i >= 0; i--) {
            long cnt = (prev << i) + (i / x << i >> 1);
            if (cnt > k) continue;

            k -= cnt;
            num |= 1L << i;
            if ((i + 1) % x == 0) prev++;
        }
        return num - 1;
    }

    public static void main(String[] args) {
        assert new Solution().findMaximumNumber(9, 1) == 6;
        assert new Solution().findMaximumNumber(7, 2) == 9;
    }

}
