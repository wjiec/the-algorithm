package weekly.w392.D;

import java.util.Arrays;

/**
 * 3108. Minimum Cost Walk in Weighted Graph
 *
 * https://leetcode.cn/contest/weekly-contest-392/problems/minimum-cost-walk-in-weighted-graph/
 *
 * There is an undirected weighted graph with n vertices labeled from 0 to n - 1.
 *
 * You are given the integer n and an array edges, where edges[i] = [ui, vi, wi] indicates that
 * there is an edge between vertices ui and vi with a weight of wi.
 *
 * A walk on a graph is a sequence of vertices and edges. The walk starts and ends with a vertex,
 * and each edge connects the vertex that comes before it and the vertex that comes after it.
 *
 * It's important to note that a walk may visit the same edge or vertex more than once.
 *
 * The cost of a walk starting at node u and ending at node v is defined as the bitwise AND of the
 * weights of the edges traversed during the walk. In other words, if the sequence of edge weights
 * encountered during the walk is w0, w1, w2, ..., wk, then the cost is calculated as w0 & w1 & w2 & ... & wk,
 * where & denotes the bitwise AND operator.
 *
 * You are also given a 2D array query, where query[i] = [si, ti]. For each query, you need to find
 * the minimum cost of the walk starting at vertex si and ending at vertex ti.
 *
 * If there exists no such walk, the answer is -1.
 *
 * Return the array answer, where answer[i] denotes the minimum cost of a walk for query i.
 */

public class Solution {

    private int[] bits = null;
    private int[] parent = null;

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        bits = new int[n]; parent = new int[n];
        Arrays.setAll(parent, i -> i);
        Arrays.fill(bits, Integer.MAX_VALUE);
        for (var edge : edges) union(edge[0], edge[1], edge[2]);

        int[] ans = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int fa = find(query[i][0], Integer.MAX_VALUE);
            int fb = find(query[i][1], Integer.MAX_VALUE);
            if (fa != fb) ans[i] = -1;
            else ans[i] = bits[fa];
        }

        return ans;
    }

    private int find(int x, int v) {
        if (parent[x] == x) {
            bits[x] &= v;
            return x;
        }

        parent[x] = find(parent[x], v);
        bits[x] &= v;

        return parent[x];
    }

    private void union(int a, int b, int v) {
        int fa = find(a, v), fb = find(b, v);

        parent[fa] = fb;
        bits[fb] &= bits[fa];
        bits[fa] &= bits[fb];
    }

    public static void main(String[] args) {
    }

}
