package problem.p293flipgame;

import java.util.ArrayList;
import java.util.List;

/**
 * 293. Flip Game
 *
 * https://leetcode-cn.com/problems/flip-game/
 *
 * You are playing a Flip Game with your friend.
 *
 * You are given a string currentState that contains only '+' and '-'.
 * You and your friend take turns to flip two consecutive "++" into "--".
 *
 * The game ends when a person can no longer make a move, and therefore the other person will be the winner.
 *
 * Return all possible states of the string currentState after one valid move.
 *
 * You may return the answer in any order.
 *
 * If there is no valid move, return an empty list [].
 */

public class Solution {

    public List<String> generatePossibleNextMoves(String currentState) {
        List<String> ans = new ArrayList<>();
        char[] chars = currentState.toCharArray();
        for (int i = 0, n = chars.length - 1; i < n; i++) {
            if (chars[i] == chars[i + 1] && chars[i] == '+') {
                chars[i] = chars[i + 1] = '-';
                ans.add(new String(chars));
                chars[i] = chars[i + 1] = '+';
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generatePossibleNextMoves("++++"));
        System.out.println(new Solution().generatePossibleNextMoves("+"));
    }

}
