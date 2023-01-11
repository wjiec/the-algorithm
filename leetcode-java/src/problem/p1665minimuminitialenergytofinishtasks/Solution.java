package problem.p1665minimuminitialenergytofinishtasks;

import common.TODO;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1665. Minimum Initial Energy to Finish Tasks
 *
 * https://leetcode.cn/problems/minimum-initial-energy-to-finish-tasks/
 *
 * You are given an array tasks where tasks[i] = [actuali, minimumi]:
 *
 * actuali is the actual amount of energy you spend to finish the ith task.
 * minimumi is the minimum amount of energy you require to begin the ith task.
 * For example, if the task is [10, 12] and your current energy is 11, you cannot
 * start this task. However, if your current energy is 13, you can complete this
 * task, and your energy will be 3 after finishing it.
 *
 * You can finish the tasks in any order you like.
 *
 * Return the minimum initial amount of energy you will need to finish all the tasks.
 */

public class Solution {

    @TODO
    public int minimumEffort(int[][] tasks) {
        // [c, r] 最低需要 r 能量, 同时消耗 c 能量
        Arrays.sort(tasks, Comparator.comparingInt(v -> v[0] - v[1]));

        int ans = 0;
        for (int i = tasks.length - 1; i >= 0; i--) {
            ans = Math.max(ans + tasks[i][0], tasks[i][1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumEffort(new int[][]{{1,2},{2,4},{4,8}}) == 8;
        assert new Solution().minimumEffort(new int[][]{{1,3},{2,4},{10,11},{10,12},{8,9}}) == 32;
        assert new Solution().minimumEffort(new int[][]{{1,7},{2,8},{3,9},{4,10},{5,11},{6,12}}) == 27;
    }

}
