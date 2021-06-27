package weekly.w247.p0p5797maximumproductdifferencebetweentwopairs;

import java.util.Arrays;

/**
 * 5797. Maximum Product Difference Between Two Pairs
 *
 * https://leetcode-cn.com/contest/weekly-contest-247/problems/maximum-product-difference-between-two-pairs/
 *
 * The product difference between two pairs (a, b) and (c, d) is defined as (a * b) - (c * d).
 *
 * For example, the product difference between (5, 6) and (2, 7) is (5 * 6) - (2 * 7) = 16.
 * Given an integer array nums, choose four distinct indices w, x, y,
 * and z such that the product difference between pairs (nums[w], nums[x]) and (nums[y], nums[z]) is maximized.
 *
 * Return the maximum such product difference.
 */

public class Solution {

    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        return (nums[nums.length - 1] * nums[nums.length - 2]) - (nums[0] * nums[1]);
    }

    public static void main(String[] args) {
        assert new Solution().maxProductDifference(new int[]{5,6,2,7,4}) == 34;
        assert new Solution().maxProductDifference(new int[]{4,2,5,9,7,4,8}) == 64;
    }

}
