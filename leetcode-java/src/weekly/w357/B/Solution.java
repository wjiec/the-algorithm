package weekly.w357.B;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2811. Check if it is Possible to Split Array
 *
 * https://leetcode.cn/contest/weekly-contest-357/problems/check-if-it-is-possible-to-split-array/
 *
 * You are given an array nums of length n and an integer m. You need to determine
 * if it is possible to split the array into n non-empty arrays by performing a series of steps.
 *
 * In each step, you can select an existing array (which may be the result of previous steps)
 * with a length of at least two and split it into two subarrays, if, for each resulting
 * subarray, at least one of the following holds:
 *
 * The length of the subarray is one, or
 * The sum of elements of the subarray is greater than or equal to m.
 * Return true if you can split the given array into n arrays, otherwise return false.
 *
 * Note: A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public boolean canSplitArray(List<Integer> nums, int m) {
        int sum = 0;
        for (var v : nums) sum += v;
        return canSplit(nums, m, sum, 0, nums.size() - 1);
    }

    private Map<Integer, Boolean> memo = new HashMap<>();

    private boolean canSplit(List<Integer> nums, int m, int sum, int l, int r) {
        if (r - l <= 1) return true;

        int key = (l << 16) | r;
        if (!memo.containsKey(key)) {
            boolean ans = false;
            if (sum - nums.get(l) >= m) {
                ans = canSplit(nums, m, sum - nums.get(l), l + 1, r);
            }
            if (!ans && sum - nums.get(r) >= m) {
                ans = canSplit(nums, m, sum - nums.get(r), l, r - 1);
            }

            memo.put(key, ans);
        }
        return memo.get(key);
    }

    public static void main(String[] args) {
    }

}
