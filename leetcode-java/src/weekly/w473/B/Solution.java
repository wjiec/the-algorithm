package weekly.w473.B;

import java.util.Arrays;

/**
 * Q2. Maximum Alternating Sum of Squares
 *
 * https://leetcode.cn/contest/weekly-contest-473/problems/maximum-alternating-sum-of-squares/
 *
 * You are given an integer array nums. You may rearrange the elements in any order.
 *
 * The alternating score of an array arr is defined as:
 *
 * score = arr[0]2 - arr[1]2 + arr[2]2 - arr[3]2 + ...
 *
 * Return an integer denoting the maximum possible alternating score of nums after rearranging its elements.
 */

public class Solution {

    public long maxAlternatingSum(int[] nums) {
        // 偶数位置的数的平方 - 奇数位置的数的平方
        //  - 负数的平方和正数的平方一致
        for (int i = 0; i < nums.length; i++) nums[i] = Math.abs(nums[i]);
        // 直接排序
        Arrays.sort(nums);

        // 计算一共有多少个偶数位置以及奇数位置
        int n = nums.length, ec = (n + 1) / 2, oc = n - ec, i = n - 1; long ans = 0;
        for (; ec > 0; i--, ec--) ans += (long) nums[i] * nums[i];
        for (; oc > 0; i--, oc--) ans -= (long) nums[i] * nums[i];
        return ans;
    }

    public static void main(String[] args) {
    }

}
