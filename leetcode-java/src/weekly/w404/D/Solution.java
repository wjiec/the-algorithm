package weekly.w404.D;

import java.util.*;

/**
 * 3203. Find Minimum Diameter After Merging Two Trees
 *
 * https://leetcode.cn/contest/weekly-contest-404/problems/find-minimum-diameter-after-merging-two-trees/
 *
 * There exist two undirected trees with n and m nodes, numbered from 0 to n - 1 and
 * from 0 to m - 1, respectively. You are given two 2D integer arrays edges1 and
 * edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates
 * that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi]
 * indicates that there is an edge between nodes ui and vi in the second tree.
 *
 * You must connect one node from the first tree with another node from the second tree with an edge.
 *
 * Return the minimum possible diameter of the resulting tree.
 *
 * The diameter of a tree is the length of the longest path between any two nodes in the tree.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        var a = minimumHeightTree(edges1);
        var b = minimumHeightTree(edges2);
        return Math.max(a[0] + b[0] - 1, Math.max(a[1], b[1]));
    }

    private int[] minimumHeightTree(int[][] edges) {
        if (edges.length == 0) return new int[]{1, 1};
        if (edges.length == 1) return new int[]{2, 2};

        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (var edge : edges) {
            g.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            g.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }

        int[] degree = new int[edges.length + 1];
        int[] maxHeight = new int[edges.length + 1];

        Queue<Integer> q = new ArrayDeque<>();
        for (var kv : g.entrySet()) {
            if (kv.getValue().size() == 1) {
                q.add(kv.getKey());
                maxHeight[kv.getKey()] = 1;
            } else {
                degree[kv.getKey()] = kv.getValue().size();
            }
        }

        int ans = 0, root = 0;
        while (!q.isEmpty()) {
            root = q.remove();
            ans = Math.max(ans, maxHeight[root]);

            for (var next : g.get(root)) {
                maxHeight[next] = Math.max(maxHeight[next], maxHeight[root] + 1);
                if (--degree[next] == 1) {
                    q.add(next);
                }
            }
        }

        int max = 0;
        for (var a : g.get(root)) {
            int ac = dfs(g, a, root);
            for (var b : g.get(root)) {
                if (a.equals(b)) continue;

                int bc = dfs(g, b, root);
                max = Math.max(max, ac + bc);
            }
        }

        return new int[]{ans, max};
    }

    private int dfs(Map<Integer, Set<Integer>> g, int node, int parent) {
        int ans = 1;
        for (var next : g.get(node)) {
            if (next != parent) {
                ans = Math.max(ans, dfs(g, next, node) + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumDiameterAfterMerge(new int[][]{{0,1},{6,3},{9,4},{2,6},{5,2},{8,5},{0,8},{7,0},{7,9}}, new int[][]{{0,1},{0,2},{0,3}}) == 8;
        assert new Solution().minimumDiameterAfterMerge(new int[][]{{0,1},{2,0},{3,2},{3,6},{8,7},{4,8},{5,4},{3,5},{3,9}}, new int[][]{{0,1},{0,2},{0,3}}) == 7;

        assert new Solution().minimumDiameterAfterMerge(new int[][]{{0,1},{0,2},{0,3},{2,4},{2,5},{3,6},{2,7}}, new int[][]{{0,1},{0,2},{0,3},{2,4},{2,5},{3,6},{2,7}}) == 5;
        assert new Solution().minimumDiameterAfterMerge(new int[][]{{0,1},{0,2},{0,3}}, new int[][]{{0, 1}}) == 3;
    }

}
