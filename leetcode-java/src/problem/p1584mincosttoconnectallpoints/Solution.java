package problem.p1584mincosttoconnectallpoints;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 1584. Min Cost to Connect All Points
 *
 * https://leetcode.cn/problems/min-cost-to-connect-all-points/
 *
 * You are given an array points representing integer coordinates of some
 * points on a 2D-plane, where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance
 * between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if
 * there is exactly one simple path between any two points.
 */

public class Solution {

    private record Edge(int d, int a, int b) {}

    private static class DisjointSetUnion {
        private final int[] parent;
        private final int[] rank;

        public DisjointSetUnion(int n) {
            this.rank = new int[n];
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int v) {
            return parent[v] == v ? v : (parent[v] = find(parent[v]));
        }

        public boolean unionSet(int x, int y) {
            int fx = find(x), fy = find(y);
            if (fx == fy) return false;

            if (rank[fx] < rank[fy]) {
                int temp = fx;
                fx = fy;
                fy = temp;
            }

            rank[fx] += rank[fy];
            parent[fy] = fx;
            return true;
        }
    }

    public int minCostConnectPoints(int[][] points) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int dist = distance(points[i][0], points[i][1], points[j][0], points[j][1]);
                edges.add(new Edge(dist, i, j));
            }
        }
        edges.sort(Comparator.comparingInt(v -> v.d));

        int ans = 0, count = 0;
        DisjointSetUnion dsu = new DisjointSetUnion(points.length);
        for (var edge : edges) {
            if (dsu.unionSet(edge.a, edge.b)) {
                ans += edge.d;
                if (++count == points.length) break;
            }
        }
        return ans;
    }

    private int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static void main(String[] args) {
        assert new Solution().minCostConnectPoints(new int[][]{{0,0},{2,2},{3,10},{5,2},{7,0}}) == 20;
        assert new Solution().minCostConnectPoints(new int[][]{{3,12},{-2,5},{-4,1}}) == 18;
        assert new Solution().minCostConnectPoints(new int[][]{{0,0},{1,1},{1,0},{-1,1}}) == 4;
        assert new Solution().minCostConnectPoints(new int[][]{{-1000000,-1000000},{1000000,1000000}}) == 4000000;
        assert new Solution().minCostConnectPoints(new int[][]{{0,0}}) == 0;
    }

}
