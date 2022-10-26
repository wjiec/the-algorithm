package problem.p1882processtasksusingservers;

import common.Checker;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1882. Process Tasks Using Servers
 *
 * https://leetcode.cn/problems/process-tasks-using-servers/
 *
 * You are given two 0-indexed integer arrays servers and tasks of lengths n and m respectively.
 * servers[i] is the weight of the ith server, and tasks[j] is the time needed to process the jth task in seconds.
 *
 * Tasks are assigned to the servers using a task queue. Initially, all servers are free, and the queue is empty.
 *
 * At second j, the jth task is inserted into the queue (starting with the 0th task being inserted at second 0).
 * As long as there are free servers and the queue is not empty, the task in the front of the queue
 * will be assigned to a free server with the smallest weight, and in case of a tie, it is assigned
 * to a free server with the smallest index.
 *
 * If there are no free servers and the queue is not empty, we wait until a server becomes free and immediately
 * assign the next task. If multiple servers become free at the same time, then multiple tasks from the queue
 * will be assigned in order of insertion following the weight and index priorities above.
 *
 * A server that is assigned task j at second t will be free again at second t + tasks[j].
 *
 * Build an array ans of length m, where ans[j] is the index of the server the jth task will be assigned to.
 *
 * Return the array ans.
 */

public class Solution {

    private static class Server {
        private long free = 0;
        private final int index;
        private final int weight;
        public Server(int i, int w) { index = i; weight = w; }
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<Server> busy = new PriorityQueue<>(Comparator.comparingLong(s -> s.free));
        PriorityQueue<Server> idle = new PriorityQueue<>((a, b) -> {
            if (a.weight != b.weight) return a.weight - b.weight;
            return a.index - b.index;
        });
        for (int i = 0; i < servers.length; i++) idle.add(new Server(i, servers[i]));

        long curr = 0;
        int[] ans = new int[tasks.length];
        for (int i = 0; i < tasks.length; ) {
            curr = Math.max(curr, i);
            while (!busy.isEmpty() && busy.peek().free <= curr) {
                idle.add(busy.remove());
            }
            if (idle.isEmpty() && !busy.isEmpty()) {
                curr = busy.peek().free;
                while (!busy.isEmpty() && busy.peek().free <= curr) {
                    idle.add(busy.remove());
                }
            }

            Server s = idle.remove();
            ans[i] = s.index;
            s.free = curr + tasks[i++];
            busy.add(s);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().assignTasks(new int[]{10,63,95,16,85,57,83,95,6,29,71},
            new int[]{70,31,83,15,32,67,98,65,56,48,38,90,5}), new int[]{8,0,3,9,5,1,10,6,4,2,7,9,0});

        assert Checker.check(new Solution().assignTasks(new int[]{3,3,2}, new int[]{1,2,3,2,1,2}), new int[]{2,2,0,2,1,2});
        assert Checker.check(new Solution().assignTasks(new int[]{5,1,4,3,2}, new int[]{2,1,2,4,5,2,1}), new int[]{1,4,1,4,1,3,2});
    }

}
