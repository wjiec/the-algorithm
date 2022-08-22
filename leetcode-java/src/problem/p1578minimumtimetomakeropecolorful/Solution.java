package problem.p1578minimumtimetomakeropecolorful;

/**
 * 1578. Minimum Time to Make Rope Colorful
 *
 * https://leetcode.cn/problems/minimum-time-to-make-rope-colorful/
 *
 * Alice has n balloons arranged on a rope. You are given a 0-indexed string colors
 * where colors[i] is the color of the ith balloon.
 *
 * Alice wants the rope to be colorful. She does not want two consecutive balloons
 * to be of the same color, so she asks Bob for help.
 *
 * Bob can remove some balloons from the rope to make it colorful.
 * You are given a 0-indexed integer array neededTime where neededTime[i] is
 * the time (in seconds) that Bob needs to remove the ith balloon from the rope.
 *
 * Return the minimum time Bob needs to make the rope colorful.
 */

public class Solution {

    public int minCost(String colors, int[] neededTime) {
        int ans = 0, n = neededTime.length;
        for (int i = 0; i < n; ) {
            int sum = 0, max = 0;
            char color = colors.charAt(i);
            while (i < n && colors.charAt(i) == color) {
                sum += neededTime[i];
                max = Math.max(max, neededTime[i]);
                i++;
            }
            ans += sum - max;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minCost("abaac", new int[]{1,2,3,4,5}) == 3;
        assert new Solution().minCost("abc", new int[]{1,2,3}) == 0;
        assert new Solution().minCost("aabaa", new int[]{1,2,3,4,1}) == 2;
    }

}
