package weekly.w410.C;

/**
 * 3250. Find the Count of Monotonic Pairs I
 *
 * https://leetcode.cn/contest/weekly-contest-410/problems/find-the-count-of-monotonic-pairs-i/
 *
 * You are given an array of positive integers nums of length n.
 *
 * We call a pair of non-negative integer arrays (arr1, arr2) monotonic if:
 *
 * The lengths of both arrays are n.
 * arr1 is monotonically non-decreasing, in other words, arr1[0] <= arr1[1] <= ... <= arr1[n - 1].
 * arr2 is monotonically non-increasing, in other words, arr2[0] >= arr2[1] >= ... >= arr2[n - 1].
 * arr1[i] + arr2[i] == nums[i] for all 0 <= i <= n - 1.
 *
 * Return the count of monotonic pairs.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 */

public class Solution {

    public int countOfPairs(int[] nums) {
        final long MOD = 1_000_000_007;

        // dp[i] 表示前一位置的 arr1 为 i 时的的方案数
        long[] dp = new long[51];
        for (int v = 0; v <= nums[0]; v++) dp[v]++;

        for (int i = 1; i < nums.length; i++) {
            long[] curr = new long[dp.length];

            // 枚举当前位选择 v1 的方案数
            for (int v1 = 0; v1 < dp.length && v1 <= nums[i]; v1++) {
                // 计算对应的 arr2 位置的值
                int v2 = nums[i] - v1;

                // 可以从前一位为 j 的方案转移过来
                for (int j = 0; j <= v1 && j <= nums[i - 1]; j++) {
                    // 对于 arr2 来说, 前一位的值需要 >= 当前位的值
                    if (nums[i - 1] - j >= v2) {
                        // nums[i - 1] - nums[i] - v1 >= j
                        curr[v1] = (curr[v1] +dp[j]) % MOD;
                    } else break;
                }
            }

            dp = curr;
        }

        int ans = 0;
        for (var v : dp) ans = (int) ((ans + v) % MOD);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countOfPairs(new int[]{2,3,2}) == 4;
        assert new Solution().countOfPairs(new int[]{5,5,5,5}) == 126;
    }

}
