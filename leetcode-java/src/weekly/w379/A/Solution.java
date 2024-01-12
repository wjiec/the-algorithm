package weekly.w379.A;

/**
 * 3000. Maximum Area of Longest Diagonal Rectangle
 *
 * https://leetcode.cn/contest/weekly-contest-379/problems/maximum-area-of-longest-diagonal-rectangle/
 *
 * You are given a 2D 0-indexed integer array dimensions.
 *
 * For all indices i, 0 <= i < dimensions.length, dimensions[i][0] represents the
 * length and dimensions[i][1] represents the width of the rectangle i.
 *
 * Return the area of the rectangle having the longest diagonal. If there are multiple
 * rectangles with the longest diagonal, return the area of the rectangle having the maximum area.
 */

public class Solution {

    public int areaOfMaxDiagonal(int[][] dimensions) {
        int diagonal = 0, area = 0;
        for (var rect : dimensions) {
            int curr = rect[0] * rect[0] + rect[1] * rect[1];
            if (curr > diagonal) {
                diagonal = curr;
                area = rect[0] * rect[1];
            } else if (curr == diagonal) {
                area = Math.max(area, rect[0] * rect[1]);
            }
        }
        return area;
    }

    public static void main(String[] args) {
    }

}
