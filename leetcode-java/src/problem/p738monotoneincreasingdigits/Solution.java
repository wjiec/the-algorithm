package problem.p738monotoneincreasingdigits;

/**
 * 738. Monotone Increasing Digits
 *
 * https://leetcode.cn/problems/monotone-increasing-digits/
 *
 * An integer has monotone increasing digits if and only
 * if each pair of adjacent digits x and y satisfy x <= y.
 *
 * Given an integer n, return the largest number that is less than
 * or equal to n with monotone increasing digits.
 */

public class Solution {

    public int monotoneIncreasingDigits(int n) {
        int[] digits = new int[10];
        int idx = digits.length - 1;
        for (; n != 0; n /= 10, idx--) digits[idx] = n % 10;

        for (int l = idx + 1, r = idx + 1; r < digits.length - 1; r++) {
            if (digits[r] > digits[r + 1]) {
                digits[l] -= 1;
                for (int j = l + 1; j < digits.length; j++) {
                    digits[j] = 9;
                }
                break;
            } else if (digits[r] < digits[r + 1]) l = r + 1;
        }

        int ans = 0;
        for (int digit : digits) ans = ans * 10 + digit;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().monotoneIncreasingDigits(10) == 9;
        assert new Solution().monotoneIncreasingDigits(1234) == 1234;
        assert new Solution().monotoneIncreasingDigits(332) == 299;
        assert new Solution().monotoneIncreasingDigits(333) == 333;
        assert new Solution().monotoneIncreasingDigits(100) == 99;
        assert new Solution().monotoneIncreasingDigits(331) == 299;
        assert new Solution().monotoneIncreasingDigits(3451) == 3449;
        assert new Solution().monotoneIncreasingDigits(13575) == 13569;
    }

}
