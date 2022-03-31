package problem.p452minimumnumberofarrowstoburstballoons;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 452. Minimum Number of Arrows to Burst Balloons
 *
 * https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane.
 * The balloons are represented as a 2D integer array points where points[i] = [xstart, xend]
 * denotes a balloon whose horizontal diameter stretches between xstart and xend.
 *
 * You do not know the exact y-coordinates of the balloons.
 *
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
 * A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend.
 * There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely,
 * bursting any balloons in its path.
 *
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 */

public class Solution {

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(v -> v[1]));
        int ans = 1, curr = points[0][1];
        for (int[] point : points) {
            if (point[0] > curr) {
                ans++;
                curr = point[1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findMinArrowShots(new int[][]{
            {9,12},{1,10},{4,11},{8,12},{3,9},{6,9},{6,7}
        }) == 2;

        assert new Solution().findMinArrowShots(new int[][]{
            {-2147483646,-2147483645},{2147483646,2147483647}
        }) == 2;

        assert new Solution().findMinArrowShots(new int[][]{
            {3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}
        }) == 2;

        assert new Solution().findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}}) == 2;
        assert new Solution().findMinArrowShots(new int[][]{{1,2},{3,4},{5,6},{7,8}}) == 4;
        assert new Solution().findMinArrowShots(new int[][]{{1,2},{2,3},{3,4},{4,5}}) == 2;
    }

}
