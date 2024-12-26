package weekly.bw146.A;

/**
 * 3392. Count Subarrays of Length Three With a Condition
 *
 * https://leetcode.cn/contest/biweekly-contest-146/problems/count-subarrays-of-length-three-with-a-condition/
 *
 * Given an integer array nums, return the number of subarrays of length 3 such that
 * the sum of the first and third numbers equals exactly half of the second number.
 */

public class Solution {

    public int countSubarrays(int[] nums) {
        int ans = 0;
        for (int l = 0, r = 2; r < nums.length; l++, r++) {
            if (2 * (nums[l] + nums[r]) == nums[l + 1]) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
