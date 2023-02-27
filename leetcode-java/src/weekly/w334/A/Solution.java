package weekly.w334.A;

/**
 * 2574. Left and Right Sum Differences
 *
 * https://leetcode.cn/problems/left-and-right-sum-differences/
 *
 * Given a 0-indexed integer array nums, find a 0-indexed integer array answer where:
 *
 * answer.length == nums.length.
 * answer[i] = |leftSum[i] - rightSum[i]|.
 *
 * Where:
 *
 * leftSum[i] is the sum of elements to the left of the index i in the array nums.
 * If there is no such element, leftSum[i] = 0.
 *
 * rightSum[i] is the sum of elements to the right of the index i in the array nums.
 * If there is no such element, rightSum[i] = 0.
 *
 * Return the array answer.
 */

public class Solution {

    public int[] leftRigthDifference(int[] nums) {
        int[] left = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            left[i] += left[i - 1] + nums[i - 1];
        }

        int[] right = new int[nums.length];
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] += right[i + 1] + nums[i + 1];
        }

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = Math.abs(left[i] - right[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
