package daily.d220911p857minimumcosttohirekworkers;

import common.Checker;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 857. Minimum Cost to Hire K Workers
 *
 * https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/
 *
 * There are n workers. You are given two integer arrays quality and wage where quality[i] is the
 * quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.
 *
 * We want to hire exactly k workers to form a paid group. To hire a group of k workers, we
 * must pay them according to the following rules:
 *
 * Every worker in the paid group should be paid in the ratio of their quality compared to other
 * workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Given the integer k, return the least amount of money needed to form a paid group satisfying
 * the above conditions. Answers within 10-5 of the actual answer will be accepted.
 */

public class Solution {

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        Integer[] order = new Integer[quality.length];
        for (int i = 0; i < quality.length; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> quality[b] * wage[a] - quality[a] * wage[b]);

        double ans = Double.MAX_VALUE, total = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k - 1; i++) {
            total += quality[order[i]];
            pq.add(quality[order[i]]);
        }

        for (int i = k - 1; i < quality.length; i++) {
            int x = order[i];
            total += quality[x];
            pq.add(quality[x]);
            ans = Math.min(ans, (double) wage[x] / quality[x] * total);
            total -= pq.remove();
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().mincostToHireWorkers(new int[]{25,68,35,62,52,57,35,83,40,51}, new int[]{147,97,251,129,438,443,120,366,362,343}, 6), 1979.31429);

        assert Checker.check(new Solution().mincostToHireWorkers(new int[]{10,20,5}, new int[]{70,50,30}, 2), 105.0);
        assert Checker.check(new Solution().mincostToHireWorkers(new int[]{3,1,10,10,1}, new int[]{4,8,2,2,7}, 3), 30.66667);
    }

}
