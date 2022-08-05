package problem.p1401circleandrectangleoverlapping;

import common.TODO;

/**
 * 1401. Circle and Rectangle Overlapping
 *
 * https://leetcode.cn/problems/circle-and-rectangle-overlapping/
 *
 * You are given a circle represented as (radius, xCenter, yCenter) and an axis-aligned rectangle
 * represented as (x1, y1, x2, y2), where (x1, y1) are the coordinates of the bottom-left
 * corner, and (x2, y2) are the coordinates of the top-right corner of the rectangle.
 *
 * Return true if the circle and rectangle are overlapped otherwise return false.
 * In other words, check if there is any point (xi, yi) that belongs to the circle and
 * the rectangle at the same time.
 */

public class Solution {

    @TODO(url = "https://leetcode.cn/problems/circle-and-rectangle-overlapping/solution/by-baoya_uncle-nw90/")
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // 判断圆心是否在绿色区域内
        if (isInRectangle(xCenter, yCenter, x1, y1, x2, y2)) return true;

        // 判断圆心是否在蓝色区域内
        if (isInRectangle(xCenter, yCenter, x1 - radius, y1, x1, y2)) return true;
        if (isInRectangle(xCenter, yCenter, x1, y2, x2, y2 + radius)) return true;
        if (isInRectangle(xCenter, yCenter, x2, y1, x2 + radius, y2)) return true;
        if (isInRectangle(xCenter, yCenter, x1, y1 - radius, x2, y1)) return true;

        // 判断圆心是否在红色区域内
        if (getDistSquare(xCenter, yCenter, x1, y2) <= radius * radius) return true;
        if (getDistSquare(xCenter, yCenter, x2, y2) <= radius * radius) return true;
        if (getDistSquare(xCenter, yCenter, x2, y1) <= radius * radius) return true;
        if (getDistSquare(xCenter, yCenter, x1, y1) <= radius * radius) return true;

        // 不在任意区域，圆和矩形不重叠。
        return false;
    }

    public boolean isInRectangle(int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        return xCenter >= x1 && xCenter <= x2 && yCenter >= y1 && yCenter <= y2;
    }

    public int getDistSquare(int xCenter, int yCenter, int x, int y) {
        return (xCenter - x) * (xCenter - x) + (yCenter - y) * (yCenter - y);
    }

    public static void main(String[] args) {
        assert new Solution().checkOverlap(1, 0, 0, 1, -1, 3, 1);
        assert new Solution().checkOverlap(1, 0, 0, -1, 0, 0, 1);
        assert new Solution().checkOverlap(1, 1, 1, -3, -3, 3, 3);
    }

}
