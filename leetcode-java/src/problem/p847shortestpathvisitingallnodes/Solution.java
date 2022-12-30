package problem.p847shortestpathvisitingallnodes;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 847. Shortest Path Visiting All Nodes
 *
 * https://leetcode.cn/problems/shortest-path-visiting-all-nodes
 *
 * You have an undirected, connected graph of n nodes labeled from 0 to n - 1.
 *
 * You are given an array graph where graph[i] is a list of all the nodes
 * connected with node i by an edge.
 *
 * Return the length of the shortest path that visits every node.
 *
 * You may start and stop at any node, you may revisit nodes multiple times, and
 * you may reuse edges.
 */

public class Solution {

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        // [node, state, step]
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][1 << n];
        for (int i = 0; i < n; i++) {
            visited[i][1 << i] = true;
            queue.add(new int[]{i, 1 << i, 0});
        }

        int guard = (1 << n) - 1;
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int node = curr[0], state = curr[1], step = curr[2];
            if (state == guard) return step;

            for (var next : graph[node]) {
                int nextState = state | 1 << next;
                if (!visited[next][nextState]) {
                    visited[next][nextState] = true;
                    queue.add(new int[]{next, nextState, step + 1});
                }
            }
        }
        return -1; // unreached
    }

    public static void main(String[] args) {
        assert new Solution().shortestPathLength(new int[][]{{1,2,3},{0},{0},{0}}) == 4;
        assert new Solution().shortestPathLength(new int[][]{{1},{0,2,4},{1,3,4},{2},{1,2}}) == 4;
    }

}
