package problem.p469pconvexpolygon;

import java.util.List;

/**
 * 469. Convex Polygon
 *
 * https://leetcode.cn/problems/convex-polygon/
 *
 * You are given an array of points on the X-Y plane points where points[i] = [xi, yi].
 * The points form a polygon when joined sequentially.
 *
 * Return true if this polygon is convex and false otherwise.
 *
 * You may assume the polygon formed by given points is always a simple polygon.
 * In other words, we ensure that exactly two edges intersect at each vertex and
 * that edges otherwise don't intersect each other.
 */

public class Solution {

    public boolean isConvex(List<List<Integer>> points) {
        int n = points.size();
        long prev = 0;
        for (int i = 0; i < n; i++) {
            long v1x = points.get((i + 1) % n).get(0) - points.get(i).get(0);
            long v1y = points.get((i + 1) % n).get(1) - points.get(i).get(1);

            long v2x = points.get((i + 2) % n).get(0) - points.get((i + 1) % n).get(0);
            long v2y = points.get((i + 2) % n).get(1) - points.get((i + 1) % n).get(1);

            long curr = v1x * v2y - v1y * v2x;
            if (curr != 0) {
                if (prev * curr < 0) return false;
                prev = curr;
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
