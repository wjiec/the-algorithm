package lcci.s4.p1routebetweennodeslcci;

import java.util.*;

/**
 * 面试题 04.01. 节点间通路
 *
 * https://leetcode.cn/problems/route-between-nodes-lcci/
 *
 * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, Set<Integer>> edges = new HashMap<>();
        for (var g : graph) {
            edges.computeIfAbsent(g[0], v -> new HashSet<>()).add(g[1]);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int curr = queue.remove();
            if (curr == target) return true;

            if (edges.containsKey(curr)) {
                for (var next : edges.get(curr)) {
                    if (!visited[next]) {
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().findWhetherExistsPath(3, new int[][]{{0, 1}, {0, 2}, {1, 2}, {1, 2}}, 0, 2);
        assert new Solution().findWhetherExistsPath(5, new int[][]{{0, 1}, {0, 2}, {0, 4}, {0, 4}, {0, 1}, {1, 3}, {1, 4}, {1, 3}, {2, 3}, {3, 4}}, 0, 4);
    }

}
