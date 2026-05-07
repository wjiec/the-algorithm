package weekly.w490.A;

/**
 * Q1. Find the Score Difference in a Game
 *
 * https://leetcode.cn/contest/weekly-contest-490/problems/find-the-score-difference-in-a-game/
 *
 * You are given an integer array nums, where nums[i] represents the points scored in the ith game.
 *
 * There are exactly two players. Initially, the first player is active and the second player is inactive.
 *
 * The following rules apply sequentially for each game i:
 *
 * If nums[i] is odd, the active and inactive players swap roles.
 * In every 6th game (that is, game indices 5, 11, 17, ...), the active and inactive players swap roles.
 * The active player plays the ith game and gains nums[i] points.
 *
 * Return the score difference, defined as the first player's total score minus the second player's total score.
 */

public class Solution {

    public int scoreDifference(int[] nums) {
        int ans = 0, base = 1;
        for (int i = 1; i <= nums.length; i++) {
            int v = nums[i - 1];
            if ((v & 1) == 1) base *= -1;
            if (i % 6 == 0) base *= -1;
            ans += base * v;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
