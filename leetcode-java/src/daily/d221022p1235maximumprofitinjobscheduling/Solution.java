package daily.d221022p1235maximumprofitinjobscheduling;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1235. Maximum Profit in Job Scheduling
 *
 * https://leetcode.cn/problems/maximum-profit-in-job-scheduling/
 *
 * We have n jobs, where every job is scheduled to be done from startTime[i]
 * to endTime[i], obtaining a profit of profit[i].
 *
 * You're given the startTime, endTime and profit arrays, return the maximum profit
 * you can take such that there are no two jobs in the subset with overlapping time range.
 *
 * If you choose a job that ends at time X you will be able to start another job that
 * starts at time X.
 */

public class Solution {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];
        for (int i = 0; i < jobs.length; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        Arrays.sort(jobs, Comparator.comparingInt(j -> j[1]));

        int[] dp = new int[startTime.length + 1];
        for (int i = 1; i <= startTime.length; i++) {
            int k = binarySearch(jobs, i - 1, jobs[i - 1][0]);
            dp[i] = Math.max(dp[i - 1], dp[k] + jobs[i - 1][2]);
        }
        return dp[startTime.length];
    }

    private int binarySearch(int[][] jobs, int right, int target) {
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (jobs[mid][1] > target) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    public static void main(String[] args) {
        assert new Solution().jobScheduling(new int[]{1,2,3,3}, new int[]{3,4,5,6}, new int[]{50,10,40,70}) == 120;
        assert new Solution().jobScheduling(new int[]{1,2,3,4,6}, new int[]{3,4,5,6}, new int[]{50,10,40,70}) == 120;
    }

}
