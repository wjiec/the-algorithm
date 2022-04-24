package problem.p593validsquare;

/**
 * 593. Valid Square
 *
 * https://leetcode-cn.com/problems/valid-square/
 *
 * Given the coordinates of four points in 2D space p1, p2, p3 and p4,
 * return true if the four points construct a square.
 *
 * The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.
 *
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 */

public class Solution {

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        return doValidSquare(p1, p2, p3, p4) && doValidSquare(p2, p1, p3, p4)
            && doValidSquare(p2, p1, p4, p3) && doValidSquare(p4, p3, p2, p1);
    }

    private boolean doValidSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        double a = distance(p1[0], p1[1], p2[0], p2[1]);
        double b = distance(p1[0], p1[1], p3[0], p3[1]);
        double c = distance(p1[0], p1[1], p4[0], p4[1]);
        if (a == 0 || b == 0 || c == 0) return false;

        if (a == b) return c == distance(p2[0], p2[1], p3[0], p3[1]);
        if (a == c) return b == distance(p2[0], p2[1], p4[0], p4[1]);
        if (b == c) return a == distance(p3[0], p3[1], p4[0], p4[1]);
        return false;
    }

    private double distance(int x, int y, int a, int b) {
        return Math.sqrt(Math.pow(x - a, 2) + Math.pow(y - b, 2));
    }

    public static void main(String[] args) {
        assert !new Solution().validSquare(new int[]{2, 1}, new int[]{2, 2}, new int[]{2, 0}, new int[]{0, 1});
        assert !new Solution().validSquare(new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0});

        assert new Solution().validSquare(new int[]{0, 0}, new int[]{1, 1}, new int[]{1, 0}, new int[]{0, 1});
        assert !new Solution().validSquare(new int[]{0, 0}, new int[]{1, 1}, new int[]{1, 0}, new int[]{0, 12});
        assert new Solution().validSquare(new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{0, -1});
    }

}
