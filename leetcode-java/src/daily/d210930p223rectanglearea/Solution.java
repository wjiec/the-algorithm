package daily.d210930p223rectanglearea;

/**
 * 223. Rectangle Area
 *
 * https://leetcode-cn.com/problems/rectangle-area/
 *
 * Given the coordinates of two rectilinear rectangles in a 2D plane,
 * return the total area covered by the two rectangles.
 *
 * The first rectangle is defined by its bottom-left corner (ax1, ay1) and its top-right corner (ax2, ay2).
 *
 * The second rectangle is defined by its bottom-left corner (bx1, by1) and its top-right corner (bx2, by2).
 */

public class Solution {

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int shadow = range(ax1, ax2, bx1, bx2) * range(ay1, ay2, by1, by2);
        return (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1) - shadow;
    }

    private int range(int a, int b, int x, int y) {
        if (a <= x && x <= b) { // a <= x <= y
            return y >= b ? (b - x) : (y - x);
        }
        if (x <= a && a <= y) { // x <= a <= y
            return b >= y ? (y - a) : (b - a);
        }
        return 0;
    }

    public static void main(String[] args) {
        assert new Solution().computeArea(-3, 0, 3, 4, 0, -1, 9, 2) == 45;
        assert new Solution().computeArea(-2, -2, 2, 2, -2, -2, 2, 2) == 16;
    }

}
