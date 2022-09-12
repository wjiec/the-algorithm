package problem.p1834singlethreadedcpu;

import common.Checker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1834. Single-Threaded CPU
 *
 * https://leetcode.cn/problems/single-threaded-cpu/
 *
 * You are given n tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where
 * tasks[i] = [enqueueTimei, processingTimei] means that the ith task will be available to process
 * at enqueueTimei and will take processingTimei to finish processing.
 *
 * You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
 *
 * If the CPU is idle and there are no available tasks to process, the CPU remains idle.
 * If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time.
 * If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
 * Once a task is started, the CPU will process the entire task without stopping.
 * The CPU can finish a task then start a new one instantly.
 *
 * Return the order in which the CPU will process the tasks.
 */

public class Solution {

    public int[] getOrder(int[][] tasks) {
        // [enqueue, processing, index]
        int[][] sorted = new int[tasks.length][];
        for (int i = 0; i < tasks.length; i++) {
            sorted[i] = new int[]{tasks[i][0], tasks[i][1], i};
        }
        Arrays.sort(sorted, Comparator.comparingInt(t -> t[0]));

        // [enqueue, processing, index]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
            a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);

        int idx = 0, n = tasks.length;
        int[] ans = new int[n];
        for (int i = 0, t = sorted[0][0]; i < n; ) {
            if (pq.isEmpty() && sorted[i][0] > t) t = sorted[i][0];
            while (i < n && sorted[i][0] <= t) pq.add(sorted[i++]);

            int[] curr = pq.remove();
            ans[idx++] = curr[2]; t += curr[1];
        }

        while (!pq.isEmpty()) ans[idx++] = pq.remove()[2];
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getOrder(new int[][]{{5,2},{7,2},{9,4},{6,3},{5,10},{1,1}}), new int[]{5,0,1,3,2,4});

        assert Checker.check(new Solution().getOrder(new int[][]{{1,2},{2,4},{3,2},{4,1}}), new int[]{0,2,3,1});
        assert Checker.check(new Solution().getOrder(new int[][]{{7,10},{7,12},{7,5},{7,4},{7,2}}), new int[]{4,3,2,0,1});
        assert Checker.check(new Solution().getOrder(new int[][]{{7,10},{7,12},{7,5},{7,4},{7,2}}), new int[]{4,3,2,0,1});
    }

}
