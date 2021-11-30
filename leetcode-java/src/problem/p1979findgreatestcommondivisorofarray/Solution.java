package problem.p1979findgreatestcommondivisorofarray;

/**
 * 1979. Find Greatest Common Divisor of Array
 *
 * https://leetcode-cn.com/problems/find-greatest-common-divisor-of-array/
 *
 * Given an integer array nums, return the greatest common divisor of the smallest number and largest number in nums.
 *
 * The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.
 */

public class Solution {

    public int findGCD(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (var n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }

        return gcd(max, min);
    }

    private int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    public static void main(String[] args) {
        assert new Solution().findGCD(new int[]{2,5,6,9,10}) == 2;
        assert new Solution().findGCD(new int[]{7,5,6,8,3}) == 1;
        assert new Solution().findGCD(new int[]{3,3}) == 3;

        assert new Solution().findGCD(new int[]{10,15}) == 5;
    }

}
