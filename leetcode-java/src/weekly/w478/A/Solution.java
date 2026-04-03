package weekly.w478.A;

import java.util.Arrays;

/**
 * Q1. Count Elements With at Least K Greater Values
 *
 * https://leetcode.cn/contest/weekly-contest-478/problems/count-elements-with-at-least-k-greater-values/
 *
 * You are given an integer array nums of length n and an integer k.
 *
 * An element in nums is said to be qualified if there exist at least k elements
 * in the array that are strictly greater than it.
 *
 * Return an integer denoting the total number of qualified elements in nums.
 */

public class Solution {

    public int countElements(int[] nums, int k) {
        Arrays.sort(nums); int ans = 0;
        // r 是第一个大于 nums[l] 的位置
        for (int l = 0, r = 0; l < nums.length; l++) {
            while (r < nums.length && nums[r] <= nums[l]) r++;
            if (nums.length - r >= k) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
