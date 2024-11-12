package weekly.bw141.D;

/**
 * 3317. Find the Number of Possible Ways for an Event
 *
 * https://leetcode.cn/problems/find-the-number-of-possible-ways-for-an-event
 *
 * You are given three integers n, x, and y.
 *
 * An event is being held for n performers. When a performer arrives, they are assigned to one of the x stages.
 * All performers assigned to the same stage will perform together as a band, though some stages might remain empty.
 *
 * After all performances are completed, the jury will award each band a score in the range [1, y].
 *
 * Return the total number of possible ways the event can take place.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 * Note that two events are considered to have been held differently if either of the
 * following conditions is satisfied:
 *
 * Any performer is assigned a different stage.
 * Any band is awarded a different score.
 */

public class Solution {

    // 需要从 n 个人中选出 [0, x] 个节目, 每一个有表演者的节目得分为 [1, y]
    public int numberOfWays(int n, int x, int y) {
        final int MOD = 1_000_000_007;
        // 我们考虑枚举节目数 i, 现在以上问题变成:
        //  - 从 n 个人里选出 i 个节目, 一共有多少种方案
        //  - 这 i 个节目都可以打 [1, y] 分, 一共有多少种打分方式
        // 以上两个问题是互不影响的, 所以需要乘起来

        // 预计算出从 n 个人里选出 k 个节目的方案数
        //  - 考虑最后一个人的选择: 可以加入一个新的节目, 或是选择之前的 k 中的一个节目加入
        // dp[i][j] 表示从前 i 个人选出 j 个节目的方案数
        long[][] dp = new long[n + 1][x + 1]; dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= x; j++) {
                // 我们有以下状态转移方程
                //  - 当前这个人可以选择加入一个新的节目, 从 dp[i - 1][j - 1] 转移
                //  - 也可以选择加入到之前的任意一个节目中, 既 j * dp[i - 1][j]
                // 一个人不可能既加入新节目的同时加入之前任意一个节目, 所以得加起来
                dp[i][j] = (dp[i - 1][j - 1] + j * dp[i - 1][j]) % MOD;
            }
        }

        // 预计算出 x 个节目有多少种打分方式
        long[] score = new long[x + 1]; score[0] = 1;
        for (int i = 1; i <= x; i++) score[i] = (score[i - 1] * y) % MOD;

        long ans = 0, perm = 1;
        // 此时我们考虑枚举节目数 i, 计算每种情况的节目方案数和打分方案数
        for (int i = 1; i <= x; i++) {
            // 同时我们是从 x 个节目中选出 i 个节目, 且与顺序有关, 既 A^x_i
            perm = (perm * (x - i + 1)) % MOD;
            ans = (ans + perm * ((dp[n][i] * score[i]) % MOD)) % MOD;
        }

        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfWays(1, 2, 3) == 6;
        assert new Solution().numberOfWays(5, 2, 1) == 32;
        assert new Solution().numberOfWays(3, 3, 4) == 684;
    }

}
