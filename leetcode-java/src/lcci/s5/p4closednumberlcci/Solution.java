package lcci.s5.p4closednumberlcci;

import common.Checker;

/**
 * 面试题 05.04. 下一个数
 *
 * https://leetcode.cn/problems/closed-number-lcci/
 *
 * 下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
 */

public class Solution {

    public int[] findClosedNumbers(int num) {
        int len = 0, n = 31;
        int[] bits = new int[n];
        for (int v = num; v != 0; v >>= 1) {
            bits[len++] = v & 1;
        }

        int largest = -1, smallest = -1;
        for (int i = 1; i < n; i++) {
            if (bits[i] == 1 && bits[i - 1] == 0) {
                smallest = num >> (i + 1) << (i + 1);
                smallest |= 1 << (i - 1);

                int shr = i - 2, end = i - 1;
                for (int j = 0; j < end; j++) {
                    if (bits[j] == 1) smallest |= 1 << shr--;
                }
                break;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (bits[i] == 1 && bits[i + 1] == 0) {
                largest = num >> (i + 1) << (i + 1);
                largest |= 1 << (i + 1);

                for (int shr = 0, j = 0; j < i; j++) {
                    if (bits[j] == 1) {
                        largest |= 1 << shr++;
                    }
                }
                break;
            }
        }

        return new int[]{largest, smallest};
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().findClosedNumbers(2147483647), new int[]{-1, -1});
        assert Checker.anyOrder(new Solution().findClosedNumbers(67), new int[]{69, 56});

        assert Checker.anyOrder(new Solution().findClosedNumbers(2), new int[]{4, 1});
        assert Checker.anyOrder(new Solution().findClosedNumbers(500), new int[]{504, 498});
        assert Checker.anyOrder(new Solution().findClosedNumbers(1), new int[]{2, -1});
    }

}
