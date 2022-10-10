package problem.p2069walkingrobotsimulationii;

import common.Checker;

/**
 * 2069. Walking Robot Simulation II
 *
 * https://leetcode.cn/problems/walking-robot-simulation-ii/
 *
 * A width x height grid is on an XY-plane with the bottom-left cell at (0, 0) and the
 * top-right cell at (width - 1, height - 1). The grid is aligned with the four cardinal
 * directions ("North", "East", "South", and "West"). A robot is initially at cell (0, 0)
 * facing direction "East".
 *
 * The robot can be instructed to move for a specific number of steps. For each step, it does the following.
 *
 * Attempts to move forward one cell in the direction it is facing.
 * If the cell the robot is moving to is out of bounds, the robot instead turns 90 degrees
 * counterclockwise and retries the step.
 * After the robot finishes moving the number of steps required, it stops and awaits the next instruction.
 *
 * Implement the Robot class:
 *
 * Robot(int width, int height) Initializes the width x height grid with the robot at (0, 0) facing "East".
 * void step(int num) Instructs the robot to move forward num steps.
 * int[] getPos() Returns the current cell the robot is at, as an array of length 2, [x, y].
 * String getDir() Returns the current direction of the robot, "North", "East", "South", or "West".
 */

public class Solution {

    private static class Robot {
        private final int width, height, perimeter;

        private int tot = 0;
        public Robot(int width, int height) {
            this.width = width; this.height = height;
            perimeter = 2 * (width + height) - 4;
        }

        public void step(int num) { tot += num; }

        public int[] getPos() {
            int idx = tot % perimeter;
            if (idx < width) return new int[]{idx, 0};

            int rightTop = width + height - 1;
            if (idx < rightTop) return new int[]{width - 1, idx - width + 1};

            int leftTop = width + width + height - 2;
            if (idx < leftTop) return new int[]{width - (idx - rightTop) - 2, height - 1};

            return new int[]{0, height - (idx - leftTop) - 2};
        }

        public String getDir() {
            if (tot == 0) return "East";

            int idx = tot % perimeter;
            if (idx > 0 && idx < width) return "East";
            if (idx >= width && idx < width + height - 1) return "North";
            if (idx >= width + height - 1 && idx < width + width + height - 2) return "West";
            return "South";
        }
    }

    public static void main(String[] args) {
        Robot robot = new Robot(6, 3);
        robot.step(2);
        robot.step(2);
        assert Checker.check(robot.getPos(), new int[]{4, 0});
        assert robot.getDir().equals("East");
        robot.step(2);
        robot.step(1);
        robot.step(4);
        assert Checker.check(robot.getPos(), new int[]{1, 2});
        assert robot.getDir().equals("West");
    }

}
