package problem.p1129shortestpathwithalternatingcolors;

import common.Checker;

import java.util.*;

/**
 * 1129. Shortest Path with Alternating Colors
 *
 * https://leetcode.cn/problems/shortest-path-with-alternating-colors/
 *
 * You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1.
 * Each edge is red or blue in this graph, and there could be self-edges and parallel edges.
 *
 * You are given two arrays redEdges and blueEdges where:
 *
 * redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
 * blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
 * Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to
 * node x such that the edge colors alternate along the path, or -1 if such a path does not exist.
 */

public class Solution {

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        Map<Integer, Set<Integer>> red = new HashMap<>();
        for (var edge : redEdges) red.computeIfAbsent(edge[0], v -> new HashSet<>()).add(edge[1]);

        Map<Integer, Set<Integer>> blue = new HashMap<>();
        for (var edge : blueEdges) blue.computeIfAbsent(edge[0], v -> new HashSet<>()).add(edge[1]);

        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        boolean[] redVisited = new boolean[n];
        boolean[] blueVisited = new boolean[n];
        Queue<int[]> queue = new ArrayDeque<>(); // [node, steps, color]

        redVisited[0] = true; queue.add(new int[]{0, 0, 0});
        blueVisited[0] = true; queue.add(new int[]{0, 0, 1});

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int node = curr[0], steps = curr[1], color = curr[2];
            if (ans[node] == -1) ans[node] = steps;

            if (color == 0 && blue.containsKey(node)) {
                for (var next : blue.get(node)) {
                    if (!blueVisited[next]) {
                        blueVisited[next] = true;
                        queue.add(new int[]{next, steps + 1, 1});
                    }
                }
            } else if (color == 1 && red.containsKey(node)) {
                for (var next : red.get(node)) {
                    if (!redVisited[next]) {
                        redVisited[next] = true;
                        queue.add(new int[]{next, steps + 1, 0});
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().shortestAlternatingPaths(3, new int[][]{{0,1},{1,2}}, new int[][]{}), new int[]{0,1,-1});
        assert Checker.check(new Solution().shortestAlternatingPaths(3, new int[][]{{0,1}}, new int[][]{{2,1}}), new int[]{0,1,-1});
    }

}
