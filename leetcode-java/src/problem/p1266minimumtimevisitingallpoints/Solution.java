package problem.p1266minimumtimevisitingallpoints;

/**
 * 1266. Minimum Time Visiting All Points
 *
 * https://leetcode-cn.com/problems/minimum-time-visiting-all-points/
 *
 * On a 2D plane, there are n points with integer coordinates points[i] = [xi, yi].
 * Return the minimum time in seconds to visit all the points in the order given by points.
 *
 * You can move according to these rules:
 *
 * In 1 second, you can either:
 *      move vertically by one unit,
 *      move horizontally by one unit, or
 *      move diagonally sqrt(2) units (in other words, move one unit vertically
 *      then one unit horizontally in 1 second).
 *
 * You have to visit the points in the same order as they appear in the array.
 *
 * You are allowed to pass through points that appear later in the order, but these do not count as visits.
 */

public class Solution {

    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0, l = points.length;
        int x = points[0][0], y = points[0][1];
        for (int i = 1; i < l; i++) {
            int nx = points[i][0], ny = points[i][1],
                ax = Math.abs(x - nx), ay = Math.abs(y - ny),
                min = Math.min(ax, ay);
            ans += min + (ax - min) + (ay - min);
            x = nx; y = ny;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minTimeToVisitAllPoints(new int[][]{
            {1,1}, {3,4}, {-1,0}
        }) == 7;
    }

}
