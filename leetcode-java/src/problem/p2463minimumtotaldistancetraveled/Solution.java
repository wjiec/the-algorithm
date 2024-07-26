package problem.p2463minimumtotaldistancetraveled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 2463. Minimum Total Distance Traveled
 *
 * https://leetcode.cn/problems/minimum-total-distance-traveled/
 *
 * There are some robots and factories on the X-axis. You are given an integer array robot
 * where robot[i] is the position of the ith robot. You are also given a 2D integer array
 * factory where factory[j] = [positionj, limitj] indicates that positionj is the position
 * of the jth factory and that the jth factory can repair at most limitj robots.
 *
 * The positions of each robot are unique. The positions of each factory are also unique.
 * Note that a robot can be in the same position as a factory initially.
 *
 * All the robots are initially broken; they keep moving in one direction. The direction
 * could be the negative or the positive direction of the X-axis. When a robot reaches a
 * factory that did not reach its limit, the factory repairs the robot, and it stops moving.
 *
 * At any moment, you can set the initial direction of moving for some robot. Your
 * target is to minimize the total distance traveled by all the robots.
 *
 * Return the minimum total distance traveled by all the robots. The test cases are generated
 * such that all the robots can be repaired.
 *
 * Note that
 *
 * All robots move at the same speed.
 *
 * If two robots move in the same direction, they will never collide.
 *
 * If two robots move in opposite directions and they meet at some point, they do not collide.
 * They cross each other.
 *
 * If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
 *
 * If the robot moved from a position x to a position y, the distance it moved is |y - x|.
 */

public class Solution {

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        var r = robot.stream().mapToInt(i -> i).sorted().toArray();
        Arrays.sort(factory, Comparator.comparingInt(v -> v[0]));

        var dp = new long[r.length + 1];
        Arrays.fill(dp, (long) 1e18); dp[0] = 0;

        for (var f : factory) {
            for (var j = r.length; j > 0; j--) {
                var cost = 0L;
                for (var k = 1; k <= Math.min(j, f[1]); ++k) {
                    cost += Math.abs(r[j - k] - f[0]);
                    dp[j] = Math.min(dp[j], dp[j - k] + cost);
                }
            }
        }

        return dp[r.length];
    }

    public static void main(String[] args) {
        // Robot            9      11                   99      101
        // Factory:     7      10       14          96      100     103
        assert new Solution().minimumTotalDistance(List.of(9,11,99,101), new int[][]{{10,1},{7,1},{14,1},{100,1},{96,1},{103,1}}) == 6;

        assert new Solution().minimumTotalDistance(List.of(0,4,6), new int[][]{{2,2},{6,2}}) == 4;
    }

}
