package lcci.s16.p1swapnumberslcci;

import common.Checker;
import common.PrettyPrinter;

/**
 * 面试题 16.01. 交换数字
 *
 * https://leetcode.cn/problems/swap-numbers-lcci/
 *
 * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
 */

public class Solution {

    public int[] swapNumbers(int[] numbers) {
        // a' = a ^ b
        numbers[0] ^= numbers[1];
        // b' = b ^ a' = b ^ (a ^ b) = a
        numbers[1] ^= numbers[0];
        // a'' = a' ^ b' = (a ^ b) ^ a = b
        numbers[0] ^= numbers[1];

        return numbers;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().swapNumbers(new int[]{1, 2}), new int[]{2, 1});
    }

}
