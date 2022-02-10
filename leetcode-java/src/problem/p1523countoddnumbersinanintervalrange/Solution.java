package problem.p1523countoddnumbersinanintervalrange;

/**
 * 1523. Count Odd Numbers in an Interval Range
 *
 * https://leetcode-cn.com/problems/count-odd-numbers-in-an-interval-range/
 *
 * Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).
 */

public class Solution {

    public int countOdds(int low, int high) {
        return (high - low) / 2 + ((low % 2 == 1 || high % 2 == 1) ? 1 : 0);
    }

    public static void main(String[] args) {
        assert new Solution().countOdds(3, 7) == 3;
        assert new Solution().countOdds(8, 10) == 1;
    }

}
