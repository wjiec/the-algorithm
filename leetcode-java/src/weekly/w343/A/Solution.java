package weekly.w343.A;

/**
 * 2660. Determine the Winner of a Bowling Game
 *
 * https://leetcode.cn/contest/weekly-contest-343/problems/determine-the-winner-of-a-bowling-game/
 *
 * You are given two 0-indexed integer arrays player1 and player2, that represent
 * the number of pins that player 1 and player 2 hit in a bowling game, respectively.
 *
 * The bowling game consists of n turns, and the number of pins in each turn is exactly 10.
 *
 * Assume a player hit xi pins in the ith turn. The value of the ith turn for the player is:
 *
 * 2xi if the player hit 10 pins in any of the previous two turns.
 * Otherwise, It is xi.
 * The score of the player is the sum of the values of their n turns.
 *
 * Return
 *
 * 1 if the score of player 1 is more than the score of player 2,
 * 2 if the score of player 2 is more than the score of player 1, and
 * 0 in case of a draw.
 */

public class Solution {

    public int isWinner(int[] player1, int[] player2) {
        int w1 = score(player1), w2 = score(player2);
        return w1 == w2 ? 0 : (w1 > w2 ? 1 : 2);
    }

    private int score(int[] player) {
        int ans = 0;
        for (int i = 0; i < player.length; i++) {
            ans += player[i];
            if (i - 1 >= 0 && player[i - 1] == 10) ans += player[i];
            else if (i - 2 >= 0 && player[i - 2] == 10) ans += player[i];
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
