package problem.p1185dayoftheweek;

/**
 * 1185. Day of the Week
 *
 * https://leetcode-cn.com/problems/day-of-the-week/
 *
 * Given a date, return the corresponding day of the week for that date.
 *
 * The input is given as three integers representing the day, month and year respectively.
 *
 * Return the answer as one of the following values
 *  {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
 */

public class Solution {

    private final String[] week = new String[]{
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    public String dayOfTheWeek(int day, int month, int year) {
        if (month == 1 || month == 2) {
            month += 12;
            year -= 1;
        }
        return week[(day + 2*month + 3*(month+1)/5 + year + year/4 - year/100 + year/400 + 1) % 7];
    }

    public static void main(String[] args) {
        assert new Solution().dayOfTheWeek(29, 2, 2016).equals("Monday");

        assert new Solution().dayOfTheWeek(31, 8, 2019).equals("Saturday");
        assert new Solution().dayOfTheWeek(18, 7, 1999).equals("Sunday");
        assert new Solution().dayOfTheWeek(15, 8, 1993).equals("Sunday");
    }

}
