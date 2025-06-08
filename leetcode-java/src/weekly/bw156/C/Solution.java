package weekly.bw156.C;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Q3. Maximum Weighted K-Edge Path
 *
 * https://leetcode.cn/contest/biweekly-contest-156/problems/maximum-weighted-k-edge-path/
 *
 * You are given an integer n and a Directed Acyclic Graph (DAG) with n nodes labeled
 * from 0 to n - 1. This is represented by a 2D array edges, where edges[i] = [ui, vi, wi]
 * indicates a directed edge from node ui to vi with weight wi.
 *
 * You are also given two integers, k and t.
 *
 * Your task is to determine the maximum possible sum of edge weights for any path in the graph such that:
 *
 * The path contains exactly k edges.
 * The total sum of edge weights in the path is strictly less than t.
 *
 * Return the maximum possible sum of weights for such a path. If no such path exists, return -1.
 */

/** @noinspection unchecked*/
public class Solution {

    private Map<Integer, Integer>[] g = null;

    // 在 DAG 中找到边数为 k 且边权之和 < t 的最大边权和
    public int maxWeight(int n, int[][] edges, int k, int t) {
        g = new Map[n]; Arrays.setAll(g, i -> new HashMap<>());
        for (var edge : edges) {
            g[edge[0]].put(edge[1], edge[2]);
        }

        memo = new boolean[n][k + 1][t + 1];
        for (int i = 0; i < n; i++) dfs(i, 0, 0, k, t);
        return ans;
    }

    private int ans = -1;
    private boolean[][][] memo = null;

    // 当前在节点 node 处, 且当前已经有 n 条边, 总和为 s,
    private void dfs(int node, int n, int s, int k, int t) {
        if (n == k) { if (s < t) ans = Math.max(ans, s); return; };
        if (memo[node][n][s]) return;

        for (var next : g[node].entrySet()) {
            int nextSum = s + next.getValue();
            if (nextSum < t) dfs(next.getKey(), n + 1, nextSum, k, t);
        }
        memo[node][n][s] = true;
    }

    public static void main(String[] args) {
        assert new Solution().maxWeight(4, new int[][]{{0,2,3},{1,3,10},{0,3,5},{2,3,10},{0,1,1}}, 2, 38) == 13;
        assert new Solution().maxWeight(4, new int[][]{{0,2,6},{2,3,2}, {0,3,7}}, 1, 588) == 7;

        assert new Solution().maxWeight(3, new int[][]{{0,1,1},{1,2,2}}, 2, 4) == 3;
        assert new Solution().maxWeight(3, new int[][]{{0,1,2},{0,2,3}}, 1, 3) == 2;
        assert new Solution().maxWeight(3, new int[][]{{0,1,6},{1,2,8}}, 1, 6) == -1;
    }

}
