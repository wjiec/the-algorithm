package problem.p874walkingrobotsimulation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 874. Walking Robot Simulation
 *
 * https://leetcode-cn.com/problems/walking-robot-simulation/
 *
 * A robot on an infinite XY-plane starts at point (0, 0) and faces north.
 * The robot can receive one of three possible types of commands:
 *
 * -2: turn left 90 degrees,
 * -1: turn right 90 degrees, or
 * 1 <= k <= 9: move forward k units.
 * Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi).
 *
 * If the robot would try to move onto them, the robot stays on the previous grid
 * square instead (but still continues following the rest of the route.)
 *
 * Return the maximum Euclidean distance that the robot will be
 * from the origin squared (i.e. if the distance is 5, return 25).
 *
 * Note:
 *
 * North means +Y direction.
 * East means +X direction.
 * South means -Y direction.
 * West means -X direction.
 */

public class Solution {

    private Map<Integer, Set<Integer>> barriers = new HashMap<>();

    public int robotSim(int[] commands, int[][] obstacles) {
        for (var xy : obstacles) {
            barriers.putIfAbsent(xy[0], new HashSet<>());
            barriers.get(xy[0]).add(xy[1]);
        }

        int x = 0, y = 0, ans = 0, deg = 0;
        for (var command : commands) {
            if (command == -1) deg -= 90;
            else if (command == -2) deg += 90;
            if (deg < 0) deg += 360;
            else if (deg > 360) deg -= 360;
            if (command > 0) {
                command *= deg == 180 || deg == 90 ? -1 : 1;
                if (deg == 90 || deg == 270) x = moveX(x, y, command);
                else y = moveY(x, y, command);

                ans = Math.max(ans, x * x + y * y);
            }
        }
        return ans;
    }

    private int moveX(int x, int y, int command) {
        int n = Math.abs(command), step = command / n;
        for (int i = 0; i < n; i++) {
            x += step;
            if (barriers.containsKey(x) && barriers.get(x).contains(y)) {
                return x - step;
            }
        }

        return x;
    }

    private int moveY(int x, int y, int command) {
        int n = Math.abs(command), step = command / n;
        if (barriers.containsKey(x)) {
            var barrier = barriers.get(x);
            for (int i = 0; i < n; i++) {
                y += step;
                if (barrier.contains(y)) return y - step;
            }
            return y;
        }
        return y + command;
    }

    public static void main(String[] args) {
        assert new Solution().robotSim(new int[]{1,1,3,4,3}, new int[][]{
            {-1,5},{-4,-4},{-3,3},{3,0},{2,5},{-4,4},{-3,1},{-2,-4},{-1,-4},{0,-3}
        }) == 144;
        assert new Solution().robotSim(new int[]{4,-1,3}, new int[][]{}) == 25;
        assert new Solution().robotSim(new int[]{4,-1,4,-2,4}, new int[][]{{2,4}}) == 65;
    }

}
