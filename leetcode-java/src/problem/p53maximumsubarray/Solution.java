package problem.p53maximumsubarray;

/**
 * 53. Maximum Subarray
 *
 * https://leetcode-cn.com/problems/maximum-subarray/
 *
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 */

public class Solution {

    public int maxSubArray(int[] nums) {
        int max = nums[0];
        for (int l = 0; l < nums.length; l++) {
            int curr = nums[l];
            if (curr > max) {
                max = curr;
            }

            for (int r = l + 1; r < nums.length; r++) {
                curr += nums[r];
                if (curr > max) {
                    max = curr;
                }
            }
        }
        return max;
    }

    public int maxSubArray1(int[] nums) {
        int curr = 0, max = nums[0];
        for (var n : nums) {
            curr = Math.max(curr + n, n);
            max = Math.max(curr, max);
        }
        return max;
    }

    public static void main(String[] args) {
        assert new Solution().maxSubArray(new int[]{0}) == 0;
        assert new Solution().maxSubArray(new int[]{1}) == 1;
        assert new Solution().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}) == 6;
        assert new Solution().maxSubArray(new int[]{5,4,-1,7,8}) == 23;
        assert new Solution().maxSubArray(new int[]{-2,1}) == 1;

        assert new Solution().maxSubArray1(new int[]{0}) == 0;
        assert new Solution().maxSubArray1(new int[]{1}) == 1;
        assert new Solution().maxSubArray1(new int[]{-2,1,-3,4,-1,2,1,-5,4}) == 6;
        assert new Solution().maxSubArray1(new int[]{5,4,-1,7,8}) == 23;
        assert new Solution().maxSubArray1(new int[]{-2,1}) == 1;
    }

}
