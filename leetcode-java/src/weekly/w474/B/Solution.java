package weekly.w474.B;

import java.util.Arrays;

/**
 * Q2. Maximum Product of Three Elements After One Replacement
 *
 * https://leetcode.cn/contest/weekly-contest-474/problems/maximum-product-of-three-elements-after-one-replacement/
 *
 * You are given an integer array nums.
 *
 * You must replace exactly one element in the array with any integer value in the range [-105, 105] (inclusive).
 *
 * After performing this single replacement, determine the maximum possible
 * product of any three elements at distinct indices from the modified array.
 *
 * Return an integer denoting the maximum product achievable.
 */

public class Solution {

    public long maxProduct(int[] nums) {
        // 从中选 3 个元素使得乘积最大
        //  - 要么就是两个最小的负数, 然后再找个数变成 1e5
        //  - 要么就是两个最大的正数, 然后再找个数变成 1e5
        //  - 要么就是最大的正数加上最小负数, 然后再找个数变成 -1e5
        Arrays.sort(nums);

        int n = nums.length, v = (int) 1e5;
        long n1 = nums[0], n2 = nums[1], p1 = nums[n - 1], p2 = nums[n - 2];
        return Math.max(n1 * n2 * v, Math.max(p1 * p2 * v, n1 * p1 * -v));
    }

    public static void main(String[] args) {
        assert new Solution().maxProduct(new int[]{-100000, -100000, -100000}) == 1000000000000000L;
    }

}
