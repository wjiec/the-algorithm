package problem.p436findrightinterval;

import common.Checker;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 436. Find Right Interval
 *
 * https://leetcode-cn.com/problems/find-right-interval/
 *
 * You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
 *
 * The right interval for an interval i is an interval j such that startj >= endi and startj is minimized.
 *
 * Note that i may equal j.
 *
 * Return an array of right interval indices for each interval i.
 * If no right interval exists for interval i, then put -1 at index i.
 */

public class Solution {

    public int[] findRightInterval(int[][] intervals) {
        int[][] sort = new int[intervals.length][2];
        for (int i = 0; i < intervals.length; i++) {
            sort[i][0] = i;
            sort[i][1] = intervals[i][0];
        }
        Arrays.sort(sort, Comparator.comparingInt(v -> v[1]));

        int[] ans = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            ans[i] = find(sort, intervals[i][1]);
        }
        return ans;
    }

    private int find(int[][] sort, int k) {
        int l = 0, r = sort.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (sort[mid][1] == k) return sort[mid][0];
            else if (sort[mid][1] > k) r = mid;
            else l = mid + 1;
        }
        return l < sort.length && sort[l][1] >= k ? sort[l][0] : -1;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findRightInterval(new int[][]{{1,2}}), new int[]{-1});
        assert Checker.check(new Solution().findRightInterval(new int[][]{{3,4},{2,3},{1,2}}), new int[]{-1,0,1});
        assert Checker.check(new Solution().findRightInterval(new int[][]{{1,4},{2,3},{3,4}}), new int[]{-1,2,-1});
    }

}
