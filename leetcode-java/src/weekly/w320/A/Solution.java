package weekly.w320.A;

/**
 * 6241. Number of Unequal Triplets in Array
 *
 * https://leetcode.cn/contest/weekly-contest-320/problems/number-of-unequal-triplets-in-array/
 *
 * You are given a 0-indexed array of positive integers nums.
 * Find the number of triplets (i, j, k) that meet the following conditions:
 *
 * 0 <= i < j < k < nums.length
 * nums[i], nums[j], and nums[k] are pairwise distinct.
 * In other words, nums[i] != nums[j], nums[i] != nums[k], and nums[j] != nums[k].
 *
 * Return the number of triplets that meet the conditions.
 */

public class Solution {

    public int unequalTriplets(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] != nums[j] && nums[j] != nums[k] && nums[i] != nums[k]) ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
