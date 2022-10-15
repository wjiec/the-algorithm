package weekly.bw89.A;

/**
 * 6208. Number of Valid Clock Times
 *
 * https://leetcode.cn/contest/biweekly-contest-89/problems/number-of-valid-clock-times/
 *
 * You are given a string of length 5 called time, representing the current time on a digital
 * clock in the format "hh:mm". The earliest possible time is "00:00" and
 * the latest possible time is "23:59".
 *
 * In the string time, the digits represented by the ? symbol are unknown, and must be
 * replaced with a digit from 0 to 9.
 *
 * Return an integer answer, the number of valid clock times that can be created by
 * replacing every ? with a digit from 0 to 9.
 */

public class Solution {

    private int ans = 0;

    public int countTime(String time) {
        dfs(time.toCharArray(), 0);
        return ans;
    }

    private void dfs(char[] time, int i) {
        if (i == time.length) {
            int h = (time[0] - '0') * 10 + (time[1] - '0');
            int m = (time[3] - '0') * 10 + (time[4] - '0');

            if (h <= 23 && m <= 59) ans++;
            return;
        }

        if (time[i] == '?') {
            for (char j = '0'; j <= '9'; j++) {
                time[i] = j;
                dfs(time, i + 1);
                time[i] = '?';
            }
        } else dfs(time, i + 1);
    }

    public static void main(String[] args) {
    }

}
