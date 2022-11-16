package offer2.p74;

import common.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 剑指 Offer II 074. 合并区间
 *
 * https://leetcode.cn/problems/SsGoHC/
 *
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 */

public class Solution {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));

        int left = intervals[0][0], right = intervals[0][0];
        List<int[]> ans = new ArrayList<>();
        for (var interval : intervals) {
            if (interval[0] > right) {
                ans.add(new int[]{left, right});
                left = interval[0]; right = interval[1];
            } else right = Math.max(right, interval[1]);
        }
        ans.add(new int[]{left, right});

        return ans.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().merge(new int[][]{{1,3},{2,6},{8,10},{15,18}}), new int[][]{{1,6},{8,10},{15,18}});
        assert Checker.check(new Solution().merge(new int[][]{{1,4},{4,5}}), new int[][]{{1,5}});
    }

}
