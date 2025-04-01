package weekly.w441.A;

import java.util.HashSet;
import java.util.Set;

/**
 * 3487. Maximum Unique Subarray Sum After Deletion
 *
 * https://leetcode.cn/contest/weekly-contest-441/problems/maximum-unique-subarray-sum-after-deletion/
 *
 * You are given an integer array nums.
 *
 * You are allowed to delete any number of elements from nums without making it empty.
 *
 * After performing the deletions, select a subarray of nums such that:
 *
 * All elements in the subarray are unique.
 * The sum of the elements in the subarray is maximized.
 *
 * Return the maximum sum of such a subarray.
 */

public class Solution {

    public int maxSum(int[] nums) {
        int ans1 = Integer.MIN_VALUE;
        for (var v : nums) ans1 = Math.max(ans1, v);

        int ans2 = 0;
        Set<Integer> seen = new HashSet<>();
        for (var v : nums) {
            if (v > 0 && seen.add(v)) ans2 += v;
        }
        if (seen.isEmpty()) return ans1;
        return Math.max(ans1, ans2);
    }

    public static void main(String[] args) {
    }

}
