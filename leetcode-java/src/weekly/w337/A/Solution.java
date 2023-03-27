package weekly.w337.A;

/**
 * 2595. Number of Even and Odd Bits
 *
 * https://leetcode.cn/problems/number-of-even-and-odd-bits/
 *
 * You are given a positive integer n.
 *
 * Let even denote the number of even indices in the binary representation of n (0-indexed) with value 1.
 *
 * Let odd denote the number of odd indices in the binary representation of n (0-indexed) with value 1.
 *
 * Return an integer array answer where answer = [even, odd].
 */

public class Solution {

    public int[] evenOddBit(int n) {
        int even = 0, odd = 0;
        for (int i = 0; i < 12; i++) {
            if ((n & (1 << i)) != 0) {
                if (i % 2 == 0) even++;
                else odd++;
            }
        }
        return new int[]{even, odd};
    }

    public static void main(String[] args) {
    }

}
