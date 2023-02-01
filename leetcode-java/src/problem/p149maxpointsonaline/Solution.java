package problem.p149maxpointsonaline;

import common.PrettyPrinter;
import common.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 * 149. Max Points on a Line
 *
 * https://leetcode.cn/problems/max-points-on-a-line/
 *
 * Given an array of points where points[i] = [xi, yi] represents a point
 * on the X-Y plane, return the maximum number of points that lie on
 * the same straight line.
 */

public class Solution {

    @Tag({"数学", "斜率", "同一直线"})
    public int maxPoints(int[][] points) {
        int ans = 1;
        for (int i = 0; i < points.length; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            Map<Integer, Integer> slopes = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                int x2 = points[j][0], y2 = points[j][1];

                int dy = y2 - y1, dx = x2 - x1;
                if (dx == 0) dy = 1;
                else if (dy == 0) dx = 1;
                else {
                    if (dy < 0) { dx = -dx; dy = -dy; }

                    int g = gcd(Math.abs(dx), Math.abs(dy));
                    dx /= g; dy /= g;
                }

                ans = Math.max(ans, slopes.merge(dx * 20001 + dy, 1, Integer::sum) + 1);
            }
        }
        return ans;
    }

    private static int gcd(int a, int b) { return a % b == 0 ? b : gcd(b, a % b); }

    public static void main(String[] args) {
        assert new Solution().maxPoints(new int[][]{{0,1},{1,1},{2,1}}) == 3;
        assert new Solution().maxPoints(new int[][]{{3,3},{1,4},{1,1},{2,1},{2,2}}) == 3;

        assert new Solution().maxPoints(new int[][]{{1,1},{2,2},{3,3}}) == 3;
        assert new Solution().maxPoints(new int[][]{{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}}) == 4;
    }

}
