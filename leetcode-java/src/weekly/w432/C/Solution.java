package weekly.w432.C;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 3419. Minimize the Maximum Edge Weight of Graph
 *
 * https://leetcode.cn/contest/weekly-contest-432/problems/minimize-the-maximum-edge-weight-of-graph/
 *
 * You are given two integers, n and threshold, as well as a directed weighted graph of n nodes numbered
 * from 0 to n - 1. The graph is represented by a 2D integer array edges,
 * where edges[i] = [Ai, Bi, Wi] indicates that there is an edge going from node Ai to node Bi with weight Wi.
 *
 * You have to remove some edges from this graph (possibly none), so that it satisfies the following conditions:
 *
 * Node 0 must be reachable from all other nodes.
 * The maximum edge weight in the resulting graph is minimized.
 * Each node has at most threshold outgoing edges.
 * Return the minimum possible value of the maximum edge weight after removing the necessary edges.
 *
 * If it is impossible for all conditions to be satisfied, return -1.
 */

public class Solution {

    private final Map<Integer, Map<Integer, Integer>> g = new HashMap<>();

    /** @noinspection DuplicatedCode*/
    public int minMaxWeight(int n, int[][] edges, int threshold) {
        // 反向从 0 到所有的其他节点
        int maxWeight = 0;
        for (var edge : edges) {
            maxWeight = Math.max(maxWeight, edge[2]);
            g.computeIfAbsent(edge[1], k -> new HashMap<>())
                .merge(edge[0], edge[2], Integer::min);
        }

        // 二分枚举最大边权并进行检查是否能满足要求
        int l = 0, r = maxWeight + 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(n, threshold, mid)) r = mid;
            else l = mid + 1;
        }

        return r > maxWeight ? -1 : r;
    }

    // 检查删除所有大于 maxWeight 的边之后是否能满足条件
    private boolean check(int n, int threshold, int maxWeight) {
        boolean[] seen = new boolean[n];
        // 每个节点的入度最多为 threshold
        int[] indegree = new int[n];

        // 从 0 出发开始遍历所有边
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0); seen[0] = true;

        while (!queue.isEmpty()) {
            var curr = queue.remove(); n--;
            if (g.containsKey(curr)) {
                for (var next : g.get(curr).entrySet()) {
                    if (seen[next.getKey()]) continue;
                    if (next.getValue() > maxWeight) continue;

                    if (++indegree[next.getKey()] > threshold) return false;
                    if (!seen[next.getKey()]) {
                        seen[next.getKey()] = true;
                        queue.add(next.getKey());
                    }
                }
            }
        }

        return n == 0;
    }

    public static void main(String[] args) {
        assert new Solution().minMaxWeight(3, new int[][]{{2,0,75},{1,0,69},{1,0,97}}, 1) == 75;
        assert new Solution().minMaxWeight(3, new int[][]{{0,1,26},{1,0,35},{1,2,94}}, 2) == -1;
        assert new Solution().minMaxWeight(2, new int[][]{{1,0,4}}, 1) == 4;
        assert new Solution().minMaxWeight(5, new int[][]{{1,0,1},{2,0,2},{3,0,1},{4,3,1},{2,1,1}}, 2) == 1;
        assert new Solution().minMaxWeight(5, new int[][]{{0,1,1},{0,2,2},{0,3,1},{0,4,1},{1,2,1},{1,4,1}}, 1) == -1;
        assert new Solution().minMaxWeight(5, new int[][]{{1,2,1},{1,3,3},{1,4,5},{2,3,2},{3,4,2},{4,0,1}}, 1) == 2;
        assert new Solution().minMaxWeight(5, new int[][]{{1,2,1},{1,3,3},{1,4,5},{2,3,2},{4,0,1}}, 1) == -1;
    }

}
