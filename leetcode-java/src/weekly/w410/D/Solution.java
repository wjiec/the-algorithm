package weekly.w410.D;

import java.util.Arrays;

/**
 * 3251. Find the Count of Monotonic Pairs II
 *
 * https://leetcode.cn/contest/weekly-contest-410/problems/find-the-count-of-monotonic-pairs-ii/
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

    private static final long MOD = 1_000_000_007;

    private static class BIT {
        private final long[] tree;
        public BIT(int n) { tree = new long[n + 3]; }
        public void reset() { Arrays.fill(tree, 0); }

        public void update(int k, long v) {
            for (k++; k < tree.length; k += lowbit(k)) {
                tree[k] = (tree[k] + v + MOD) % MOD;
            }
        }

        public long query(int k) {
            long ans = 0;
            for (k++; k > 0; k -= lowbit(k)) {
                ans = (ans + tree[k]) % MOD;
            }
            return ans;
        }

        public static int lowbit(int v) { return v & -v; }
    }

    public int countOfPairs(int[] nums) {
        int max = 0;
        for (var v : nums) max = Math.max(max, v);

        // dp[i] 表示前一位置的 arr1 为 i 时的的方案数
        BIT[] bits = new BIT[]{new BIT(max), new BIT(max)};
        for (int v = 0; v <= nums[0]; v++) bits[0].update(v, 1);

        for (int i = 1; i < nums.length; i++) {
            BIT curr = bits[i & 1], prev = bits[(i & 1) ^ 1];
            curr.reset();

            // 枚举当前位选择 v1 的方案数
            for (int v1 = 0; v1 <= nums[i]; v1++) {
                // 可以从前一位为 j 的方案转移过来
                // 前一位可选的右边界需要满足以下条件
                int right = Math.min(v1, nums[i - 1]);
                // 前一位同时需要满足以下条件
                // nums[i - 1] - j >= nums[i] - v1
                // nums[i - 1] >= nums[i] - v1 + j
                // nums[i - 1] - nums[i] + v1 >= j
                right = Math.min(right, nums[i - 1] - nums[i] + v1);

                curr.update(v1, prev.query(right));
            }
        }

        return (int) (bits[(nums.length & 1) ^ 1].query(max + 1) % MOD);
    }

    public static void main(String[] args) {
        assert new Solution().countOfPairs(new int[]{2,3,2}) == 4;
        assert new Solution().countOfPairs(new int[]{5,5,5,5}) == 126;
    }

}
