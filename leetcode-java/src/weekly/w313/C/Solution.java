package weekly.w313.C;

/**
 * 6194. Minimize XOR
 *
 * https://leetcode.cn/problems/minimize-xor/
 *
 * Given two positive integers num1 and num2, find the integer x such that:
 *
 * x has the same number of set bits as num2, and
 * The value x XOR num1 is minimal.
 * Note that XOR is the bitwise XOR operation.
 *
 * Return the integer x. The test cases are generated such that x is uniquely determined.
 *
 * The number of set bits of an integer is the number of 1's in its binary representation.
 */

public class Solution {

    public int minimizeXor(int num1, int num2) {
        int n = Integer.bitCount(num2);

        int idx = 0;
        int[] bits = new int[32];
        while (num1 != 0) {
            bits[idx++] = num1 & 1;
            num1 >>= 1;
        }

        for (int i = idx - 1; i >= 0; i--) {
            if (bits[i] == 1 && n > 0) {
                n--;
            } else bits[i] = 0;
        }

        if (n > 0) {
            for (int i = 0; n > 0; i++) {
                if (bits[i] == 0) {
                    bits[i] = 1;
                    n--;
                }
                if (i >= idx) idx++;
            }
        }

        int ans = 0;
        for (int i = 0; i < idx; i++) {
            ans |= bits[i] << i;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimizeXor(25, 72) == 24;

        assert new Solution().minimizeXor(3, 5) == 3;
        assert new Solution().minimizeXor(1, 12) == 3;
    }

}
