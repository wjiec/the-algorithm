package weekly.w433.A;

/**
 * 3427. Sum of Variable Length Subarrays
 *
 * https://leetcode.cn/contest/weekly-contest-433/problems/sum-of-variable-length-subarrays/
 *
 * You are given an integer array nums of size n. For each index i where 0 <= i < n, define
 * a subarray nums[start ... i] where start = max(0, i - nums[i]).
 *
 * Return the total sum of all elements from the subarray defined for each index in the array.
 */

public class Solution {

    public int subarraySum(int[] nums) {
        int[] pre = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += pre[i + 1] - pre[Math.max(0, i - nums[i])];
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
