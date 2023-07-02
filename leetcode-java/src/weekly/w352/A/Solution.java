package weekly.w352.A;

/**
 * 6909. Longest Even Odd Subarray With Threshold
 *
 * https://leetcode.cn/contest/weekly-contest-352/problems/longest-even-odd-subarray-with-threshold/
 *
 * You are given a 0-indexed integer array nums and an integer threshold.
 *
 * Find the length of the longest subarray of nums starting at index l and ending
 * at index r (0 <= l <= r < nums.length) that satisfies the following conditions:
 *
 * nums[l] % 2 == 0
 * For all indices i in the range [l, r - 1], nums[i] % 2 != nums[i + 1] % 2
 * For all indices i in the range [l, r], nums[i] <= threshold
 * Return an integer denoting the length of the longest such subarray.
 *
 * Note: A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0 && nums[i] <= threshold) {
                ans = Math.max(ans, 1);
                for (int j = i + 1; j < n; j++) {
                    if (nums[j] > threshold || (nums[j] % 2) == (nums[j - 1] % 2)) break;
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
