package problem.p1545findkthbitinnthbinarystring;

/**
 * 1545. Find Kth Bit in Nth Binary String
 *
 * https://leetcode.cn/problems/find-kth-bit-in-nth-binary-string/
 *
 * Given two positive integers n and k, the binary string Sn is formed as follows:
 *
 * S1 = "0"
 * Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i > 1
 * Where + denotes the concatenation operation, reverse(x) returns the reversed string x,
 * and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).
 *
 * For example, the first four strings in the above sequence are:
 *
 * S1 = "0"
 * S2 = "011"
 * S3 = "0111001"
 * S4 = "011100110110001"
 * Return the kth bit in Sn. It is guaranteed that k is valid for the given n.
 */

public class Solution {

    public char findKthBit(int n, int k) {
        if (n == 1) return '0';

        int mid = 1 << (n - 1);
        if (k == mid) return '1';

        if (k < mid) return findKthBit(n - 1, k);
        return invert(findKthBit(n - 1, (mid << 1) - k));
    }

    private char invert(char c) {
        return c == '0' ? '1' : '0';
    }

    public static void main(String[] args) {
        assert new Solution().findKthBit(3, 1) == '0';
        assert new Solution().findKthBit(4, 11) == '1';
        assert new Solution().findKthBit(1, 1) == '0';
        assert new Solution().findKthBit(2, 3) == '1';
    }

}
