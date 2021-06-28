package offer.p10feibonaqishulielcof;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 *
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 *
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */

public class Solution {

    public int fib(int n) {
        if (n == 0) return 0;

        int prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            int t = curr;
            curr = (curr + prev) % 1000000007;
            prev = t;
        }
        return curr;
    }

    public int fib1(int n) {
        int prev = 0, curr = 1;
        for (int i = 0; i < n; i++) {
            int sum = (prev + curr) % 1000000007;
            prev = curr;
            curr = sum;
        }
        return prev;
    }

    public static void main(String[] args) {
        assert new Solution().fib(2) == 1;
        assert new Solution().fib(5) == 5;
        assert new Solution().fib(45) == 134903163;
        assert new Solution().fib(95) == 407059028;

        assert new Solution().fib1(2) == 1;
        assert new Solution().fib1(5) == 5;
        assert new Solution().fib1(45) == 134903163;
        assert new Solution().fib1(95) == 407059028;
    }

}
