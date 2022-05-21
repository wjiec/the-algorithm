package problem.p849maximizedistancetoclosestperson;

/**
 * 849. Maximize Distance to Closest Person
 *
 * https://leetcode.cn/problems/maximize-distance-to-closest-person/
 *
 * You are given an array representing a row of seats where seats[i] = 1 represents
 * a person sitting in the ith seat, and seats[i] = 0 represents that the ith seat is empty (0-indexed).
 *
 * There is at least one empty seat, and at least one person sitting.
 *
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 *
 * Return that maximum distance to the closest person.
 */

public class Solution {

    public int maxDistToClosest(int[] seats) {
        int ans = 0, idx = 0;
        for (; idx < seats.length; idx++) {
            if (seats[idx] == 0) ans++;
            else break;
        }

        int prev = idx;
        for (; idx < seats.length; idx++) {
            if (seats[idx] == 1) {
                ans = Math.max(ans, (idx - prev) / 2);
                prev = idx;
            }
        }
        ans = Math.max(ans, (seats.length - 1 - prev));
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxDistToClosest(new int[]{1,0,0,0,1,0,1}) == 2;
        assert new Solution().maxDistToClosest(new int[]{1,0,0,0}) == 3;
        assert new Solution().maxDistToClosest(new int[]{0,1}) == 1;
        assert new Solution().maxDistToClosest(new int[]{0,0,1}) == 2;
    }

}
