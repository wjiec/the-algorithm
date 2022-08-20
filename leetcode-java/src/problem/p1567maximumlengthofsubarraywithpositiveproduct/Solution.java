package problem.p1567maximumlengthofsubarraywithpositiveproduct;

/**
 * 1567. Maximum Length of Subarray With Positive Product
 *
 * https://leetcode.cn/problems/maximum-length-of-subarray-with-positive-product/
 *
 * Given an array of integers nums, find the maximum length of a subarray where
 * the product of all its elements is positive.
 *
 * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 *
 * Return the maximum length of a subarray with positive product.
 */

public class Solution {

    public int getMaxLen(int[] nums) {
        int[] positive = new int[nums.length];
        int[] negative = new int[nums.length];
        if (nums[0] > 0) positive[0] = 1;
        else if (nums[0] < 0) negative[0] = 1;

        int ans = positive[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                positive[i] = 0;
                negative[i] = 0;
            } else if (nums[i] > 0) {
                positive[i] = positive[i - 1] + 1;
                if (negative[i - 1] > 0)
                    negative[i] = negative[i - 1] + 1;
            } else {
                if (negative[i - 1] > 0)
                    positive[i] = negative[i - 1] + 1;
                negative[i] = positive[i - 1] + 1;
            }

            ans = Math.max(ans, positive[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().getMaxLen(new int[]{-16,0,-5,2,2,-13,11,8}) == 6;

        assert new Solution().getMaxLen(new int[]{1,-2,-3,4}) == 4;
        assert new Solution().getMaxLen(new int[]{0,1,-2,-3,-4}) == 3;
        assert new Solution().getMaxLen(new int[]{-1,-2,-3,0,1}) == 2;
    }

}
