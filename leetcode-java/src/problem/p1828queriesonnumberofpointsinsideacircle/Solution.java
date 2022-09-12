package problem.p1828queriesonnumberofpointsinsideacircle;

import common.Checker;

/**
 * 1828. Queries on Number of Points Inside a Circle
 *
 * https://leetcode.cn/problems/queries-on-number-of-points-inside-a-circle/
 *
 * You are given an array points where points[i] = [xi, yi] is the coordinates of
 * the ith point on a 2D plane. Multiple points can have the same coordinates.
 *
 * You are also given an array queries where queries[j] = [xj, yj, rj] describes a
 * circle centered at (xj, yj) with a radius of rj.
 *
 * For each query queries[j], compute the number of points inside the jth circle.
 * Points on the border of the circle are considered inside.
 *
 * Return an array answer, where answer[j] is the answer to the jth query.
 */

public class Solution {

    public int[] countPoints(int[][] points, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            for (var point : points) {
                if (distance(point, queries[i]) <= (double) queries[i][2]) {
                    ans[i]++;
                }
            }
        }
        return ans;
    }

    private double distance(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().countPoints(new int[][]{
            {1,3},{3,3},{5,3},{2,2}
        }, new int[][]{{2,3,1},{4,3,1},{1,1,2}}), new int[]{3,2,2});

        assert Checker.check(new Solution().countPoints(new int[][]{
            {1,1},{2,2},{3,3},{4,4},{5,5}
        }, new int[][]{{1,2,2},{2,2,2},{4,3,2},{4,3,3}}), new int[]{2,3,2,4});
    }

}
