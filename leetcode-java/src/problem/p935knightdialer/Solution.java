package problem.p935knightdialer;

import java.util.Arrays;

/**
 * 935. Knight Dialer
 *
 * https://leetcode.cn/problems/knight-dialer/
 *
 * The chess knight has a unique movement, it may move two squares vertically and one square horizontally,
 * or two squares horizontally and one square vertically (with both forming the shape of an L).
 * The possible movements of chess knight are shown in this diagaram:
 *
 * We have a chess knight and a phone pad as shown below,
 * the knight can only stand on a numeric cell (i.e. blue cell).
 *
 * Given an integer n, return how many distinct phone numbers of length n we can dial.
 *
 * You are allowed to place the knight on any numeric cell initially and then you should perform n - 1
 * jumps to dial a number of length n. All jumps should be valid knight jumps.
 *
 * As the answer may be very large, return the answer modulo 109 + 7.
 */

public class Solution {

    public int knightDialer(int n) {
        int MOD = 1_000_000_007;
        int[][] moves = new int[][]{
            {4, 6},
            {6, 8}, {7, 9}, {4, 8},
            {0, 3, 9}, {}, {0, 1, 7},
            {2, 6}, {1, 3}, {2, 4}
        };

        int[][] dp = new int[2][10];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < n; i++) {
            int[] from = dp[(i + 1) % 2], to = dp[i % 2];
            Arrays.fill(to, 0);
            for (int j = 0; j < 10; j++) {
                for (int next : moves[j]) {
                    to[next] = (from[j] + to[next]) % MOD;
                }
            }
        }

        int ans = 0;
        for (int v : dp[(n + 1) % 2]) ans = (ans + v) % MOD;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().knightDialer(1) == 10;
        assert new Solution().knightDialer(2) == 20;
        assert new Solution().knightDialer(3131) == 136006598;
    }

}
