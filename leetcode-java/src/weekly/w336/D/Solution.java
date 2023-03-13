package weekly.w336.D;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2589. Minimum Time to Complete All Tasks
 *
 * https://leetcode.cn/problems/minimum-time-to-complete-all-tasks/
 *
 * There is a computer that can run an unlimited number of tasks at the same time.
 * You are given a 2D integer array tasks where tasks[i] = [starti, endi, durationi]
 * indicates that the ith task should run for a total of durationi seconds (not necessarily
 * continuous) within the inclusive time range [starti, endi].
 *
 * You may turn on the computer only when it needs to run a task.
 * You can also turn it off if it is idle.
 *
 * Return the minimum time during which the computer should be turned on to complete all tasks.
 */

public class Solution {

    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, Comparator.comparingInt(v -> v[1]));
        int ans = 0, max = tasks[tasks.length - 1][1];
        boolean[] running = new boolean[max + 1];
        for (var task : tasks) {
            int duration = task[2];
            for (int i = task[0]; i <= task[1]; i++) {
                if (running[i]) --duration;
            }
            for (int i = task[1]; duration > 0; --i) {
                if (!running[i]) {
                    running[i] = true;
                    --duration;
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findMinimumTime(new int[][]{{6,15,4},{3,7,1},{4,20,4}}) == 4;

        assert new Solution().findMinimumTime(new int[][]{{2,3,1},{4,5,1},{1,5,2}}) == 2;
        assert new Solution().findMinimumTime(new int[][]{{1,3,2},{2,5,3},{5,6,2}}) == 4;
    }

}
