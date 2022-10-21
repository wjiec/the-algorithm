package problem.p2365taskschedulerii;

import java.util.HashMap;
import java.util.Map;

/**
 * 2365. Task Scheduler II
 *
 * https://leetcode.cn/problems/task-scheduler-ii/
 *
 * You are given a 0-indexed array of positive integers tasks, representing tasks that
 * need to be completed in order, where tasks[i] represents the type of the ith task.
 *
 * You are also given a positive integer space, which represents the minimum number of days
 * that must pass after the completion of a task before another task of the
 * same type can be performed.
 *
 * Each day, until all tasks have been completed, you must either:
 *
 * Complete the next task from tasks, or
 * Take a break.
 * Return the minimum number of days needed to complete all tasks.
 */

public class Solution {

    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Long> usable = new HashMap<>();

        long ans = 1;
        for (var task : tasks) {
            Long cd = usable.get(task);
            if (cd == null || cd < ans) {
                usable.put(task, ans + space);
            } else {
                ans = cd + 1;
                usable.put(task, ans + space);
            }

            ans++;
        }

        return ans - 1;
    }

    public static void main(String[] args) {
        assert new Solution().taskSchedulerII(new int[]{1,2,1,2,3,1}, 3) == 9;
        assert new Solution().taskSchedulerII(new int[]{5,8,8,5}, 2) == 6;
    }

}
