package daily.d230516pminimumdifficultyofajobschedule;

import java.util.Arrays;

/**
 * 1335. Minimum Difficulty of a Job Schedule
 *
 * https://leetcode.cn/problems/minimum-difficulty-of-a-job-schedule/
 *
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work
 * on the ith job, you have to finish all the jobs j where 0 <= j < i).
 *
 * You have to finish at least one task every day. The difficulty of a job
 * schedule is the sum of difficulties of each day of the d days.
 *
 * The difficulty of a day is the maximum difficulty of a job done on that day.
 *
 * You are given an integer array jobDifficulty and an integer d.
 * The difficulty of the ith job is jobDifficulty[i].
 *
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 */

public class Solution {

    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) return -1;

        int[][] dp = new int[d + 1][n];
        for (var row : dp) Arrays.fill(row, 0x3f3f3f3f);

        int mx = 0;
        for (int i = 0; i < n; i++) {
            dp[0][i] = mx = Math.max(mx, jobDifficulty[i]);
        }

        for (int i = 1; i < d; i++) {
            for (int j = i; j < n; j++) {
                int curr = 0;
                for (int k = j; k >= i; k--) {
                    curr = Math.max(curr, jobDifficulty[k]);
                    dp[i][j] = Math.min(dp[i][j], curr + dp[i - 1][k - 1]);
                }
            }
        }

        return dp[d - 1][n - 1];
    }

    public static void main(String[] args) {
    }

}
