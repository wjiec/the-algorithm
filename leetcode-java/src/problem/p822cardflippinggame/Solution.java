package problem.p822cardflippinggame;

/**
 * 822. Card Flipping Game
 *
 * https://leetcode.cn/problems/card-flipping-game/
 *
 * You are given two 0-indexed integer arrays fronts and backs of length n,
 * where the ith card has the positive integer fronts[i] printed
 * on the front and backs[i] printed on the back.
 *
 * Initially, each card is placed on a table such that the front number
 * is facing up and the other is facing down.
 *
 * You may flip over any number of cards (possibly zero).
 *
 * After flipping the cards, an integer is considered good if
 * it is facing down on some card and not facing up on any card.
 *
 * Return the minimum possible good integer after flipping the cards.
 * If there are no good integers, return 0.
 */

public class Solution {

    public int flipgame(int[] fronts, int[] backs) {
        boolean[] disabled = new boolean[2001];
        for (int i = 0; i < fronts.length; i++)
            if (fronts[i] == backs[i])
                disabled[fronts[i]] = true;

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < fronts.length; i++) {
            if (fronts[i] != backs[i]) {
                if (!disabled[fronts[i]] && fronts[i] < ans) ans = fronts[i];
                if (!disabled[backs[i]] && backs[i] < ans) ans = backs[i];
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().flipgame(new int[]{1,2,4,4,7}, new int[]{1,3,4,1,3}) == 2;
        assert new Solution().flipgame(new int[]{1}, new int[]{1}) == 0;
    }

}
