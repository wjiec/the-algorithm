package weekly.w356.B;

import java.util.HashSet;
import java.util.Set;

/**
 * 2799. Count Complete Subarrays in an Array
 *
 * https://leetcode.cn/contest/weekly-contest-356/problems/count-complete-subarrays-in-an-array/
 *
 * You are given an array nums consisting of positive integers.
 *
 * We call a subarray of an array complete if the following condition is satisfied:
 *
 * The number of distinct elements in the subarray is equal to the number of distinct elements in the whole array.
 * Return the number of complete subarrays.
 *
 * A subarray is a contiguous non-empty part of an array.
 */

public class Solution {

    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> full = new HashSet<>();
        for (var v: nums) full.add(v);

        int ans = 0, n = nums.length;
        for (int i = 0; i <= n - full.size(); i++) {
            Set<Integer> curr = new HashSet<>();
            for (int j = i; j < n; j++) {
                if (curr.add(nums[j]) && curr.size() == full.size()) {
                    ans += n - j;
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
