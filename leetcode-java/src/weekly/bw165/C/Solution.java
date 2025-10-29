package weekly.bw165.C;

import ability.Benchmark;

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

    /** @noinspection unchecked*/
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

    private static class Optimization {
        public int[][] generateSchedule(int n) {
            // 要求一个队伍不能连续两天进行比赛, 也就是两天的四个队伍不能相同
            if (n <= 4) return new int[0][];

            // 有 n 个队伍的需要安排比赛, 重点是找到分组的方法, 使得我们可以通过循环去处理
            //
            // 分组方案是根据间隔去对所有的比赛进行分组, 比如在 n = 5 时, 有:
            //  - d = 1: (0, 1) (1, 2) (2, 3) (3, 4) (4, 0)
            //  - d = 2: (0, 2) (1, 3) (2, 4) (3, 0) (4, 1)
            //  - d = 3: (0, 3) (1, 4) (2, 0) (3, 1) (4, 2)
            //  - d = 4: (0, 4) (1, 0) (2, 1) (3, 2) (4, 3)
            //
            // 可以发现在 d = [2, n - 2] 范围内的比赛已经是合法的排列
            //  - 对于 d = 1 和 d = n - 1 需要单独进行处理
            //
            // 考虑使用间隔的方式进行排列, 也就是对于序列 0 1 2 3 4
            //  - 我们排列为: 0 2 4 1 3
            //  - d = 1: (0, 1) (2, 3) (4, 0) (1, 2) (3, 4)
            //  - d = 4: (0, 4) (2, 1) (4, 3) (1, 0) (3, 2)
            // 但是以上排列会导致 d = 4 时的第一个与 d = 3 的最后一个出现连续比赛
            //  - 所以我们对于 d = 4, 先排列奇数位: 1 3 0 2 4
            //  - d = 4': (1, 0) (3, 2) (0, 4) (2, 1) (4, 3)
            //
            // 对于 n 位偶数的情况, 尝试进行排列, 比如 n = 6, 有:
            //  - d = 1: (0, 1) (1, 2) (2, 3) (3, 4) (4, 5) (5, 0)
            //  - d = 2: (0, 2) (1, 3) (2, 4) (3, 5) (4, 0) (5, 1)
            //  - d = 3: (0, 3) (1, 4) (2, 5) (3, 0) (4, 1) (5, 2)
            //  - d = 4: (0, 4) (1, 5) (2, 0) (3, 1) (4, 2) (5, 3)
            //  - d = 5: (0, 5) (1, 0) (2, 1) (3, 2) (4, 3) (5, 4)
            //
            // 间隔排列:
            //  - d = 1: (0, 1) (2, 3) (4, 5) (1, 2) (3, 4) (5, 0)
            //  - d = 5: (0, 5) (2, 1) (4, 3) (1, 0) (3, 2) (5, 4)
            // 此时 d = 1 的最后一个会与 d = 2 的第一个产生冲突, 直接交换 d = 1 的最后两个防止冲突
            //  - d = 1': (0, 1) (2, 3) (4, 5) (1, 2) (5, 0) (3, 4)
            // d = n - 1 的第一个会与 d = n - 2 的最后一个产生冲突, 交换 d = n - 1 的开始两个防止冲突
            //  - d = 5': (2, 1) (0, 5) (4, 3) (1, 0) (3, 2) (5, 4)

            int N = n * (n - 1), i = 0; int[][] ans = new int[N][];

            // 处理 d = 1 的情况, 如果 n 是奇数就直接排, 否则需要交换最后两个
            for (int j = 0; j < n; j += 2) ans[i++] = new int[]{j, (j + 1) % n};
            for (int j = 1; j < n; j += 2) ans[i++] = new int[]{j, (j + 1) % n};
            if ((n & 1) == 0) { var t = ans[n - 2]; ans[n - 2] = ans[n - 1]; ans[n - 1] = t; }

            // 处理所有 d = {2, ..., n - 2} 的情况
            for (int d = 2; d < n - 1; d++) {
                for (int j = 0; j < n; j++) {
                    ans[i++] = new int[]{j, (j + d) % n};
                }
            }

            // 处理 d = n - 1 的情况, 如果 n 是奇数, 则需要先排奇数再排偶数, 否则相反
            for (int j = n & 1; j < n; j += 2) ans[i++] = new int[]{j, (j + n - 1) % n};
            for (int j = (n & 1) ^ 1; j < n; j += 2) ans[i++] = new int[]{j, (j + n - 1) % n};
            if ((n & 1) == 0) { var t = ans[N - n]; ans[N - n] = ans[N - n + 1]; ans[N - n + 1] = t; }

            return ans;
        }
    }

    private static boolean check(int[][] ans) {
        for (int i = 1; i < ans.length; i++) {
            int a = ans[i - 1][0], b = ans[i - 1][1];
            int c = ans[i][0], d = ans[i][1];

            if (a == b || a == c || a == d || b == c || b == d || c == d) {
                System.out.printf("%d: %s\t\t%d: %s\n", i - 1,
                    Arrays.toString(ans[i - 1]), i, Arrays.toString(ans[i]));
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Optimization", () -> {
            for (int i = 1; i <= 50; i++) {
                assert check(new Optimization().generateSchedule(i));
            }
        });

        assert check(new Solution().generateSchedule(4));
        assert check(new Solution().generateSchedule(5));
        assert check(new Solution().generateSchedule(6));
    }

}
