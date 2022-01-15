package problem.p8stringtointegeratoi;

/**
 * 8. String to Integer (atoi)
 *
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 *
 * Implement the myAtoi(string s) function, which converts a string
 * to a 32-bit signed integer (similar to C/C++'s atoi function).
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 * Read in and ignore any leading whitespace.
 *
 * Check if the next character (if not already at the end of the string) is '-' or '+'.
 * Read this character in if it is either. This determines if the final result is negative or positive respectively.
 * Assume the result is positive if neither is present.
 *
 * Read in next the characters until the next non-digit character or the end of the input is reached.
 * The rest of the string is ignored.
 *
 * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32).
 * If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
 *
 * If the integer is out of the 32-bit signed integer range [-231, 231 - 1],
 * then clamp the integer so that it remains in the range.
 * Specifically, integers less than -231 should be clamped to -231,
 * and integers greater than 231 - 1 should be clamped to 231 - 1.
 *
 * Return the integer as the final result.
 * Note:
 *
 * Only the space character ' ' is considered a whitespace character.
 * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
 */

public class Solution {

    public int myAtoi(String s) {
        int idx = 0, n = s.length();
        while (idx < n && s.charAt(idx) == ' ') idx++;
        if (idx == n) return 0;

        char c = s.charAt(idx);
        boolean negative = false;
        if (c == '-' || c == '+') negative = s.charAt(idx++) == '-';

        if (idx == n) return 0;
        c = s.charAt(idx);
        if (!('0' <= c && c <= '9')) return 0;

        long value = 0;
        while (idx < n) {
            c = s.charAt(idx++);
            if ('0' <= c && c <= '9') {
                value = value * 10 + (c - '0');
            } else break;

            if (negative && -value <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
            else if (!negative && value >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
        }
        return (int) value * (negative ? -1 : 1);
    }

    public static void main(String[] args) {
        assert new Solution().myAtoi("+") == 0;

        assert new Solution().myAtoi("42") == 42;
        assert new Solution().myAtoi("   -42") == -42;
        assert new Solution().myAtoi("4193 with words") == 4193;
        assert new Solution().myAtoi("words and 987") == 0;
        assert new Solution().myAtoi("-91283472332") == -2147483648;
    }

}
