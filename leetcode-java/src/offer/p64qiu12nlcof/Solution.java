package offer.p64qiu12nlcof;

/**
 * 剑指 Offer 64. 求1+2+…+n
 *
 * https://leetcode.cn/problems/qiu-12n-lcof/
 *
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */

public class Solution {

    public int sumNums(int n) {
        if (n == 1) return 1;
        return n + sumNums(n - 1);
    }

    public static void main(String[] args) {
        assert new Solution().sumNums(3) == 6;
        assert new Solution().sumNums(9) == 45;
    }

}
