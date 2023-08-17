package weekly.w358.C;

import java.util.List;
import java.util.TreeMap;

/**
 * 2817. Minimum Absolute Difference Between Elements With Constraint
 *
 * https://leetcode.cn/contest/weekly-contest-358/problems/minimum-absolute-difference-between-elements-with-constraint/
 *
 * You are given a 0-indexed integer array nums and an integer x.
 *
 * Find the minimum absolute difference between two elements in the array that are at least x indices apart.
 *
 * In other words, find two indices i and j such that abs(i - j) >= x and abs(nums[i] - nums[j]) is minimized.
 *
 * Return an integer denoting the minimum absolute difference between two elements that are at least x indices apart.
 */

public class Solution {

    public int minAbsoluteDifference(List<Integer> nums, int x) {
        TreeMap<Integer, Integer> right = new TreeMap<>();
        for (int i = x; i < nums.size(); i++) {
            right.merge(nums.get(i), 1, Solution::sum);
        }

        int ans = Integer.MAX_VALUE;
        TreeMap<Integer, Integer> left = new TreeMap<>();
        for (int i = 0; i < nums.size(); i++) {
            ans = Math.min(ans, min(left, nums.get(i)));
            ans = Math.min(ans, min(right, nums.get(i)));
            if (ans == 0) return 0;

            if (i + x < nums.size()) right.merge(nums.get(i + x), -1, Solution::sum);
            if (i - x >= 0) left.merge(nums.get(i - x), 1, Solution::sum);
        }

        return ans;
    }

    private int min(TreeMap<Integer, Integer> m, int v) {
        if (m.containsKey(v)) return 0;

        Integer lower = m.lowerKey(v), upper = m.higherKey(v);
        return Math.min(
            lower == null ? Integer.MAX_VALUE : (v - lower),
            upper == null ? Integer.MAX_VALUE : (upper - v)
        );
    }

    public static Integer sum(Integer a, Integer b) { return a + b == 0 ? null : (a + b); }

    public static void main(String[] args) {
        assert new Solution().minAbsoluteDifference(List.of(4,3,2,4), 2) == 0;
        assert new Solution().minAbsoluteDifference(List.of(5,3,2,10,15), 1) == 1;
        assert new Solution().minAbsoluteDifference(List.of(1,2,3,4), 3) == 3;
    }

}
