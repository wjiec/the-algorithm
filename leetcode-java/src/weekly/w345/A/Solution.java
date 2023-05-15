package weekly.w345.A;

import common.Checker;

/**
 * 2682. Find the Losers of the Circular Game
 *
 * https://leetcode.cn/contest/weekly-contest-345/problems/find-the-losers-of-the-circular-game/
 *
 * There are n friends that are playing a game. The friends are sitting in a circle and are numbered
 * from 1 to n in clockwise order. More formally, moving clockwise from the ith friend brings you to
 * the (i+1)th friend for 1 <= i < n, and moving clockwise from the nth friend brings you to the 1st friend.
 *
 * The rules of the game are as follows:
 *
 * 1st friend receives the ball.
 *
 * After that, 1st friend passes it to the friend who is k steps away from them in the clockwise direction.
 *
 * After that, the friend who receives the ball should pass it to the friend who is 2 * k steps away from them
 * in the clockwise direction.
 *
 * After that, the friend who receives the ball should pass it to the friend who is 3 * k steps away from them
 * in the clockwise direction, and so on and so forth.
 *
 * In other words, on the ith turn, the friend holding the ball should pass it to the friend who is i * k steps
 * away from them in the clockwise direction.
 *
 * The game is finished when some friend receives the ball for the second time.
 *
 * The losers of the game are friends who did not receive the ball in the entire game.
 *
 * Given the number of friends, n, and an integer k, return the array answer, which contains the losers of the
 * game in the ascending order.
 */

public class Solution {

    public int[] circularGameLosers(int n, int k) {
        int[] count = new int[n];
        for (int i = 0, s = k; true; i += s, s += k) {
            if (++count[i % n] == 2) break;
        }

        int len = 0;
        for (var v : count) if (v == 0) len++;

        int[] ans = new int[len];
        for (int i = 0, j = 0; i < n; i++) {
            if (count[i] == 0) {
                ans[j++] = i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().circularGameLosers(5, 2), new int[]{4, 5});
        assert Checker.check(new Solution().circularGameLosers(4, 4), new int[]{2, 3, 4});
    }

}
