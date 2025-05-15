package weekly.w447.B;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 3532. Path Existence Queries in a Graph I
 *
 * https://leetcode.cn/contest/weekly-contest-447/problems/path-existence-queries-in-a-graph-i/
 *
 * You are given an integer n representing the number of nodes in a graph, labeled from 0 to n - 1.
 *
 * You are also given an integer array nums of length n sorted in non-decreasing order, and an integer maxDiff.
 *
 * An undirected edge exists between nodes i and j if the absolute difference between nums[i] and nums[j] is at
 * most maxDiff (i.e., |nums[i] - nums[j]| <= maxDiff).
 *
 * You are also given a 2D integer array queries. For each queries[i] = [ui, vi], determine whether there
 * exists a path between nodes ui and vi.
 *
 * Return a boolean array answer, where answer[i] is true if there exists a path between ui and vi
 * in the ith query and false otherwise.
 */

public class Solution {

    // 数组按非严格递增排序
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // 对于位置 i, 前后可以到 [l, r] 其中 |nums[l] - nums[i]| <= maxDiff 且 |nums[r] - nums[i]| <= maxDiff
        //  - 只要两个相邻位置不断开, 那么就可以一直沿着路线走下去, 否则就是断开的
        List<Integer> cuts = new ArrayList<>();
        cuts.add(0);
        // 找到所有的断开位置, [cuts[i], cuts[i + 1]) 是一个区间
        for (int i = 1; i < n; i++) {
            if (Math.abs(nums[i] - nums[i - 1]) > maxDiff) {
                cuts.add(i);
            }
        }
        cuts.add(n);

        // 检查所有的 query 是否跨区间了
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            // 保证 u <= v
            int u = queries[i][0], v = queries[i][1];
            if (u > v) { int t = u; u = v; v = t; }

            int li = binarySearch(cuts, u);
            int l = cuts.get(li), r = cuts.get(li + 1);
            ans[i] = l <= v && v < r;
        }

        return ans;
    }

    // 找到最后一个 <= target 的值
    private int binarySearch(List<Integer> array, int target) {
        int l = 0, r = array.size();
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (array.get(mid) <= target) l = mid;
            else r = mid;
        }
        return l;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().pathExistenceQueries(2, new int[]{1, 3}, 1, new int[][]{{0,0},{0,1}}), new boolean[]{true, false});
    }

}
