package weekly.w382.C;

/**
 * 3021. Alice and Bob Playing Flower Game
 *
 * https://leetcode.cn/contest/weekly-contest-382/problems/alice-and-bob-playing-flower-game/
 *
 * Alice and Bob are playing a turn-based game on a circular field surrounded by flowers.
 * The circle represents the field, and there are x flowers in the clockwise direction
 * between Alice and Bob, and y flowers in the anti-clockwise direction between them.
 *
 * The game proceeds as follows:
 *
 * Alice takes the first turn.
 * In each turn, a player must choose either the clockwise or anti-clockwise
 * direction and pick one flower from that side.
 * At the end of the turn, if there are no flowers left at all, the current
 * player captures their opponent and wins the game.
 * Given two integers, n and m, the task is to compute the number of possible
 * pairs (x, y) that satisfy the conditions:
 *
 * Alice must win the game according to the described rules.
 * The number of flowers x in the clockwise direction must be in the range [1,n].
 * The number of flowers y in the anti-clockwise direction must be in the range [1,m].
 *
 * Return the number of possible pairs (x, y) that satisfy the conditions mentioned in the statement.
 */

public class Solution {

    public long flowerGame(int n, int m) {
        long ans = 0, odd = 0, even = 0;
        for (int i = 1; i <= m; i++) {
            if (i % 2 == 0) even++;
            else odd++;
        }

        for (long i = 1; i <= n; i++) { // 顺时针
            if (i % 2 == 0) ans += odd; // 逆时针要有奇数朵花
            else ans += even; // 逆时针要有偶数朵花
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
