package weekly.w465.D;

import common.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q4. Sum of Beautiful Subsequences
 *
 * https://leetcode.cn/contest/weekly-contest-465/problems/sum-of-beautiful-subsequences/
 *
 * You are given an integer array nums of length n.
 *
 * For every positive integer g, we define the beauty of g as the product of g
 * and the number of strictly increasing subsequences of nums whose greatest
 * common divisor (GCD) is exactly g.
 *
 * Return the sum of beauty values for all positive integers g.
 *
 * Since the answer could be very large, return it modulo 1e9 + 7.
 */

public class Solution {

    private static class FenwickTree {
        private final long[] tree;
        public FenwickTree(int n) { tree = new long[n + 1]; }
        public void update(int i, long v) {
            for (; i < tree.length; i += i & -i) {
                tree[i] += v;
            }
        }
        public long query(int i) {
            long ans = 0;
            for (; i > 0; i -= i & -i) ans += tree[i];
            return ans;
        }
    }

    private static final int MOD = 1_000_000_007;

    @Tag("倍数容斥")
    @SuppressWarnings("unchecked")
    public int totalBeauty(int[] nums) {
        // 对于每个 g = 1, 2, 3, ..., 求出有多少个严格递增子序列的 GCD 恰好等于 g
        int mx = 0;
        for (var v : nums) mx = Math.max(mx, v);

        List<Integer>[] groups = new List[mx + 1];
        Arrays.setAll(groups, i -> new ArrayList<>());
        for (var v : nums) {
            // 枚举 v 的因子 p
            for (int f = 1; f * f <= v; f++) {
                if (v % f == 0) {
                    groups[f].add(v);
                    if (f * f < v) groups[v / f].add(v);
                }
            }
        }

        int[] dp = new int[mx + 1]; long ans = 0;
        for (int i = mx; i > 0; i--) {
            long curr = countIncreasingSubSequence(groups[i], i, mx);
            // 倍数容斥
            for (int j = i * 2; j <= mx; j += i) curr -= dp[j];

            dp[i] = (int) (curr % MOD); ans += ((curr % MOD) * i);
        }
        return (int) (((ans % MOD) + MOD) % MOD);
    }

    // 计算 b 中的严格递增子序列的个数
    private long countIncreasingSubSequence(List<Integer> b, int g, int mx) {
        FenwickTree t = new FenwickTree(mx / g); long ans = 0;
        for (var v : b) {
            v /= g;

            // cnt 表示以 v 结尾的严格递增子序列的个数
            long cnt = t.query(v - 1) + 1; // v 单独作为一个子序列
            ans += cnt % MOD; t.update(v, cnt % MOD);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
