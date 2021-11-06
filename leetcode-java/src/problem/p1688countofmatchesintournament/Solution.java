package problem.p1688countofmatchesintournament;

/**
 * 1688. Count of Matches in Tournament
 *
 * https://leetcode-cn.com/problems/count-of-matches-in-tournament/
 *
 * You are given an integer n, the number of teams in a tournament that has strange rules:
 *
 * If the current number of teams is even, each team gets paired with another team.
 *
 * A total of n / 2 matches are played, and n / 2 teams advance to the next round.
 *
 * If the current number of teams is odd, one team randomly advances in the tournament, and the rest gets paired.
 *
 * A total of (n - 1) / 2 matches are played, and (n - 1) / 2 + 1 teams advance to the next round.
 *
 * Return the number of matches played in the tournament until a winner is decided.
 */

public class Solution {

    public int numberOfMatches(int n) {
        int ans = 0;
        while (n > 1) {
            ans += n / 2;
            n = n / 2 + (n % 2 == 0 ? 0 : 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfMatches(7) == 6;
        assert new Solution().numberOfMatches(14) == 13;
    }

}
