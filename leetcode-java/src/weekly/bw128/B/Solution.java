package weekly.bw128.B;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 100280. Minimum Rectangles to Cover Points
 *
 * https://leetcode.cn/contest/biweekly-contest-128/problems/minimum-rectangles-to-cover-points/
 *
 * You are given a 2D integer array points, where points[i] = [xi, yi]. You are also given an integer w.
 * Your task is to cover all the given points with rectangles.
 *
 * Each rectangle has its lower end at some point (x1, 0) and its upper end at some point (x2, y2),
 * where x1 <= x2, y2 >= 0, and the condition x2 - x1 <= w must be satisfied for each rectangle.
 *
 * A point is considered covered by a rectangle if it lies within or on the boundary of the rectangle.
 *
 * Return an integer denoting the minimum number of rectangles needed so that each point is covered
 * by at least one rectangle.
 *
 * Note: A point may be covered by more than one rectangle.
 */

public class Solution {

    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int ans = 1, prev = points[0][0];
        for (int[] point : points) {
            if (point[0] - prev > w) {
                ans++; prev = point[0];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
