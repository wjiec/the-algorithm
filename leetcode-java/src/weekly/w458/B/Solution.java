package weekly.w458.B;

import java.util.Arrays;

/**
 * Q2. Minimize Maximum Component Cost
 *
 *  https://leetcode.cn/contest/weekly-contest-458/problems/minimize-maximum-component-cost/
 *
 *  You are given an undirected connected graph with n nodes labeled from 0 to n - 1
 *  and a 2D integer array edges where edges[i] = [ui, vi, wi] denotes an undirected
 *  edge between node ui and node vi with weight wi, and an integer k.
 *
 * You are allowed to remove any number of edges from the graph such that the resulting
 * graph has at most k connected components.
 *
 * The cost of a component is defined as the maximum edge weight in that component.
 * If a component has no edges, its cost is 0.
 *
 * Return the minimum possible value of the maximum cost among all components after such removals.
 */

public class Solution {

    public int minCost(int n, int[][] edges, int k) {
        int l = -1, r = 0;
        for (var edge : edges) r = Math.max(r, edge[2] + 1);

        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(n, edges, k, mid)) r = mid;
            else l = mid;
        }
        return r;
    }

    private static class UnionFind {
        private int chunk = 0;
        private final int[] parent;
        public UnionFind(int n) {
            chunk = n; parent = new int[n];
            Arrays.setAll(parent, i -> i);
        }

        public int find(int x) { return parent[x] == x ? x : (parent[x] = find(parent[x])); }
        public void union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa != pb) { chunk--; parent[pa] = pb; }
        }
    }

    // 连接所有不超过 maxW 的边之后的连通分量是否小于等于 k
    private boolean check(int n, int[][] edges, int k, int maxW) {
        UnionFind uf = new UnionFind(n);
        for (var edge : edges) {
            if (edge[2] <= maxW) uf.union(edge[0], edge[1]);
        }
        return uf.chunk <= k;
    }

    public static void main(String[] args) {
    }

}
