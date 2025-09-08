package weekly.bw163.C;

import java.util.*;

/**
 * Q3. Minimum Cost Path with Edge Reversals
 *
 * https://leetcode.cn/contest/biweekly-contest-163/problems/minimum-cost-path-with-edge-reversals/
 *
 * You are given a directed, weighted graph with n nodes labeled from 0 to n - 1, and an array edges
 * where edges[i] = [ui, vi, wi] represents a directed edge from node ui to node vi with cost wi.
 *
 * Each node ui has a switch that can be used at most once: when you arrive at ui and have not yet
 * used its switch, you may activate it on one of its incoming edges vi → ui reverse that edge
 * to ui → vi and immediately traverse it.
 *
 * The reversal is only valid for that single move, and using a reversed edge costs 2 * wi.
 *
 * Return the minimum total cost to travel from node 0 to node n - 1. If it is not possible, return -1.
 */

public class Solution {

    /** @noinspection unchecked, DuplicatedCode */
    public int minCost(int n, int[][] edges) {
        // 可以增加一条从 v -> u 的路径, 权重为 2 * w
        Map<Integer, Integer>[] g = new Map[n];
        Arrays.setAll(g, i -> new HashMap<>());
        for (var edge : edges) {
            g[edge[0]].merge(edge[1], edge[2], Integer::min);
            g[edge[1]].merge(edge[0], 2 * edge[2], Integer::min);
        }

        int[] seen = new int[n]; Arrays.fill(seen, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));
        pq.add(new int[]{0, 0}); seen[0] = 0;
        while (!pq.isEmpty()) {
            var curr = pq.remove();
            if (curr[0] == n - 1) return curr[1];
            for (var next : g[curr[0]].entrySet()) {
                if (curr[1] + next.getValue() < seen[next.getKey()]) {
                    pq.add(new int[]{next.getKey(), seen[next.getKey()] = curr[1] + next.getValue()});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
    }

}
