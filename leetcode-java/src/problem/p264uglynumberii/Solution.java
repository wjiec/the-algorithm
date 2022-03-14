package problem.p264uglynumberii;

import java.util.TreeSet;

/**
 * 264. Ugly Number II
 *
 * https://leetcode-cn.com/problems/ugly-number-ii/
 *
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return the nth ugly number.
 */

public class Solution {

    public int nthUglyNumber(int n) {
        TreeSet<Long> set = new TreeSet<>(); set.add(1L);
        for (int i = 1; i < n; i++) {
            //noinspection ConstantConditions
            long curr = set.pollFirst();
            if (curr * 2 > 0) set.add(curr * 2);
            if (curr * 3 > 0) set.add(curr * 3);
            if (curr * 5 > 0) set.add(curr * 5);
        }
        return set.first().intValue();
    }

    public static void main(String[] args) {
        assert new Solution().nthUglyNumber(1600) == 1399680000;

        assert new Solution().nthUglyNumber(10) == 12;
        assert new Solution().nthUglyNumber(1) == 1;
    }

}
