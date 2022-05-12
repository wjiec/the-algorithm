package problem.p785isgraphbipartite;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 785. Is Graph Bipartite?
 *
 * https://leetcode.cn/problems/is-graph-bipartite/
 *
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
 * You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
 * More formally, for each v in graph[u], there is an undirected edge between node u and node v.
 * The graph has the following properties:
 *
 * There are no self-edges (graph[u] does not contain u).
 * There are no parallel edges (graph[u] does not contain duplicate values).
 * If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge
 * in the graph connects a node in set A and a node in set B.
 *
 * Return true if and only if it is bipartite.
 */

public class Solution {

    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == 0) {
                if (!bfs(graph, i, 1, colors)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean bfs(int[][] graph, int node, int color, int[] colors) {
        Queue<int[]> queue = new ArrayDeque<>();
        for (queue.add(new int[]{node, color}); !queue.isEmpty(); ) {
            int[] curr = queue.remove();
            colors[curr[0]] = curr[1];
            for (var next : graph[curr[0]]) {
                if (colors[next] == -curr[1]) continue;
                if (colors[next] != 0) return false;
                queue.add(new int[]{next, -curr[1]});
            }
        }

        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().isBipartite(new int[][]{
            {1,2,3},{0,2},{0,1,3},{0,2}
        });

        assert new Solution().isBipartite(new int[][]{
            {1,3},{0,2},{1,3},{0,2}
        });
    }

}
