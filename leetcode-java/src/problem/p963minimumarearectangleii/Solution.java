package problem.p963minimumarearectangleii;

import common.Checker;

import java.util.HashSet;
import java.util.Set;

/**
 * 963. Minimum Area Rectangle II
 *
 * https://leetcode.cn/problems/minimum-area-rectangle-ii/
 *
 * You are given an array of points in the X-Y plane points where points[i] = [xi, yi].
 *
 * Return the minimum area of any rectangle formed from these points, with sides not necessarily
 * parallel to the X and Y axes. If there is not any such rectangle, return 0.
 *
 * Answers within 10-5 of the actual answer will be accepted.
 */

public class Solution {

    private record Point(int x, int y) {
        public double distance(Point p) {
            double px = x - p.x;
            double py = y - p.y;
            return Math.sqrt(px * px + py * py);
        }
    }

    public double minAreaFreeRect(int[][] points) {
        Set<Point> set = new HashSet<>();
        Point[] pts = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            pts[i] = new Point(points[i][0], points[i][1]);
            set.add(pts[i]);
        }

        double ans = Double.MAX_VALUE;
        for (int i = 0, n = points.length; i < n; i++) {
            Point a = pts[i];
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                Point b = pts[j];
                for (int k = j + 1; k < n; k++) {
                    if (k == i) continue;

                    Point c = pts[k];
                    Point d = new Point(b.x + c.x - a.x, b.y + c.y - a.y);
                    if (set.contains(d)) {
                        int dot = ((b.x - a.x) * (c.x - a.x) + (b.y - a.y) * (c.y - a.y));
                        if (dot == 0) {
                            double area = a.distance(b) * a.distance(c);
                            if (area < ans) ans = area;
                        }
                    }
                }
            }
        }
        return ans == Double.MAX_VALUE ? 0.0 : ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().minAreaFreeRect(new int[][]{{1,2},{2,1},{1,0},{0,1}}), 2.0);
        assert Checker.check(new Solution().minAreaFreeRect(new int[][]{{0,1},{2,1},{1,1},{1,0},{2,0}}), 1.0);
        assert Checker.check(new Solution().minAreaFreeRect(new int[][]{{0,3},{1,2},{3,1},{1,3},{2,1}}), 0.0);
    }

}
