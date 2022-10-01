package problem.p1986minimumnumberofworksessionstofinishthetasks;

import common.TODO;
import common.Tag;

/**
 * 1986. Minimum Number of Work Sessions to Finish the Tasks
 *
 * https://leetcode.cn/problems/minimum-number-of-work-sessions-to-finish-the-tasks/
 *
 * There are n tasks assigned to you. The task times are represented as an integer array
 * tasks of length n, where the ith task takes tasks[i] hours to finish.
 *
 * A work session is when you work for at most sessionTime consecutive hours and then take a break.
 *
 * You should finish the given tasks in a way that satisfies the following conditions:
 *
 * If you start a task in a work session, you must complete it in the same work session.
 * You can start a new task immediately after finishing the previous one.
 * You may complete the tasks in any order.
 * Given tasks and sessionTime, return the minimum number of work sessions needed to finish
 * all the tasks following the conditions above.
 *
 * The tests are generated such that sessionTime is greater than or equal to the maximum element in tasks[i].
 */

public class Solution {

    @TODO
    @Tag({"连续完成任务", "至多连续完成N小时"})
    public int minSessions(int[] tasks, int sessionTime) {
        int n = 1 << tasks.length;
        int[][] dp = new int[n][2];
        for (var x : dp) x[0] = x[1] = Integer.MAX_VALUE;
        dp[0][0] = 1; dp[0][1] = 0;

        for (int mask = 0; mask < n; mask++) {
            for (int i = 0; i < tasks.length; i++) {
                if ((mask & 1 << i) != 0) {
                    dp[mask] = min(dp[mask], add(dp[mask ^ (1 << i)], tasks[i], sessionTime));
                }
            }
        }
        return dp[n - 1][0];
    }

    private int[] add(int[] curr, int x, int st) {
        if (curr[1] + x <= st) {
            return new int[]{curr[0], curr[1] + x};
        }
        return new int[]{curr[0] + 1, x};
    }

    private int[] min(int[] a, int[] b) {
        if (a[0] == b[0]) {
            return a[1] <= b[1] ? a : b;
        }
        return a[0] < b[0] ? a : b;
    }

    public static void main(String[] args) {
        assert new Solution().minSessions(new int[]{1,2,3}, 3) == 2;
        assert new Solution().minSessions(new int[]{3,1,3,1,1}, 8) == 2;
        assert new Solution().minSessions(new int[]{1,2,3,4,5}, 15) == 1;
    }

}
