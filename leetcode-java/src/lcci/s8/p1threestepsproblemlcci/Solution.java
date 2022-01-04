package lcci.s8.p1threestepsproblemlcci;

/**
 * 面试题 08.01. 三步问题
 *
 * https://leetcode-cn.com/problems/three-steps-problem-lcci/
 *
 * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。
 *
 * 结果可能很大，你需要对结果模1000000007。
 */

public class Solution {

    public int waysToStep(int n) {
        int a = 0, b = 0, c = 1, MOD = 1000000007;
        for (int i = 1; i <= n; i++) {
            long t = ((long) a + b + c) % MOD;
            a = b; b = c; c = (int) t;
        }
        return c;
    }

    public static void main(String[] args) {
        assert new Solution().waysToStep(61) == 752119970;

        assert new Solution().waysToStep(3) == 4;
        assert new Solution().waysToStep(5) == 13;
    }

}
