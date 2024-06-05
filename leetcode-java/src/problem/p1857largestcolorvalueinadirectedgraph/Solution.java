package problem.p1857largestcolorvalueinadirectedgraph;

import ability.Benchmark;

import java.util.*;

/**
 * 1857. Largest Color Value in a Directed Graph
 *
 * https://leetcode.cn/problems/largest-color-value-in-a-directed-graph
 *
 * There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
 *
 * You are given a string colors where colors[i] is a lowercase English letter representing
 * the color of the ith node in this graph (0-indexed). You are also given a 2D array edges
 * where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
 *
 * A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that
 * there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the
 * path is the number of nodes that are colored the most frequently occurring color along
 * that path.
 *
 * Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
 */

public class Solution {

    private char[] colors = null;
    private final Map<Integer, Set<Integer>> g = new HashMap<>();

    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        int[] indegree = new int[n];
        for (var edge : edges) {
            indegree[edge[0]]++;
            g.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]); // 反过来存图
        }

        // 拓扑排序检查环
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        int seen = 0;
        int[] copyDegree = new int[n];
        System.arraycopy(indegree, 0, copyDegree, 0, n);
        while (!queue.isEmpty()) {
            int curr = queue.remove(); seen++;
            for (var next : g.getOrDefault(curr, Collections.emptySet())) {
                if (--copyDegree[next] == 0) queue.add(next);
            }
        }
        if (seen != n) return -1;

        this.colors = colors.toCharArray();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                // 如果一个节点的入度为0, 则从这个点开始反向遍历
                var curr = dfs(i);
                for (var v : curr) ans = Math.max(ans, v);
            }
        }

        return ans;
    }

    private int ans = 0;
    private final Map<Integer, int[]> memo = new HashMap<>();

    private int[] dfs(int node) {
        if (!memo.containsKey(node)) {
            int[] curr = new int[26];
            int color = colors[node] - 'a'; curr[color] = 1;
            for (var next : g.getOrDefault(node, Collections.emptySet())) {
                int[] add = dfs(next);
                for (int i = 0; i < curr.length; i++) {
                    curr[i] = Math.max(curr[i], add[i] + (color == i ? 1 : 0));
                    ans = Math.max(ans, curr[i]);
                }
            }
            memo.put(node, curr);
        }

        return memo.get(node);
    }

    private static class TopologicalSort {
        public int largestPathValue(String colors, int[][] edges) {
            int n = colors.length();
            int[] indegree = new int[n];
            Map<Integer, List<Integer>> g = new HashMap<>();
            for (var edge : edges) {
                indegree[edge[1]]++;
                g.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            }

            int[][] dp = new int[n][26];
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                if (indegree[i] == 0) queue.add(i);
            }

            int ans = 0, seen = 0;
            while (!queue.isEmpty()) {
                var curr = queue.remove(); seen++;
                ans = Math.max(ans, ++dp[curr][colors.charAt(curr) - 'a']);
                if (g.containsKey(curr)) {
                    for (var next : g.get(curr)) {
                        for (int i = 0; i < 26; i++) {
                            dp[next][i] = Math.max(dp[next][i], dp[curr][i]);
                        }

                        if (--indegree[next] == 0) {
                            queue.add(next);
                        }
                    }
                }
            }
            return seen != n ? -1 : ans;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("origin", () -> {
            assert new Solution().largestPathValue("hhqhuqhqff", new int[][]{{0,1},{0,2},{2,3},{3,4},{3,5},{5,6},{2,7},{6,7},{7,8},{3,8},{5,8},{8,9},{3,9},{6,9}}) == 3;

            assert new Solution().largestPathValue("abaca", new int[][]{{0,1},{0,2},{2,3},{3,4}}) == 3;
            assert new Solution().largestPathValue("a", new int[][]{{0,0}}) == -1;
        });

        Benchmark.benchmark("TopologicalSort", () -> {
            assert new TopologicalSort().largestPathValue("hhqhuqhqff", new int[][]{{0,1},{0,2},{2,3},{3,4},{3,5},{5,6},{2,7},{6,7},{7,8},{3,8},{5,8},{8,9},{3,9},{6,9}}) == 3;

            assert new TopologicalSort().largestPathValue("abaca", new int[][]{{0,1},{0,2},{2,3},{3,4}}) == 3;
            assert new TopologicalSort().largestPathValue("a", new int[][]{{0,0}}) == -1;
        });
    }

}
