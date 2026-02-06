package weekly.bw169.A;

/**
 * Q1. Minimum Moves to Equal Array Elements III
 *
 * https://leetcode.cn/contest/biweekly-contest-169/problems/minimum-moves-to-equal-array-elements-iii/
 *
 * You are given an integer array nums.
 *
 * In one move, you may increase the value of any single element nums[i] by 1.
 *
 * Return the minimum total number of moves required so that all elements in nums become equal.
 */

public class Solution {

    public int minMoves(int[] nums) {
        int mx = 0, ans = 0;
        for (var v : nums) mx = Math.max(mx, v);
        for (var v : nums) ans += mx - v;
        return ans;
    }

    public static void main(String[] args) {
    }

}
