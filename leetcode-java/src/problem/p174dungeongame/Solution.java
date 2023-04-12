package problem.p174dungeongame;

import java.util.Arrays;

/**
 * 174. Dungeon Game
 *
 * https://leetcode.cn/problems/dungeon-game/
 *
 * The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially positioned
 * in the top-left room and must fight his way through dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer.
 * If at any point his health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons (represented by negative integers), so the knight
 * loses health upon entering these rooms; other rooms are either empty (represented as 0) or
 * contain magic orbs that increase the knight's health (represented by positive integers).
 *
 * To reach the princess as quickly as possible, the knight decides to move only
 * rightward or downward in each step.
 *
 * Return the knight's minimum initial health so that he can rescue the princess.
 *
 * Note that any room can contain threats or power-ups, even the first room the knight enters
 * and the bottom-right room where the princess is imprisoned.
 */

public class Solution {

    public int calculateMinimumHP(int[][] dungeon) {
        int l = 1, r = 1000 * 401, ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(dungeon, mid)) {
                ans = mid; r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
    }

    private boolean check(int[][] dungeon, int hp) {
        int m = dungeon.length, n = dungeon[0].length;

        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], -1);
        if ((dp[0][0] = dungeon[0][0] + hp) <= 0) return false;
        for (int j = 1; j < n && dp[0][j - 1] > 0; j++) dp[0][j] = dungeon[0][j] + dp[0][j - 1];
        for (int i = 1; i < m; i++) {
            int fails = 0;
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
                if (dp[i - 1][j] > 0) dp[i][j] = Math.max(dp[i][j], dungeon[i][j] + dp[i - 1][j]);
                if (j != 0 && dp[i][j - 1] > 0) dp[i][j] = Math.max(dp[i][j], dungeon[i][j] + dp[i][j - 1]);
                if (dp[i][j] <= 0) fails++;
            }
            if (fails == n) return false;
        }

        return dp[m - 1][n - 1] > 0;
    }

    private static class SimpleDynamicProgramming {
        public int calculateMinimumHP(int[][] dungeon) {
            int m = dungeon.length, n = dungeon[0].length;
            int[][] dp = new int[m + 1][n + 1];
            for (var row : dp) Arrays.fill(row, Integer.MAX_VALUE);

            dp[m][n - 1] = dp[m - 1][n] = 1;
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    int x = Math.min(dp[i + 1][j], dp[i][j + 1]);
                    dp[i][j] = Math.max(1, x - dungeon[i][j]);
                }
            }
            return dp[0][0];
        }
    }

    public static void main(String[] args) {
        assert new Solution().calculateMinimumHP(new int[][]{{1,-2,3},{2,-2,-2}}) == 2;

        assert new Solution().calculateMinimumHP(new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}}) == 7;
    }

}
