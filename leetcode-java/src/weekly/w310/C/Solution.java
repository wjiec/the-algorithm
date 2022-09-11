package weekly.w310.C;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 6178. Divide Intervals Into Minimum Number of Groups
 *
 * https://leetcode.cn/contest/weekly-contest-310/problems/divide-intervals-into-minimum-number-of-groups/
 *
 * You are given a 2D integer array intervals where intervals[i] = [lefti, righti]
 * represents the inclusive interval [lefti, righti].
 *
 * You have to divide the intervals into one or more groups such that each interval is in
 * exactly one group, and no two intervals that are in the same group intersect each other.
 *
 * Return the minimum number of groups you need to make.
 *
 * Two intervals intersect if there is at least one common number between them.
 * For example, the intervals [1, 5] and [5, 8] intersect.
 */

public class Solution {

    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (var curr : intervals) {
            Integer prev = map.lowerKey(curr[0]);
            if (prev != null) {
                map.merge(prev, 1, (old, val) -> old - val == 0 ? null : old - val);
            }
            map.merge(curr[1], 1, Integer::sum);
        }

        int ans = 0;
        for (var v : map.values()) ans += v;
        return ans;
    }

    public static void main(String[] args) {
    }

}
