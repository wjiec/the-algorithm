package weekly.w294.p0percentageofletterinstring;

/**
 * 6074. Percentage of Letter in String
 *
 * https://leetcode.cn/contest/weekly-contest-294/problems/percentage-of-letter-in-string/
 *
 * Given a string s and a character letter, return the percentage of characters
 * in s that equal letter rounded down to the nearest whole percent.
 */

public class Solution {

    public int percentageLetter(String s, char letter) {
        int n = s.length(), cnt = 0;
        for (var c : s.toCharArray()) if (c == letter) cnt++;
        return (int) ((double) cnt / n * 100);
    }

    public static void main(String[] args) {
    }

}
