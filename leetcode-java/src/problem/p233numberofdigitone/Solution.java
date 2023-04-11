package problem.p233numberofdigitone;

/**
 * 233. Number of Digit One
 *
 * https://leetcode-cn.com/problems/number-of-digit-one/
 *
 * Given an integer n, count the total number of digit 1
 * appearing in all non-negative integers less than or equal to n.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int countDigitOne(int n) {
        int high = n / 10, curr = n % 10, low = 0, base = 1, ans = 0;
        while (high != 0 || curr != 0) {
            if (curr == 0) ans += high * base;
            else if (curr == 1) ans += high * base + low + 1;
            else ans += (high + 1) * base;

            low += curr * base;
            curr = high % 10;
            high /= 10;
            base *= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countDigitOne(12) == 5;
        assert new Solution().countDigitOne(13) == 6;
        assert new Solution().countDigitOne(0) == 0;
    }

}
