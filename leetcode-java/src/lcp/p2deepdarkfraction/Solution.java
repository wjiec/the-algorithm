package lcp.p2deepdarkfraction;

import common.Checker;

/**
 * LCP 02. 分式化简
 *
 * https://leetcode-cn.com/problems/deep-dark-fraction/
 *
 * 输入的cont代表连分数的系数（cont[0]代表上图的a0，以此类推）。返回一个长度为2的数组[n, m]，使得连分数的值等于n / m，且n, m最大公约数为1。
 */

public class Solution {

    public int[] fraction(int[] cont) {
        int a = cont[cont.length - 1], b = 1;
        for (int i = cont.length - 2; i >= 0; i--) {
            int t = a;
            a = cont[i] * a + b;
            b = t;
        }
        return new int[]{a, b};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().fraction(new int[]{3, 2, 0, 2}), new int[]{13, 4});
        assert Checker.check(new Solution().fraction(new int[]{0, 0, 3}), new int[]{3, 1});
    }

}
