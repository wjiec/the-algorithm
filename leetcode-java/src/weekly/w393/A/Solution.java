package weekly.w393.A;

/**
 * 114. Latest Time You Can Obtain After Replacing Characters
 *
 * https://leetcode.cn/contest/weekly-contest-393/problems/latest-time-you-can-obtain-after-replacing-characters/
 *
 * You are given a string s representing a 12-hour format time
 * where some of the digits (possibly none) are replaced with a "?".
 *
 * 12-hour times are formatted as "HH:MM", where HH is between 00 and 11, and MM is between 00 and 59.
 * The earliest 12-hour time is 00:00, and the latest is 11:59.
 *
 * You have to replace all the "?" characters in s with digits such that the time we obtain by the
 * resulting string is a valid 12-hour format time and is the latest possible.
 *
 * Return the resulting string.
 */

public class Solution {

    public String findLatestTime(String s) {
        char[] chars = s.toCharArray();
        if (chars[0] == '?') {
            switch (chars[1]) {
                case '?' -> { chars[0] = '1'; chars[1] = '1'; }
                case '1', '0' -> chars[0] = '1';
                default -> chars[0] = '0';
            }
        }

        if (chars[1] == '?') {
            switch (chars[0]) {
                case '1' -> chars[1] = '1';
                default -> chars[1] = '9';
            }
        }

        if (chars[3] == '?') chars[3] = '5';
        if (chars[4] == '?') chars[4] = '9';

        return new String(chars);
    }

    public static void main(String[] args) {
    }

}
