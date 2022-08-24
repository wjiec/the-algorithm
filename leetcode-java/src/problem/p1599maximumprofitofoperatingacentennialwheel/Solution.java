package problem.p1599maximumprofitofoperatingacentennialwheel;

/**
 * 1599. Maximum Profit of Operating a Centennial Wheel
 *
 * https://leetcode.cn/problems/maximum-profit-of-operating-a-centennial-wheel/
 *
 * You are the operator of a Centennial Wheel that has four gondolas, and each gondola has room for
 * up to four people. You have the ability to rotate the gondolas counterclockwise, which costs you
 * runningCost dollars.
 *
 * You are given an array customers of length n where customers[i] is the number of new customers
 * arriving just before the ith rotation (0-indexed). This means you must rotate the wheel i times
 * before the customers[i] customers arrive. You cannot make customers wait if there is room in the gondola.
 *
 * Each customer pays boardingCost dollars when they board on the gondola closest to the ground and
 * will exit once that gondola reaches the ground again.
 *
 * You can stop the wheel at any time, including before serving all customers. If you decide to
 * stop serving customers, all subsequent rotations are free in order to get all the customers
 * down safely. Note that if there are currently more than four customers waiting at the wheel, only
 * four will board the gondola, and the rest will wait for the next rotation.
 *
 * Return the minimum number of rotations you need to perform to maximize your profit.
 * If there is no scenario where the profit is positive, return -1.
 */

public class Solution {

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        if (4 * boardingCost <= runningCost) return -1;

        int n = customers.length;
        for (int i = 0; i < n - 1; i++) {
            if (customers[i] > 4) {
                customers[i + 1] += customers[i] - 4;
                customers[i] = 4;
            }
        }

        int ans = -1, max = 0;
        for (int i = 0; i < n - 1; i++) {
            int prev = i == 0 ? 0 : customers[i - 1];
            customers[i] = prev + customers[i] * boardingCost - runningCost;
            if (customers[i] > max) { max = customers[i]; ans = i + 1; }
        }

        if (n - 2 >= 0) {
            int g = customers[n - 1] / 4, r = customers[n - 1] % 4;
            if (g != 0) {
                customers[n - 1] = customers[n - 2] + g * 4 * boardingCost - runningCost;
            }
            if (r * boardingCost - runningCost > 0) {
                g++;
                customers[n - 1] = customers[n - 2] + Math.max(r * boardingCost - runningCost, 0);
            }
            if (customers[n - 1] > max) ans = n + g - 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperationsMaxProfit(new int[]{1,2,1,2,1,2,1,1}, 90, 46) == 8;
        assert new Solution().minOperationsMaxProfit(new int[]{0,0,0,0,0,50}, 100, 1) == 18;
        assert new Solution().minOperationsMaxProfit(new int[]{2}, 2, 4) == -1;

        assert new Solution().minOperationsMaxProfit(new int[]{8,3}, 5, 6) == 3;
        assert new Solution().minOperationsMaxProfit(new int[]{10,9,6}, 6, 4) == 7;
        assert new Solution().minOperationsMaxProfit(new int[]{3,4,0,5,1}, 1, 92) == -1;
    }

}
