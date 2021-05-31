package problem.p693binarynumberwithalternatingbits;

/**
 * 693. Binary Number with Alternating Bits
 *
 * https://leetcode-cn.com/problems/binary-number-with-alternating-bits/
 *
 * Given a positive integer, check whether it has alternating bits:
 * namely, if two adjacent bits will always have different values.
 */

public class Solution {

    public boolean hasAlternatingBits(int n) {
        int last = n & 0x1; n >>= 1;
        while (n != 0) {
            int v = n & 0x1;
            if (last == v) return false;
            last = v;
            n >>= 1;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().hasAlternatingBits(5);
        assert !new Solution().hasAlternatingBits(7);
        assert !new Solution().hasAlternatingBits(11);
        assert new Solution().hasAlternatingBits(10);
        assert !new Solution().hasAlternatingBits(3);
    }

}
