package problem.p1434numberofwaystoweardifferenthatstoeachother;

import ability.Benchmark;

import java.util.*;

/**
 * 1434. Number of Ways to Wear Different Hats to Each Other
 *
 * https://leetcode.cn/problems/number-of-ways-to-wear-different-hats-to-each-other/description/
 *
 * There are n people and 40 types of hats labeled from 1 to 40.
 *
 * Given a 2D integer array hats, where hats[i] is a list of all hats preferred by the ith person.
 *
 * Return the number of ways that the n people wear different hats to each other.
 *
 * Since the answer may be too large, return it modulo 109 + 7.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public int numberWays(List<List<Integer>> hats) {
        return dfs(hats, 0, 0);
    }

    private final static int MOD = 1_000_000_007;
    private final Map<Long, Integer>[] memo = new Map[10];
    { Arrays.setAll(memo, i -> new HashMap<>()); }

    private int dfs(List<List<Integer>> hats, int curr, long state) {
        if (curr == hats.size()) return 1;
        if (memo[curr].containsKey(state)) return memo[curr].get(state);

        int ans = 0;
        for (var hat : hats.get(curr)) {
            if ((state & (1L << hat)) == 0) {
                ans = (ans + dfs(hats, curr + 1, state | (1L << hat))) % MOD;
            }
        }

        memo[curr].put(state, ans);
        return ans;
    }

    private static class DynamicProgramming {
        public int numberWays(List<List<Integer>> hats) {
            // 喜欢 i 帽子的人
            List<Integer>[] whoLike = new List[41];
            Arrays.setAll(whoLike, i -> new HashMap<>());
            for (int i = 0; i < hats.size(); i++) {
                for (var hat : hats.get(i)) whoLike[hat].add(i);
            }

            int mask = 1 << hats.size();
            // dp[i][j] 表示使用前 i 顶帽子，并且已分配的人为 j 的方案数
            int[][] dp = new int[41][mask]; dp[0][0] = 1;
            for (int i = 1; i < dp.length; i++) {
                // 都有哪些人喜欢 i 这顶帽子
                for (var who : whoLike[i]) {
                    for (int j = 0; j < mask; j++) {
                        boolean canWear = (j & (1 << who)) == 0;

                        dp[i][j] += dp[i - 1][j] + (who == j && (dp[i - 1][j] & (1 << j)) == 0 ? 1 : 0);
                    }
                }
            }

            return 1;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("memo + dfs", () -> {
            assert new Solution().numberWays(new ArrayList<>(List.of(
                List.of(1,3,5,10,12,13,14,15,16,18,19,20,21,27,34,35,38,39,40),
                List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40),
                List.of(3,7,10,12,13,14,15,17,21,25,29,31,35,40),
                List.of(2,3,7,8,9,11,12,14,15,16,17,18,19,20,22,24,25,28,29,32,33,34,35,36,38),
                List.of(6,12,17,20,22,26,28,30,31,32,34,35),
                List.of(1,4,6,7,12,13,14,15,21,22,27,28,30,31,32,35,37,38,40),
                List.of(6,12,21,25,38),
                List.of(1,3,4,5,6,7,8,9,10,11,12,13,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,34,35,36,37,38,39,40)
            ))) == 842465346;
        }, 1);

        Benchmark.benchmark("dp", () -> {
            assert new DynamicProgramming().numberWays(new ArrayList<>(List.of(
                List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40),
                List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40),
                List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40),
                List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40),
                List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40),
                List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40),
                List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40),
                List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40),
                List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40),
                List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40)
                ))) == 842465346;
        });
    }

}
