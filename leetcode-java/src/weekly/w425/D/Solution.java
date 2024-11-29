package weekly.w425.D;

import java.util.*;

/**
 * 3367. Maximize Sum of Weights after Edge Removals
 *
 * https://leetcode.cn/contest/weekly-contest-425/problems/maximize-sum-of-weights-after-edge-removals/
 *
 * There exists an undirected tree with n nodes numbered 0 to n - 1. You are given a 2D integer array
 * edges of length n - 1, where edges[i] = [ui, vi, wi] indicates that there is an edge between nodes
 * ui and vi with weight wi in the tree.
 *
 * Your task is to remove zero or more edges such that:
 *
 * Each node has an edge with at most k other nodes, where k is given.
 * The sum of the weights of the remaining edges is maximized.
 *
 * Return the maximum possible sum of weights for the remaining edges after making the necessary removals.
 */

public class Solution {

    private final Map<Integer, Map<Integer, Integer>> g = new HashMap<>();

    public long maximizeSumOfWeights(int[][] edges, int k) {
        for (var edge : edges) {
            g.computeIfAbsent(edge[0], node -> new HashMap<>()).put(edge[1], edge[2]);
            g.computeIfAbsent(edge[1], node -> new HashMap<>()).put(edge[0], edge[2]);
        }

        return dfs(0, -1, k, false);
    }

    private final Map<Integer, Long> memo = new HashMap<>();

    private long dfs(int curr, int parent, int k, boolean noParent) {
        int key = ((noParent ? 1 : 0) << 30) | curr;
        if (memo.containsKey(key)) return memo.get(key);

        //  - 记录删除或不删除与子节点相连的边之后的结果值
        Map<Integer, Long> keep = new HashMap<>(); // 保持这条边的值
        Map<Integer, Long> delete = new HashMap<>(); // 删除边之后的值
        for (var next : g.get(curr).entrySet()) {
            if (next.getKey() == parent) continue;

            keep.put(next.getKey(), next.getValue() + dfs(next.getKey(), curr, k, false));
            delete.put(next.getKey(), dfs(next.getKey(), curr, k, true));
        }

        // 如果父节点保持与当前节点的边, 则需要全部统计, 否则可以减 1
        int needDelete = (g.get(curr).size() - (noParent ? 1 : 0)) - k;
        if (needDelete <= 0) { // 不需要在进行删除了
            long ans = 0;
            for (var v : keep.values()) ans += v;
            memo.put(key, ans);
            return ans;
        }

        // 否则需要删除多余的边
        long ans = deleteEdge(keep, delete, needDelete);
        memo.put(key, ans);
        return ans;
    }

    // 一个数要么从 keep 中出, 要么从 delete 中出, 同时 delete 中最多选 n 个, 求最大值
    private long deleteEdge(Map<Integer, Long> keep, Map<Integer, Long> delete, int n) {
        List<Integer> keys = new ArrayList<>(keep.keySet());
        // dp[i][j] 表示从在 keys 的前 i 个中选择 j 个 delete 的方案和
        long[][] dp = new long[keys.size() + 1][n + 1];
        for (var row : dp) Arrays.fill(row, Integer.MIN_VALUE);
        dp[0][0] = 0;

        for (int i = 1; i <= keys.size(); i++) {
            int key = keys.get(i - 1);

            for (int j = 0; j <= n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j] + keep.get(key), j - 1 < 0 ? 0 : (dp[i - 1][j - 1] + delete.get(key)));
            }
        }

        return dp[keys.size()][n];
    }

    public static void main(String[] args) {
        assert new Solution().maximizeSumOfWeights(new int[][]{{0,1,34},{0,2,17}}, 1) == 34;
        assert new Solution().maximizeSumOfWeights(new int[][]{{0,1,25},{0,2,10},{1,3,29}}, 1) == 39;

        assert new Solution().maximizeSumOfWeights(new int[][]{{0,1,4},{0,2,2},{2,3,12},{2,4,6}}, 2) == 22;
        assert new Solution().maximizeSumOfWeights(new int[][]{{0,1,5},{1,2,10},{0,3,15},{3,4,20},{3,5,5},{0,6,10}}, 3) == 65;
    }

}
