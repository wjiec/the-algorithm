package lcci.s17.p1addwithoutpluslcci;

/**
 * 面试题 17.01. 不用加号的加法
 *
 * https://leetcode-cn.com/problems/add-without-plus-lcci/
 *
 * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
 */

public class Solution {

    public int add(int a, int b) {
        int x = a ^ b, c = (a & b) << 1;
        while (c != 0) {
            int t = x ^ c;
            c = (x & c) << 1;
            x = t;
        }
        return x;
    }

    public static void main(String[] args) {
        assert new Solution().add(1, 2) == 3;
        assert new Solution().add(1, 1) == 2;
    }

}
