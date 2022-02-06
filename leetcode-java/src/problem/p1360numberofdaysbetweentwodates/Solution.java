package problem.p1360numberofdaysbetweentwodates;

/**
 * 1360. Number of Days Between Two Dates
 *
 * https://leetcode-cn.com/problems/number-of-days-between-two-dates/
 *
 * Write a program to count the number of days between two dates.
 *
 * The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.
 */

public class Solution {

    private final int[] daysOfMonth = new int[]{
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public int daysBetweenDates(String date1, String date2) {
        int[] d1 = parse(date1), d2 = parse(date2);
        return Math.abs(calcDays(d1[0], d1[1], d1[2]) - calcDays(d2[0], d2[1], d2[2]));
    }

    private int calcDays(int year, int month, int day) {
        for (int i = month; i > 1; i--) day += daysOfMonth[i - 1 - 1];
        if ((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) && month > 2) day++;
        day += (365 * (year - 1) + (year - 1) / 4 - (year - 1) / 100 + (year - 1) / 400);
        return day - 1;
    }

    private int[] parse(String date) {
        return new int[]{
            Integer.parseInt(date.substring(0, 4)),
            Integer.parseInt(date.substring(5, 7)),
            Integer.parseInt(date.substring(8, 10))
        };
    }

    public static void main(String[] args) {
        assert new Solution().daysBetweenDates("2019-06-29", "2019-06-30") == 1;
        assert new Solution().daysBetweenDates("2020-01-15", "2019-12-31") == 15;
    }

}
