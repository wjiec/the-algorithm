package weekly.bw155.D;

import java.util.Arrays;

/**
 * 3530. Maximum Profit from Valid Topological Order in DAG
 *
 * https://leetcode.cn/contest/biweekly-contest-155/problems/maximum-profit-from-valid-topological-order-in-dag/
 *
 * You are given a Directed Acyclic Graph (DAG) with n nodes labeled from 0 to n - 1, represented
 * by a 2D array edges, where edges[i] = [ui, vi] indicates a directed edge from node ui to vi.
 *
 * Each node has an associated score given in an array score, where score[i] represents the score of node i.
 *
 * You must process the nodes in a valid topological order.
 * Each node is assigned a 1-based position in the processing order.
 *
 * The profit is calculated by summing up the product of each node's score and its position in the ordering.
 *
 * Return the maximum possible profit achievable with an optimal topological order.
 *
 * A topological order of a DAG is a linear ordering of its nodes
 * such that for every directed edge u → v, node u comes before v in the ordering.
 */

public class Solution {

    // 拓扑排序之后的节点分数乘以索引的和的最大值
    public int maxProfit(int n, int[][] edges, int[] score) {
        if (edges.length == 0) {
            Arrays.sort(score);
            int ans = 0;
            for (int i = 0; i < n; i++) ans += score[i] * (i + 1);
            return ans;
        }

        // 由于元素数量较少, 直接枚举所有排列配合记忆化枚举
        //  - 对于第 i 个位置所选择的节点, 需要满足拓扑排序的要求

        // 记录每个节点的直接前驱节点
        int[] pre = new int[n];
        for (var edge : edges) {
            pre[edge[1]] |= 1 << edge[0];
        }

        return dfs(0, pre, score, new int[1 << n]);
    }

    private int dfs(int mask, int[] pre, int[] score, int[] memo) {
        if (memo[mask] > 0) return memo[mask];

        int ans = 0, i = Integer.bitCount(mask);
        for (int j = 0; j < score.length; j++) {
            // 检查当前是否能用 j 这个节点, 也就是 j 的直接前驱节点都是完成状态
            if ((mask & (1 << j)) == 0 && (pre[j] & mask) == pre[j]) {
                ans = Math.max(ans, dfs(mask | 1 << j, pre, score, memo) + score[j] * (i + 1));
            }
        }
        return memo[mask] = ans;
    }

    public static void main(String[] args) {
        // 53407 -> 92726
        // 89087
        // 53407 * 1 + 89087 * 2 + 92726 * 3
        assert new Solution().maxProfit(3, new int[][]{{0,2}}, new int[]{53407,89087,92726}) == 509759;
        assert new Solution().maxProfit(3, new int[][]{{0,1}}, new int[]{21915,50942,52739}) == 282016;
    }

}
