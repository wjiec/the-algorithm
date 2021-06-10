package problem.p812largesttrianglearea;

/**
 * 812. Largest Triangle Area
 *
 * https://leetcode-cn.com/problems/largest-triangle-area/
 *
 * You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.
 */

public class Solution {

    // @TODO
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    ans = Math.max(ans, calcArea(points[i], points[j], points[k]));
                }
            }
        }
        return ans;
    }

    private double calcArea(int[] a, int[] b, int[] c) {
        return Math.abs(a[0] * b[1] + b[0] * c[1] + c[0] * a[1] - a[1] * b[0] - b[1] * c[0] - c[1] * a[0]) / 2.0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().largestTriangleArea(new int[][]{{0,0},{0,1},{1,0},{0,2},{2,0}}));
        assert new Solution().largestTriangleArea(new int[][]{{0,0},{0,1},{1,0},{0,2},{2,0}}) == 2.0;
    }

}
