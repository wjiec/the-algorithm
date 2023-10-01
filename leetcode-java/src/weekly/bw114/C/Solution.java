package weekly.bw114.C;

/**
 * 100019. Split Array Into Maximum Number of Subarrays
 *
 * https://leetcode.cn/contest/biweekly-contest-114/problems/split-array-into-maximum-number-of-subarrays/
 *
 * You are given an array nums consisting of non-negative integers.
 *
 * We define the score of subarray nums[l..r] such that l <= r as nums[l] AND
 * nums[l + 1] AND ... AND nums[r] where AND is the bitwise AND operation.
 *
 * Consider splitting the array into one or more subarrays such that the following conditions are satisfied:
 *
 * Each element of the array belongs to exactly one subarray.
 * The sum of scores of the subarrays is the minimum possible.
 * Return the maximum number of subarrays in a split that satisfies the conditions above.
 *
 * A subarray is a contiguous part of an array.
 */

public class Solution {

    public int maxSubarrays(int[] nums) {
        int sum = nums[0];
        for (int num : nums) sum &= num;
        if (sum != 0) return 1;

        int ans = 0, curr = -1;
        for (var num : nums) {
            if (curr == -1) curr = num;
            else curr &= num;
            if (curr == 0) {
                ans++;
                curr = -1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
