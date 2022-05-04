package problem.p684redundantconnection;

import common.Checker;

/**
 * 684. Redundant Connection
 *
 * https://leetcode-cn.com/problems/redundant-connection/
 *
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * You are given a graph that started as a tree with n nodes labeled from 1 to n,
 * with one additional edge added. The added edge has two different vertices chosen from 1 to n,
 * and was not an edge that already existed.
 *
 * The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that
 * there is an edge between nodes ai and bi in the graph.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes.
 * If there are multiple answers, return the answer that occurs last in the input.
 */

public class Solution {

    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        for (int i = 1; i <= edges.length; i++) parent[i] = i;

        for (int[] edge : edges) {
            if (find(parent, edge[0]) != find(parent, edge[1])) {
                union(parent, edge[0], edge[1]);
            } else return edge;
        }

        return new int[]{0};
    }

    private void union(int[] parent, int a, int b) {
        parent[find(parent, a)] = find(parent, b);
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().findRedundantConnection(new int[][]{
            {1,2}, {1,3}, {2,3}
        }), new int[]{2, 3});

        assert Checker.anyOrder(new Solution().findRedundantConnection(new int[][]{
            {1,2}, {2,3}, {3,4}, {1,4}, {1,5}
        }), new int[]{1, 4});
    }

}
