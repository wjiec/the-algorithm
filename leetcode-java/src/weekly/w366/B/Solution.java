package weekly.w366.B;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 2895. Minimum Processing Time
 *
 * https://leetcode.cn/contest/weekly-contest-366/problems/minimum-processing-time/
 *
 * You have n processors each having 4 cores and n * 4 tasks that need to be executed
 * such that each core should perform only one task.
 *
 * Given a 0-indexed integer array processorTime representing the time at which each
 * processor becomes available for the first time and a 0-indexed integer array tasks
 * representing the time it takes to execute each task, return the minimum time when
 * all of the tasks have been executed by the processors.
 *
 * Note: Each core executes the task independently of the others.
 */

public class Solution {

    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (var idleTime : processorTime) {
            for (int i = 0; i < 4; i++) pq.add((long) idleTime);
        }

        tasks.sort(Integer::compare);
        Collections.reverse(tasks);
        for (var task : tasks) {
            long proc = pq.remove();
            proc |= ((proc >> 32) + 1) << 32;
            pq.add(proc + task);
        }

        long ans = 0;
        while (!pq.isEmpty()) ans = pq.remove();
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().minProcessingTime(new ArrayList<>(List.of(8,10)), new ArrayList<>(List.of(2,2,3,1,8,7,4,5))) == 16;
        assert new Solution().minProcessingTime(new ArrayList<>(List.of(10,20)), new ArrayList<>(List.of(2,3,1,2,5,8,4,3))) == 23;
    }

}
