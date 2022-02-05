package problem.p57insertinterval;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. Insert Interval
 *
 * https://leetcode-cn.com/problems/insert-interval/
 *
 * You are given an array of non-overlapping intervals where intervals[i] = [starti, endi] represent
 * the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 *
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals
 * still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 */

public class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        boolean inserted = false;
        List<int[]> list = new ArrayList<>();
        int l = newInterval[0], r = newInterval[1];
        for (var interval : intervals) {
            if (r < interval[0]) {
                if (!inserted) {
                    list.add(new int[]{l, r});
                    inserted = true;
                }
                list.add(interval);
            } else if (l > interval[1]) {
                list.add(interval);
            } else {
                l = Math.min(l, interval[0]);
                r = Math.max(r, interval[1]);
            }
        }

        if (!inserted) list.add(new int[]{l, r});
        return list.toArray(new int[][]{});
    }


    public static void main(String[] args) {
        assert Checker.check(new Solution().insert(new int[][]{
            {1, 3}, {6, 9}
        }, new int[]{2, 5}), new int[][]{
            {1, 5}, {6, 9}
        });

        assert Checker.check(new Solution().insert(new int[][]{
            {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}
        }, new int[]{4, 8}), new int[][]{
            {1, 2}, {3, 10}, {12, 16}
        });

        assert Checker.check(new Solution().insert(new int[][]{
            // empty
        }, new int[]{5, 7}), new int[][]{
            {5, 7}
        });

        assert Checker.check(new Solution().insert(new int[][]{
            {1, 5}
        }, new int[]{2, 3}), new int[][]{
            {1, 5}
        });

        assert Checker.check(new Solution().insert(new int[][]{
            {1, 5}
        }, new int[]{2, 7}), new int[][]{
            {1, 7}
        });
    }

}
