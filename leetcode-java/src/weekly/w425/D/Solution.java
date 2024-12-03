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

        return dfs(0, -1, k)[1];
    }

    // 返回值 [x, y] 表示当 curr 与 parent 连接时的答案为 x, 不连接时的答案为 y
    private long[] dfs(int curr, int parent, int k) {
        // 对于当前节点的所有子节点来说, 我们分别计算连接和不连接的值
        //  - 由于我们最多只能连接 k 个节点, 所以我们需要从连接的取 k 个, 不连接的取 n - k 个
        //  - 同时在连接父节点的情况下, 我们最多只能连接 k - 1 个, 计算方式同理
        //  - 与 https://leetcode.cn/problems/mice-and-cheese/ 相同
        long totalNotConnected = 0;
        List<Long> increment = new ArrayList<>();
        for (var next : g.get(curr).entrySet()) {
            if (next.getKey() == parent) continue;

            long[] pair = dfs(next.getKey(), curr, k);
            totalNotConnected += pair[1]; // 默认先都不连接

            long currInc = pair[0] + next.getValue() - pair[1];
            if (currInc > 0) increment.add(currInc); // 从不连接的变成连接的增量
        }

        increment.sort(Collections.reverseOrder());
        long[] sum = new long[increment.size() + 1];
        for (int i = 1; i <= increment.size(); i++) sum[i] = sum[i - 1] + increment.get(i - 1);

        // 连接时, 最多只能取 k - 1 个, 不连接时最多只能取 k 个
        return new long[]{
            totalNotConnected + sum[Math.min(sum.length - 1, k - 1)],
            totalNotConnected + sum[Math.min(sum.length - 1, k)]
        };
    }

    public static void main(String[] args) {
        assert new Solution().maximizeSumOfWeights(new int[][]{{0,1,34},{0,2,17}}, 1) == 34;
        assert new Solution().maximizeSumOfWeights(new int[][]{{0,1,25},{0,2,10},{1,3,29}}, 1) == 39;

        assert new Solution().maximizeSumOfWeights(new int[][]{{0,1,4},{0,2,2},{2,3,12},{2,4,6}}, 2) == 22;
        assert new Solution().maximizeSumOfWeights(new int[][]{{0,1,5},{1,2,10},{0,3,15},{3,4,20},{3,5,5},{0,6,10}}, 3) == 65;
    }

}
