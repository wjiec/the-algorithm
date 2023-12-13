package weekly.w375.C;

import java.util.ArrayList;
import java.util.List;

/**
 * 2962. Count Subarrays Where Max Element Appears at Least K Times
 *
 * https://leetcode.cn/contest/weekly-contest-375/problems/count-subarrays-where-max-element-appears-at-least-k-times/
 *
 * You are given an integer array nums and a positive integer k.
 *
 * Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.
 *
 * A subarray is a contiguous sequence of elements within an array.
 */

public class Solution {

    public long countSubarrays(int[] nums, int k) {
        int max = 0, n = nums.length;
        for (var v : nums) max = Math.max(max, v);

        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == max) index.add(i);
        }

        long ans = 0, prev = -1;
        for (int i = k - 1; i < index.size(); i++) {
            int l = index.get(i - k + 1), r = index.get(i);
            ans += (l - prev) * (n - r);
            prev = l;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
