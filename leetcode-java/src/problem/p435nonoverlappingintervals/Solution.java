package problem.p435nonoverlappingintervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. Non-overlapping Intervals
 *
 * https://leetcode-cn.com/problems/non-overlapping-intervals/
 *
 * Given an array of intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to
 * make the rest of the intervals non-overlapping.
 */

public class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[1]));

        int selected = 1, right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= right) {
                selected++;
                right = intervals[i][1];
            }
        }
        return intervals.length - selected;
    }

    public int eraseOverlapIntervalsDP(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));

        int ans = intervals.length;
        int[] dp = new int[intervals.length];
        for (int i = 0, n = intervals.length; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (intervals[j][1] <= intervals[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.min(ans, n - dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().eraseOverlapIntervals(new int[][]{{1, 100}, {11, 22}, {1, 11}, {2, 12}}) == 2;

        assert new Solution().eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}) == 1;
        assert new Solution().eraseOverlapIntervals(new int[][]{{1,2},{1,2},{1,2}}) == 2;
        assert new Solution().eraseOverlapIntervals(new int[][]{{1,2},{2,3}}) == 0;
    }

}
