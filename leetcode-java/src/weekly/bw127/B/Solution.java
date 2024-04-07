package weekly.bw127.B;

/**
 * 3096. Minimum Levels to Gain More Points
 *
 * https://leetcode.cn/contest/biweekly-contest-127/problems/minimum-levels-to-gain-more-points/
 *
 * You are given a binary array possible of length n.
 *
 * Danielchandg and Bob are playing a game that consists of n levels. Some of the levels
 * in the game are impossible to clear while others can always be cleared.
 *
 * In particular, if possible[i] == 0, then the ith level is impossible to clear for both the players.
 * A player gains 1 point on clearing a level and loses 1 point if the player fails to clear it.
 *
 * At the start of the game, Danielchandg will play some levels in the given order starting
 * from the 0th level, after which Bob will play for the rest of the levels.
 *
 * Danielchandg wants to know the minimum number of levels he should play to gain more
 * points than Bob, if both players play optimally to maximize their points.
 *
 * Return the minimum number of levels danielchandg should play to gain more points.
 * If this is not possible, return -1.
 *
 * Note that each player must play at least 1 level.
 */

public class Solution {

    public int minimumLevels(int[] possible) {
        int n = possible.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + (possible[i - 1] == 0 ? -1 : 1);
        }

        for (int i = 1; i < n; i++) {
            if ((sum[i] << 1) > sum[n]) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
    }

}
