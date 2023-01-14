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

    private record Edge(int idx, int from, int to, int weight) { }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        parents = new int[n]; total = n;
        Edge[] egs = new Edge[edges.length];
        for (int i = 0; i < egs.length; i++) {
            egs[i] = new Edge(i, edges[i][0], edges[i][1], edges[i][2]);
        }
        Arrays.sort(egs, Comparator.comparingInt(v -> v.weight));

        reset(); int minimum = 0;
        for (var edge : egs) {
            if (union(edge.from, edge.to)) {
                minimum += edge.weight;
            }
        }

        List<Integer> pseudo = new ArrayList<>();
        List<Integer> critical = new ArrayList<>();

        for (int i = 0; i < egs.length; i++) {
            reset(); int curr = 0;
            for (int j = 0; j < egs.length; j++) {
                if (i != j && union(egs[j].from, egs[j].to)) {
                    curr += egs[j].weight;
                }
            }
            if (setCount != 1 || curr > minimum) {
                critical.add(egs[i].idx);
                continue;
            }

            reset(); curr = egs[i].weight;
            union(egs[i].from, egs[i].to);
            for (int j = 0; j < egs.length; j++) {
                if (i != j && union(egs[j].from, egs[j].to)) {
                    curr += egs[j].weight;
                }
            }
            if (curr == minimum) pseudo.add(egs[i].idx);
        }

        return List.of(critical, pseudo);
    }

    private int total = 0;
    private int setCount = 0;
    private int[] parents = null;

    private void reset() {
        setCount = total;
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    private boolean union(int a, int b) {
        int fa = find(a), fb = find(b);
        if (fa != fb) {
            setCount--;
            parents[fa] = fb;
            return true;
        }
        return false;
    }

    private int find(int v) {
        return v == parents[v] ? v : (parents[v] = find(parents[v]));
    }

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
