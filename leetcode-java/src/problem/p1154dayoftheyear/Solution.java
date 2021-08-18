package problem.p1154dayoftheyear;

/**
 * 1154. Day of the Year
 *
 * https://leetcode-cn.com/problems/day-of-the-year/
 *
 * Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD,
 * return the day number of the year.
 */

public class Solution {

    private final int[] daysOfMonth = new int[]{
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public int dayOfYear(String date) {
        int year = Integer.valueOf(date.substring(0, 4), 10);
        int month = Integer.valueOf(date.substring(5, 7), 10);
        int day = Integer.valueOf(date.substring(8, 10), 10);

        day += month > 2 && year % 4 == 0 ? 1 : 0;
        for (int i = 1; i < month; i++) {
            day += daysOfMonth[i - 1];
        }
        return day;
    }

    public static void main(String[] args) {
        assert new Solution().dayOfYear("2012-01-02") == 2;

        assert new Solution().dayOfYear("2019-01-09") == 9;
        assert new Solution().dayOfYear("2019-02-10") == 41;
        assert new Solution().dayOfYear("2003-03-01") == 60;
        assert new Solution().dayOfYear("2004-03-01") == 61;
    }

}
