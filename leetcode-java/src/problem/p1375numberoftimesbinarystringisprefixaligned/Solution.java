package problem.p1375numberoftimesbinarystringisprefixaligned;

/**
 * 1375. Number of Times Binary String Is Prefix-Aligned
 *
 * https://leetcode.cn/problems/number-of-times-binary-string-is-prefix-aligned/
 *
 * You have a 1-indexed binary string of length n where all the bits are 0 initially.
 * We will flip all the bits of this binary string (i.e., change them from 0 to 1) one by one.
 * You are given a 1-indexed integer array flips where flips[i] indicates that the bit at index i
 * will be flipped in the ith step.
 *
 * A binary string is prefix-aligned if, after the ith step, all the bits in the inclusive
 * range [1, i] are ones and all the other bits are zeros.
 *
 * Return the number of times the binary string is prefix-aligned during the flipping process.
 */

public class Solution {

    public int numTimesAllBlue(int[] flips) {
        int ans = 0, ones = 0;
        boolean[] bits = new boolean[flips.length + 1];
        for (int i = 1; i <= flips.length; i++) {
            int flipping = flips[i - 1];
            bits[flipping] = !bits[flipping];

            if (flipping < i) ones += bits[flipping] ? 1 : -1;
            if (bits[i]) ones++;
            if (ones == i) ans++;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numTimesAllBlue(new int[]{3,2,4,1,5}) == 2;
        assert new Solution().numTimesAllBlue(new int[]{4,1,2,3}) == 1;
    }

}
