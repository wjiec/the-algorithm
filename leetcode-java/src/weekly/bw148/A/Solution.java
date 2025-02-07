package weekly.bw148.A;

/**
 * 3423. Maximum Difference Between Adjacent Elements in a Circular Array
 *
 * https://leetcode.cn/contest/biweekly-contest-148/problems/maximum-difference-between-adjacent-elements-in-a-circular-array/
 *
 * Given a circular array nums, find the maximum absolute difference between adjacent elements.
 *
 * Note: In a circular array, the first and last elements are adjacent.
 */

public class Solution {

    public int maxAdjacentDistance(int[] nums) {
        int ans = Math.abs(nums[0] - nums[nums.length - 1]);
        for (int i = 1; i < nums.length; i++) {
            ans = Math.max(ans, Math.abs(nums[i] - nums[i - 1]));
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
