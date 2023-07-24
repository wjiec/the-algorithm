package weekly.w354.B;

import java.util.Arrays;

/**
 * 2779. Maximum Beauty of an Array After Applying Operation
 *
 * https://leetcode.cn/contest/weekly-contest-354/problems/maximum-beauty-of-an-array-after-applying-operation/
 *
 * You are given a 0-indexed array nums and a non-negative integer k.
 *
 * In one operation, you can do the following:
 *
 * Choose an index i that hasn't been chosen before from the range [0, nums.length - 1].
 * Replace nums[i] with any integer from the range [nums[i] - k, nums[i] + k].
 * The beauty of the array is the length of the longest subsequence consisting of equal elements.
 *
 * Return the maximum possible beauty of the array nums after applying the operation any number of times.
 *
 * Note that you can apply the operation to each index only once.
 *
 * A subsequence of an array is a new array generated from the original array by deleting
 * some elements (possibly none) without changing the order of the remaining elements.
 */

public class Solution {

    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);

        int ans = 1, n = nums.length;
        for (int l = 0, r = 0, v = 0; v < nums[n - 1]; v++) {
            while (r < n && nums[r] <= v + k) r++;
            while (l < r && nums[l] < v - k) l++;
            ans = Math.max(ans, r - l);
        }
        return ans;
    }

}
