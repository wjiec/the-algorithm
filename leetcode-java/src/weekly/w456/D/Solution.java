package weekly.w456.D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Q4. Maximize Spanning Tree Stability with Upgrades
 *
 * https://leetcode.cn/contest/weekly-contest-456/problems/maximize-spanning-tree-stability-with-upgrades/
 *
 * You are given an integer n, representing n nodes numbered from 0 to n - 1
 * and a list of edges, where edges[i] = [ui, vi, si, musti]:
 *
 * ui and vi indicates an undirected edge between nodes ui and vi.
 * si is the strength of the edge.
 * musti is an integer (0 or 1). If musti == 1, the edge must be included in the spanning tree.
 * These edges cannot be upgraded.
 *
 * You are also given an integer k, the maximum number of upgrades you can perform.
 * Each upgrade doubles the strength of an edge, and each eligible edge (with musti == 0) can be upgraded at most once.
 *
 * The stability of a spanning tree is defined as the minimum strength score among all edges included in it.
 *
 * Return the maximum possible stability of any valid spanning tree. If it is impossible to connect all nodes, return -1.
 *
 * Note: A spanning tree of a graph with n nodes is a subset of the edges that connects
 * all nodes together (i.e. the graph is connected) without forming any cycles,
 * and uses exactly n - 1 edges.
 */

public class Solution {

    private static class UnionFind {
        private final int[] parent, count;
        public UnionFind(int n) {
            parent = new int[n]; count = new int[n];
            Arrays.fill(count, 1);
            Arrays.setAll(parent, i -> i);
        }

        public int size(int x) { return count[find(x)]; }
        public int find(int x) { return parent[x] == x ? x : (parent[x] = find(parent[x])); }
        public boolean union(int u, int v) {
            int up = find(u), vp = find(v);
            if (up != vp) {
                parent[up] = vp;
                count[vp] += count[up];
                return true;
            }
            return false;
        }
    }

    // [u, v, s, m]
    public int maxStability(int n, int[][] edges, int k) {
        // 所有 must == 1 的边必须包含在最小生成树里, 同时这些边不能升级
        //  - 也就是如果 mustMi = min(edges[must == 1]) 是所有边的最小值, 那么答案就是 mustMi
        //  - 否则我们可以考虑升级其他的边
        //      - 如果升级这些边之后 >= mustMi, 则答案就是 mustMi
        //      - 否则升级之后还是 < mustMi, 则答案就是这些值的最小值
        //
        // 从这里可以看出我们应该要尽可能的选择 >= mustMi 或者翻倍之后大于等于 mustMi 的边
        int ans = Integer.MAX_VALUE, mustNode = -1;
        UnionFind uf = new UnionFind(n);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        for (var edge : edges) {
            if (edge[3] == 1) {
                mustNode = edge[0];
                if (!uf.union(edge[0], edge[1])) return -1;
                ans = Math.min(ans, edge[2]);
            } else pq.add(edge);
        }
        // 如果存在必须边, 且必须边组成的树已经满足要求, 则直接返回
        // 否则就说明树不完整且存在一些边的强度小于必须边的最小值
        if (mustNode >= 0 && uf.size(mustNode) == n) return ans;

        // 先加入所有 >= mustMi 的边, 这些边不影响树最终的强度
        while (!pq.isEmpty() && pq.peek()[2] >= ans) {
            var curr = pq.remove();
            if (uf.union(curr[0], curr[1])) {
                if (mustNode < 0) mustNode = curr[0];
                if (uf.size(mustNode) == n) return ans; // 满足条件则直接返回
            }
        }

        // 然后我们需要从大到小加入所有的其余边, 使得生成树
        List<Integer> doubleEdge = new ArrayList<>();
        while (!pq.isEmpty()) {
            var curr = pq.remove();
            if (mustNode < 0) mustNode = curr[0];
            if (uf.union(curr[0], curr[1])) {
                doubleEdge.add(curr[2]);
                if (uf.size(mustNode) == n) break;
            }
        }
        if (uf.size(mustNode) != n) return -1;

        doubleEdge.sort(Integer::compare);
        int doubleValue = Integer.MAX_VALUE;
        for (var v : doubleEdge) {
            if (k-- > 0) doubleValue = Math.min(doubleValue, v * 2);
            else doubleValue = Math.min(doubleValue, v);
        }

        return Math.min(ans, doubleValue);
    }

    public static void main(String[] args) {
        assert new Solution().maxStability(7, new int[][]{
            {2,4,84747,0},{0,6,34286,1},{2,3,11963,1},{0,2,15682,0},
            {4,5,11120,1},{5,6,96773,1},{0,5,91844,0}
        }, 5) == -1;

        assert new Solution().maxStability(10, new int[][]{
            {1,3,85282,1},{2,7,15170,1},{5,8,83816,0},{0,9,6574,1},
            {0,6,97906,0},{7,9,3,0},{8,9,87261,1},{3,9,21740,0},
            {2,6,90270,0},{3,6,47414,1},{6,9,57119,0},{7,8,46995,0}
        }, 7) == -1;

        assert new Solution().maxStability(3, new int[][]{{0,1,2,1}, {1,2,3,0}}, 1) == 2;
        assert new Solution().maxStability(3, new int[][]{{0,1,4,0}, {1,2,3,0}, {0,2,1,0}}, 2) == 6;
        assert new Solution().maxStability(3, new int[][]{{0,1,1,1}, {1,2,1,1}, {2,0,1,1}}, 0) == -1;
    }

}
