package problem.p949largesttimeforgivendigits;

/**
 * 949. Largest Time for Given Digits
 *
 * https://leetcode.cn/problems/largest-time-for-given-digits/
 *
 * Given an array arr of 4 digits, find the latest 24-hour time that
 * can be made using each digit exactly once.
 *
 * 24-hour times are formatted as "HH:MM", where HH is between 00 and 23,
 * and MM is between 00 and 59. The earliest 24-hour time is 00:00,
 * and the latest is 23:59.
 *
 * Return the latest 24-hour time in "HH:MM" format. If no valid time
 * can be made, return an empty string.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private int minutes = -1;
    private String ans = "";

    public String largestTimeFromDigits(int[] arr) {
        dfs(arr[0], arr[1], arr[2], arr[3], -1, -1, -1, -1);
        return ans;
    }

    private void dfs(int a, int b, int c, int d, int h1, int h2, int m1, int m2) {
        if (h1 == -1) {
            if (a < 3) dfs(-1, b, c, d, a, h2, m1, m2);
            if (b < 3) dfs(a, -1, c, d, b, h2, m1, m2);
            if (c < 3) dfs(a, b, -1, d, c, h2, m1, m2);
            if (d < 3) dfs(a, b, c, -1, d, h2, m1, m2);
        } else if (h2 == -1) {
            if (h1 == 2) {
                if (0 <= a && a < 4) dfs(-1, b, c, d, h1, a, m1, m2);
                if (0 <= b && b < 4) dfs(a, -1, c, d, h1, b, m1, m2);
                if (0 <= c && c < 4) dfs(a, b, -1, d, h1, c, m1, m2);
                if (0 <= d && d < 4) dfs(a, b, c, -1, h1, d, m1, m2);
            } else {
                if (a >= 0) dfs(-1, b, c, d, h1, a, m1, m2);
                if (b >= 0) dfs(a, -1, c, d, h1, b, m1, m2);
                if (c >= 0) dfs(a, b, -1, d, h1, c, m1, m2);
                if (d >= 0) dfs(a, b, c, -1, h1, d, m1, m2);
            }
        } else if (m1 == -1) {
            if (0 <= a && a < 6) dfs(-1, b, c, d, h1, h2, a, m2);
            if (0 <= b && b < 6) dfs(a, -1, c, d, h1, h2, b, m2);
            if (0 <= c && c < 6) dfs(a, b, -1, d, h1, h2, c, m2);
            if (0 <= d && d < 6) dfs(a, b, c, -1, h1, h2, d, m2);
        } else if (m2 == -1) {
            if (a >= 0) dfs(-1, b, c, d, h1, h2, m1, a);
            if (b >= 0) dfs(a, -1, c, d, h1, h2, m1, b);
            if (c >= 0) dfs(a, b, -1, d, h1, h2, m1, c);
            if (d >= 0) dfs(a, b, c, -1, h1, h2, m1, d);
        } else {
            int mm = (h1 * 10 + h2) * 60 + m1 * 10 + m2;
            if (mm > minutes) {
                minutes = mm;
                ans = "" + h1 + "" + h2 + ":" + m1 + "" + m2;
            }
        }
    }

    private static class BruteForce {
        public String largestTimeFromDigits(int[] arr) {
            int ans = -1;
            for (int h1 = 0; h1 < 4; h1++) {
                for (int h2 = 0; h2 < 4; h2++) {
                    if (h2 == h1) continue;

                    for (int m1 = 0; m1 < 4; m1++) {
                        if (m1 == h1 || m1 == h2) continue;

                        for (int m2 = 0; m2 < 4; m2++) {
                            if (m2 == h1 || m2 == h2 || m2 == m1) continue;

                            int hh = arr[h1] * 10 + arr[h2], mm = arr[m1] * 10 + arr[m2];
                            if (hh < 24 && mm < 60) {
                                ans = Math.max(ans, hh * 60 + mm);
                            }
                        }
                    }
                }
            }
            return ans >= 0 ? String.format("%02d:%02d", ans / 60, ans % 60) : "";
        }
    }

    public static void main(String[] args) {
        assert new Solution().largestTimeFromDigits(new int[]{1,2,3,4}).equals("23:41");
        assert new Solution().largestTimeFromDigits(new int[]{5,5,5,5}).equals("");
        assert new Solution().largestTimeFromDigits(new int[]{0,0,0,0}).equals("00:00");
        assert new Solution().largestTimeFromDigits(new int[]{0,0,1,0}).equals("10:00");

        assert new BruteForce().largestTimeFromDigits(new int[]{1,2,3,4}).equals("23:41");
        assert new BruteForce().largestTimeFromDigits(new int[]{5,5,5,5}).equals("");
        assert new BruteForce().largestTimeFromDigits(new int[]{0,0,0,0}).equals("00:00");
        assert new BruteForce().largestTimeFromDigits(new int[]{0,0,1,0}).equals("10:00");
    }

}
