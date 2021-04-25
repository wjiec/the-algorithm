package problem.p7reverseinteger;

import java.util.regex.Matcher;

/**
 * 7. Reverse Integer
 *
 * https://leetcode-cn.com/problems/reverse-integer/
 *
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go
 * outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 */

public class Solution {

    public int reverse(int x) {
        int r = 0;
        while (x != 0) {
            r = r * 10 + (x % 10);
            if (Math.abs(r % 10) != Math.abs(x % 10)) {
                return 0;
            }
            x /= 10;
        }

        return r;
    }

    public static void main(String[] args) {
        assert new Solution().reverse(0) == 0;
        assert new Solution().reverse(1) == 1;
        assert new Solution().reverse(-1) == -1;
        assert new Solution().reverse(-123) == -321;
        assert new Solution().reverse(123) == 321;
        assert new Solution().reverse(-2147483648) == 0;
        assert new Solution().reverse(1534236469) == 0;
    }

}
