package problem.p2249countlatticepointsinsideacircle;

import java.util.HashSet;
import java.util.Set;

/**
 * 2249. Count Lattice Points Inside a Circle
 *
 * https://leetcode.cn/problems/count-lattice-points-inside-a-circle/
 *
 * Given a 2D integer array circles where circles[i] = [xi, yi, ri] represents the center (xi, yi) and
 * radius ri of the ith circle drawn on a grid, return the number of lattice points
 * that are present inside at least one circle.
 *
 * Note:
 *
 * A lattice point is a point with integer coordinates.
 * Points that lie on the circumference of a circle are also considered to be inside it.
 */

public class Solution {

    public int countLatticePoints(int[][] circles) {
        Set<Integer> ans = new HashSet<>();
        for (var circle : circles) {
            int x = circle[0], y = circle[1], r = circle[2];
            for (int a = x - r; a <= x + r; a++) {
                for (int b = y - r; b <= y + r; b++) {
                    int id = id(a, b);
                    if (!ans.contains(id) && distance(a, b, x, y) <= (double) r) ans.add(id);
                }
            }
        }
        return ans.size();
    }

    private int id(int x, int y) { return (x << 16) | y; }
    private double distance(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2, dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        assert new Solution().countLatticePoints(new int[][]{{2,2,1}}) == 5;
        assert new Solution().countLatticePoints(new int[][]{{2,2,2},{3,4,1}}) == 16;
    }

}
