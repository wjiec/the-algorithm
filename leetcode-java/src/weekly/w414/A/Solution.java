package weekly.w414.A;

import java.util.StringJoiner;

/**
 * 3280. Convert Date to Binary
 *
 * https://leetcode.cn/contest/weekly-contest-414/problems/convert-date-to-binary/
 *
 * You are given a string date representing a Gregorian calendar date in the yyyy-mm-dd format.
 *
 * date can be written in its binary representation obtained by converting year, month, and day
 * to their binary representations without any leading zeroes and writing them down
 * in year-month-day format.
 *
 * Return the binary representation of date.
 */

public class Solution {

    public String convertDateToBinary(String date) {
        StringJoiner sj = new StringJoiner("-");
        for (var part : date.split("-")) {
            sj.add(Integer.toBinaryString(Integer.parseInt(part)));
        }
        return sj.toString();
    }

    public static void main(String[] args) {
    }

}
