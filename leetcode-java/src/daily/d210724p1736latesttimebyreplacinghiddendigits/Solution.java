package daily.d210724p1736latesttimebyreplacinghiddendigits;

/**
 * 1736. Latest Time by Replacing Hidden Digits
 *
 * https://leetcode-cn.com/problems/latest-time-by-replacing-hidden-digits/
 *
 * You are given a string time in the form of hh:mm,
 * where some of the digits in the string are hidden (represented by ?).
 *
 * The valid times are those inclusively between 00:00 and 23:59.
 *
 * Return the latest valid time you can get from time by replacing the hidden digits.
 */

public class Solution {

    public String maximumTime(String time) {
        char h0 = time.charAt(0), h1 = time.charAt(1), m0 = time.charAt(3), m1 = time.charAt(4);
        if (h0 == '?') {
            if (h1 == '?' || h1 <= '3') h0 = '2';
            else h0 = '1';
        }

        if (h1 == '?') {
            if (h0 == '2') h1 = '3';
            else h1 = '9';
        }

        if (m0 == '?') m0 = '5';
        if (m1 == '?') m1 = '9';

        return "" + h0 + h1 + ':' + m0 + m1;
    }

    public static void main(String[] args) {
        assert new Solution().maximumTime("2?:?0").equals("23:50");
        assert new Solution().maximumTime("0?:3?").equals("09:39");
        assert new Solution().maximumTime("1?:22").equals("19:22");
    }

}
