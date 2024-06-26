package weekly.w403.C;

import java.util.Arrays;

/**
 * 3196. Maximize Total Cost of Alternating Subarrays
 *
 * https://leetcode.cn/contest/weekly-contest-403/problems/maximize-total-cost-of-alternating-subarrays/
 *
 * You are given an integer array nums with length n.
 *
 * The cost of a subarray nums[l..r], where 0 <= l <= r < n, is defined as:
 *
 * cost(l, r) = nums[l] - nums[l + 1] + ... + nums[r] * (−1)r − l
 *
 * Your task is to split nums into subarrays such that the total cost of the subarrays is maximized,
 * ensuring each element belongs to exactly one subarray.
 *
 * Formally, if nums is split into k subarrays, where k > 1, at indices i1, i2, ..., ik − 1,
 * where 0 <= i1 < i2 < ... < ik - 1 < n - 1, then the total cost will be:
 *
 * cost(0, i1) + cost(i1 + 1, i2) + ... + cost(ik − 1 + 1, n − 1)
 *
 * Return an integer denoting the maximum total cost of the subarrays after splitting the array optimally.
 *
 * Note: If nums is not split into subarrays, i.e. k = 1, the total cost is simply cost(0, n - 1).
 */

public class Solution {

    public long maximumTotalCost(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        long[] dp1 = new long[n]; // 作为第一个 l
        long[] dp2 = new long[n]; // 作为第一个 r
        long[] dp3 = new long[n]; // 作为最后一个奇数的 r 且不为第一个 r
        long[] dp4 = new long[n]; // 作为最后一个偶数的 r 且不为第一个 r
        Arrays.fill(dp1, Long.MIN_VALUE / 2);
        Arrays.fill(dp2, Long.MIN_VALUE / 2);
        Arrays.fill(dp3, Long.MIN_VALUE / 2);
        Arrays.fill(dp4, Long.MIN_VALUE / 2);
        dp1[0] = nums[0];

        for (int i = 1; i < n; i++) {
            // 如果当前项作为第一个 l, 则直接相加
            dp1[i] = max(dp1[i - 1], dp2[i - 1], dp3[i - 1], dp4[i - 1]) + nums[i];
            // 如果当前项作为第一个 r 的情况
            dp2[i] = dp1[i - 1] - nums[i];
            // 如果当前项作为最后一个奇数 r 且不为第一个 r 的情况
            if (i > 2) dp3[i] = dp4[i - 1] - nums[i];
            // 如果当前项作为最后一个偶数 r 且不为第一个 r 的情况
            if (i > 1) dp4[i] = max(dp2[i - 1] + 2L * nums[i - 1] + nums[i], dp2[i - 1] + nums[i]);
        }

        return max(dp1[n - 1], dp2[n - 1], dp3[n - 1], dp4[n - 1]);
    }

    private long max(long ...nums) {
        long ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > ans) ans = nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumTotalCost(new int[]{-3,2,-2,-5,-1}) == 1;

        // -3, -5 + 17 - 10 = -1
        assert new Solution().maximumTotalCost(new int[]{-3,-5,-17,-10}) == -1;

        assert new Solution().maximumTotalCost(new int[]{-937}) == -937;
        assert new Solution().maximumTotalCost(new int[]{-13, 3}) == -10;
    }

}
