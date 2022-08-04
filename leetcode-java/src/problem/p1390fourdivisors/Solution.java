package problem.p1390fourdivisors;

/**
 * 1390. Four Divisors
 *
 * https://leetcode.cn/problems/four-divisors/
 *
 * Given an integer array nums, return the sum of divisors of the integers in that array that
 * have exactly four divisors. If there is no such integer in the array, return 0.
 */

public class Solution {

    public int sumFourDivisors(int[] nums) {
        int sum = 0;
        for (var v : nums) sum += sumOfDivisors(v);
        return sum;
    }

    private int sumOfDivisors(int v) {
        int divisor = 0;
        int n = (int) Math.sqrt(v);
        for (int i = 2; i <= n; i++) {
            if (v % i == 0) {
                if (divisor != 0) return 0;
                divisor = i;
            }
        }
        if (divisor == 0 || divisor == v / divisor) return 0;
        return 1 + v + divisor + v / divisor;
    }

    public static void main(String[] args) {
        assert new Solution().sumFourDivisors(new int[]{1,2,3,4,5,6,7,8,9,10}) == 45;

        assert new Solution().sumFourDivisors(new int[]{21,4,7}) == 32;
        assert new Solution().sumFourDivisors(new int[]{21,21}) == 64;
        assert new Solution().sumFourDivisors(new int[]{1,2,3,4,5}) == 0;
    }

}
