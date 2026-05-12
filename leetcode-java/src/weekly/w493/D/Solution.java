package weekly.w493.D;

import java.util.*;

/**
 * Q4. Maximum Points Activated with One Addition
 *
 * https://leetcode.cn/contest/weekly-contest-493/problems/maximum-points-activated-with-one-addition/
 *
 * You are given a 2D integer array points, where points[i] = [xi, yi] represents the coordinates of the ith point.
 * All coordinates in points are distinct.
 *
 * If a point is activated, then all points that have the same x-coordinate or y-coordinate become activated as well.
 *
 * Activation continues until no additional points can be activated.
 *
 * You may add one additional point at any integer coordinate (x, y) not already present in points.
 * Activation begins by activating this newly added point.
 *
 * Return an integer denoting the maximum number of points that can be activated, including the newly added point.
 */

public class Solution {

    private static class UnionFind {
        // 记录每个节点的父节点是谁, 默认都指向自己
        private int groups = 0;
        private final int[] parent;
        private final int[] count;

        // 初始化并查集, 设置每个节点的父节点为自己
        public UnionFind(int n) {
            groups = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            count = new int[n];
            Arrays.fill(count, 1);
        }

        // 合并两个节点
        public boolean union(int a, int b) {
            int fa = find(a), fb = find(b);
            if (fa == fb) return false;

            groups--;
            parent[fa] = fb;
            count[fb] += count[fa];
            return true;
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

    public int maxActivated(int[][] points) {
        // 某个点被激活, 则具有相同的 x 或 y 的点也都会被激活(连通)
        UnionFind uf = new UnionFind(points.length);
        Map<Integer, List<Integer>> xAxis = new HashMap<>();
        Map<Integer, List<Integer>> yAxis = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            var x = xAxis.computeIfAbsent(points[i][0], k -> new ArrayList<>());
            if (!x.isEmpty()) uf.union(i, x.get(x.size() - 1)); x.add(i);

            var y = yAxis.computeIfAbsent(points[i][1], k -> new ArrayList<>());
            if (!y.isEmpty()) uf.union(i, y.get(y.size() - 1)); y.add(i);
        }
        // 如果只有一个组的话, 那么就是这个组的大小 + 1
        if (uf.groups == 1) return points.length + 1;

        // 如果存在 k 个组, 我们需要找到最大的两个组

        return 1;
    }

    public static void main(String[] args) {
    }

}
