package weekly.w400.B;

import java.util.Arrays;

/**
 * 100311. Count Days Without Meetings
 *
 * https://leetcode.cn/contest/weekly-contest-400/problems/count-days-without-meetings/
 *
 * You are given a positive integer days representing the total number of days an employee is available
 * for work (starting from day 1). You are also given a 2D array meetings of size n where,
 * meetings[i] = [start_i, end_i] represents the starting and ending days of meeting i (inclusive).
 *
 * Return the count of days when the employee is available for work but no meetings are scheduled.
 *
 * Note: The meetings may overlap.
 */

public class Solution {

    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return b[1] - a[1];
        });

        int seen = 0;
        for (var meeting : meetings) {
            int l = meeting[0], r = meeting[1];
            if (r >= seen) days -= r - Math.max(l, seen) + 1;
            seen = Math.max(seen, r + 1);
        }
        return days;
    }

    public static void main(String[] args) {
        assert new Solution().countDays(10, new int[][]{{5,7},{1,3},{9,10}}) == 2;
        assert new Solution().countDays(5, new int[][]{{2,4},{1,3}}) == 1;
        assert new Solution().countDays(6, new int[][]{{1,6}}) == 0;
    }

}
