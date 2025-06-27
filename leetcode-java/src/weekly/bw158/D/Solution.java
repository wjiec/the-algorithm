package weekly.bw158.D;

import ability.Benchmark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q4. Maximum Good Subtree Score
 *
 * https://leetcode.cn/contest/biweekly-contest-158/problems/maximum-good-subtree-score/
 *
 * You are given an undirected tree rooted at node 0 with n nodes numbered from 0 to n - 1.
 * Each node i has an integer value vals[i], and its parent is given by par[i].
 *
 * A subset of nodes within the subtree of a node is called good if every digit from 0 to 9 appears
 * at most once in the decimal representation of the values of the selected nodes.
 *
 * The score of a good subset is the sum of the values of its nodes.
 *
 * Define an array maxScore of length n, where maxScore[u] represents the maximum possible
 * sum of values of a good subset of nodes that belong to the subtree rooted at node u,
 * including u itself and all its descendants.
 *
 * Return the sum of all values in maxScore.
 *
 * Since the answer may be large, return it modulo 1e9 + 7.
 */

public class Solution {

    private static final int MASK = 1 << 10;
    private static final int MOD = 1_000_000_007;

    /** @noinspection unchecked*/
    private final List<Integer>[] g = new List[501];
    { Arrays.setAll(g, i -> new ArrayList<>()); }

    /** @noinspection unchecked*/
    private static final List<Integer>[] xor = new List[MASK]; // sum(xor) = 57002
    static {
        Arrays.setAll(xor, i -> new ArrayList<>());
        for (int i = 1; i < MASK; i++) {
            for (int j = 1; j < MASK; j++) {
                if ((i & j) == 0) xor[i].add(j);
            }
        }
    }

    public int goodSubtreeSum(int[] vals, int[] par) {
        for (int i = 1; i < par.length; i++) g[par[i]].add(i);
        dfs(0, vals);
        return (int) ans;
    }

    private long ans = 0;

    private long[] dfs(int curr, int[] vals) {
        int v = vals[curr], mask = digitMask(v);

        long[] dp = new long[MASK];
        if (mask > 0) dp[mask] = v;
        if (g[curr].isEmpty()) {
            if (mask > 0) ans = (ans + v) % MOD;
            return mask > 0 ? dp : null;
        }

        for (var next : g[curr]) {
            var x = dfs(next, vals);
            if (x == null) continue;
            for (int i = 1; i < MASK; i++) {
                dp[i] = Math.max(dp[i], x[i]);
                for (int j : xor[i]) dp[i | j] = Math.max(dp[i | j], dp[i] + x[j]);
            }
        }

        long max = 0;
        for (int i = 1; i < MASK; i++) {
            if (mask > 0 && (i & mask) == 0) {
                dp[i | mask] = Math.max(dp[i | mask], dp[i] + v);
            }
            max = Math.max(max, dp[i]);
        }

        ans = (ans + max) % MOD;
        return max == 0 ? null : dp;
    }

    private int digitMask(int val) {
        int ans = 0;
        for (; val != 0; val /= 10) {
            int digit = val % 10;
            if ((ans & (1 << digit)) != 0) return 0;
            ans |= 1 << digit;
        }
        return ans;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().goodSubtreeSum(new int[]{
                846213,357140,87350,56497,134265,401732,297081
            }, new int[]{-1,3,1,0,2,6,4}) == 3256605;
            assert new Solution().goodSubtreeSum(new int[]{9787, 1916}, new int[]{-1, 0}) == 0;

            assert new Solution().goodSubtreeSum(new int[]{2, 3}, new int[]{-1, 0}) == 8;
            assert new Solution().goodSubtreeSum(new int[]{1, 5, 2}, new int[]{-1, 0, 0}) == 15;
            assert new Solution().goodSubtreeSum(new int[]{34, 1, 2}, new int[]{-1, 0, 1}) == 42;
            assert new Solution().goodSubtreeSum(new int[]{3, 22, 5}, new int[]{-1, 0, 1}) == 18;
        });
    }

}
