package problem.p986intervallistintersections;

import common.Checker;
import common.TODO;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. Interval List Intersections
 *
 * https://leetcode.cn/problems/interval-list-intersections/
 *
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and
 * secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 *
 * The intersection of two closed intervals is a set of real numbers that are either empty or
 * represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 */

public class Solution {

    @TODO
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> ans = new ArrayList<>();
        for (int a = 0, b = 0; a < firstList.length && b < secondList.length; ) {
            int lo = Math.max(firstList[a][0], secondList[b][0]);
            int hi = Math.min(firstList[a][1], secondList[b][1]);
            if (lo <= hi) ans.add(new int[]{lo, hi});

            if (firstList[a][1] < secondList[b][1]) a++;
            else b++;
        }
        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        Checker.check(new Solution().intervalIntersection(new int[][]{
            {0,2},{5,10},{13,23},{24,25}
        }, new int[][]{
            {1,5},{8,12},{15,24},{25,26}
        }), new int[][]{
            {1,2},{5,5},{8,10},{15,23},{24,24},{25,25}
        });

        Checker.check(new Solution().intervalIntersection(new int[][]{
            {1,3},{5,9}
        }, new int[][]{}), new int[][]{});
    }

}
