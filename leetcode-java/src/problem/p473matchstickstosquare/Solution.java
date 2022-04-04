package problem.p473matchstickstosquare;

/**
 * 473. Matchsticks to Square
 *
 * https://leetcode-cn.com/problems/matchsticks-to-square/
 *
 * You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick.
 * You want to use all the matchsticks to make one square. You should not break any stick,
 * but you can link them up, and each matchstick must be used exactly one time.
 *
 * Return true if you can make this square and false otherwise.
 */

public class Solution {

    private int edge = 0;
    private int[] edges = new int[4];

    public boolean makesquare(int[] matchsticks) {
        int sum = 0, max = 0;
        for (var n : matchsticks) { sum += n; max = Math.max(max, n); }

        edge = sum / 4;
        if (sum % 4 != 0 || edge < max) return false;

        return dfs(matchsticks, 0);
    }

    private boolean dfs(int[] matchsticks, int i) {
        if (i == matchsticks.length) {
            return edges[0] == edges[1] && edges[1] == edges[2] && edges[2] == edges[3];
        }

        for (int j = 0; j < 4; j++) {
            if (edges[j] + matchsticks[i] <= edge) {
                edges[j] += matchsticks[i];
                if (dfs(matchsticks, i + 1)) {
                    return true;
                }
                edges[j] -= matchsticks[i];
            }
        }

        return false;
    }

    public static void main(String[] args) {
        assert new Solution().makesquare(new int[]{1,1,2,2,2});
        assert !new Solution().makesquare(new int[]{3,3,3,3,4});
    }

}
