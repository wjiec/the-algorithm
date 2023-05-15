package weekly.w345.D;

import java.util.HashSet;
import java.util.Set;

/**
 * 2685. Count the Number of Complete Components
 *
 * https://leetcode.cn/contest/weekly-contest-345/problems/count-the-number-of-complete-components/
 *
 * You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1.
 * You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected
 * edge connecting vertices ai and bi.
 *
 * Return the number of complete connected components of the graph.
 *
 * A connected component is a subgraph of a graph in which there exists a path between any two vertices, and
 * no vertex of the subgraph shares an edge with a vertex outside of the subgraph.
 *
 * A connected component is said to be complete if there exists an edge between every pair of its vertices.
 */

public class Solution {

    private static class UnionFind {
        private final int[] parent;
        private final int[] edges;
        private final int[] nodes;
        public UnionFind(int n) {
            parent = new int[n];
            edges = new int[n];
            nodes = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                edges[i] = 0;
                nodes[i] = 1;
            }
        }
        private int edge(int x) { return edges[x]; }
        private int node(int x) { return nodes[x]; }
        private int find(int x) { return parent[x] == x ? x : (parent[x] = find(parent[x])); }
        private void union(int a, int b) {
            int fa = find(a), fb = find(b);
            if (fa != fb) {
                parent[fa] = fb;
                nodes[fb] += nodes[fa];
                edges[fb] += edges[fa];
            }
            edges[fb]++;
        }
    }

    public int countCompleteComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (var edge : edges) uf.union(edge[0], edge[1]);

        int ans = 0;
        Set<Integer> found = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int p = uf.find(i);
            if (found.add(p)) {
                int ec = uf.edge(p), nc = uf.node(p);
                if (ec * 2 == nc * (nc - 1)) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countCompleteComponents(6, new int[][]{{0, 1}, {0, 2}, {1, 2}, {3, 4}}) == 3;
        assert new Solution().countCompleteComponents(6, new int[][]{{0, 1}, {0, 2}, {1, 2}, {3, 4}, {3, 5}}) == 1;
    }

}
