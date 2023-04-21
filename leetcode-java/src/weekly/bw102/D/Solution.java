package weekly.bw102.D;

import java.util.*;

/**
 * 2642. Design Graph With Shortest Path Calculator
 *
 * https://leetcode.cn/contest/biweekly-contest-102/problems/design-graph-with-shortest-path-calculator/
 *
 * There is a directed weighted graph that consists of n nodes numbered from 0 to n - 1.
 * The edges of the graph are initially represented by the given array edges
 * where edges[i] = [fromi, toi, edgeCosti] meaning that there is an edge
 * from fromi to toi with the cost edgeCosti.
 *
 * Implement the Graph class:
 *
 * Graph(int n, int[][] edges) initializes the object with n nodes and the given edges.
 *
 * addEdge(int[] edge) adds an edge to the list of edges where edge = [from, to, edgeCost].
 * It is guaranteed that there is no edge between the two nodes before adding this one.
 *
 * int shortestPath(int node1, int node2) returns the minimum cost of a path from node1 to node2.
 * If no path exists, return -1. The cost of a path is the sum of the costs of the edges in the path.
 */

public class Solution {

    private static class Graph {

        private final int vertices;
        // 图中顶点之间的权重
        private final Map<Integer, Map<Integer, Integer>> weight;

        public Graph(int n, int[][] edges) {
            vertices = n; weight = new HashMap<>();
            for (var edge : edges) addEdge(edge);
        }

        public void addEdge(int[] edge) {
            weight.computeIfAbsent(edge[0], v -> new HashMap<>())
                .put(edge[1], edge[2]);
        }

        private static final int INF = Integer.MAX_VALUE / 2;

        public int shortestPath(int node1, int node2) {
            int[] distance = new int[vertices];
            Arrays.fill(distance, INF);

            // [node, distance] 距离
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingLong(v -> v[1]));
            pq.add(new int[]{node1, 0});

            // 对那些刚刚被加入集合的结点的所有出边执行松弛操作
            while (!pq.isEmpty()) {
                int[] curr = pq.remove();
                int idx = curr[0];

                if (distance[idx] <= curr[1]) continue;
                distance[idx] = curr[1];

                if (weight.containsKey(idx)) {
                    for (var next : weight.get(idx).keySet()) {
                        if (distance[next] >= INF) {
                            pq.add(new int[]{next, curr[1] + weight.get(idx).get(next)});
                        }
                    }
                }
            }

            return distance[node2] == INF ? -1 : distance[node2];
        }
    }


    public static void main(String[] args) {
    }

}
