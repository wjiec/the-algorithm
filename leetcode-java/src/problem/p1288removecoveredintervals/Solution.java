package problem.p1288removecoveredintervals;

import java.util.Arrays;

/**
 * 1288. Remove Covered Intervals
 *
 * https://leetcode.cn/problems/remove-covered-intervals/
 *
 * Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove
 * all intervals that are covered by another interval in the list.
 *
 * The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
 *
 * Return the number of remaining intervals.
 */

public class Solution {

    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        int ans = 0, last = -1;
        for (var interval : intervals) {
            if (interval[1] > last) {
                last = interval[1]; ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().removeCoveredIntervals(new int[][]{{1,4},{3,6},{2,8}}) == 2;
        assert new Solution().removeCoveredIntervals(new int[][]{{1,4},{2,3}}) == 1;
    }

}
