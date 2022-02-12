package daily.d210904feibonaqishulielcof;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 *
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 *
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 *
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 */

public class Solution {

    public int fib(int n) {
        long prev = 0, curr = 1;
        for (int i = 0; i < n; i++) {
            long next = (prev + curr) % 1000000007;
            prev = curr; curr = next;
        }
        return (int) prev;
    }

    public static void main(String[] args) {
        assert new Solution().fib(2) == 1;
        assert new Solution().fib(5) == 5;
    }

}
