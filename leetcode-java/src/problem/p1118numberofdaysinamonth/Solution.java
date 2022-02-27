package problem.p1118numberofdaysinamonth;

/**
 * 1118. Number of Days in a Month
 *
 * https://leetcode-cn.com/problems/number-of-days-in-a-month/
 *
 * Given a year year and a month month, return the number of days of that month.
 */

public class Solution {

    private final int[] dayOfMonth = new int[]{
        0,
        31, 28, 31, 30, 31, 30,
        31, 31, 30, 31, 30, 31
    };

    public int numberOfDays(int year, int month) {
        int days = dayOfMonth[month];
        if (month == 2 && ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))) {
            days += 1;
        }
        return days;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfDays(1992, 7) == 31;
        assert new Solution().numberOfDays(2000, 2) == 29;
        assert new Solution().numberOfDays(1900, 2) == 28;
    }

}
