package problem.p312burstballoons;

import java.util.Arrays;

/**
 * 312. Burst Balloons
 *
 * https://leetcode.cn/problems/burst-balloons/
 *
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number
 * on it represented by an array nums. You are asked to burst all the balloons.
 *
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
 * If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon
 * with a 1 painted on it.
 *
 * Return the maximum coins you can collect by bursting the balloons wisely.
 */

public class Solution {

    private int[] values = null;
    private int[][] memo = null;

    public int maxCoins(int[] nums) {
        int n = nums.length;
        values = new int[n + 2];
        System.arraycopy(nums, 0, values, 1, n);
        values[0] = values[values.length - 1] = 1;

        memo = new int[n + 2][n + 2];
        for (var row : memo) Arrays.fill(row, -1);

        return resolve(0, n + 1);
    }

    public int resolve(int l, int r) {
        if (l + 1 >= r) return 0;
        if (memo[l][r] == -1) {
            for (int i = l + 1; i < r; i++) {
                int sum = values[l] * values[i] * values[r];
                sum += resolve(l, i) + resolve(i, r);
                memo[l][r] = Math.max(memo[l][r], sum);
            }
        }
        return memo[l][r];
    }

    public static void main(String[] args) {
        assert new Solution().maxCoins(new int[]{35,16,83,87,84,59,48,41,20,54}) == 1849648;
        assert new Solution().maxCoins(new int[]{9,76,64}) == 44416;

        assert new Solution().maxCoins(new int[]{3,1,5,8}) == 167;
        assert new Solution().maxCoins(new int[]{1,5}) == 10;
        assert new Solution().maxCoins(new int[]{2,1,1,1,100,1,1,1,1,1,1,1,99,1,1,1,3}) == 41704;
    }

}
