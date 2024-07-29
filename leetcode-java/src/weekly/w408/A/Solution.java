package weekly.w408.A;

/**
 * 3232. Find if Digit Game Can Be Won
 *
 * https://leetcode.cn/contest/weekly-contest-408/problems/find-if-digit-game-can-be-won/
 *
 * You are given an array of positive integers nums.
 *
 * Alice and Bob are playing a game. In the game, Alice can choose either all single-digit
 * numbers or all double-digit numbers from nums, and the rest of the numbers are given to Bob.
 *
 * Alice wins if the sum of her numbers is strictly greater than the sum of Bob's numbers.
 *
 * Return true if Alice can win this game, otherwise, return false.
 */

public class Solution {

    public boolean canAliceWin(int[] nums) {
        int one = 0, two = 0, sum = 0;
        for (var v : nums) {
            sum += v;
            switch (String.valueOf(v).length()) {
                case 1 -> one += v;
                case 2 -> two += v;
            }
        }

        return (2 * one > sum) || (2 * two > sum);
    }

    public static void main(String[] args) {
    }

}
