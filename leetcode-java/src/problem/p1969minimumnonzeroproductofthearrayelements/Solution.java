package problem.p1969minimumnonzeroproductofthearrayelements;

import ability.Ability;

import java.math.BigInteger;

/**
 * 1969. Minimum Non-Zero Product of the Array Elements
 *
 * https://leetcode.cn/problems/minimum-non-zero-product-of-the-array-elements/
 *
 * You are given a positive integer p. Consider an array nums (1-indexed) that consists of the integers
 * in the inclusive range [1, 2p - 1] in their binary representations. You are allowed to do the
 * following operation any number of times:
 *
 * Choose two elements x and y from nums.
 * Choose a bit in x and swap it with its corresponding bit in y. Corresponding bit
 * refers to the bit that is in the same position in the other integer.
 * For example, if x = 1101 and y = 0011, after swapping the 2nd bit from the right, we
 * have x = 1111 and y = 0001.
 *
 * Find the minimum non-zero product of nums after performing the above operation any number of times.
 * Return this product modulo 109 + 7.
 *
 * Note: The answer should be the minimum product before the modulo operation is done.
 */

public class Solution {

    public int minNonZeroProduct(int p) {
        // 4 4 4
        // 111b * 001b^3 * 110b^3
        //
        // 8 8 8 8
        // 1111b * 0001b^7 * 1110b^7
        //
        // (1<<p - 1) * (1<<p - 2)^(1<<(p - 1) - 1)
        long MOD = 1_000_000_007;
        BigInteger a = BigInteger.valueOf((1L << p) - 1);

        BigInteger base = BigInteger.valueOf((1L << p) - 2);
        BigInteger pow = BigInteger.valueOf((1L << (p - 1)) - 1);
        BigInteger b = Ability.Math.pow(base, pow, BigInteger.valueOf(MOD));

        BigInteger ans = a.multiply(b);
        return ans.mod(BigInteger.valueOf(MOD)).intValue();
    }

    public static void main(String[] args) {
        assert new Solution().minNonZeroProduct(32) == 505517599;

        assert new Solution().minNonZeroProduct(1) == 1;
        assert new Solution().minNonZeroProduct(2) == 6;
        assert new Solution().minNonZeroProduct(3) == 1512;
    }

}
