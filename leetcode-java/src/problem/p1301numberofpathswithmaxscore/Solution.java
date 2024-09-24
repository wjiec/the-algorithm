package problem.p1301numberofpathswithmaxscore;

import common.Checker;

import java.util.Arrays;
import java.util.List;

/**
 * 1301. Number of Paths with Max Score
 *
 * https://leetcode.cn/problems/number-of-paths-with-max-score/
 *
 * You are given a square board of characters. You can move on the board starting
 * at the bottom right square marked with the character 'S'.
 *
 * You need to reach the top left square marked with the character 'E'.
 * The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or
 * with an obstacle 'X'. In one move you can go up, left or up-left (diagonally) only
 * if there is no obstacle there.
 *
 * Return a list of two integers: the first integer is the maximum sum of numeric characters
 * you can collect, and the second is the number of such paths that you can take to
 * get that maximum sum, taken modulo 10^9 + 7.
 *
 * In case there is no path, return [0, 0].
 */

public class Solution {

    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int[][] dp = new int[n][n];
        int[][] cnt = new int[n][n];
        char[][] chars = new char[n][];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
            Arrays.fill(cnt[i], -1);
            chars[i] = board.get(i).toCharArray();
        }

        dp[n - 1][n - 1] = 0;
        cnt[n - 1][n - 1] = 1;
        for (int j = n - 2; j >= 0; j--) {
            if (chars[n - 1][j] == 'X') break;

            dp[n - 1][j] = dp[n - 1][j + 1] + (chars[n - 1][j] - '0');
            cnt[n - 1][j] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (chars[i][j] != 'X') {
                    int v = chars[i][j] == 'E' ? 0 : (chars[i][j] - '0');

                    move(dp, cnt, i, j, i + 1, j, v);
                    if (j + 1 < n) {
                        move(dp, cnt, i, j, i, j + 1, v);
                        move(dp, cnt, i, j, i + 1, j + 1, v);
                    }
                }
            }
        }

        return new int[]{Math.max(dp[0][0], 0), Math.max(cnt[0][0], 0)};
    }

    private void move(int[][] dp, int[][] cnt, int tox, int toy, int fx, int fy, int v) {
        if (dp[fx][fy] == -1) return;

        if (dp[fx][fy] + v > dp[tox][toy]) {
            dp[tox][toy] = dp[fx][fy] + v;
            cnt[tox][toy] = cnt[fx][fy];
        } else if (dp[fx][fy] + v == dp[tox][toy]) {
            cnt[tox][toy] = (cnt[tox][toy] + cnt[fx][fy]) % 1_000_000_007;
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().pathsWithMaxScore(List.of("E23","2X2","12S")), new int[]{7, 1});
        assert Checker.check(new Solution().pathsWithMaxScore(List.of("E12","1X1","21S")), new int[]{4, 2});
        assert Checker.check(new Solution().pathsWithMaxScore(List.of("E11","XXX","11S")), new int[]{0, 0});
    }

}
