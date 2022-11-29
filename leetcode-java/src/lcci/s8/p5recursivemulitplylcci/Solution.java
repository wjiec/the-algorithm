package lcci.s8.p5recursivemulitplylcci;

/**
 * 面试题 08.05. 递归乘法
 *
 * https://leetcode.cn/problems/recursive-mulitply-lcci/
 *
 * 递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
 */

public class Solution {

    public int multiply(int a, int b) {
        if (b == 0 || a == 0) return 0;
        return (multiply(a, b >> 1) << 1) + (((b & 1) == 1) ? a : 0);
    }

    public static void main(String[] args) {
        assert new Solution().multiply(1, 10) == 10;
        assert new Solution().multiply(3, 4) == 12;
    }

}
