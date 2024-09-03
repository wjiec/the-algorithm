package weekly.w413.B;

import java.util.PriorityQueue;

/**
 * 3275. K-th Nearest Obstacle Queries
 *
 * https://leetcode.cn/contest/weekly-contest-413/problems/k-th-nearest-obstacle-queries/
 *
 * There is an infinite 2D plane.
 *
 * You are given a positive integer k. You are also given a 2D array queries, which
 * contains the following queries:
 *
 * queries[i] = [x, y]: Build an obstacle at coordinate (x, y) in the plane. It is guaranteed
 * that there is no obstacle at this coordinate when this query is made.
 *
 * After each query, you need to find the distance of the kth nearest obstacle from the origin.
 *
 * Return an integer array results where results[i] denotes the kth nearest obstacle after
 * query i, or results[i] == -1 if there are less than k obstacles.
 *
 * Note that initially there are no obstacles anywhere.
 *
 * The distance of an obstacle at coordinate (x, y) from the origin is given by |x| + |y|.
 */

public class Solution {

    /** @noinspection DataFlowIssue*/
    public int[] resultsArray(int[][] queries, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            pq.add(Math.abs(queries[i][0]) + Math.abs(queries[i][1]));
            if (pq.size() > k) pq.remove();
            ans[i] = pq.size() == k ? pq.peek() : -1;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
