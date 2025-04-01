package weekly.w441.B;

import common.Checker;

import java.util.*;

/**
 * 3488. Closest Equal Element Queries
 *
 * https://leetcode.cn/contest/weekly-contest-441/problems/closest-equal-element-queries/
 *
 * You are given a circular array nums and an array queries.
 *
 * For each query i, you have to find the following:
 *
 * The minimum distance between the element at index queries[i] and any other index j
 * in the circular array, where nums[j] == nums[queries[i]]. If no such index exists,
 * the answer for that query should be -1.
 *
 * Return an array answer of the same size as queries, where answer[i] represents the result for query i.
 */

public class Solution {

    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int i = 0; i < n; i++) m.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);

        List<Integer> ans = new ArrayList<>();
        for (var query : queries) {
            List<Integer> list = m.get(nums[query]);
            if (list.size() != 1) {
                int idx = Collections.binarySearch(list, query);

                int curr = list.get(idx);
                int prev = list.get((idx - 1 + list.size()) % list.size());
                int next = list.get((idx + 1 + list.size()) % list.size());
                ans.add(Math.min((curr - prev + n) % n, (next + n - curr) % n));
            } else ans.add(-1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().solveQueries(new int[]{1,3,1,4,1,3,2}, new int[]{0,3,5}), List.of(2,-1,3));
    }

}
