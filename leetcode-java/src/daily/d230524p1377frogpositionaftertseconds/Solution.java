package daily.d230524p1377frogpositionaftertseconds;

import common.Checker;
import common.PrettyPrinter;

import java.util.HashSet;
import java.util.Set;

/**
 * 1377. Frog Position After T Seconds
 *
 * https://leetcode.cn/problems/frog-position-after-t-seconds/
 *
 * Given an undirected tree consisting of n vertices numbered from 1 to n.
 * A frog starts jumping from vertex 1. In one second, the frog jumps from
 * its current vertex to another unvisited vertex if they are directly connected.
 *
 * The frog can not jump back to a visited vertex. In case the frog can jump to several
 * vertices, it jumps randomly to one of them with the same probability. Otherwise, when
 * the frog can not jump to any unvisited vertex, it jumps forever on the same vertex.
 *
 * The edges of the undirected tree are given in the array edges, where
 * edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi.
 *
 * Return the probability that after t seconds the frog is on the vertex target.
 * Answers within 10-5 of the actual answer will be accepted.
 */

@SuppressWarnings({"DuplicatedCode", "unchecked"})
public class Solution {

    private final Set<Integer>[] paths = new Set[102];

    public double frogPosition(int n, int[][] edges, int t, int target) {
        for (int i = 0; i < paths.length; i++) paths[i] = new HashSet<>();
        for (var edge : edges) {
            paths[edge[0]].add(edge[1]);
            paths[edge[1]].add(edge[0]);
        }

        memo = new double[t + 1][101];
        boolean[] seen = new boolean[n + 1];
        memo[0][1] = 1; seen[1] = true;
        dfs(1, 0, 1, seen);
        return memo[t][target];
    }

    private double[][] memo;

    private void dfs(int curr, int seconds, double probability, boolean[] seen) {
        if (seconds == memo.length - 1) return;

        double count = 0;
        for (var next : paths[curr]) {
            if (!seen[next]) count++;
        }
        if (count == 0) {
            memo[seconds + 1][curr] = probability;
            dfs(curr, seconds + 1, probability, seen);
            return;
        }

        double currProbability = probability / count;
        for (var next : paths[curr]) {
            if (!seen[next]) {
                seen[next] = true;
                memo[seconds + 1][next] += currProbability;
                dfs(next, seconds + 1, currProbability, seen);
                seen[next] = false;
            }
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().frogPosition(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 20, 6), 0.1666666666666667);

        assert Checker.check(new Solution().frogPosition(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 2, 4), 0.16666666666666666);
        assert Checker.check(new Solution().frogPosition(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 1, 7), 0.3333333333333333);
    }

}
