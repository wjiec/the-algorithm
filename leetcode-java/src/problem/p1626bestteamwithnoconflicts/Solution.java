package problem.p1626bestteamwithnoconflicts;

import java.util.Arrays;

/**
 * 1626. Best Team With No Conflicts
 *
 * https://leetcode.cn/problems/best-team-with-no-conflicts/
 *
 * You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with
 * the highest overall score. The score of the team is the sum of scores of all the players in the team.
 *
 * However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player
 * has a strictly higher score than an older player. A conflict does not occur between players of the same age.
 *
 * Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of
 * the ith player, respectively, return the highest overall score of all possible basketball teams.
 */

public class Solution {

    private record Player(int age, int score) {}

    public int bestTeamScore(int[] scores, int[] ages) {
        Player[] players = new Player[scores.length];
        for (int i = 0; i < scores.length; i++) {
            players[i] = new Player(ages[i], scores[i]);
        }
        Arrays.sort(players, (a, b) -> a.age == b.age ? a.score - b.score : a.age - b.age);

        int ans = 0;
        int[] dp = new int[players.length];
        for (int i = 0; i < players.length; i++) {
            dp[i] = players[i].score;
            for (int j = 0; j < i; j++) {
                if (players[j].score <= players[i].score) {
                    dp[i] = Math.max(dp[i], dp[j] + players[i].score);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().bestTeamScore(new int[]{1,3,7,3,2,4,10,7,5}, new int[]{4,5,2,1,1,2,4,1,4}) == 29;
        assert new Solution().bestTeamScore(new int[]{1,1,1,1,1,1,1,1,1,1}, new int[]{811,364,124,873,790,656,581,446,885,134}) == 10;

        assert new Solution().bestTeamScore(new int[]{1,3,5,10,15}, new int[]{1,2,3,4,5}) == 34;
        assert new Solution().bestTeamScore(new int[]{4,5,6,5}, new int[]{2,1,2,1}) == 16;
        assert new Solution().bestTeamScore(new int[]{1,2,3,5}, new int[]{8,9,10,1}) == 6;
    }

}
