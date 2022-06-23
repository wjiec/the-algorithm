package problem.p1041robotboundedincircle;

/**
 * 1041. Robot Bounded In Circle
 *
 * https://leetcode.cn/problems/robot-bounded-in-circle/
 *
 * On an infinite plane, a robot initially stands at (0, 0) and faces north. Note that:
 *
 * The north direction is the positive direction of the y-axis.
 * The south direction is the negative direction of the y-axis.
 * The east direction is the positive direction of the x-axis.
 * The west direction is the negative direction of the x-axis.
 * The robot can receive one of three instructions:
 *
 * "G": go straight 1 unit.
 * "L": turn 90 degrees to the left (i.e., anti-clockwise direction).
 * "R": turn 90 degrees to the right (i.e., clockwise direction).
 * The robot performs the instructions given in order, and repeats them forever.
 *
 * Return true if and only if there exists a circle in the plane such that the robot
 * never leaves the circle.
 */

public class Solution {

    private record Point(int a, int b, int c) {}

    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0, deg = 90, n = instructions.length();
        for (int i = 0; i < 4 * n; i++) {
            char c = instructions.charAt(i % n);

            if (c == 'L' || c == 'R') {
                deg = ((deg + (c == 'L' ? 90 : -90)) + 360) % 360;
                continue;
            }

            switch (deg) {
                case 0 -> x++;
                case 90 -> y++;
                case 180 -> x--;
                case 270 -> y--;
            }
        }
        return x == 0 && y == 0 && deg == 90;
    }

    public static void main(String[] args) {
        assert !new Solution().isRobotBounded("GLGLGGLGL");

        assert new Solution().isRobotBounded("GGLLGG");
        assert !new Solution().isRobotBounded("GG");
        assert new Solution().isRobotBounded("GL");
    }

}
