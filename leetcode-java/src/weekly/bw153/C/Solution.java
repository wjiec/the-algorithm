package weekly.bw153.C;

import common.PrettyPrinter;

/**
 * 3500. Minimum Cost to Divide Array Into Subarrays
 *
 * https://leetcode.cn/contest/biweekly-contest-153/problems/minimum-cost-to-divide-array-into-subarrays/
 *
 * You are given two integer arrays, nums and cost, of the same size, and an integer k.
 *
 * You can divide nums into subarrays. The cost of the ith subarray consisting of elements nums[l..r] is:
 *
 * (nums[0] + nums[1] + ... + nums[r] + k * i) * (cost[l] + cost[l + 1] + ... + cost[r]).
 * Note that i represents the order of the subarray: 1 for the first subarray, 2 for the second, and so on.
 *
 * Return the minimum total cost possible from any valid division.
 */

public class Solution {

    // (nums[0] + nums[1] + ... + nums[r] + k * i) * (cost[l] + cost[l + 1] + ... + cost[r])
    public long minimumCost(int[] nums, int[] cost, int k) {
        // (acc[r + 1] + k * i) * (costAcc[r + 1] - costAcc[l])

        int n = nums.length;
        long[] acc = new long[n + 1];
        long[] costAcc = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            acc[i] = acc[i - 1] + nums[i - 1];
            costAcc[i] = costAcc[i - 1] + cost[i - 1];
        }

        // dp[i] = [sum, tot_k] 表示从 [0, i) 的最小代价以及已经累加的 k
        long[][] dp = new long[n + 1][2];

        // 枚举右端点 r, 使用 r 作为当前子数组
        for (int r = 1; r <= n; r++) {
            dp[r][0] = Long.MAX_VALUE;

            // 枚举左端点所使用的位置
            for (int l = r; l > 0; l--) {
                long curr = dp[l - 1][0] + (acc[r] + dp[l - 1][1] + k) * (costAcc[r] - costAcc[l - 1]);
                if (curr < dp[r][0] || (curr == dp[r][0] && dp[l - 1][1] + k < dp[r][1])) {
                    dp[r][0] = curr; dp[r][1] = dp[l - 1][1] + k;
                }
            }
        }

        PrettyPrinter.println(dp);

        return dp[n][0];
    }

    public static void main(String[] args) {
        assert new Solution().minimumCost(new int[]{3,1,4}, new int[]{4,6,6}, 1) == 110;
        assert new Solution().minimumCost(new int[]{4,8,5,1,14,2,2,12,1}, new int[]{7,2,8,4,2,2,1,1,2}, 7) == 985;
    }

}
