package problem.p1732findthehighestaltitude;

/**
 * 1732. Find the Highest Altitude
 *
 * https://leetcode-cn.com/problems/find-the-highest-altitude/
 *
 * There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes.
 *
 * The biker starts his trip on point 0 with altitude equal 0.
 *
 * You are given an integer array gain of length n where gain[i] is the net gain in altitude
 * between points i and i + 1 for all (0 <= i < n). Return the highest altitude of a point.
 */

public class Solution {

    public int largestAltitude(int[] gain) {
        int curr = 0, ans = 0;
        for (var n : gain) ans = Math.max(ans, curr += n);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().largestAltitude(new int[]{-5,1,5,0,-7}) == 1;
        assert new Solution().largestAltitude(new int[]{-4,-3,-2,-1,4,3,2}) == 0;
    }

}
