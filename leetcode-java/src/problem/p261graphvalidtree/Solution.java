package problem.p261graphvalidtree;

import common.TODO;

import java.util.*;

/**
 * 261. Graph Valid Tree
 *
 * https://leetcode-cn.com/problems/graph-valid-tree/
 *
 * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges
 * where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
 *
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 */

public class Solution {

    @TODO(tips = "UnionFind")
    public boolean validTree(int n, int[][] edges) {
        if (edges == null || edges.length == 0) return n == 1;

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (var edge : edges) {
            if (!map.containsKey(edge[0])) map.put(edge[0], new HashSet<>());
            if (!map.containsKey(edge[1])) map.put(edge[1], new HashSet<>());

            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        if (!map.containsKey(0)) return false;

        boolean[] visited = new boolean[n];
        Queue<int[]> queue = new ArrayDeque<>(); // [val, parent]

        visited[0] = true;
        queue.add(new int[]{0, -1});

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            for (int next : map.get(curr[0])) {
                if (next != curr[0] && next != curr[1]) {
                    if (visited[next]) return false;

                    visited[next] = true;
                    queue.add(new int[]{next, curr[0]});
                }
            }
        }

        for (int i = 0; i < n; i++) if (!visited[i]) return false;
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().validTree(5, new int[][]{{0,1},{0,2},{0,3},{1,4}});
        assert !new Solution().validTree(5, new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}});
    }

}
