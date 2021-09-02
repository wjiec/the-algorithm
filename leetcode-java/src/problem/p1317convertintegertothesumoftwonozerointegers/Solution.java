package problem.p1317convertintegertothesumoftwonozerointegers;

import java.util.Arrays;

/**
 * 1317. Convert Integer to the Sum of Two No-Zero Integers
 *
 * https://leetcode-cn.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/
 *
 * Given an integer n. No-Zero integer is a positive integer which
 * doesn't contain any 0 in its decimal representation.
 *
 * Return a list of two integers [A, B] where:
 *
 * A and B are No-Zero integers.
 * A + B = n
 * It's guarateed that there is at least one valid solution.
 * If there are many valid solutions you can return any of them.
 */

public class Solution {

    public int[] getNoZeroIntegers(int n) {
        if (n < 10) return new int[]{1, n - 1};

        int bits = (int) Math.log10(n), a = (int) Math.pow(10, bits) - 1, b = n - a;
        for (int v = b, base = 1; v > 0; v /= 10, base *= 10) {
            if (v % 10 == 0) {
                a -= base; b += base;
            }
        }
        return new int[]{b, a};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().getNoZeroIntegers(2)));
        System.out.println(Arrays.toString(new Solution().getNoZeroIntegers(11)));
        System.out.println(Arrays.toString(new Solution().getNoZeroIntegers(10000)));
        System.out.println(Arrays.toString(new Solution().getNoZeroIntegers(69)));
        System.out.println(Arrays.toString(new Solution().getNoZeroIntegers(1010)));
        System.out.println(Arrays.toString(new Solution().getNoZeroIntegers(1099)));
    }

}
