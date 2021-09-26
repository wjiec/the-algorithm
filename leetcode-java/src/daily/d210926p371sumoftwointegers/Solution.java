package daily.d210926p371sumoftwointegers;

import java.util.Arrays;

/**
 * 371. Sum of Two Integers
 *
 * https://leetcode-cn.com/problems/sum-of-two-integers/
 *
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 */

public class Solution {

    public int getSum(int a, int b) {
        int[] left = toBits(a), right = toBits(b);
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));

        int carry = 0;
        int[] ans = new int[32];
        for (int i = 0; i < ans.length; i++) {
            if (left[i] == 1 && right[i] == 1) {
                ans[i] = carry;
                carry = 1;
            } else if (left[i] == 0 && right[i] == 0) {
                ans[i] = carry;
                carry = 0;
            } else {
                ans[i] = left[i] + right[i] + carry;
                carry = ans[i] == 2 ? 1 : 0;
                ans[i] %= 2;
            }
        }
        System.out.println(Arrays.toString(ans));
        System.out.println();

        return fromBits(ans);
    }

    private int[] toBits(int n) {
        int[] bits = new int[32];
        for (int i = 0; n != 0; n = n >>> 1, i++) {
            bits[i] = n & 1;
        }
        return bits;
    }

    private int fromBits(int[] bits) {
        int n = 0;
        for (int i = bits.length - 1; i >= 0; i--) {
            if (bits[i] == 1) {
                n |= (1 << i);
            }
        }
        return n;
    }

    public static void main(String[] args) {
        assert new Solution().getSum(1, 2) == 3;
        assert new Solution().getSum(2, 3) == 5;
        assert new Solution().getSum(-2, 3) == 1;
        assert new Solution().getSum(-2, -3) == -5;
        assert new Solution().getSum(2, -3) == -1;
    }

}
