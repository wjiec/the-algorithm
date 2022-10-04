package problem.p2001numberofpairsofinterchangeablerectangles;

import java.util.Arrays;

/**
 * 2001. Number of Pairs of Interchangeable Rectangles
 *
 * https://leetcode.cn/problems/number-of-pairs-of-interchangeable-rectangles/
 *
 * You are given n rectangles represented by a 0-indexed 2D integer array rectangles, where
 * rectangles[i] = [widthi, heighti] denotes the width and height of the ith rectangle.
 *
 * Two rectangles i and j (i < j) are considered interchangeable if they have the same
 * width-to-height ratio. More formally, two rectangles are interchangeable
 * if widthi/heighti == widthj/heightj (using decimal division, not integer division).
 *
 * Return the number of pairs of interchangeable rectangles in rectangles.
 */

public class Solution {

    public long interchangeableRectangles(int[][] rectangles) {
        double[] ratio = new double[rectangles.length];
        for (int i = 0; i < rectangles.length; i++) {
            ratio[i] = (double) rectangles[i][0] / rectangles[i][1];
        }
        Arrays.sort(ratio);

        long ans = 0, curr = 0; double prev = -1;
        for (double r : ratio) {
            if (r == prev) curr++;
            else { curr = 1; prev = r; }
            if (curr > 1) ans += curr - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().interchangeableRectangles(new int[][]{{4,8},{3,6},{10,20},{15,30}}) == 6;
        assert new Solution().interchangeableRectangles(new int[][]{{4,5},{7,8}}) == 0;
    }

}
