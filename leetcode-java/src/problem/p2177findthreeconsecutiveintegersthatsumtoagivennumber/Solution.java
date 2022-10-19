package problem.p2177findthreeconsecutiveintegersthatsumtoagivennumber;

import common.Checker;

/**
 * 2177. Find Three Consecutive Integers That Sum to a Given Number
 *
 * https://leetcode.cn/problems/find-three-consecutive-integers-that-sum-to-a-given-number/
 *
 * Given an integer num, return three consecutive integers (as a sorted array) that sum to num.
 * If num cannot be expressed as the sum of three consecutive integers, return an empty array.
 */

public class Solution {

    public long[] sumOfThree(long num) {
        if (num % 3 != 0) return new long[0];
        return new long[]{num / 3 - 1, num / 3, num / 3 + 1};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sumOfThree(33), new long[]{10,11,12});
        assert Checker.check(new Solution().sumOfThree(4), new long[]{});
    }

}
