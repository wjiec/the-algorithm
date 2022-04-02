package weekly.bw75.p0minimumbitflipstoconvertnumber;

/**
 * 6033. Minimum Bit Flips to Convert Number
 *
 * https://leetcode-cn.com/contest/biweekly-contest-75/problems/minimum-bit-flips-to-convert-number/
 *
 * A bit flip of a number x is choosing a bit in the binary representation of x and flipping it from either 0 to 1 or 1 to 0.
 *
 * For example, for x = 7, the binary representation is 111 and we may choose any bit
 * (including any leading zeros not shown) and flip it. We can flip the first bit from the right to get 110,
 * flip the second bit from the right to get 101, flip the fifth bit from the right (a leading zero)
 * to get 10111, etc.
 *
 * Given two integers start and goal, return the minimum number of bit flips to convert start to goal.
 */

public class Solution {

    public int minBitFlips(int start, int goal) {
        int xor = start ^ goal, ans = 0;
        for (; xor != 0; xor >>= 1) {
            if ((xor & 1) == 1) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minBitFlips(10, 7) == 3;
        assert new Solution().minBitFlips(3, 4) == 3;
    }

}
