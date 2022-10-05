package problem.p2008maximumearningsfromtaxi;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2008. Maximum Earnings From Taxi
 *
 * https://leetcode.cn/problems/maximum-earnings-from-taxi/
 *
 * There are n points on a road you are driving your taxi on. The n points on the road are labeled
 * from 1 to n in the direction you are going, and you want to drive from point 1 to point n to
 * make money by picking up passengers. You cannot change the direction of the taxi.
 *
 * The passengers are represented by a 0-indexed 2D integer array rides, where
 * rides[i] = [starti, endi, tipi] denotes the ith passenger requesting a ride
 * from point starti to point endi who is willing to give a tipi dollar tip.
 *
 * For each passenger i you pick up, you earn endi - starti + tipi dollars.
 * You may only drive at most one passenger at a time.
 *
 * Given n and rides, return the maximum number of dollars you can earn
 * by picking up the passengers optimally.
 *
 * Note: You may drop off a passenger and pick up a different passenger at the same point.
 */

public class Solution {

    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, Comparator.comparingInt(r -> r[0]));

        long[] dp = new long[n + 1];
        for (int i = 1, j = 0; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
            while (j < rides.length && rides[j][0] == i) {
                int end = rides[j][1], fee = rides[j][2];
                dp[end] = Math.max(dp[end], dp[i] + end - i + fee);
                j++;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        assert new Solution().maxTaxiEarnings(5, new int[][]{{2,5,4},{1,5,1}}) == 7;
        assert new Solution().maxTaxiEarnings(20, new int[][]{
            {1,6,1},{3,10,2},{10,12,3},{11,12,2},{12,15,2},{13,18,1}
        }) == 20;
    }

}
