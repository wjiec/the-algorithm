package weekly.w322.D;

import java.util.*;

/**
 * 6256. Divide Nodes Into the Maximum Number of Groups
 *
 * https://leetcode.cn/contest/weekly-contest-322/problems/divide-nodes-into-the-maximum-number-of-groups/
 *
 * You are given a positive integer n representing the number of nodes in an undirected graph.
 * The nodes are labeled from 1 to n.
 *
 * You are also given a 2D integer array edges, where edges[i] = [ai, bi] indicates that
 * there is a bidirectional edge between nodes ai and bi.
 *
 * Notice that the given graph may be disconnected.
 *
 * Divide the nodes of the graph into m groups (1-indexed) such that:
 *
 * Each node in the graph belongs to exactly one group.
 * For every pair of nodes in the graph that are connected by an edge [ai, bi], if ai belongs to
 * the group with index x, and bi belongs to the group with index y, then |y - x| = 1.
 *
 * Return the maximum number of groups (i.e., maximum m) into which you can divide the nodes.
 * Return -1 if it is impossible to group the nodes with the given conditions.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static class UnionFind {
        private final int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
        public int find(int v) {
            if (parent[v] != v) {
                return find(parent[v]);
            }
            return parent[v];
        }
    }

    public int magnificentSets(int n, int[][] edges) {
        Set<Integer>[] g = new Set[n + 1];
        UnionFind uf = new UnionFind(n + 1);
        for (int i = 1; i <= n; i++) g[i] = new HashSet<>();
        for (var edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
            uf.union(edge[0], edge[1]);
        }

        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int w = walk(g, i);
            if (w == -1) return -1;
            map.merge(uf.find(i), w, Integer::max);
        }
        for (var v : map.values()) ans += v;

        return ans;
    }

    private int walk(Set<Integer>[] g, int start) {
        int[] visited = new int[g.length];
        int ans = visited[start] = 1;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int curr = queue.remove();
            ans = Math.max(ans, visited[curr]);

            for (var next : g[curr]) {
                if (visited[next] == 0) {
                    queue.add(next);
                    visited[next] = visited[curr] + 1;
                } else if (Math.abs(visited[next] - visited[curr]) != 1) return -1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().magnificentSets(6, new int[][]{{1,2},{1,4},{1,5},{2,6},{2,3},{4,6}}) == 4;
        assert new Solution().magnificentSets(3, new int[][]{{1,2},{2,3},{3,1}}) == -1;
    }

}
