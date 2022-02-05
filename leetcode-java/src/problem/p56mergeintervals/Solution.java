package problem.p56mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. Merge Intervals
 *
 * https://leetcode-cn.com/problems/merge-intervals/
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 */

public class Solution {

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));

        int start = intervals[0][0], end = intervals[0][1];
        List<int[]> list = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            if (end < intervals[i][0]) {
                list.add(new int[]{start, end});
                start = intervals[i][0];
            }
            end = Math.max(end, intervals[i][1]);
        }

        list.add(new int[]{start, end});
        return list.toArray(new int[][]{});
    }

    public static void main(String[] args) {
        assert Arrays.deepEquals(new Solution().merge(new int[][]{
            {1, 4}, {2, 3}
        }), new int[][]{
            {1, 4}
        });

        assert Arrays.deepEquals(new Solution().merge(new int[][]{
            {1, 3}, {2, 6}, {8, 10}, {15, 18}
        }), new int[][]{
            {1, 6}, {8, 10}, {15, 18}
        });

        assert Arrays.deepEquals(new Solution().merge(new int[][]{
            {1, 4}, {4, 5}
        }), new int[][]{
            {1, 5}
        });
    }

}
