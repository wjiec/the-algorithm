package problem.p201bitwiseandofnumbersrange;

/**
 * 201. Bitwise AND of Numbers Range
 *
 * https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/
 *
 * Given two integers left and right that represent the range [left, right],
 * return the bitwise AND of all numbers in this range, inclusive.
 */

public class Solution {

    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        while (left < right) {
            left >>= 1;
            right >>= 1;
            ++shift;
        }
        return left << shift;
    }

    public static void main(String[] args) {
        assert new Solution().rangeBitwiseAnd(5, 7) == 4;
        assert new Solution().rangeBitwiseAnd(0, 0) == 0;
        assert new Solution().rangeBitwiseAnd(1, 2147483647) == 0;
    }

}
