package weekly.w446.C;

import common.Checker;

/**
 * 3524. Find X Value of Array I
 *
 * https://leetcode.cn/contest/weekly-contest-446/problems/find-x-value-of-array-i/
 *
 * You are given an array of positive integers nums, and a positive integer k.
 *
 * You are allowed to perform an operation once on nums, where in each operation
 * you can remove any non-overlapping prefix and suffix from nums such that nums remains non-empty.
 *
 * You need to find the x-value of nums, which is the number of ways to perform this
 * operation so that the product of the remaining elements leaves a remainder of x when divided by k.
 *
 * Return an array result of size k where result[x] is the x-value of nums for 0 <= x <= k - 1.
 *
 * A prefix of an array is a subarray that starts from the beginning of the array and extends to any point within it.
 *
 * A suffix of an array is a subarray that starts at any point within the array and extends to the end of the array.
 *
 * Note that the prefix and suffix to be chosen for the operation can be empty.
 */

public class Solution {

    // 移除任意的前缀和后缀 -> 也就是找到一个子数组
    //  - ans[x] 表示子数组的乘积 % k == x 的数量
    public long[] resultArray(int[] nums, int k) {
        // 对于 nums[i] 可以选择加入 i - 1 组成的子数组中, 也可以选择自己单独作为一组
        long[] ans = new long[k];

        // dp[i][j] 表示在 [0, i) 范围内使用 i - 1 作为结尾的子数组中对 k 取模为 j 的方案数
        long[][] dp = new long[nums.length + 1][k];
        for (int i = 1; i <= nums.length; i++) {
            int curr = nums[i - 1] % k;
            // 当前元素自己作为一组
            dp[i][curr % k] = 1;

            // 刷表法枚举前一个元素位置的余数值
            for (int pj = 0; pj < k; pj++) {
                dp[i][(curr * pj) % k] += dp[i - 1][pj];
            }

            // 保存答案
            for (int j = 0; j < k; j++) ans[j] += dp[i][j];
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().resultArray(new int[]{1,2,3,4,5}, 3), new long[]{9,2,4});
        assert Checker.check(new Solution().resultArray(new int[]{1,2,4,8,16,32}, 4), new long[]{18,1,2,0});
        assert Checker.check(new Solution().resultArray(new int[]{1,1,2,1,1}, 2), new long[]{9,6});
    }

}
