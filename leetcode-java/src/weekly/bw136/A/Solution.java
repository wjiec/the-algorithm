package weekly.bw136.A;

import java.util.HashMap;
import java.util.Map;

/**
 * 100381. Find the Number of Winning Players
 *
 * https://leetcode.cn/contest/biweekly-contest-136/problems/find-the-number-of-winning-players/
 *
 * You are given an integer n representing the number of players in a game and a 2D array
 * pick where pick[i] = [xi, yi] represents that the player xi picked a ball of color yi.
 *
 * Player i wins the game if they pick strictly more than i balls of the same color. In other words,
 *
 * Player 0 wins if they pick any ball.
 * Player 1 wins if they pick at least two balls of the same color.
 * ...
 * Player i wins if they pick at leasti + 1 balls of the same color.
 *
 * Return the number of players who win the game.
 *
 * Note that multiple players can win the game.
 */

public class Solution {

    public int winningPlayerCount(int n, int[][] pick) {
        Map<Integer, Map<Integer, Integer>> m = new HashMap<>();
        for (var p : pick) {
            m.computeIfAbsent(p[0], k -> new HashMap<>()).merge(p[1], 1, Integer::sum);
        }

        int ans = 0;
        for (var kv : m.entrySet()) {
            for (var ball : kv.getValue().values()) {
                if (ball > kv.getKey()) {
                    ans++; break;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
