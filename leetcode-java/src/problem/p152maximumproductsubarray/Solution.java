package problem.p152maximumproductsubarray;

/**
 * 152. Maximum Product Subarray
 *
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 *
 * Given an integer array nums, find a contiguous non-empty subarray within the array
 * that has the largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 */

public class Solution {

    public int maxProduct(int[] nums) {
        int[] maxDp = new int[nums.length];
        int[] minDp = new int[nums.length];

        System.arraycopy(nums, 0, maxDp, 0, nums.length);
        System.arraycopy(nums, 0, minDp, 0, nums.length);

        for (int i = 1; i < nums.length; i++) {
            maxDp[i] = Math.max(maxDp[i - 1] * nums[i], Math.max(nums[i], minDp[i - 1] * nums[i]));
            minDp[i] = Math.min(minDp[i - 1] * nums[i], Math.min(nums[i], maxDp[i - 1] * nums[i]));
        }

        int ans = maxDp[0];
        for (int i = 0; i < nums.length; i++) ans = Math.max(ans, maxDp[i]);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxProduct(new int[]{2,3,-2,4}) == 6;
        assert new Solution().maxProduct(new int[]{-2,0,-1}) == 0;
    }

}
