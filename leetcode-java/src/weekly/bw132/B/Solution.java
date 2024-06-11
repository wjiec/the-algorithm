package weekly.bw132.B;

/**
 * 3175. Find The First Player to win K Games in a Row
 *
 * https://leetcode.cn/contest/biweekly-contest-132/problems/find-the-first-player-to-win-k-games-in-a-row/
 *
 * A competition consists of n players numbered from 0 to n - 1.
 *
 * You are given an integer array skills of size n and a positive integer k, where
 * skills[i] is the skill level of player i.
 *
 * All integers in skills are unique.
 *
 * All players are standing in a queue in order from player 0 to player n - 1.
 *
 * The competition process is as follows:
 *
 * The first two players in the queue play a game, and the player with the higher skill level wins.
 * After the game, the winner stays at the beginning of the queue, and the loser goes to the end of it.
 * The winner of the competition is the first player who wins k games in a row.
 *
 * Return the initial index of the winning player.
 */

public class Solution {

    public int findWinningPlayer(int[] skills, int k) {
        int maxId = 0, currMax = 0, currCnt = 0;
        for (int i = 1; i < skills.length; i++) {
            if (skills[i] > skills[maxId]) maxId = i;
            if (skills[i] > skills[currMax]) { currMax = i; currCnt = 0; }
            if (++currCnt == k) return currMax;
        }
        return maxId;
    }

    public static void main(String[] args) {
        assert new Solution().findWinningPlayer(new int[]{4,2,6,3,9}, 2) == 2;
    }

}
