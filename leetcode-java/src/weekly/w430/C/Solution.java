package weekly.w430.C;

import ability.Benchmark;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static ability.Ability.Math.pow;

/**
 * 3404. Count Special Subsequences
 *
 * https://leetcode.cn/contest/weekly-contest-430/problems/count-special-subsequences/
 *
 * You are given an array nums consisting of positive integers.
 *
 * A special subsequence is defined as a subsequence of length 4, represented by
 * indices (p, q, r, s), where p < q < r < s. This subsequence must satisfy the following conditions:
 *
 * nums[p] * nums[r] == nums[q] * nums[s]
 * There must be at least one element between each pair of indices.
 * In other words, q - p > 1, r - q > 1 and s - r > 1.
 *
 * A subsequence is a sequence derived from the array by deleting zero or more
 * elements without changing the order of the remaining elements.
 *
 * Return the number of different special subsequences in nums.
 */

public class Solution {

    private final int MOD = 1_000_000_007;
    private final long[] inv = new long[1001];
    { for (int i = 1; i < inv.length; i++) inv[i] = pow(i, MOD - 2, MOD); }

    // p < q < r < s
    //
    // nums[p] * nums[r] == nums[q] * nums[s]
    //  - nums[p] / nums[q] * nums[r] == nums[s]
    //
    // q - p > 1 && r - q > 1 && s - r > 1
    public long numberOfSubsequences(int[] nums) {
        int mx = 0;
        for (var v : nums) mx = Math.max(mx, v);

        int[][] suf = new int[nums.length + 1][mx + 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            System.arraycopy(suf[i + 1], 0, suf[i], 0, mx + 1);
            suf[i][nums[i]]++;
        }

        long ans = 0;
        for (int p = 0; p < nums.length; p++) {
            for (int q = p + 2; q < nums.length; q++) {
                for (int r = q + 2; r < nums.length - 1; r++) {
                    int v = (int) (((long) nums[p] * nums[r]) % MOD);
                    v = (int) (((long) v * inv[nums[q]]) % MOD);
                    if (v <= mx) ans += suf[r + 2][v];
                }
            }
        }

        return ans;
    }

    /** @noinspection unchecked*/
    private static class Optimization {

        private final int MOD = 1_000_000_007;
        private final long[] inv = new long[1001];
        { for (int i = 1; i < inv.length; i++) inv[i] = pow(i, MOD - 2, MOD); }

        private int div(int a, int b) { return (int) (((long) a * inv[b]) % MOD); }

        // p < q < r < s
        //
        // nums[p] * nums[r] == nums[q] * nums[s]
        //  - nums[p] / nums[q] == nums[s] / nums[r]
        //
        // 需要注意, 我们实际上需要的是 nums[p] / nums[q]
        //
        // q - p > 1 && r - q > 1 && s - r > 1
        public long numberOfSubsequences(int[] nums) {
            long ans = 0;
            List<Integer>[] pre = new List[nums.length];
            Arrays.setAll(pre, i -> new ArrayList<>());
            Map<Integer, Integer> prefix = new HashMap<>();
            // 枚举 r 和 s
            for (int r = 0; r < nums.length; r++) {
                for (int s = r + 2; s < nums.length; s++) {
                    // 找到 r - 1 的左边所有等于 inv[nums[r]] * nums[s] 的 pq 数对的数量
                    ans += prefix.getOrDefault(div(nums[s], nums[r]), 0);
                    // 把所有需要枚举的左边值都加入到数组中
                    pre[s].add(div(nums[r], nums[s]));
                }

                if (r > 0) for (var v : pre[r - 1]) prefix.merge(v, 1, Integer::sum);
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        int[] in = new int[1000];
        for (int i = 0; i < in.length; i++) {
            in[i] = ThreadLocalRandom.current().nextInt(1000);
        }

        assert new Optimization().numberOfSubsequences(new int[]{1,2,3,4,3,6,1}) == 1;
        assert new Optimization().numberOfSubsequences(new int[]{14,8,7,1,1,4,2,32,28}) == 4;
        assert new Optimization().numberOfSubsequences(new int[]{3,4,3,4,3,4,3,4}) == 3;
        Benchmark.benchmark("Optimization", () -> {
            new Optimization().numberOfSubsequences(in);
        });
        assert new Solution().numberOfSubsequences(in) == new Optimization().numberOfSubsequences(in);

        Benchmark.benchmark("random", () -> {
            new Solution().numberOfSubsequences(in);
        });

        assert new Solution().numberOfSubsequences(new int[]{14,8,7,1,1,4,2,32,28}) == 4;
        assert new Solution().numberOfSubsequences(new int[]{1,2,3,4,3,6,1}) == 1;
        assert new Solution().numberOfSubsequences(new int[]{3,4,3,4,3,4,3,4}) == 3;
    }

}
