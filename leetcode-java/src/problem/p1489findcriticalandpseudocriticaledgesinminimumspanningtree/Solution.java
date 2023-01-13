package problem.p1489findcriticalandpseudocriticaledgesinminimumspanningtree;

import common.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree
 *
 * https://leetcode.cn/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/
 *
 * Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1, and
 * an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional and weighted
 * edge between nodes ai and bi. A minimum spanning tree (MST) is a subset of the graph's
 * edges that connects all vertices without cycles and with the minimum possible total edge weight.
 *
 * Find all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST).
 * An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical
 * edge. On the other hand, a pseudo-critical edge is that which can appear in some MSTs but not all.
 *
 * Note that you can return the indices of the edges in any order.
 */

public class Solution {

    private static class Edge {
        private boolean available = true;
        private final int idx, from, to, weight;
        public Edge(int idx, int from, int to, int weight) {
            this.idx = idx; this.from = from; this.to = to; this.weight = weight;
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        parents = new int[n];
        Edge[] egs = new Edge[edges.length];
        for (int i = 0; i < egs.length; i++) {
            egs[i] = new Edge(i, edges[i][0], edges[i][1], edges[i][2]);
        }
        Arrays.sort(egs, Comparator.comparingInt(v -> v.weight));

        MST minimum = mst(n, egs[0].idx, egs);
        List<Integer> pseudo = new ArrayList<>();
        List<Integer> critical = new ArrayList<>();

        for (int i = 0; i < egs.length; i++) {
            egs[i].available = false;
            MST curr = mst(n, i, egs);
            egs[i].available = true;

            if (curr.weight > minimum.weight) critical.add(egs[i].idx);
            if (curr.weight == minimum.weight) pseudo.add(egs[i].idx);
        }

        System.out.println(List.of(critical, pseudo));
        return List.of(critical, pseudo);
    }

    private record MST(int weight, List<Integer> discard) {}

    private MST mst(int n, int i, Edge[] edges) {
        reset();
        int weight = edges[i].weight;
        union(edges[i].from, edges[i].to);
        List<Integer> discard = new ArrayList<>();
        for (var edge : edges) {
            if (edge.available) {
                if (find(edge.from) != find(edge.to)) {
                    weight += edge.weight;
                    union(edge.from, edge.to);
                }
            }
        }

        // 判断能不能删除当前这条边
        int group = find(0);
        for (int x = 0; x < n; x++) {
            if (find(x) != group) {
                return new MST(Integer.MAX_VALUE, new ArrayList<>());
            }
        }

        return new MST(weight, discard);
    }

    private int[] parents = null;

    private void reset() {
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    private void union(int a, int b) { parents[find(a)] = find(b); }

    private int find(int v) { return v == parents[v] ? v : (parents[v] = find(parents[v])); }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findCriticalAndPseudoCriticalEdges(4, new int[][]{
            {0,1,1},{0,3,1},{0,2,1},{1,2,1},{1,3,1},{2,3,1}
        }), List.of(List.of(),List.of(0,1,2,3,4,5)));

        assert Checker.check(new Solution().findCriticalAndPseudoCriticalEdges(6, new int[][]{
            {0,1,1},{1,2,1},{0,2,1},{2,3,4},{3,4,2},{3,5,2},{4,5,2}
        }), List.of(List.of(3),List.of(0,1,2,4,5,6)));

        assert Checker.check(new Solution().findCriticalAndPseudoCriticalEdges(5, new int[][]{
            {0,1,1},{1,2,1},{2,3,2},{0,3,2},{0,4,3},{3,4,3},{1,4,6}
        }), List.of(List.of(0,1),List.of(2,3,4,5)));

        assert Checker.check(new Solution().findCriticalAndPseudoCriticalEdges(4, new int[][]{
            {0,1,1},{1,2,1},{2,3,1},{0,3,1}
        }), List.of(List.of(),List.of(0,1,2,3)));
    }

}
