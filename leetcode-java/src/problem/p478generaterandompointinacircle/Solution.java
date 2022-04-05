package problem.p478generaterandompointinacircle;

import java.util.Arrays;

/**
 * 478. Generate Random Point in a Circle
 *
 * https://leetcode-cn.com/problems/generate-random-point-in-a-circle/
 *
 * Given the radius and the position of the center of a circle, implement the function randPoint which
 * generates a uniform random point inside the circle.
 *
 * Implement the Solution class:
 *
 * Solution(double radius, double x_center, double y_center) initializes the object with the radius of the
 * circle radius and the position of the center (x_center, y_center).
 *
 * randPoint() returns a random point inside the circle. A point on the circumference of the
 * circle is considered to be in the circle. The answer is returned as an array [x, y].
 */

public class Solution {

    private final double radius, x, y;

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius; x = x_center; y = y_center;
    }

    public double[] randPoint() {
        double a = x - radius, b = y - radius;

        while (true) {
            double c = a + Math.random() * radius * 2;
            double d = b + Math.random() * radius * 2;
            double r = Math.sqrt((c - a) * (c - a) + (d - b) * (d - b));
            if (r <= radius) return new double[]{c, d};
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution(1., 0., 0.);
        System.out.println(Arrays.toString(solution.randPoint()));
        System.out.println(Arrays.toString(solution.randPoint()));
        System.out.println(Arrays.toString(solution.randPoint()));
        System.out.println(Arrays.toString(solution.randPoint()));
        System.out.println(Arrays.toString(solution.randPoint()));
    }

}
