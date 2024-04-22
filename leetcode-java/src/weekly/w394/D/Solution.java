package weekly.w394.D;

import common.Checker;

import java.util.*;

/**
 * 3123. Find Edges in Shortest Paths
 *
 * https://leetcode.cn/contest/weekly-contest-394/problems/find-edges-in-shortest-paths/
 *
 * You are given an undirected weighted graph of n nodes numbered from 0 to n - 1.
 *
 * The graph consists of m edges represented by a 2D array edges, where
 * edges[i] = [ai, bi, wi] indicates that there is an edge between
 * nodes ai and bi with weight wi.
 *
 * Consider all the shortest paths from node 0 to node n - 1 in the graph.
 * You need to find a boolean array answer where answer[i] is true if the edge
 * edges[i] is part of at least one shortest path. Otherwise, answer[i] is false.
 *
 * Return the array answer.
 *
 * Note that the graph may not be connected.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final static long INF = Long.MAX_VALUE / 2;

    private final Map<Integer, Map<Integer, int[]>> g = new HashMap<>();

    public boolean[] findAnswer(int n, int[][] edges) {
        ans = new boolean[edges.length];
        for (int i = 0; i < edges.length; i++) {
            var edge = edges[i];
            g.computeIfAbsent(edge[0], v -> new HashMap<>()).put(edge[1], new int[]{edge[2], i});
            g.computeIfAbsent(edge[1], v -> new HashMap<>()).put(edge[0], new int[]{edge[2], i});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(v -> v[1]));
        pq.add(new long[]{0, 0});

        while (!pq.isEmpty()) {
            var curr = pq.remove();
            int idx = (int) curr[0];

            if (dist[idx] <= curr[1]) continue;
            dist[idx] = curr[1];

            if (g.containsKey(idx)) {
                for (var next : g.get(idx).entrySet()) {
                    if (dist[next.getKey()] >= INF) {
                        pq.add(new long[]{next.getKey(), curr[1] + next.getValue()[0]});
                    }
                }
            }
        }

        long w = dist[n - 1]; end = n - 1;
        if (w < INF) {
            nodeSeen.add(0);
            dfs(0, w);
        }

        return ans;
    }

    private int end = 0;
    private boolean[] ans = null;
    private final Set<Integer> nodeSeen = new HashSet<>();
    private final Set<Integer> edgeSeen = new HashSet<>();

    private void dfs(int node, long w) {
        if (node == end) {
            for (var x : edgeSeen) ans[x] = true;
            return;
        }

        for (var next : g.get(node).entrySet()) {
            if (w >= next.getValue()[0] && !nodeSeen.contains(next.getKey())) {
                nodeSeen.add(next.getKey());
                edgeSeen.add(next.getValue()[1]);
                dfs(next.getKey(), w - next.getValue()[0]);
                nodeSeen.remove(next.getKey());
                edgeSeen.remove(next.getValue()[1]);
            }
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findAnswer(7, new int[][]{
            {5,1,3},{3,1,3},{4,3,3},{0,3,9},{2,5,4},{6,2,10},{6,1,7},{0,1,2},{6,4,6},{0,5,1},{3,2,10},{3,6,6}
        }), new boolean[]{false,false,false,false,false,false,true,true,false,false,false,false});

        assert Checker.check(new Solution().findAnswer(3, new int[][]{
            {2,1,6}
        }), new boolean[]{false});

        assert Checker.check(new Solution().findAnswer(6, new int[][]{
            {0,1,4},{0,2,1},{1,3,2},{1,4,3},{1,5,1},{2,3,1},{3,5,3},{4,5,2}
        }), new boolean[]{true,true,true,false,true,true,true,false});

        assert Checker.check(new Solution().findAnswer(4, new int[][]{
            {2,0,1},{0,1,1},{0,3,4},{3,2,2}
        }), new boolean[]{true,false,false,true});
    }

}
