package problem.p323numberofconnectedcomponentsinanundirectedgraph;

import java.util.HashSet;
import java.util.Set;

/**
 * 323. Number of Connected Components in an Undirected Graph
 *
 * https://leetcode.cn/problems/number-of-connected-components-in-an-undirected-graph/
 *
 * You have a graph of n nodes. You are given an integer n and an array edges where
 * edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
 *
 * Return the number of connected components in the graph.
 */

public class Solution {

    private static class UnionFind {
        private final int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public void union(int a, int b) { parent[find(a)] = find(b); }
        public int find(int v) { return v == parent[v] ? v : (parent[v] = find(parent[v])); }
    }

    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (var edge : edges) uf.union(edge[0], edge[1]);

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) set.add(uf.find(i));
        return set.size();
    }

    public static void main(String[] args) {
        assert new Solution().countComponents(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}) == 2;
        assert new Solution().countComponents(5, new int[][]{{0,1}, {1,2}, {2,3}, {3,4}}) == 1;
    }

}
