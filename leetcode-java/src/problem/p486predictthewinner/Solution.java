package problem.p486predictthewinner;

/**
 * 486. Predict the Winner
 *
 * https://leetcode-cn.com/problems/predict-the-winner/
 *
 * You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.
 *
 * Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of 0.
 * At each turn, the player takes one of the numbers from either end of the array (i.e.,
 * nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1.
 *
 * The player adds the chosen number to their score. The game ends when there are no more elements in the array.
 *
 * Return true if Player 1 can win the game. If the scores of both players are equal,
 * then player 1 is still the winner, and you should also return true.
 *
 * You may assume that both players are playing optimally.
 */

public class Solution {

    // see https://leetcode-cn.com/problems/stone-game/
    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) dp[i][i] = nums[i];

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }

    public static void main(String[] args) {
        assert !new Solution().PredictTheWinner(new int[]{1,5,2});
        assert new Solution().PredictTheWinner(new int[]{1,5,233,7});
    }

}
