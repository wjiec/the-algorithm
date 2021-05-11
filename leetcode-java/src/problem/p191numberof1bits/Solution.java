package problem.p191numberof1bits;

/**
 * 191. Number of 1 Bits
 *
 * https://leetcode-cn.com/problems/number-of-1-bits/
 *
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 *
 * Note:
 *
 * Note that in some languages, such as Java, there is no unsigned integer type. In this case,
 * the input will be given as a signed integer type. It should not affect your implementation,
 * as the integer's internal binary representation is the same, whether it is signed or unsigned.
 *
 * In Java, the compiler represents the signed integers using 2's complement notation.
 * Therefore, in Example 3, the input represents the signed integer. -3.
 */

public class Solution {

    public int hammingWeight(int n) {
        int rs = n < 0 ? 1 : 0;
        for (int i = 0; i < 31; i++) {
            if (((n >> i) & 0x1) == 1) {
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().hammingWeight(1) == 1;
        assert new Solution().hammingWeight(2) == 1;
        assert new Solution().hammingWeight(3) == 2;
    }

}
