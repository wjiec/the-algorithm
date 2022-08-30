package problem.p1690stonegamevii;

/**
 * 1690. Stone Game VII
 *
 * https://leetcode.cn/problems/stone-game-vii/
 *
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * There are n stones arranged in a row. On each player's turn, they can remove either the leftmost
 * stone or the rightmost stone from the row and receive points equal to the sum of the remaining
 * stones' values in the row. The winner is the one with the higher score when there are no stones
 * left to remove.
 *
 * Bob found that he will always lose this game (poor Bob, he always loses), so he decided to minimize
 * the score's difference. Alice's goal is to maximize the difference in the score.
 *
 * Given an array of integers stones where stones[i] represents the value of the ith stone from
 * the left, return the difference in Alice and Bob's score if they both play optimally.
 */

public class Solution {

    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= stones.length; i++) {
            sum[i] = sum[i - 1] + stones[i - 1];
        }

        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(
                    sum[j + 1] - sum[i + 1] - dp[i + 1][j],
                    sum[j] - sum[i] - dp[i][j - 1]
                );
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        assert new Solution().stoneGameVII(new int[]{5,3,1,4,2}) == 6;
        assert new Solution().stoneGameVII(new int[]{7,90,5,1,100,10,10,2}) == 122;
    }

}
