package weekly.w426.C;

import common.Checker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3372. Maximize the Number of Target Nodes After Connecting Trees I
 *
 * https://leetcode.cn/contest/weekly-contest-426/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/
 *
 * There exist two undirected trees with n and m nodes, with distinct labels
 * in ranges [0, n - 1] and [0, m - 1], respectively.
 *
 * You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively,
 * where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first
 * tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.
 *
 * You are also given an integer k.
 *
 * Node u is target to node v if the number of edges on the path from u to v is less than or equal to k.
 * Note that a node is always target to itself.
 *
 * Return an array of n integers answer, where answer[i] is the maximum possible number of nodes
 * target to node i of the first tree if you have to connect one node from the first tree to
 * another node in the second tree.
 *
 * Note that queries are independent from each other. That is, for every query you will remove
 * the added edge before proceeding to the next query.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        // 对于 tree1 来说, 连接 tree2 的哪个节点其实是固定的, 我们需要找到 tree2 中的某个节点
        //  - 从这个节点出发经过 k - 1 步可以到达最多的节点
        //  - 枚举两棵树的所有节点, 找到每个节点在 k 步可以达到的节点数

        // 由于选择 tree2 的节点实际与 tree1 无关, 所以我们先算 tree2 的最多 k - 1 步能达到的节点数的最大值
        buildTree(edges2); int maxNodes = 0;
        for (int i = 0; i <= edges2.length; i++) maxNodes = Math.max(maxNodes, dfs(i, -1, k - 1));

        // 枚举 tree1 中的所有节点
        buildTree(edges1); int[] ans = new int[edges1.length + 1];
        for (int i = 0; i <= edges1.length; i++) {
            ans[i] = dfs(i, -1, k) + maxNodes;
        }

        return ans;
    }

    private final Map<Integer, Set<Integer>> g = new HashMap<>();

    private void buildTree(int[][] edges) {
        g.clear();
        for (var edge : edges) {
            g.computeIfAbsent(edge[0], x -> new HashSet<>()).add(edge[1]);
            g.computeIfAbsent(edge[1], x -> new HashSet<>()).add(edge[0]);
        }
    }

    private int dfs(int curr, int parent, int k) {
        if (k < 0) return 0;

        int ans = 1;
        for (var next : g.get(curr)) {
            if (next == parent) continue;
            ans += dfs(next, curr, k - 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().maxTargetNodes(new int[][]{{0,1},{0,2},{2,3},{2,4}}, new int[][]{{0,1},{0,2},{0,3},{2,7},{1,4},{4,5},{4,6}}, 2), new int[]{9,7,9,8,8});
        assert Checker.check(new Solution().maxTargetNodes(new int[][]{{0,1},{0,2},{0,3},{0,4}}, new int[][]{{0,1},{1,2},{2,3}}, 1), new int[]{6,3,3,3,3});
    }

}
