package weekly.w475.D;

import common.Preposing;
import common.Tag;

/**
 * Q4. Maximize Cyclic Partition Score
 *
 * https://leetcode.cn/contest/weekly-contest-475/problems/maximize-cyclic-partition-score/
 *
 * You are given a cyclic array nums and an integer k.
 *
 * Partition nums into at most k subarrays. As nums is cyclic, these subarrays may wrap around
 * from the end of the array back to the beginning.
 *
 * The range of a subarray is the difference between its maximum and minimum values.
 * The score of a partition is the sum of subarray ranges.
 *
 * Return the maximum possible score among all cyclic partitions.
 */

public class Solution {

    @Tag({"股票交易", "子数组最大最小差值"})
    @Preposing(weekly.bw158.B.Solution.class)
    public long maximumScore(int[] nums, int k) {
        int n = nums.length;
        int[] dup = new int[n << 1];
        System.arraycopy(nums, 0, dup, 0, n);
        System.arraycopy(nums, 0, dup, n, n);

        // 对于某一种分割来说, 如果有
        //  - [..., max, ..., min, ...]: max 前面的数可以分给环形的前一个子数组, 不会影响 [max, min] 的分数
        //  - [..., min, ..., max, ...]: max 后面的数可以分给环形的后一个子数组, 同样不会影响 [min, max] 的分数
        // 所以我们可以以此为分界线将环形数组进行分割
        int max = 0, maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) max = nums[maxIndex = i];
        }

        return Math.max(
            maximumScore(dup, maxIndex, maxIndex + n, k), // max 位置作为左端点
            maximumScore(dup, maxIndex + 1, maxIndex + n + 1, k) // max位置作为右端点
        );
    }

    private static final long INF = Long.MIN_VALUE >> 1;
    private static final int NO_STOCK = 0, NORM_STOCK = 1, SHORT_STOCK = 2; // 0: 未持有股票, 1: 持有普通股票, 2: 持有做空股票

    // 计算 nums 在范围 [l, r) 中经过最多 k 次操作可以得到的最大得分
    private long maximumScore(int[] nums, int l, int r, int k) {
        long[][] dp = new long[k + 2][3]; dp[0][NO_STOCK] = INF;
        for (int j = 1; j <= k + 1; j++) dp[j][NORM_STOCK] = INF;

        // 枚举在当前位置执行任意操作后的最大得分
        for (int i = l; i < r; i++) {
            // 枚举完成操作的次数
            for (int j = k + 1; j > 0; j--) {
                // 未持有股票, 也就是卖出持有的普通股票并得到 nums[i] 的收益, 或者是在持有做空时去掉成本 nums[i]
                dp[j][NO_STOCK] = Math.max(dp[j][NO_STOCK], Math.max(dp[j][NORM_STOCK] + nums[i], dp[j][SHORT_STOCK] - nums[i]));
                // 持有普通股票只能是在未持有股票的情况下, 以当前的价格买入股票, 得分减少 nums[i]
                dp[j][NORM_STOCK] = Math.max(dp[j][NORM_STOCK], dp[j - 1][NO_STOCK] - nums[i]);
                // 持有做空股票只能是在未持有股票的情况下, 以当前的价格获得 nums[i] (做空交易是先加后减)
                dp[j][SHORT_STOCK] = Math.max(dp[j][SHORT_STOCK], dp[j - 1][NO_STOCK] + nums[i]);
            }
        }

        return dp[k + 1][0];
    }

    public static void main(String[] args) {
        // 0 1 2 3 4 5 6 7
        // 1 2 4 3 1 2 4 3
        assert new Solution().maximumScore(new int[]{1, 2, 4, 3}, 1) == 3;
        assert new Solution().maximumScore(new int[]{1, 2, 3, 3}, 2) == 3;
        assert new Solution().maximumScore(new int[]{1, 2, 3, 3}, 1) == 2;
        assert new Solution().maximumScore(new int[]{1, 2, 3, 3}, 4) == 3;
    }

}
