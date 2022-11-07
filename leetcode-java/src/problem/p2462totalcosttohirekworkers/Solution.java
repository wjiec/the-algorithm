package problem.p2462totalcosttohirekworkers;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 6231. Total Cost to Hire K Workers
 *
 * https://leetcode.cn/problems/total-cost-to-hire-k-workers/
 *
 * You are given a 0-indexed integer array costs where costs[i] is the cost of hiring the ith worker.
 *
 * You are also given two integers k and candidates. We want to hire exactly k workers
 * according to the following rules:
 *
 * You will run k sessions and hire exactly one worker in each session.
 * In each hiring session, choose the worker with the lowest cost from either the first candidates
 * workers or the last candidates workers. Break the tie by the smallest index.
 *
 * For example, if costs = [3,2,7,7,1,2] and candidates = 2, then in the first hiring session, we will
 * choose the 4th worker because they have the lowest cost [3,2,7,7,1,2].
 *
 * In the second hiring session, we will choose 1st worker because they have the
 * same lowest cost as 4th worker but they have the smallest index [3,2,7,7,2].
 *
 * Please note that the indexing may be changed in the process.
 *
 * If there are fewer than candidates workers remaining, choose the worker with the
 * lowest cost among them. Break the tie by the smallest index.
 *
 * A worker can only be chosen once.
 *
 * Return the total cost to hire exactly k workers.
 */

public class Solution {

    public long totalCost(int[] costs, int k, int candidates) {
        long ans = 0;
        if (2 * candidates >= costs.length) {
            Arrays.sort(costs);
            for (int i = 0; i < k; i++) {
                ans += costs[i];
            }
            return ans;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if (costs[a] != costs[b]) return costs[a] - costs[b];
            return a - b;
        });

        for (int i = 0; i < candidates; i++) {
            pq.add(i); pq.add(costs.length - i - 1);
        }

        for (int l = candidates, r = costs.length - candidates - 1; k > 0; k--) {
            int idx = pq.remove(); ans += costs[idx];
            if (l <= r) pq.add(idx < l ? l++ : r--);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().totalCost(new int[]{17,12,10,2,7,2,11,20,8}, 3, 4) == 11;
        assert new Solution().totalCost(new int[]{1,2,4,1}, 3, 3) == 4;
    }

}
