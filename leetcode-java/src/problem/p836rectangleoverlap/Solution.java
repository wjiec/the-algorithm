package problem.p836rectangleoverlap;

/**
 * 836. Rectangle Overlap
 *
 * https://leetcode-cn.com/problems/rectangle-overlap/
 *
 * An axis-aligned rectangle is represented as a list [x1, y1, x2, y2],
 * where (x1, y1) is the coordinate of its bottom-left corner,
 * and (x2, y2) is the coordinate of its top-right corner.
 * Its top and bottom edges are parallel to the X-axis, and its left and right edges are parallel to the Y-axis.
 *
 * Two rectangles overlap if the area of their intersection is positive.
 * To be clear, two rectangles that only touch at the corner or edges do not overlap.
 *
 * Given two axis-aligned rectangles rec1 and rec2, return true if they overlap, otherwise return false.
 */

public class Solution {

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (rec2[0] >= rec1[2] || rec2[1] >= rec1[3]) {
            return false;
        }

        if (rec2[2] <= rec1[0] || rec2[3] <= rec1[1]) {
            return false;
        }

        if (rec1[0] == rec1[2] || rec1[1] == rec1[3] || rec2[0] == rec2[2] || rec2[1] == rec2[3]) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().isRectangleOverlap(new int[]{-1,0,1,1}, new int[]{0,-1,0,1});
        assert new Solution().isRectangleOverlap(new int[]{7,8,13,15}, new int[]{10,8,12,20});
        assert new Solution().isRectangleOverlap(new int[]{0,0,2,2}, new int[]{1,1,3,3});
        assert !new Solution().isRectangleOverlap(new int[]{0,0,1,1}, new int[]{1,0,2,1});
        assert !new Solution().isRectangleOverlap(new int[]{0,0,1,1}, new int[]{2,2,3,3});
        assert !new Solution().isRectangleOverlap(new int[]{0,0,1,1}, new int[]{1,1,1,1});
    }

}
