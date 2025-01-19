package weekly.w431.D;

import common.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 3414. Maximum Score of Non-overlapping Intervals
 *
 * https://leetcode.cn/contest/weekly-contest-431/problems/maximum-score-of-non-overlapping-intervals/
 *
 * You are given a 2D integer array intervals, where intervals[i] = [li, ri, weighti].
 * Interval i starts at position li and ends at ri, and has a weight of weighti.
 * You can choose up to 4 non-overlapping intervals. The score of the chosen intervals is defined
 * as the total sum of their weights.
 *
 * Return the lexicographically smallest array of at most 4 indices from intervals with
 * maximum score, representing your choice of non-overlapping intervals.
 *
 * Two intervals are said to be non-overlapping if they do not share any points.
 * In particular, intervals sharing a left or right boundary are considered overlapping.
 */

public class Solution {

    private record Interval(int l, int r, int w, int i) {}
    private record State(long w, List<Integer> idx) {
        public State move(long w, int i) {
            List<Integer> currIdx = new ArrayList<>(idx);
            currIdx.add(i); currIdx.sort(Integer::compare);
            return new State(this.w + w, currIdx);
        }
        private static int compare(List<Integer> a, List<Integer> b) {
            for (int i = 0; i < Math.max(a.size(), b.size()); i++) {
                if (i >= a.size()) return -1;
                if (i >= b.size()) return 1;
                if (a.get(i) < b.get(i)) return -1;
                if (b.get(i) < a.get(i)) return 1;
            }
            return 0;
        }
        private static State min(State a, State b) {
            if (a.w != b.w) return a.w > b.w ? a : b;
            return compare(a.idx, b.idx) < 0 ? a : b;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        // 按照右端点排序, 对于每一个区间可以选或不选, 如果选的话我们从小于当前区间左端点的最大位置转移
        Interval[] list = new Interval[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            var curr = intervals.get(i);
            list[i] = new Interval(curr.get(0), curr.get(1), curr.get(2), i);
        }
        Arrays.sort(list, Comparator.comparingInt(i -> i.r));

        // dp[i][j] 表示使用前 i 个区间且区间索引长度为 j 的方案
        State[][] dp = new State[intervals.size() + 1][5];
        for (var row : dp) Arrays.setAll(row, i -> new State(0, new ArrayList<>()));
        for (int i = 0; i < list.length; i++) {
            var curr = list[i];
            // 找到小于当前区间左端点的最大值
            var found = binarySearch(list, curr.l, i);

            // 枚举所选择的区间数量
            for (int j = 4; j > 0; j--) {
                dp[i + 1][j] = State.min(dp[i][j], dp[found + 1][j - 1].move(curr.w, curr.i));
            }
        }

        var ans = dp[intervals.size()][4].idx;
        int[] converted = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) converted[i] = ans.get(i);
        return converted;
    }

    // 在 intervals 的 [0, upper) 范围内中找到 elem.r < target 的最大下标位置
    private int binarySearch(Interval[] intervals, int target, int upper) {
        int l = -1, r = upper;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (intervals[mid].r < target) l = mid;
            else r = mid;
        }
        return l;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().maximumWeight(List.of(List.of(5,8,1),List.of(6,7,7),List.of(4,7,3),List.of(9,10,6),List.of(7,8,2),List.of(11,14,3),List.of(3,5,5))), new int[]{1, 3, 5, 6});
        assert Checker.check(new Solution().maximumWeight(List.of(List.of(1,3,2),List.of(4,5,2),List.of(1,5,5),List.of(6,9,3),List.of(6,7,1),List.of(8,9,1))), new int[]{2, 3});
    }

}
