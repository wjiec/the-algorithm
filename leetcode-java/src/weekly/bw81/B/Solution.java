package weekly.bw81.B;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 6106. Count Unreachable Pairs of Nodes in an Undirected Graph
 *
 * https://leetcode.cn/contest/biweekly-contest-81/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/
 *
 * You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1.
 * You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there
 * exists an undirected edge connecting nodes ai and bi.
 *
 * Return the number of pairs of different nodes that are unreachable from each other.
 */

public class Solution {

    private static class UnionFind {
        private final int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public void union(int a, int b) {
            parent[find(b)] = find(a);
        }
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }

    public long countPairs(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (var edge : edges) uf.union(edge[0], edge[1]);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.merge(uf.find(i), 1, Integer::sum);
        }

        long ans = 0, sum = 0;
        List<Integer> list = new ArrayList<>(map.values());
        for (var v : list) {
            ans += v * sum; sum += v;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countPairs(7, new int[][]{{0,2},{0,5},{2,4},{1,6},{5,4}}) == 14;
        assert new Solution().countPairs(12, new int[][]{}) == 66;
    }

}
