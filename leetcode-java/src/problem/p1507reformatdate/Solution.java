package problem.p1507reformatdate;

/**
 * 1507. Reformat Date
 *
 * https://leetcode-cn.com/problems/reformat-date/
 *
 * Given a date string in the form Day Month Year, where:
 *
 * Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
 * Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
 * Year is in the range [1900, 2100].
 *
 * Convert the date string to the format YYYY-MM-DD, where:
 *
 * YYYY denotes the 4 digit year.
 * MM denotes the 2 digit month.
 * DD denotes the 2 digit day.
 */

public class Solution {

    private final String[] months = new String[] {
        "", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    public String reformatDate(String date) {
        String[] components = date.split(" ");
        int days = 0, month = 0, year = 0;
        for (var c : components[0].toCharArray()) {
            if ('0' <= c && c <= '9') days = days * 10 + (c - '0');
        }
        for (int i = 0; i < months.length; i++) {
            if (months[i].equals(components[1])) {
                month = i;
                break;
            }
        }
        for (var c : components[2].toCharArray()) {
            if ('0' <= c && c <= '9') year = year * 10 + (c - '0');
        }

        return String.format("%4d-%02d-%02d", year, month, days);
    }

    public static void main(String[] args) {
        assert new Solution().reformatDate("20th Oct 2052").equals("2052-10-20");
        assert new Solution().reformatDate("6th Jun 1933").equals("1933-06-06");
        assert new Solution().reformatDate("26th May 1960").equals("1960-05-26");
    }

}
