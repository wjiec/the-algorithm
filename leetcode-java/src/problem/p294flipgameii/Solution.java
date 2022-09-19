package problem.p294flipgameii;

import java.util.HashMap;
import java.util.Map;

/**
 * 294. Flip Game II
 *
 * https://leetcode.cn/problems/flip-game-ii/
 *
 * You are playing a Flip Game with your friend.
 *
 * You are given a string currentState that contains only '+' and '-'. You and your friend
 * take turns to flip two consecutive "++" into "--". The game ends when a person can no longer
 * make a move, and therefore the other person will be the winner.
 *
 * Return true if the starting player can guarantee a win, and false otherwise.
 */

public class Solution {

    private int n = 0;
    private final Map<Long, Boolean> memo = new HashMap<>();

    public boolean canWin(String currentState) {
        long state = 0; n = currentState.length();
        for (int i = 0; i < currentState.length(); i++) {
            if (currentState.charAt(i) == '+') {
                state |= 1L << i;
            }
        }
        return dfs(state);
    }

    private boolean dfs(long state) {
        if (!memo.containsKey(state)) {
            for (int i = 1; i < n; i++) {
                if ((state & (1L << i)) == 0 || (state & (1L << (i - 1))) == 0) continue;
                if (dfs(state ^ (1L << i) ^ (1L <<(i - 1)))) continue;

                memo.put(state, true);
                return true;
            }

            memo.put(state, false);
            return false;
        }
        return memo.get(state);
    }

    public static void main(String[] args) {
        assert !new Solution().canWin("+++++++++");
        assert new Solution().canWin("++++++");
        assert !new Solution().canWin("+++++");

        assert new Solution().canWin("++++");
        assert !new Solution().canWin("+");
    }

}
