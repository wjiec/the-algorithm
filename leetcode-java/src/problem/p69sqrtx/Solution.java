package problem.p69sqrtx;

/**
 * 69. Sqrt(x)
 *
 * https://leetcode-cn.com/problems/sqrtx/
 *
 * Given a non-negative integer x, compute and return the square root of x.
 *
 * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
 */

public class Solution {

    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        int l = 0, r = x / 2, rs = -1;
        while (l <= r) {
            int v = (l + r) / 2;
            if ((long)v * v <= x) {
                rs = v;
                l = v + 1;
            } else {
                r = v - 1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().mySqrt(0) == 0;
        assert new Solution().mySqrt(1) == 1;
        assert new Solution().mySqrt(2) == 1;
        assert new Solution().mySqrt(3) == 1;
        assert new Solution().mySqrt(4) == 2;
        assert new Solution().mySqrt(8) == 2;
        assert new Solution().mySqrt(9) == 3;
        assert new Solution().mySqrt(10) == 3;
        assert new Solution().mySqrt(2147395599) == 46339;
    }

}
