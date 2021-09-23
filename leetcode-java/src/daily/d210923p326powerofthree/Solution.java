package daily.d210923p326powerofthree;

/**
 * 326. Power of Three
 *
 * https://leetcode-cn.com/problems/power-of-three/
 *
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 *
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 */

public class Solution {

    public boolean isPowerOfThree(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    public static void main(String[] args) {
        assert new Solution().isPowerOfThree(27);
        assert !new Solution().isPowerOfThree(0);
        assert new Solution().isPowerOfThree(9);
        assert !new Solution().isPowerOfThree(45);
    }

}
