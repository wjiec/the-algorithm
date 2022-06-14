package problem.p1006clumsyfactorial;

/**
 * 1006. Clumsy Factorial
 *
 * https://leetcode.cn/problems/clumsy-factorial/
 *
 * The factorial of a positive integer n is the product of all positive integers less than or equal to n.
 *
 * For example, factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1.
 * We make a clumsy factorial using the integers in decreasing order by swapping out the multiply operations
 * for a fixed rotation of operations with multiply '*', divide '/', add '+', and subtract '-' in this order.
 *
 * For example, clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1.
 * However, these operations are still applied using the usual order of operations of arithmetic.
 * We do all multiplication and division steps before any addition or subtraction steps, and multiplication
 * and division steps are processed left to right.
 *
 * Additionally, the division that we use is floor division such that 10 * 9 / 8 = 90 / 8 = 11.
 *
 * Given an integer n, return the clumsy factorial of n.
 */

public class Solution {

    public int clumsy(int n) {
        int[] list = new int[n / 2 + 2];
        for (int i = 0, j = n; j > 0; j -= 4) {
            list[i++] = j * Math.max(j - 1, 1) / Math.max(j - 2, 1);
            list[i++] = Math.max(j - 3, 0);
        }

        int ans = list[0];
        for (int i = 1; i < list.length; i++) {
            ans += list[i] * (i % 2 == 1 ? 1 : -1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().clumsy(4) == 7;
        assert new Solution().clumsy(10) == 12;
    }

}
