package weekly.w426.D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3373. Maximize the Number of Target Nodes After Connecting Trees II
 *
 * https://leetcode.cn/contest/weekly-contest-426/problems/maximize-the-number-of-target-nodes-after-connecting-trees-ii/
 *
 * There exist two undirected trees with n and m nodes, labeled from [0, n - 1] and [0, m - 1], respectively.
 *
 * You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively,
 * where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first
 * tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.
 *
 * Node u is target to node v if the number of edges on the path from u to v is even. Note that a
 * node is always target to itself.
 *
 * Return an array of n integers answer, where answer[i] is the maximum possible number of nodes
 * that are target to node i of the first tree if you had to connect one node from the first tree
 * to another node in the second tree.
 *
 * Note that queries are independent from each other. That is, for every query you will remove the
 * added edge before proceeding to the next query.
 */

/** @noinspection DuplicatedCode, unchecked */
public class Solution {

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        // 在一棵树中, 从任意节点到达它的子节点的子节点都是偶数, 反过来也一样
        //  - 当连接另一棵树之后, 奇偶性就正好调转了
        buildTree(edges2);
        Set<Integer>[] g2 = new Set[]{new HashSet<>(), new HashSet<>()};
        dfs(0, -1, 0, g2);
        int maxNodes = Math.max(g2[0].size(), g2[1].size());

        buildTree(edges1);
        Set<Integer>[] g1 = new Set[]{new HashSet<>(), new HashSet<>()};
        dfs(0, -1, 0, g1);

        int[] ans = new int[edges1.length + 1];
        for (var e : g1[0]) ans[e] = g1[0].size() + maxNodes;
        for (var e : g1[1]) ans[e] = g1[1].size() + maxNodes;

        return ans;
    }

    private void dfs(int curr, int parent, int depth, Set<Integer>[] group) {
        group[depth % 2].add(curr);
        for (var next : g.get(curr)) {
            if (next == parent) continue;
            dfs(next, curr, depth + 1, group);
        }
    }

    private final Map<Integer, Set<Integer>> g = new HashMap<>();

    private void buildTree(int[][] edges) {
        g.clear();
        for (var edge : edges) {
            g.computeIfAbsent(edge[0], x -> new HashSet<>()).add(edge[1]);
            g.computeIfAbsent(edge[1], x -> new HashSet<>()).add(edge[0]);
        }
    }

    public static void main(String[] args) {
    }

}
