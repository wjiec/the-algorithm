package weekly.w457.C;

import java.util.Arrays;

/**
 * Q3. Minimum Time for K Connected Components
 *
 * https://leetcode.cn/contest/weekly-contest-457/problems/minimum-time-for-k-connected-components/
 *
 * You are given an integer n and an undirected graph with n nodes labeled from 0 to n - 1.
 * This is represented by a 2D array edges, where edges[i] = [ui, vi, timei] indicates an
 * undirected edge between nodes ui and vi that can be removed at timei.
 *
 * You are also given an integer k.
 *
 * Initially, the graph may be connected or disconnected. Your task is to find the minimum
 * time t such that after removing all edges with time <= t, the graph contains at least k
 * connected components.
 *
 * Return the minimum time t.
 *
 * A connected component is a subgraph of a graph in which there exists a path between
 * any two vertices, and no vertex of the subgraph shares an edge with a vertex
 * outside of the subgraph
 */

public class Solution {

    private static class UnionFind {
        private int count;
        private final int[] parent;
        public UnionFind(int n) {
            parent = new int[count = n];
            Arrays.setAll(parent, i -> i);
        }
        public int find(int x) { return parent[x] == x ? x : (parent[x] = find(parent[x])); }
        public void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa != pb) { parent[pa] = pb; count--; }
        }
    }

    public int minTime(int n, int[][] edges, int k) {
        int l = -1, r = Integer.MAX_VALUE / 2;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(n, edges, k, mid)) r = mid;
            else l = mid;
        }
        return r;
    }

    private boolean check(int n, int[][] edges, int k, int t) {
        UnionFind uf = new UnionFind(n);
        for (var edge : edges) {
            if (edge[2] > t) uf.union(edge[0], edge[1]);
        }
        return uf.count >= k;
    }

    public static void main(String[] args) {
    }

}
