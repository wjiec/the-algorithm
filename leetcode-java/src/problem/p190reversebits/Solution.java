package problem.p190reversebits;

/**
 * 190. Reverse Bits
 *
 * https://leetcode-cn.com/problems/reverse-bits/
 *
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * Note:
 *
 * Note that in some languages such as Java, there is no unsigned integer type. In this case,
 * both input and output will be given as a signed integer type. They should not affect your implementation,
 * as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore,
 * in Example 2 above, the input represents the signed integer -3
 * and the output represents the signed integer -1073741825.
 *
 * Follow up:
 *
 * If this function is called many times, how would you optimize it?
 */

public class Solution {

    public int reverseBits(int n) {
        int rs = n < 0 ? 1 : 0;
        for (int i = 0; i < 32; i++) {
            rs = (rs << 1) | ((n >> i) & 0x1);
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().reverseBits(43261596) == 964176192;
        assert new Solution().reverseBits((int) (4294967293L & 0xffffffffL)) == (int)(3221225471L & 0xffffffffL);
        assert new Solution().reverseBits((int) (3856704768L & 0xffffffffL)) == (int)(964176192L & 0xffffffffL);
    }

}
