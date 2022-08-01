package problem.p1362closestdivisors;

import common.Checker;

import java.util.Arrays;

/**
 * 1362. Closest Divisors
 *
 * https://leetcode.cn/problems/closest-divisors/
 *
 * Given an integer num, find the closest two integers in absolute difference
 * whose product equals num + 1 or num + 2.
 *
 * Return the two integers in any order.
 */

public class Solution {

    public int[] closestDivisors(int num) {
        int[] div1 = div(num + 1);
        int[] div2 = div(num + 2);
        return Math.abs(div1[0] - div1[1]) < Math.abs(div2[0] - div2[1]) ? div1 : div2;
    }

    private int[] div(int num) {
        for (int i = (int) Math.sqrt(num); i > 0; i--) {
            if (num % i == 0) return new int[]{i, num / i};
        }
        return new int[]{0, num};
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().closestDivisors(8), new int[]{3,3});
        assert Checker.anyOrder(new Solution().closestDivisors(123), new int[]{5,25});
        assert Checker.anyOrder(new Solution().closestDivisors(999), new int[]{25,40});
    }

}
