package weekly.w461.D;

import java.util.Arrays;

/**
 * Q4. Trionic Array II
 *
 * https://leetcode.cn/contest/weekly-contest-461/problems/trionic-array-ii/
 *
 * You are given an integer array nums of length n.
 *
 * A trionic subarray is a contiguous subarray nums[l...r] (with 0 <= l < r < n) for
 * which there exist indices l < p < q < r such that:
 *
 * nums[l...p] is strictly increasing,
 * nums[p...q] is strictly decreasing,
 * nums[q...r] is strictly increasing.
 *
 * Return the maximum sum of any trionic subarray in nums.
 */

public class Solution {

    public long maxSumTrionic(int[] nums) {
        int n = nums.length; final long INF = Long.MIN_VALUE;

        // 先找出所有的递增段, 将其按照 l 和 r 求出最大的和
        long[] lMax = new long[n], rMax = new long[n];
        Arrays.fill(lMax, INF); Arrays.fill(rMax, INF);
        for (int r = 1, l = 0; r <= n; r++) {
            if (r == n || nums[r] <= nums[r - 1]) {
                // 现在从 [l, r) 是严格递增的, 计算这一段的所有子数组和
                //  - 由于中间递减段是和递增段只重叠首尾位置, 所以递减段是必然要包含 r - 1 的
                // 我们需要从 l 开始计算一段最大值, 再从 r - 1 开始计算一段最大值
                long lSum = nums[l], rSum = nums[r - 1];
                for (int j = l + 1; j < r; j++) lMax[l] = Math.max(lMax[l], lSum += nums[j]);
                for (int j = r - 2; j >= l; j--) rMax[r - 1] = Math.max(rMax[r - 1], rSum += nums[j]);
                l = r;
            }
        }

        // 然后枚举中间递减段, 找到两侧递增段的最大值
        long ans = Long.MIN_VALUE, decreasingSum = 0;
        for (int r = 1, l = 0; r < n; r++) {
            if (nums[r] < nums[r - 1]) {
                decreasingSum += nums[r];
                // 现在从 [l, r] 都是严格递减的, 且这一段需要至少两个元素(默认已满足)
                if (lMax[r] != INF && rMax[l] != INF) {
                    ans = Math.max(ans, lMax[r] + rMax[l] + decreasingSum - nums[l] - nums[r]);
                }
            } else { l = r; decreasingSum = nums[r]; }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxSumTrionic(new int[]{0,-2,-1,-3,0,2,-1}) == -4;
        assert new Solution().maxSumTrionic(new int[]{1,4,2,7}) == 14;
    }

}
