package weekly.bw144.A;

/**
 * 3360. Stone Removal Game
 *
 * https://leetcode.cn/contest/biweekly-contest-144/problems/stone-removal-game/
 *
 * Alice and Bob are playing a game where they take turns removing stones from a pile, with Alice going first.
 *
 * Alice starts by removing exactly 10 stones on her first turn.
 * For each subsequent turn, each player removes exactly 1 fewer stone than the previous opponent.
 * The player who cannot make a move loses the game.
 *
 * Given a positive integer n, return true if Alice wins the game and false otherwise.
 */

public class Solution {

    public boolean canAliceWin(int n) {
        for (int i = 0; true; i++) {
            if ((n -= 10 - i) < 0) return i % 2 == 1;
        }
    }

    public static void main(String[] args) {
    }

}
