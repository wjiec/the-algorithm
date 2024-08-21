package problem.p2858minimumedgereversalssoeverynodeisreachable;

import java.util.*;

/**
 * 2858. Minimum Edge Reversals So Every Node Is Reachable
 *
 * https://leetcode.cn/problems/minimum-edge-reversals-so-every-node-is-reachable/description/
 *
 * There is a simple directed graph with n nodes labeled from 0 to n - 1.
 * The graph would form a tree if its edges were bi-directional.
 *
 * You are given an integer n and a 2D integer array edges, where edges[i] = [ui, vi]
 * represents a directed edge going from node ui to node vi.
 *
 * An edge reversal changes the direction of an edge, i.e., a directed edge going from
 * node ui to node vi becomes a directed edge going from node vi to node ui.
 *
 * For every node i in the range [0, n - 1], your task is to independently calculate
 * the minimum number of edge reversals required so it is possible to reach any other
 * node starting from node i through a sequence of directed edges.
 *
 * Return an integer array answer, where answer[i] is the minimum number of edge
 * reversals required so it is possible to reach any other node starting from
 * node i through a sequence of directed edges.
 * @noinspection DuplicatedCode
 */

public class Solution {

    private final Map<Integer, Set<Integer>> g = new HashMap<>();
    private final Map<Integer, Set<Integer>> rg = new HashMap<>();

    public int[] minEdgeReversals(int n, int[][] edges) {
        for (var edge : edges) {
            g.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            rg.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            rg.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }

        int[] ans = new int[n];
        ans[0] = reverse(0, -1);
        for (var next : rg.get(0)) {
            dfs(next, 0, ans);
        }

        return ans;
    }

    // 遍历每一个子节点, 检查在反转子节点和父节点的边之后还剩下多少个
    private void dfs(int curr, int parent, int[] ans) {
        // 反转 parent -> curr 之间的边
        ans[curr] = ans[parent] + (g.containsKey(curr) && g.get(curr).contains(parent) ? -1 : 1);
        for (var next : rg.get(curr)) {
            if (next == parent) continue;

            dfs(next, curr, ans);
        }
    }

    private int reverse(int curr, int parent) {
        int ans = 0;
        for (var next : rg.get(curr)) {
            if (next == parent) continue;

            // 检查当前方向是否反了
            ans += !g.containsKey(curr) || !g.get(curr).contains(next) ? 1 : 0;
            ans += reverse(next, curr);
        }

        return ans;
    }

    /** @noinspection unchecked*/
    private static class Optimization {
        private Set<Integer>[] g = null, rg = null;

        public int[] minEdgeReversals(int n, int[][] edges) {
            g = new Set[n]; rg = new Set[n];
            Arrays.setAll(g, i -> new HashSet<>());
            Arrays.setAll(rg, i -> new HashSet<>());

            for (var edge : edges) {
                g[edge[0]].add(edge[1]);

                rg[edge[0]].add(edge[1]);
                rg[edge[1]].add(edge[0]);
            }

            int[] ans = new int[n];
            ans[0] = reverse(0, -1);
            for (var next : rg[0]) {
                dfs(next, 0, ans);
            }

            return ans;
        }

        // 遍历每一个子节点, 检查在反转子节点和父节点的边之后还剩下多少个
        private void dfs(int curr, int parent, int[] ans) {
            // 反转 parent -> curr 之间的边
            ans[curr] = ans[parent] + (g[curr].contains(parent) ? -1 : 1);
            for (var next : rg[curr]) {
                if (next == parent) continue;

                dfs(next, curr, ans);
            }
        }

        private int reverse(int curr, int parent) {
            int ans = 0;
            for (var next : rg[curr]) {
                if (next == parent) continue;

                // 检查当前方向是否反了
                ans += !g[curr].contains(next) ? 1 : 0;
                ans += reverse(next, curr);
            }

            return ans;
        }
    }

    public static void main(String[] args) {
    }

}
