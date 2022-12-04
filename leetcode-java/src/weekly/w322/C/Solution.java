package weekly.w322.C;

import java.util.Arrays;

/**
 * 6255. Minimum Score of a Path Between Two Cities
 *
 * https://leetcode.cn/contest/weekly-contest-322/problems/minimum-score-of-a-path-between-two-cities/
 *
 * You are given a positive integer n representing n cities numbered from 1 to n.
 * You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that
 * there is a bidirectional road between cities ai and bi with a distance equal to distancei.
 *
 * The cities graph is not necessarily connected.
 *
 * The score of a path between two cities is defined as the minimum distance of a road in this path.
 *
 * Return the minimum possible score of a path between cities 1 and n.
 *
 * Note:
 *
 * A path is a sequence of roads between two cities.
 * It is allowed for a path to contain the same road multiple times, and you can
 * visit cities 1 and n multiple times along the path.
 *
 * The test cases are generated such that there is at least one path between 1 and n.
 */

public class Solution {

    // 简单并查集
    public static class UnionFind {
        // 记录每个节点的父节点是谁, 默认都指向自己
        private final int[] parent;
        private final int[] cost;

        // 初始化并查集, 设置每个节点的父节点为自己
        public UnionFind(int n) {
            parent = new int[n];
            cost = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            Arrays.fill(cost, Integer.MAX_VALUE);
        }

        // 合并两个节点
        public void union(int a, int b, int c) {
            int fa = find(a), fb = find(b);
            parent[fa] = fb;
            cost[fb] = cost[fa] = Math.min(c, Math.min(cost[fa], cost[fb]));
        }

        // 查找指定节点的父节点同时压缩树
        public int find(int v) {
            while (v != parent[v]) {
                parent[v] = parent[parent[v]];
                v = parent[v];
            }
            return v;
        }
    }

    public int minScore(int n, int[][] roads) {
        UnionFind uf = new UnionFind(n + 1);
        for (var road : roads) {
            uf.union(road[0], road[1], road[2]);
        }
        return uf.cost[uf.find(1)];
    }

    public static void main(String[] args) {
        assert new Solution().minScore(4, new int[][]{{1,2,2}, {1,3,4}, {3,4,7}}) == 2;
    }

}
