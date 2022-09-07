package problem.p1786numberofrestrictedpathsfromfirsttolastnode;

import common.TODO;
import common.Tag;

import java.util.*;

/**
 * 1786. Number of Restricted Paths From First to Last Node
 *
 * https://leetcode.cn/problems/number-of-restricted-paths-from-first-to-last-node/
 *
 * There is an undirected weighted connected graph. You are given a positive integer n which
 * denotes that the graph has n nodes labeled from 1 to n, and an array edges
 * where each edges[i] = [ui, vi, weighti] denotes that there is an edge
 * between nodes ui and vi with weight equal to weighti.
 *
 * A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such
 * that z0 = start and zk = end and there is an edge between zi and zi+1 where 0 <= i <= k-1.
 *
 * The distance of a path is the sum of the weights on the edges of the path. Let distanceToLastNode(x)
 * denote the shortest distance of a path between node n and node x. A restricted path is a path that
 * also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.
 *
 * Return the number of restricted paths from node 1 to node n. Since that number may be too
 * large, return it modulo 109 + 7.
 */

public class Solution {

    @TODO
    @Tag({"加权无向图", "受限路径数", "堆优化Dijkstra"})
    public int countRestrictedPaths(int n, int[][] edges) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], v -> new HashMap<>()).put(edge[1], edge[2]);
            map.computeIfAbsent(edge[1], v -> new HashMap<>()).put(edge[0], edge[2]);
        }

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n + 1]; distance[n] = 0;

        // 点编号，点距离。根据点距离从小到大
        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));
        for (q.add(new int[]{n, 0}); !q.isEmpty(); ) {
            int[] curr = q.poll(); int idx = curr[0];
            if (visited[curr[0]]) continue;

            visited[curr[0]] = true;
            Map<Integer, Integer> neighbors = map.get(idx);
            if (neighbors == null) continue;
            for (int next : neighbors.keySet()) {
                distance[next] = Math.min(distance[next], distance[idx] + neighbors.get(next));
                q.add(new int[]{next, distance[next]});
            }
        }

        // dp 过程
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) arr[i] = new int[]{i + 1, distance[i + 1]};
        Arrays.sort(arr, Comparator.comparingInt(v -> v[1]));

        // 定义 f(i) 为从第 i 个点到结尾的受限路径数量
        // 从 f[n] 递推到 f[1]
        int[] dp = new int[n + 1]; dp[n] = 1;
        for (int i = 0; i < n; i++) {
            int idx = arr[i][0], cur = arr[i][1];
            Map<Integer, Integer> mm = map.get(idx);
            if (mm == null) continue;
            for (int next : mm.keySet()) {
                if (cur > distance[next]) {
                    dp[idx] += dp[next];
                    dp[idx] %= 1_000_000_007;
                }
            }
            // 第 1 个节点不一定是距离第 n 个节点最远的点，但我们只需要 f[1]，可以直接跳出循环
            if (idx == 1) break;
        }
        return dp[1];
    }

    public static void main(String[] args) {
        assert new Solution().countRestrictedPaths(5, new int[][]{
            {1,2,3},{1,3,3},{2,3,1},{1,4,2},{5,2,2},{3,5,1},{5,4,10}
        }) == 3;
        assert new Solution().countRestrictedPaths(7, new int[][]{
            {1,3,1},{4,1,2},{7,3,4},{2,5,3},{5,6,1},{6,7,2},{7,5,3},{2,6,4}
        }) == 1;
    }

}
