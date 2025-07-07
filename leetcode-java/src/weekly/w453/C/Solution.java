package weekly.w453.C;

import java.util.TreeMap;

/**
 * Q3. Count Partitions With Max-Min Difference at Most K
 *
 * https://leetcode.cn/contest/weekly-contest-453/problems/count-partitions-with-max-min-difference-at-most-k
 *
 * You are given an integer array nums and an integer k. Your task is to partition nums
 * into one or more non-empty contiguous segments such that in each segment, the difference
 * between its maximum and minimum elements is at most k.
 *
 * Return the total number of ways to partition nums under this condition.
 *
 * Since the answer may be too large, return it modulo 1e9 + 7.
 */

public class Solution {

    private final static int MOD = 1_000_000_007;
    private static Integer sum(int a, int b) { return a + b == 0 ? null : (a + b); }

    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        // dp[r] 表示在 [0, r) 的方案数有多少
        long[] dp = new long[n + 1]; dp[0] = 1;
        // sum[i] 表示在 dp 中的前缀和
        long[] sum = new long[dp.length + 1]; sum[1] = dp[0];

        // 枚举使用 [l, r) 作为一个可行的分割 (这里需要进行优化)
        //  - 每次我们从 l = r - 1 开始倒着枚举
        //      - 如果在 [l, r) 范围内的 max - min <= k
        //      - 则我们得到一个 [0, l) + [l, r) 可行的分配方案, 也就是 dp[l]
        //  - 枚举所有符合条件的 l, 将其加起来, 最终得到的是一个从 dp[l] + ... + dp[r - 1] 的前缀和
        //      - 对于 dp 的前缀和来说 dp[l] + ... + dp[r - 1] = sum[r - 1 + 1] - sum[l]
        //                                                  = sum[r] - sum[l]
        //
        //  - 对于每个 r 我们都需要进行相似的检查, 所以我们直接记录一个区间, 这个区间中的最大值 - 最小值 <= k
        //      - 每次枚举时, 我们将 nums[r - 1] 加入到区间中, 然后移动 l, 使得区间满足要求
        int l = 0; TreeMap<Integer, Integer> m = new TreeMap<>();

        // 枚举右侧位置 r
        for (int r = 1; r <= nums.length; r++) {
            // 如果当前数字加入到由前面数字组成的组后, 最大值最小值的 > k 了, 则说明需要移动位置 l
            m.merge(nums[r - 1], 1, Solution::sum);
            while (m.lastKey() - m.firstKey() > k) {
                m.merge(nums[l++], -1, Solution::sum);
            }

            // 此时我们找到了最左侧满足条件的第一个位置 l, 我们需要计算 sum(dp[l] + ... + dp[r - 1])
            //  - 也就是 sum[r - 1 + 1] - sum[l] = sum[r] - sum[l]
            dp[r] = (sum[r] - sum[l] + MOD) % MOD;

            // 最后需要维护前缀和
            sum[r + 1] = (sum[r] + dp[r]) % MOD;
        }

        return (int) dp[nums.length];
    }

    public static void main(String[] args) {
        assert new Solution().countPartitions(new int[]{9,4,1,3,7}, 4) == 6;
        assert new Solution().countPartitions(new int[]{3,3,4}, 0) == 2;
    }

}
