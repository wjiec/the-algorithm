package weekly.bw160.C;

import java.util.*;

/**
 * Q3. Minimum Time to Reach Destination in Directed Graph
 *
 * https://leetcode.cn/contest/biweekly-contest-160/problems/minimum-time-to-reach-destination-in-directed-graph/
 *
 * You are given an integer n and a directed graph with n nodes labeled from 0 to n - 1.
 * This is represented by a 2D array edges, where edges[i] = [ui, vi, starti, endi] indicates
 * an edge from node ui to vi that can only be used at any integer time t such that starti <= t <= endi.
 *
 * You start at node 0 at time 0.
 *
 * In one unit of time, you can either:
 *
 * Wait at your current node without moving, or
 * Travel along an outgoing edge from your current node if the current time t satisfies starti <= t <= endi.
 *
 * Return the minimum time required to reach node n - 1. If it is impossible, return -1.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minTime(int n, int[][] edges) {
        Map<Integer, List<int[]>>[] g = new Map[n];
        Arrays.setAll(g, i -> new HashMap<>());
        for (var edge : edges) {
            g[edge[0]].computeIfAbsent(edge[1], k -> new ArrayList<>())
                .add(new int[]{edge[2], edge[3]});
        }

        int[] seen = new int[n]; Arrays.fill(seen, Integer.MAX_VALUE);
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));
        q.add(new int[]{0, 0}); seen[0] = 0;
        while (!q.isEmpty()) {
            var curr = q.remove();
            int node = curr[0], time = curr[1];
            if (node == n - 1) return time;

            for (var next : g[node].entrySet()) {
                int nextNode = next.getKey();
                for (var range : next.getValue()) {
                    int arrivedTime = Math.max(time, range[0]) + 1;
                    if (time <= range[1] && arrivedTime < seen[nextNode]) {
                        q.add(new int[]{nextNode, seen[nextNode] = arrivedTime});
                    }
                }
            }
        }
        return seen[n - 1] == Integer.MAX_VALUE ? -1 : seen[n - 1];
    }

    private static class Optimization {
        @SuppressWarnings("unchecked")
        public int minTime(int n, int[][] edges) {
            List<int[]>[] g = new List[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (var edge : edges) g[edge[0]].add(edge);

            int[] seen = new int[n]; Arrays.fill(seen, Integer.MAX_VALUE);
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));

            pq.add(new int[]{0, 0}); seen[0] = 0;
            while (!pq.isEmpty()) {
                var curr = pq.remove();
                int node = curr[0], time = curr[1];
                if (time > seen[node]) continue;
                if (node == n - 1) return time;

                for (var next : g[node]) {
                    int arrivedTime = Math.max(time, next[2]) + 1;
                    if (time <= next[3] && arrivedTime < seen[next[1]]) {
                        pq.add(new int[]{next[1], seen[next[1]] = arrivedTime});
                    }
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        assert new Solution().minTime(4, new int[][]{{0,1,0,3},{1,3,7,8},{0,2,1,5},{2,3,4,7}}) == 5;
    }

}
