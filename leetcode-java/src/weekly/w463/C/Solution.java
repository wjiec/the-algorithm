package weekly.w463.C;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Q3. Minimum Sum After Divisible Sum Deletions
 *
 * https://leetcode.cn/contest/weekly-contest-463/problems/minimum-sum-after-divisible-sum-deletions/
 *
 * You are given an integer array nums and an integer k.
 *
 * You may repeatedly choose any contiguous subarray of nums whose sum is divisible by k and delete it;
 * after each deletion, the remaining elements close the gap.
 *
 * Return the minimum possible sum of nums after performing any number of such deletions.
 */

public class Solution {

    public long minArraySum(int[] nums, int k) {
        int n = nums.length;

        // dp[i] 表示在 [0, i] 范围内删除可整除和后的最小数组和
        long[] dp = new long[n + 1];
        // 如果前缀和对于 k 取模有相同的余数, 则说明在这个区间内的元素之和是可以被 k 整除的
        long[] preSum = new long[n + 1];
        // 对于每次遍历的位置 i, 我们需要找到一个下标 j 使得 preSum[j] == preSum[i] 且 dp[j] 最小
        Map<Integer, Long> minMod = new HashMap<>(); minMod.put(0, 0L);
        for (int i = 1; i <= n; i++) {
            // 计算到当前位置时的前缀和与 mod k 的值
            preSum[i] = preSum[i - 1] + nums[i - 1];
            int modK = (int) (preSum[i] % k);

            // 现在我们要找到 preSum 中在 [0, i) 范围内与 modK 相同且 dp 最小的位置 j
            //  - 也就是删除 [j, i) 这个子数组, 剩余的最小和就是 dp[j]
            // 如果找不到, 则就是前一个的最小值
            dp[i] = Math.min(dp[i - 1] + nums[i - 1], minMod.getOrDefault(modK, Long.MAX_VALUE));

            minMod.put(modK, dp[i]);
        }

        return dp[n];
    }

    private static class Optimization {
        public long minArraySum(int[] nums, int k) {
            int sum = 0; long ans = 0;
            long[] minF = new long[k];
            Arrays.fill(minF, Long.MAX_VALUE); minF[0] = 0;
            for (var v : nums) {
                sum = (sum + v) % k;
                ans = Math.min(ans + v, minF[sum]);
                minF[sum] = ans;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Optimization().minArraySum(new int[]{46,86,81,59,48,76,82,92,74,35,36,32,62,64,35,6}, 4) == 6;
        assert new Optimization().minArraySum(new int[]{1,1,1}, 2) == 1;
        assert new Optimization().minArraySum(new int[]{3,1,4,1,5}, 3) == 5;

        assert new Solution().minArraySum(new int[]{46,86,81,59,48,76,82,92,74,35,36,32,62,64,35,6}, 4) == 6;
        assert new Solution().minArraySum(new int[]{1,1,1}, 2) == 1;
        assert new Solution().minArraySum(new int[]{3,1,4,1,5}, 3) == 5;
    }

}
