package offer.p65buyongjiajianchengchuzuojiafalcof;

/**
 * 剑指 Offer 65. 不用加减乘除做加法
 *
 * https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/
 *
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 */

public class Solution {

    public int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }

    public static void main(String[] args) {
        assert new Solution().add(1, 1) == 2;
        assert new Solution().add(2, -1) == 1;
        assert new Solution().add(-2, 1) == -1;
    }

}
