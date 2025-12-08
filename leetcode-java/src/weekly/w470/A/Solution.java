package weekly.w470.A;

/**
 * Q1. Compute Alternating Sum
 *
 * https://leetcode.cn/contest/weekly-contest-470/problems/compute-alternating-sum/
 *
 * You are given an integer array nums.
 *
 * The alternating sum of nums is the value obtained by adding elements at even
 * indices and subtracting elements at odd indices.
 *
 * That is, nums[0] - nums[1] + nums[2] - nums[3]...
 * Return an integer denoting the alternating sum of nums.
 */

public class Solution {

    public int alternatingSum(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += ((i & 1) == 0 ? 1 : -1) * nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
