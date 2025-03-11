package weekly.w438.D;

import java.util.Arrays;

/**
 * 3464. Maximize the Distance Between Points on a Square
 *
 * https://leetcode.cn/contest/weekly-contest-438/problems/maximize-the-distance-between-points-on-a-square/
 *
 * You are given an integer side, representing the edge length of a square with
 * corners at (0, 0), (0, side), (side, 0), and (side, side) on a Cartesian plane.
 *
 * You are also given a positive integer k and a 2D integer array points, where
 * points[i] = [xi, yi] represents the coordinate of a point lying on the boundary of the square.
 *
 * You need to select k elements among points such that the minimum Manhattan distance
 * between any two points is maximized.
 *
 * Return the maximum possible minimum Manhattan distance between the selected k points.
 *
 * The Manhattan Distance between two cells (xi, yi) and (xj, yj) is |xi - xj| + |yi - yj|.
 */

public class Solution {

    public int maxDistance(int side, int[][] points, int k) {
        // 将正方形边上的点按照顺时针映射到一维数轴上
        long[] line = new long[points.length];
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0], y = points[i][1];
            if (x == 0) line[i] = y;
            else if (y == side) line[i] = side + x;
            else if (x == side) line[i] = side * 3L - y;
            else line[i] = side * 4L - x;
        }
        Arrays.sort(line);

        // 二分答案, 然后枚举每一个点向后找到最近的另一个点, 如果足够 k 个就返回
        int l = 1, r = (int) (side * 4L / k) + 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(line, side, k, mid)) l = mid;
            else r = mid;
        }
        return l;
    }

    private boolean check(long[] line, int side, int k, int low) {
        // 枚举每一个点作为起点
        for (var start : line) {
            boolean ok = true;
            // 最后一个点的最小位置
            long end = start + side * 4L - low;
            for (long curr = start, i = 1; i < k; i++) { // 已经找到一个点了
                int next = lowerBound(line, curr + low);
                if (next == line.length || line[next] > end) {
                    ok = false; break;
                }
                curr = line[next];
            }

            if (ok) return true;
        }

        return false;
    }

    private int lowerBound(long[] nums, long target) {
        int l = -1, r = nums.length;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) r = mid;
            else l = mid;
        }
        return r;
    }

    public static void main(String[] args) {
    }

}
