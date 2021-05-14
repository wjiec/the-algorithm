package problem.p231poweroftwo;

/**
 * 231. Power of Two
 *
 * https://leetcode-cn.com/problems/power-of-two/
 *
 * Given an integer n, return true if it is a power of two. Otherwise, return false.
 *
 * An integer n is a power of two, if there exists an integer x such that n == 2x.
 */

public class Solution {

    public boolean isPowerOfTwo(int n) {
        while (n > 1 && ((n & 1) == 0)) {
            n >>= 1;
        }
        return n == 1;
    }

    public boolean isPowerOfTwo1(int n) {
        for (int i = 1; i <= n; i <<= 1) {
            if (n == i) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert !new Solution().isPowerOfTwo(0);
        assert new Solution().isPowerOfTwo(1);
        assert new Solution().isPowerOfTwo(2);
        assert !new Solution().isPowerOfTwo(3);
        assert new Solution().isPowerOfTwo(4);
        assert new Solution().isPowerOfTwo(128);
        assert !new Solution().isPowerOfTwo(255);
        assert new Solution().isPowerOfTwo(256);
        assert !new Solution().isPowerOfTwo(1023);
        assert new Solution().isPowerOfTwo(4096);
    }

}
