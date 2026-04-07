package weekly.w479.D;

import common.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q4. Maximum Subgraph Score in a Tree
 *
 * https://leetcode.cn/contest/weekly-contest-479/problems/maximum-subgraph-score-in-a-tree/
 *
 * You are given an undirected tree with n nodes, numbered from 0 to n - 1. It is represented
 * by a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that
 * there is an edge between nodes ai and bi in the tree.
 *
 * You are also given an integer array good of length n, where good[i] is 1 if
 * the ith node is good, and 0 if it is bad.
 *
 * Define the score of a subgraph as the number of good nodes minus the number of bad nodes in that subgraph.
 *
 * For each node i, find the maximum possible score among all connected subgraphs that contain node i.
 *
 * Return an array of n integers where the ith element is the maximum score for node i.
 *
 * A subgraph is a graph whose vertices and edges are subsets of the original graph.
 *
 * A connected subgraph is a subgraph in which every pair of its vertices is reachable
 * from one another using only its edges.
 */

public class Solution {

    @Tag({"换根DP"})
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
        List<Integer>[] g = new List[n]; Arrays.setAll(g, i -> new ArrayList<>());
        for (var edge : edges) { g[edge[0]].add(edge[1]); g[edge[1]].add(edge[0]); }

        // scores[i] 表示以 0 为根时, 包含 i 的最大得分是多少
        int[] scores = new int[n];
        dfs(g, 0, -1, scores, good);

        int[] ans = new int[n];
        chroot(g, 0, -1, 0, scores, ans);
        return ans;
    }

    private int dfs(List<Integer>[] g, int curr, int parent, int[] scores, int[] good) {
        for (var next : g[curr]) {
            if (next == parent) continue;
            // 当前可以排除负得分的子树
            scores[curr] += Math.max(dfs(g, next, curr, scores, good), 0);
        }
        // 当前节点一定要包含当前得分
        return scores[curr] += (good[curr] << 1) - 1;
    }

    private void chroot(List<Integer>[] g, int curr, int parent, int parentScore, int[] scores, int[] ans) {
        // 计算当前节点的最大得分, 来自于自己子树的得分, 加上父子树的得分
        int currScore = (ans[curr] = scores[curr] + Math.max(parentScore, 0));
        for (var next : g[curr]) {
            if (next == parent) continue;
            // 现在递归 next 的时候, 以 curr 为根的得分是 currScore, 我们需要计算父节点的得分
            chroot(g, next, curr, currScore - Math.max(scores[next], 0), scores, ans);
        }
    }

    public static void main(String[] args) {
    }

}
