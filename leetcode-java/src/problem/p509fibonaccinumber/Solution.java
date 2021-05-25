package problem.p509fibonaccinumber;

/**
 * 509. Fibonacci Number
 *
 * https://leetcode-cn.com/problems/fibonacci-number/
 *
 * The Fibonacci numbers, commonly denoted F(n) form a sequence,
 * called the Fibonacci sequence, such that each number is the sum of the two preceding ones,
 * starting from 0 and 1.
 */

public class Solution {

    public int fib(int n) {
        if (n == 0) return 0;

        int p = 0, c = 1;
        for (int i = 2; i <= n; i++) {
            int t = p + c;
            p = c;
            c = t;
        }
        return c;
    }

    public static void main(String[] args) {
        assert new Solution().fib(0) == 0;
        assert new Solution().fib(1) == 1;
        assert new Solution().fib(2) == 1;
        assert new Solution().fib(3) == 2;
        assert new Solution().fib(4) == 2;
    }

}
