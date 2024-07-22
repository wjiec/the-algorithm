package weekly.bw135.A;

/**
 * 3222. Find the Winning Player in Coin Game
 *
 * https://leetcode.cn/contest/biweekly-contest-135/problems/find-the-winning-player-in-coin-game/
 *
 * You are given two positive integers x and y, denoting the number of coins with values 75 and 10 respectively.
 *
 * Alice and Bob are playing a game. Each turn, starting with Alice, the player must pick up coins
 * with a total value 115. If the player is unable to do so, they lose the game.
 *
 * Return the name of the player who wins the game if both players play optimally.
 */

public class Solution {

    public String losingPlayer(int x, int y) {
        int curr = 0;
        while (x > 0 && y >= 4) { x--; y -= 4; curr++; }
        return curr % 2 == 0 ? "Bob" : "Alice";
    }

    public static void main(String[] args) {
    }

}
