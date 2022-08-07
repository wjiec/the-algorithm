package weekly.w305.B;

import java.util.*;

/**
 * 6139. Reachable Nodes With Restrictions
 *
 * https://leetcode.cn/contest/weekly-contest-305/problems/reachable-nodes-with-restrictions/
 *
 * There is an undirected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
 *
 * You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that
 * there is an edge between nodes ai and bi in the tree. You are also given an integer array restricted
 * which represents restricted nodes.
 *
 * Return the maximum number of nodes you can reach from node 0 without visiting a restricted node.
 *
 * Note that node 0 will not be a restricted node.
 */

public class Solution {

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (var edge : edges) {
            map.computeIfAbsent(edge[0], v -> new HashSet<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], v -> new HashSet<>()).add(edge[0]);
        }
        Set<Integer> disabled = new HashSet<>();
        for (var v : restricted) disabled.add(v);

        int ans = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int curr = queue.remove();
            ans++;
            if (map.containsKey(curr)) {
                for (var next : map.get(curr)) {
                    if (!disabled.contains(next) && !visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
