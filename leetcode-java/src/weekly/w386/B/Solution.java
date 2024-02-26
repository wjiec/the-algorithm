package weekly.w386.B;

/**
 * 3047. Find the Largest Area of Square Inside Two Rectangles
 *
 * https://leetcode.cn/contest/weekly-contest-386/problems/find-the-largest-area-of-square-inside-two-rectangles/
 *
 * There exist n rectangles in a 2D plane. You are given two 0-indexed 2D integer
 * arrays bottomLeft and topRight, both of size n x 2, where bottomLeft[i] and
 * topRight[i] represent the bottom-left and top-right coordinates of the ith
 * rectangle respectively.
 *
 * You can select a region formed from the intersection of two of the given rectangles.
 * You need to find the largest area of a square that can fit inside this region
 * if you select the region optimally.
 *
 * Return the largest possible area of a square, or 0 if there do not exist any
 * intersecting regions between the rectangles.
 */

public class Solution {

    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        long ans = 0;
        for (int i = 0; i < bottomLeft.length; i++) {
            int[] bl1 = bottomLeft[i], tr1 = topRight[i];

            for (int j = i + 1; j < bottomLeft.length; j++) {
                int[] bl2 = bottomLeft[j], tr2 = topRight[j];

                int h = Math.min(tr1[1], tr2[1]) - Math.max(bl1[1], bl2[1]);
                int w = Math.min(tr1[0], tr2[0]) - Math.max(bl1[0], bl2[0]);
                int e = Math.min(h, w);
                if (e > 0) ans = Math.max(ans, (long) e * e);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
