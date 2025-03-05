package weekly.bw150.B;

import common.Checker;

/**
 * 3453. Separate Squares I
 *
 * https://leetcode.cn/contest/biweekly-contest-150/problems/separate-squares-i/
 *
 * You are given a 2D integer array squares. Each squares[i] = [xi, yi, li] represents
 * the coordinates of the bottom-left point and the side length of a square parallel to the x-axis.
 *
 * Find the minimum y-coordinate value of a horizontal line such that the total area of the
 * squares above the line equals the total area of the squares below the line.
 *
 * Answers within 10-5 of the actual answer will be accepted.
 *
 * Note: Squares may overlap. Overlapping areas should be counted multiple times.
 * @noinspection DuplicatedCode
 */

public class Solution {

    public double separateSquares(int[][] squares) {
        long sum = 0, loY = Integer.MAX_VALUE, hiY = 0;
        for (var square : squares) {
            long y = square[1], l = square[2];

            sum += l * l;
            loY = Math.min(loY, y);
            hiY = Math.max(hiY, y + l);
        }

        // 有了总面积之后, 我们需要找到一条线, 使得这条线上面正方形的面积与下面正方形的面积相同
        //  使用二分查找中间位置, 直到找到最接近一半面积的位置
        double l = loY, r = hiY, half = sum / 2.0;
        while (l + 1e-6 < r) {
            double mid = l + (r - l) / 2.0;
            if (check(squares, mid, half)) r = mid;
            else l = mid;
        }
        return l;
    }

    // 检查使用 line 这条线下部的面积是否大于等于 area
    private boolean check(int[][] squares, double line, double area) {
        double sum = 0;
        for (var square : squares) {
            double y = square[1], l = square[2];
            if (y + l <= line) sum += l * l;
            else if (y < line && y + l > line) sum += l * (line - y);
        }
        return sum >= area;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().separateSquares(new int[][]{{0,0,1}, {2,2,1}}), 1.00000);
        assert Checker.check(new Solution().separateSquares(new int[][]{{0,0,2}, {1,1,1}}), 1.16667);
    }

}
