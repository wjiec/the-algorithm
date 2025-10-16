package weekly.w465.C;

/**
 * Q3. Maximum Product of Two Integers With No Common Bits
 *
 * https://leetcode.cn/contest/weekly-contest-465/problems/maximum-product-of-two-integers-with-no-common-bits/
 *
 * You are given an integer array nums.
 *
 * Your task is to find two distinct indices i and j such that the
 * product nums[i] * nums[j] is maximized, and the binary representations of
 * nums[i] and nums[j] do not share any common set bits.
 *
 * Return the maximum possible product of such a pair. If no such pair exists, return 0.
 */

public class Solution {

    public long maxProduct(int[] nums) {
        int mx = 0;
        for (var v : nums) mx = Math.max(mx, v);

        int w = 32 - Integer.numberOfLeadingZeros(mx);
        // dp[i] 表示 i 以及它所有子集的最大值
        int[] dp = new int[mx = 1 << w];
        for (var v : nums) dp[v] = v;

        // 从小到大枚举所有集合
        for (int i = 0; i < mx; i++) {
            // 枚举当前集合中的所有元素
            for (int j = 0; j < w; j++) {
                if ((i & (1 << j)) != 0) {
                    dp[i] = Math.max(dp[i], dp[i ^ (1 << j)]);
                }
            }
        }

        long ans = 0;
        for (var v : nums) ans = Math.max(ans, (long) v * dp[(mx - 1) ^ v]);
        return ans;
    }

    public static void main(String[] args) {
    }

}
