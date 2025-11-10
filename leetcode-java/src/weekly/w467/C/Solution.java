package weekly.w467.C;

import java.util.Arrays;

/**
 * Q3. Subsequence Sum After Capping Elements
 *
 * https://leetcode.cn/contest/weekly-contest-467/problems/subsequence-sum-after-capping-elements/
 *
 * You are given an integer array nums of size n and a positive integer k.
 *
 * An array capped by value x is obtained by replacing every element nums[i] with min(nums[i], x).
 *
 * For each integer x from 1 to n, determine whether it is possible to choose a subsequence
 * from the array capped by x such that the sum of the chosen elements is exactly k.
 *
 * Return a 0-indexed boolean array answer of size n, where answer[i] is true
 * if it is possible when using x = i + 1, and false otherwise.
 */

public class Solution {

    public boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
        int n = nums.length;
        // ans[i] 表示是否可以通过在经过处理后找到一个子序列的和为 k
        //  - 处理操作: 将数组中的所有位置 j 修改为 min(nums[j], i)
        boolean[] ans = new boolean[n];

        // 由于我们只需要选择子序列, 所以我们可以对数组进行排序
        Arrays.sort(nums);

        // dp[i] 表示是否能组合出和为 i 的子序列, 不选元素时和为 0
        boolean[] dp = new boolean[k + 1]; dp[0] = true;
        // 枚举当前的最大值 x 以及当前在数组中 x 出现的位置
        for (int i = 0, x = 1; x <= n; x++) {
            // 考虑所有恰好等于 x 的元素, 所有小于 x 的数之前已经考虑过了
            for (; i < n && nums[i] == x; i++) {
                for (int j = k; j >= nums[i]; j--) {
                    dp[j] = dp[j] || dp[j - x];
                }
            }

            // 枚举选择了 j 个 x
            for (int j = 0; j <= Math.min(n - i, k / x); j++) {
                if (dp[k - j * x]) {
                    ans[x - 1] = true;
                    break;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
