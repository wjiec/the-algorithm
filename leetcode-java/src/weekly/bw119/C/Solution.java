package weekly.bw119.C;

import java.util.HashMap;
import java.util.Map;

/**
 * 2958. Length of Longest Subarray With at Most K Frequency
 *
 * https://leetcode.cn/contest/biweekly-contest-119/problems/length-of-longest-subarray-with-at-most-k-frequency/
 *
 * You are given an integer array nums and an integer k.
 *
 * The frequency of an element x is the number of times it occurs in an array.
 *
 * An array is called good if the frequency of each element in this array is less than or equal to k.
 *
 * Return the length of the longest good subarray of nums.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int maxSubarrayLength(int[] nums, int k) {
        int ans = 0, n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int l = 0, r = 0; r < n; r++) {
            map.merge(nums[r], 1, Integer::sum);
            while (map.getOrDefault(nums[r], 0) > k) {
                map.merge(nums[l++], -1, (a, b) -> a + b == 0 ? null : (a + b));
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
