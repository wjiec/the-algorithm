package weekly.w368.A;

/**
 * 2908. Minimum Sum of Mountain Triplets I
 *
 * https://leetcode.cn/contest/weekly-contest-368/problems/minimum-sum-of-mountain-triplets-i/
 *
 * You are given a 0-indexed array nums of integers.
 *
 * A triplet of indices (i, j, k) is a mountain if:
 *
 * i < j < k
 * nums[i] < nums[j] and nums[k] < nums[j]
 * Return the minimum possible sum of a mountain triplet of nums. If no such triplet exists, return -1.
 */

public class Solution {

    public int minimumSum(int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] < nums[j] && nums[j] > nums[k]) {
                        ans = Math.min(ans, nums[i] + nums[j] + nums[k]);
                    }
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
    }

}
