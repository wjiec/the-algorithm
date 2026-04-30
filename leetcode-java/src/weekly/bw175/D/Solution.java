package weekly.bw175.D;

import common.TODO;
import common.Tag;

import java.util.Arrays;

/**
 * Q4. Minimum Partition Score
 *
 * https://leetcode.cn/contest/biweekly-contest-175/problems/minimum-partition-score/
 *
 * You are given an integer array nums and an integer k.
 *
 * Your task is to partition nums into exactly k subarrays and return an integer denoting
 * the minimum possible score among all valid partitions.
 *
 * The score of a partition is the sum of the values of all its subarrays.
 *
 * The value of a subarray is defined as sumArr * (sumArr + 1) / 2, where sumArr is the sum of its elements.
 */

public class Solution {

    public long minPartitionScore(int[] nums, int k) {
        // 每个元素最多 1e4, 最多 1e3 个元素, 也就是和为 1e7, s * (s + 1) / 2 ~= 1e14
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + nums[i];
        // 子数组的得分是 sum * (sum + 1) / 2 = (sum ^ 2 + sum) / 2, 需要最小化这个得分
        //
        // 枚举最右端的位置 r 和 k' 表示从 [0, r] 组成 k 个子数组的最小得分是多少
        //  - 如果自己单独作为一个子数组, 则是 dp[r - 1][k' - 1] + v * (v + 1) / 2
        //  - 如果并入之前的子数组, 则需要枚举子数组的左侧端点位置
        //      - dp[l - 1][k - 1] + (s * (s + 1) / 2)
        //  - 找到以上所有答案的最小值
        long[][] dp = new long[k][n];
        for (int i = 0; i < n; i++) dp[0][i] = sum[i + 1] * (sum[i + 1] + 1) / 2;
        // 枚举子数组数量
        for (int j = 1; j < k; j++) {
            // 枚举右端点位置 r. 由于至少要有 j 个子数组, 也就是下标是从 j 开始的
            for (int r = j; r < n; r++) {
                dp[j][r] = Long.MAX_VALUE;
                // 这里是优化的重点, see Optimization::minPartitionScore
                for (int l = r; l >= j; l--) {
                    long s = sum[r + 1] - sum[l];
                    dp[j][r] = Math.min(dp[j][r], dp[j - 1][l - 1] + (s * (s + 1) / 2));
                }
            }
        }

        return dp[k - 1][n - 1];
    }

    private static class Optimization {
        private record Vec(long x, long y) {
            public Vec sub(Vec v) { return new Vec(x - v.x, y - v.y); }
            public long dot(Vec v) { return x * v.x + y * v.y; }
            public long det(Vec v) { return x * v.y - y * v.x; }
        }

        @TODO
        @Tag({"斜率优化DP", "凸包/点积/投影"})
        public long minPartitionScore(int[] nums, int k) {
            // 在状态转移当中, 包含了一些既包含 i 又包含 j 的乘法的项, 我们无法通过移项来提取同侧的参数
            //  - 如果有单调性的话, 那么可以优化到 O(n), 否则可以优化到 O(NlogN)

            int n = nums.length;
            long[] sum = new long[n + 1];
            for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + nums[i];

            long[] dp = new long[n + 1];
            Arrays.fill(dp, Long.MAX_VALUE >> 1); dp[0] = 0;

            Vec[] vecs = new Vec[n + 1];
            for (int j = 1; j <= k; j++) {
                int head = 0, tail = 0; long s = sum[j - 1];
                vecs[tail++] = new Vec(s, dp[j - 1] + s * s - s);

                for (int i = j; i <= n - (k - j); i++) {
                    s = sum[i]; Vec p = new Vec(-2 * s, 1);
                    while (tail - head > 1 && p.dot(vecs[head]) >= p.dot(vecs[head + 1])) head++;

                    Vec v = new Vec(s, dp[i] + s * s - s);
                    dp[i] = p.dot(vecs[head]) + s * s + s;
                    while (tail - head > 1 && vecs[tail - 1].sub(vecs[tail - 2]).det(v.sub(vecs[tail - 1])) <= 0) tail--;
                    vecs[tail++] = v;
                }
            }

            return dp[n] >> 1;
        }
    }

    public static void main(String[] args) {
        assert new Optimization().minPartitionScore(new int[]{5,39,4,32}, 2) == 1656;
        assert new Optimization().minPartitionScore(new int[]{5,1,2,1}, 2) == 25;
        assert new Optimization().minPartitionScore(new int[]{1,2,3,4}, 1) == 55;
        assert new Optimization().minPartitionScore(new int[]{1,1,1}, 3) == 3;

        assert new Solution().minPartitionScore(new int[]{5,39,4,32}, 2) == 1656;

        assert new Solution().minPartitionScore(new int[]{5,1,2,1}, 2) == 25;
        assert new Solution().minPartitionScore(new int[]{1,2,3,4}, 1) == 55;
        assert new Solution().minPartitionScore(new int[]{1,1,1}, 3) == 3;
    }

}
