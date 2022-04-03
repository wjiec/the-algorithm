package weekly.w287.p0minimumnumberofoperationstoconverttime;

/**
 * 6055. Minimum Number of Operations to Convert Time
 *
 * https://leetcode-cn.com/contest/weekly-contest-287/problems/minimum-number-of-operations-to-convert-time/
 *
 * You are given two strings current and correct representing two 24-hour times.
 *
 * 24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59.
 * The earliest 24-hour time is 00:00, and the latest is 23:59.
 *
 * In one operation you can increase the time current by 1, 5, 15, or 60 minutes.
 * You can perform this operation any number of times.
 *
 * Return the minimum number of operations needed to convert current to correct.
 */

public class Solution {

    public int convertTime(String current, String correct) {
        int h1 = Integer.valueOf(current.substring(0, 2), 10);
        int m1 = Integer.valueOf(current.substring(3, 5), 10);

        int h2 = Integer.valueOf(correct.substring(0, 2), 10);
        int m2 = Integer.valueOf(correct.substring(3, 5), 10);

        int a = h1 * 60 + m1, b = h2 * 60 + m2;
        int diff = (b + 1440 - a) % 1440;
        int v1 = diff / 60; diff %= 60;
        int v2 = diff / 15; diff %= 15;
        int v3 = diff / 5; diff %= 5;
        return v1 + v2 + v3 + diff;
    }

    public static void main(String[] args) {
        assert new Solution().convertTime("02:30", "04:35") == 3;
        assert new Solution().convertTime("11:00", "11:01") == 1;
        assert new Solution().convertTime("12:00", "00:00") == 12;
        assert new Solution().convertTime("12:00", "01:16") == 15;
    }

}
