package weekly.w467.A;

/**
 * Q1. Earliest Time to Finish One Task
 *
 * https://leetcode.cn/contest/weekly-contest-467/problems/earliest-time-to-finish-one-task/
 *
 * You are given a 2D integer array tasks where tasks[i] = [si, ti].
 *
 * Each [si, ti] in tasks represents a task with start time si that takes ti units of time to finish.
 *
 * Return the earliest time at which at least one task is finished.
 */

public class Solution {

    public int earliestTime(int[][] tasks) {
        int ans = Integer.MAX_VALUE;
        for (var task : tasks) {
            ans = Math.min(ans, task[0] + task[1]);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
