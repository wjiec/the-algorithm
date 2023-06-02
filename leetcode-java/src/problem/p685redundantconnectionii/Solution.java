package problem.p685redundantconnectionii;

import common.Checker;

/**
 * 685. Redundant Connection II
 *
 * https://leetcode.cn/problems/redundant-connection-ii/
 *
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for
 * which all other nodes are descendants of this node, plus every node has exactly one parent, except for
 * the root node which has no parents.
 *
 * The given input is a directed graph that started as a rooted tree with n nodes (with
 * distinct values from 1 to n), with one additional directed edge added.
 * The added edge has two different vertices chosen from 1 to n, and was not an edge
 * that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a
 * pair [ui, vi] that represents a directed edge connecting nodes ui and vi, where
 * ui is a parent of child vi.
 *
 * Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes.
 * If there are multiple answers, return the answer that occurs last in the given 2D-array.
 */

public class Solution {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int remove = -1;
        int[] inDegree = new int[1001];
        for (int i = 0;i < edges.length; i++) {
            if (++inDegree[edges[i][1]] == 2) {
                remove = i;
            }
        }

        if (remove > 0) {
            if (!check(edges, remove)) {
                for (var edge : edges) {
                    if (edge[1] == edges[remove][1]) {
                        return edge;
                    }
                }
            } else return edges[remove];
        }

        for (int i = 0; i < parent.length; i++) parent[i] = i;
        for (int i = 0; i < edges.length; i++) {
            if (i == remove) continue;
            if (find(edges[i][0]) == find(edges[i][1])) return edges[i];
            else union(edges[i][0], edges[i][1]);
        }
        return new int[0];
    }

    private boolean check(int[][] edges, int remove) {
        for (int i = 0; i < parent.length; i++) parent[i] = i;
        for (int i = 0; i < edges.length; i++) {
            if (i == remove) continue;
            if (find(edges[i][0]) == find(edges[i][1])) return false;
            else union(edges[i][0], edges[i][1]);
        }
        return true;
    }

    private final int[] parent = new int[1001];
    private int find(int v) { return parent[v] == v ? v : (parent[v] = find(parent[v])); }
    private void union(int a, int b) { if (find(a) != find(b)) parent[find(b)] = a; }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findRedundantDirectedConnection(new int[][]{{1,2},{1,3},{2,3}}), new int[]{2,3});
        assert Checker.check(new Solution().findRedundantDirectedConnection(new int[][]{{1,2},{2,3},{3,4},{4,1},{1,5}}), new int[]{4,1});
    }

}
