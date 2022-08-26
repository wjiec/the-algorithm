package problem.p1637widestverticalareabetweentwopointscontainingnopoints;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1637. Widest Vertical Area Between Two Points Containing No Points
 *
 * https://leetcode.cn/problems/widest-vertical-area-between-two-points-containing-no-points/
 *
 * Given n points on a 2D plane where points[i] = [xi, yi], Return the widest vertical area
 * between two points such that no points are inside the area.
 *
 * A vertical area is an area of fixed-width extending infinitely along the y-axis (i.e., infinite height).
 * The widest vertical area is the one with the maximum width.
 *
 * Note that points on the edge of a vertical area are not considered included in the area.
 */

public class Solution {

    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(p -> p[0]));

        int ans = 0;
        for (int i = 1; i < points.length; i++) {
            ans = Math.max(ans, points[i][0] - points[i - 1][0]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxWidthOfVerticalArea(new int[][]{{8,7},{9,9},{7,4},{9,7}}) == 1;
        assert new Solution().maxWidthOfVerticalArea(new int[][]{{3,1},{9,0},{1,0},{1,4},{5,3},{8,8}}) == 3;
    }

}
