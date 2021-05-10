package problem.p172factorialtrailingzeroes;

/**
 * 172. Factorial Trailing Zeroes
 *
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 *
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Follow up: Could you write a solution that works in logarithmic time complexity?
 */

public class Solution {

    public int trailingZeroes(int n) {
        int rs = 0;
        while (n > 0) {
            n /= 5;
            rs += n;
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().trailingZeroes(3) == 0;
        assert new Solution().trailingZeroes(5) == 1;
        assert new Solution().trailingZeroes(0) == 0;
        assert new Solution().trailingZeroes(10) == 2;
    }

}
