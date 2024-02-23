package problem.p1611minimumonebitoperationstomakeintegerszero;

/**
 * 1611. Minimum One Bit Operations to Make Integers Zero
 *
 * https://leetcode.cn/problems/minimum-one-bit-operations-to-make-integers-zero/description/
 *
 * Given an integer n, you must transform it into 0 using the following operations any number of times:
 *
 * Change the rightmost (0th) bit in the binary representation of n.
 *
 * Change the ith bit in the binary representation of n if the (i-1)th
 * bit is set to 1 and the (i-2)th through 0th bits are set to 0.
 *
 * Return the minimum number of operations to transform n into 0.
 */

public class Solution {

    public int minimumOneBitOperations(int n) {
        if (n == 0) return 0;

        int i = Integer.toBinaryString(n).length() - 1;
        return ((1 << (i + 1)) - 1) - minimumOneBitOperations(n - (1 << i));
    }

    public static void main(String[] args) {
        assert new Solution().minimumOneBitOperations(9999999) == 15670186;

        assert new Solution().minimumOneBitOperations(3) == 2;
        assert new Solution().minimumOneBitOperations(6) == 4;
    }

}
