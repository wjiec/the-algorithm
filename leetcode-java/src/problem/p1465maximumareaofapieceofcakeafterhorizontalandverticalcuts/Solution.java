package problem.p1465maximumareaofapieceofcakeafterhorizontalandverticalcuts;

import java.util.Arrays;

/**
 * 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
 *
 * https://leetcode.cn/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
 *
 * You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:
 *
 * horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
 * verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position
 * provided in the arrays horizontalCuts and verticalCuts. Since the answer
 * can be a large number, return this modulo 109 + 7.
 */

public class Solution {

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(verticalCuts);
        Arrays.sort(horizontalCuts);

        int hl = horizontalCuts.length, vl = verticalCuts.length;
        int vMax = Math.max(verticalCuts[0], w - verticalCuts[vl - 1]);
        int hMax = Math.max(horizontalCuts[0], h - horizontalCuts[hl - 1]);

        for (int i = 1; i < hl; i++) {
            hMax = Math.max(hMax, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        for (int i = 1; i < vl; i++) {
            vMax = Math.max(vMax, verticalCuts[i] - verticalCuts[i - 1]);
        }

        return (int) (((long) hMax * (long) vMax) % 1_000_000_007);
    }

    public static void main(String[] args) {
        assert new Solution().maxArea(5, 4, new int[]{1,2,4}, new int[]{1,3}) == 4;
        assert new Solution().maxArea(5, 4, new int[]{3,1}, new int[]{1}) == 6;
        assert new Solution().maxArea(5, 4, new int[]{3}, new int[]{3}) == 9;
    }

}
