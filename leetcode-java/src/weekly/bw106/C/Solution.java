package weekly.bw106.C;

import java.util.Arrays;

/**
 * 2731. Movement of Robots
 *
 * https://leetcode.cn/contest/biweekly-contest-106/problems/movement-of-robots/
 *
 * Some robots are standing on an infinite number line with their initial coordinates given
 * by a 0-indexed integer array nums and will start moving once given the command to move.
 *
 * The robots will move a unit distance each second.
 *
 * You are given a string s denoting the direction in which robots will move on command. 'L' means
 * the robot will move towards the left side or negative side of the number line, whereas 'R' means
 * the robot will move towards the right side or positive side of the number line.
 *
 * If two robots collide, they will start moving in opposite directions.
 *
 * Return the sum of distances between all the pairs of robots d seconds after the command.
 * Since the sum can be very large, return it modulo 109 + 7.
 *
 * Note:
 *
 * For two robots at the index i and j, pair (i,j) and pair (j,i) are considered the same pair.
 * When robots collide, they instantly change their directions without wasting any time.
 * Collision happens when two robots share the same place in a moment.
 *
 * For example, if a robot is positioned in 0 going to the right and another is positioned in 2
 * going to the left, the next second they'll be both in 1 and they will change direction and the
 * next second the first one will be in 0, heading left, and another will be in 2, heading right.
 *
 * For example, if a robot is positioned in 0 going to the right and another is positioned in 1
 * going to the left, the next second the first one will be in 0, heading left, and another will be
 * in 1, heading right.
 */

public class Solution {

    public int sumDistance(int[] nums, String s, int d) {
        long[] after = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            after[i] = nums[i] + (s.charAt(i) == 'L' ? -1 : 1) * d;
        }
        Arrays.sort(after);

        long ans = 0, sum = 0;
        for (int i = 0; i < after.length; i++) {
            ans = (ans + i * after[i] - sum) % 1_000_000_007;
            sum += after[i];
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().sumDistance(new int[]{-2, 0, 2}, "RLL", 3) == 8;
    }

}
