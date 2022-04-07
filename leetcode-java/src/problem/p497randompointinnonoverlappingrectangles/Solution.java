package problem.p497randompointinnonoverlappingrectangles;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 497. Random Point in Non-overlapping Rectangles
 *
 * https://leetcode-cn.com/problems/random-point-in-non-overlapping-rectangles/
 *
 * You are given an array of non-overlapping axis-aligned rectangles rects where rects[i] = [ai, bi, xi, yi]
 * indicates that (ai, bi) is the bottom-left corner point of the ith rectangle and (xi, yi) is the top-right
 * corner point of the ith rectangle.
 *
 * Design an algorithm to pick a random integer point inside the space covered by one of the given rectangles.
 * A point on the perimeter of a rectangle is included in the space covered by the rectangle.
 *
 * Any integer point inside the space covered by one of the given rectangles should be equally likely to be returned.
 *
 * Note that an integer point is a point that has integer coordinates.
 *
 * Implement the Solution class:
 *
 * Solution(int[][] rects) Initializes the object with the given rectangles rects.
 * int[] pick() Returns a random integer point [u, v] inside the space covered by one of the given rectangles.
 */

public class Solution {

    private final int[][] rects;
    public Solution(int[][] rects) { this.rects = rects; }

    public int[] pick() {
        int totalPoints = 0;
        int[] selected = null;
        for (var rect : rects) {
            int points = (rect[3] - rect[1] + 1) * (rect[2] - rect[0] + 1);
            totalPoints += points;
            if (ThreadLocalRandom.current().nextInt(0, totalPoints) < points) {
                selected = rect;
            }
        }

        assert selected != null;
        int lx = selected[0], ly = selected[1], rx = selected[2], ry = selected[3];
        return new int[]{
            ThreadLocalRandom.current().nextInt(lx, rx + 1),
            ThreadLocalRandom.current().nextInt(ly, ry + 1)
        };
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new int[][]{{-2, -2, 1, 1}, {2, 2, 4, 6}});
        System.out.println(Arrays.toString(solution.pick()));
        System.out.println(Arrays.toString(solution.pick()));
        System.out.println(Arrays.toString(solution.pick()));
        System.out.println(Arrays.toString(solution.pick()));
        System.out.println(Arrays.toString(solution.pick()));
    }

}
