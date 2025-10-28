package weekly.bw165.C;

import common.PrettyPrinter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Q3. Generate Schedule
 *
 * https://leetcode.cn/contest/biweekly-contest-165/problems/generate-schedule/
 *
 * You are given an integer n representing n teams. You are asked to generate a schedule such that:
 *
 * Each team plays every other team exactly twice: once at home and once away.
 * There is exactly one match per day; the schedule is a list of consecutive
 * days and schedule[i] is the match on day i.
 * No team plays on consecutive days.
 *
 * Return a 2D integer array schedule, where schedule[i][0] represents the home
 * team and schedule[i][1] represents the away team. If multiple schedules meet
 * the conditions, return any one of them.
 *
 * If no schedule exists that meets the conditions, return an empty array.
 */

public class Solution {

    public int[][] generateSchedule(int n) {
        if (n <= 4) return new int[0][];
        // 每个队伍与其他队伍正好比赛 2 次, 一次在主场, 一次在客场
        //  - 也就是任意一个队伍 i 可以与 (n - 1) 个队伍匹配
        final int MAX_N = n * (n - 1);

        // 每一只队伍需要有 n - 1 次主场比赛
        // 每一只队伍还需要进行 n - 1 次客场比赛
        long[] dp = new long[n];
        Arrays.setAll(dp, i -> ((1L << n) - 1) ^ (1L << i));

        // 不能连续 2 天进行比赛
        //  - 枚举所有队伍作为主场队, 与其他队伍进行比赛
        //  - 按插空的方式使得所有相同队伍分开
        int[][] ans = new int[MAX_N][];
        ans[0] = new int[]{0, 1}; dp[0] ^= 1L << 1;
        if (!dfs(dp, 1, ans)) return new int[0][];
        return ans;
    }

    private final Set<Long>[] memo = new Set[64];
    { Arrays.setAll(memo, i -> new HashSet<>()); }

    private boolean dfs(long[] dp, int i, int[][] ans) {
        if (i == ans.length) return true;
        if (!memo[i].add(toKey(dp))) return false;

        // 当前安排的比赛必须和前一场不同
        long prev = (1L << ans[i - 1][0]) | (1L << ans[i - 1][1]);
        for (int j = 0; j < dp.length; j++) {
            if ((prev & (1L << j)) != 0) continue;

            long found = dp[j] & (dp[j] ^ prev);
            for (int k = 0; k < dp.length; k++) {
                if ((found & (1L << k)) != 0) {
                    dp[j] ^= 1L << k; ans[i] = new int[]{j, k};
                    if (dfs(dp, i + 1, ans)) return true;
                    dp[j] ^= 1L << k;
                }
            }
        }
        return false;
    }

    private long toKey(long[] dp) {
        long hashcode = 0;
        for (var v : dp) hashcode = 31L * hashcode + v;
        return hashcode;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().generateSchedule(3));
        PrettyPrinter.println(new Solution().generateSchedule(5));
    }

}
