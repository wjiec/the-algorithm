package problem.p335selfcrossing;

/**
 * 335. Self Crossing
 *
 * https://leetcode.cn/problems/self-crossing/
 *
 * You are given an array of integers distance.
 *
 * You start at the point (0, 0) on an X-Y plane, and you move distance[0] meters
 * to the north, then distance[1] meters to the west, distance[2] meters to the
 * south, distance[3] meters to the east, and so on. In other words, after
 * each move, your direction changes counter-clockwise.
 *
 * Return true if your path crosses itself or false if it does not.
 */

public class Solution {

    public boolean isSelfCrossing(int[] distance) {
        for (int i = 3; i < distance.length; i++) {
            if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]) {
                return true;
            }

            if (i == 4 && (distance[3] == distance[1] && distance[4] >= distance[2] - distance[0])) {
                return true;
            }

            if (i >= 5 && (
                distance[i - 3] - distance[i - 5] <= distance[i - 1]
                && distance[i - 1] <= distance[i - 3]
                && distance[i] >= distance[i - 2] - distance[i - 4]
                && distance[i - 2] > distance[i - 4]
            )) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().isSelfCrossing(new int[]{2,1,1,2});
        assert !new Solution().isSelfCrossing(new int[]{1,2,3,4});
        assert new Solution().isSelfCrossing(new int[]{1,1,1,1});
    }

}
