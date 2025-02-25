package weekly.w435.C;

import java.util.*;

/**
 * 3444. Minimum Increments for Target Multiples in an Array
 *
 * https://leetcode.cn/contest/weekly-contest-435/problems/minimum-increments-for-target-multiples-in-an-array/
 *
 * You are given two arrays, nums and target.
 *
 * In a single operation, you may increment any element of nums by 1.
 *
 * Return the minimum number of operations required so that each element
 * in target has at least one multiple in nums.
 */

public class Solution {

    // target 最多只有 4 个
    public int minimumIncrements(int[] nums, int[] target) {
        // 直接 dfs 枚举每个数变为 target[i] 的倍数的最小增量
        Arrays.sort(nums); Arrays.sort(target);
        return (int) dfs(nums, 0, target, (1 << target.length) - 1);
    }

    private final Map<Integer, Long> memo = new HashMap<>();

    private long dfs(int[] nums, int i, int[] target, int mask) {
        if (mask == 0) return 0;
        if (i == nums.length) return Integer.MAX_VALUE;

        int key = (mask << 24) | i;
        if (!memo.containsKey(key)) {
            // 跳过当前位
            long ans = dfs(nums, i + 1, target, mask);
            // 让当前位变成 target[mask] 的倍数
            for (var next : move(target, mask, nums[i])) {
                if (next[0] >= ans) continue;
                ans = Math.min(ans, next[0] + dfs(nums, i + 1, target, next[1]));
            }

            memo.put(key, ans);
        }

        return memo.get(key);
    }

    // [[cost, nextMask]]
    private List<int[]> move(int[] target, int mask, int curr) {
        List<int[]> ans = new ArrayList<>();
        for (int j = 0; j < target.length; j++) {
            if ((mask & (1 << j)) != 0) {
                // 使得 curr 满足 target[j] 的倍数
                int cost = (target[j] - (curr % target[j])) % target[j], nextMask = mask & ~(1 << j);
                // 检查是否还额外满足了其他的 j
                for (int k = 0; k < target.length; k++) {
                    if (k != j && (mask & (1 << k)) != 0 && (curr + cost) % target[k] == 0) {
                        nextMask &= ~(1 << k);
                    }
                }
                ans.add(new int[]{cost, nextMask});
            }
        }
        return ans;
    }

    private static class Iteration {
        public int minimumIncrements(int[] nums, int[] target) {
            int mask = 1 << target.length;
            // dp[i][j] 表示使用前 i 个元素满足 j 集合的最小代价
            int[][] dp = new int[nums.length + 1][mask];
            Arrays.fill(dp[0], 1_000_000_007); dp[0][0] = 0;
            for (int i = 1; i <= nums.length; i++) {
                int curr = nums[i - 1];

                // 预计算出 curr 满足 target[j] 的代价
                int[] cost = new int[target.length];
                for (int j = 0; j < target.length; j++) {
                    cost[j] = (target[j] - (curr % target[j])) % target[j];
                }

                // 枚举使得 curr 满足 j 的最小代价
                for (int j = 1; j < mask; j++) {
                    // 计算使 curr 满足 j 的最小代价
                    dp[i][j] = dp[i - 1][j];
                    // 现在 j 可能由 1, 2, ... target.length 个数组成, 我们需要找到最小的那个方案
                    for (int k = 0; k < target.length; k++) {
                        if ((j & (1 << k)) != 0 && cost[k] < dp[i][j]) {
                            // 计算选择当前值可以覆盖的所有倍数
                            int cover = 0, value = curr + cost[k];
                            for (int jj = 0; jj < target.length; jj++) {
                                if (value % target[jj] == 0 && (j & (1 << jj)) != 0) cover |= 1 << jj;
                            }

                            dp[i][j] = Math.min(dp[i][j], cost[k] + dp[i - 1][j ^ cover]);
                        }
                    }
                }
            }

            return dp[nums.length][mask - 1];
        }
    }

    public static void main(String[] args) {
        assert new Iteration().minimumIncrements(new int[]{4, 101}, new int[]{10, 5}) == 6;
        assert new Iteration().minimumIncrements(new int[]{8, 9, 10}, new int[]{6, 6, 10}) == 3;
        assert new Iteration().minimumIncrements(new int[]{1, 9}, new int[]{10, 5}) == 1;
        assert new Iteration().minimumIncrements(new int[]{1, 2, 3}, new int[]{4}) == 1;
        assert new Iteration().minimumIncrements(new int[]{8, 4}, new int[]{10, 5}) == 2;
        assert new Iteration().minimumIncrements(new int[]{7, 9, 10}, new int[]{7}) == 0;

        assert new Solution().minimumIncrements(new int[]{8, 9, 10}, new int[]{6, 6, 10}) == 3;
        assert new Solution().minimumIncrements(new int[]{4, 101}, new int[]{10, 5}) == 6;
        assert new Solution().minimumIncrements(new int[]{1, 9}, new int[]{10, 5}) == 1;
        assert new Solution().minimumIncrements(new int[]{1, 2, 3}, new int[]{4}) == 1;
        assert new Solution().minimumIncrements(new int[]{8, 4}, new int[]{10, 5}) == 2;
        assert new Solution().minimumIncrements(new int[]{7, 9, 10}, new int[]{7}) == 0;
    }

}
