package lcci.s16.p7maximumlcci;

/**
 * 面试题 16.07. 最大数值
 *
 * https://leetcode-cn.com/problems/maximum-lcci/
 *
 * 编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
 */

public class Solution {

    public int maximum(int a, int b) {
        int k = (int) (((long) a - (long) b) >> 63 & 1);
        return a * (1 - k) + b * k;
    }

    public static void main(String[] args) {
        assert new Solution().maximum(1, 2) == 2;
    }

}
