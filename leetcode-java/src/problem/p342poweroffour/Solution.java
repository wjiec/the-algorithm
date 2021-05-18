package problem.p342poweroffour;

/**
 * 342. Power of Four
 *
 * https://leetcode-cn.com/problems/power-of-four/
 *
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 *
 * An integer n is a power of four, if there exists an integer x such that n == 4x.
 */

public class Solution {

    public boolean isPowerOfFour(int n) {
        return Integer.toString(n, 4).matches("^10*$");
    }

    public static void main(String[] args) {
        assert new Solution().isPowerOfFour(16);
        assert !new Solution().isPowerOfFour(5);
        assert new Solution().isPowerOfFour(1);
    }

}
