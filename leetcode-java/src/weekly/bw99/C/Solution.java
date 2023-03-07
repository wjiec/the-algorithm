package weekly.bw99.C;

import ability.Ability;

import java.util.Arrays;

/**
 * 2580. Count Ways to Group Overlapping Ranges
 *
 * https://leetcode.cn/contest/biweekly-contest-99/problems/count-ways-to-group-overlapping-ranges/
 *
 * You are given a 2D integer array ranges where ranges[i] = [starti, endi] denotes that
 * all integers between starti and endi (both inclusive) are contained in the ith range.
 *
 * You are to split ranges into two (possibly empty) groups such that:
 *
 * Each range belongs to exactly one group.
 * Any two overlapping ranges must belong to the same group.
 * Two ranges are said to be overlapping if there exists at least one
 * integer that is present in both ranges.
 *
 * For example, [1, 3] and [2, 5] are overlapping because 2 and 3 occur in both ranges.
 * Return the total number of ways to split ranges into two groups. Since the answer
 * may be very large, return it modulo 109 + 7.
 */

public class Solution {

    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        int c = 0, r = -1;
        for (var range : ranges) {
            if (range[0] > r) {
                c++; r = range[1];
            } else r = Math.max(r, range[1]);
        }

        return (int) Ability.Math.pow(2, c, 1_000_000_007);
    }

    public static void main(String[] args) {
        assert new Solution().countWays(new int[][]{{0,1}}) == 2;
        assert new Solution().countWays(new int[][]{{5,11},{20,22},{1,3},{21,22},{11,11}}) == 8;
    }

}
