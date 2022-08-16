package problem.p1514pathwithmaximumprobability;

import common.Checker;
import common.TODO;

import java.util.*;

/**
 * 1514. Path with Maximum Probability
 *
 * https://leetcode.cn/problems/path-with-maximum-probability/
 *
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list
 * where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of
 * success of traversing that edge succProb[i].
 *
 * Given two nodes start and end, find the path with the maximum probability of success to go
 * from start to end and return its success probability.
 *
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs
 * from the correct answer by at most 1e-5.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private record Path(int node, double prob) {}

    @TODO(tips = "Dijkstra")
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, Set<Path>> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            map.computeIfAbsent(edges[i][0], v -> new HashSet<>())
                .add(new Path(edges[i][1], succProb[i]));
            map.computeIfAbsent(edges[i][1], v -> new HashSet<>())
                .add(new Path(edges[i][0], succProb[i]));
        }

        double[] visited = new double[n];
        Arrays.fill(visited, -1);
        visited[start] = 1.0;

        Queue<Integer> queue = new ArrayDeque<>();
        for (queue.add(start); !queue.isEmpty(); ) {
            int curr = queue.remove();
            if (map.containsKey(curr)) {
                for (var path : map.get(curr)) {
                    double nodeProb = visited[curr] * path.prob;
                    if (nodeProb > visited[path.node]) {
                        queue.add(path.node);
                        visited[path.node] = nodeProb;
                    }
                }
            }
        }

        return Math.max(visited[end], 0.);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().maxProbability(3, new int[][]{
            {0,1},{1,2},{0,2}
        }, new double[]{0.5,0.5,0.2}, 0, 2), 0.25);

        assert Checker.check(new Solution().maxProbability(3, new int[][]{
            {0,1},{1,2},{0,2}
        }, new double[]{0.5,0.5,0.3}, 0, 2), 0.3);

        assert Checker.check(new Solution().maxProbability(3, new int[][]{
            {0,1}
        }, new double[]{0.5}, 0, 2), 0.);
    }

}
