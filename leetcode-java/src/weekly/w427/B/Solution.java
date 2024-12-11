package weekly.w427.B;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 3380. Maximum Area Rectangle With Point Constraints I
 *
 * https://leetcode.cn/contest/weekly-contest-427/problems/maximum-area-rectangle-with-point-constraints-i/
 *
 * You are given an array points where points[i] = [xi, yi] represents the coordinates of a point on an infinite plane.
 *
 * Your task is to find the maximum area of a rectangle that:
 *
 * Can be formed using four of these points as its corners.
 * Does not contain any other point inside or on its border.
 * Has its edges parallel to the axes.
 *
 * Return the maximum area that you can obtain or -1 if no such rectangle is possible.
 */

public class Solution {

    public int maxRectangleArea(int[][] points) {
        int ans = -1, n = points.length;
        for (int i = 0; i < (1 << n); i++) {
            if (Integer.bitCount(i) != 4) continue;
            ans = Math.max(ans, rectangleArea(points, i));
        }
        return ans;
    }

    private int rectangleArea(int[][] points, int mask) {
        TreeMap<Integer, List<Integer>> xAxis = new TreeMap<>();
        TreeMap<Integer, List<Integer>> yAxis = new TreeMap<>();
        for (int i = 0; i < points.length; i++) {
            if ((mask & (1 << i)) != 0) {
                xAxis.computeIfAbsent(points[i][0], k -> new ArrayList<>()).add(points[i][1]);
                yAxis.computeIfAbsent(points[i][1], k -> new ArrayList<>()).add(points[i][0]);
            }
        }

        // 检查是否只有是一个与坐标轴对应的矩形
        if (xAxis.size() != 2 || yAxis.size() != 2) return -1;

        // 检查内部是否还有其他点
        int x1 = xAxis.firstKey(), x2 = xAxis.lastKey();
        int y1 = yAxis.firstKey(), y2 = yAxis.lastKey();
        for (int i = 0; i < points.length; i++) {
            if ((mask & (1 << i)) == 0) {
                int x = points[i][0], y = points[i][1];
                if ((x1 <= x && x <= x2) && (y1 <= y && y <= y2)) return -1;
            }
        }

        return (x2 - x1) * (y2 - y1);
    }

    public static void main(String[] args) {
    }

}
