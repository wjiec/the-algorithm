package problem.p2110numberofsmoothdescentperiodsofastock;

/**
 * 2110. Number of Smooth Descent Periods of a Stock
 *
 * https://leetcode.cn/problems/number-of-smooth-descent-periods-of-a-stock/
 *
 * You are given an integer array prices representing the daily price history of a stock, where
 * prices[i] is the stock price on the ith day.
 *
 * A smooth descent period of a stock consists of one or more contiguous days such that the price
 * on each day is lower than the price on the preceding day by exactly 1.
 *
 * The first day of the period is exempted from this rule.
 *
 * Return the number of smooth descent periods.
 */

public class Solution {

    public long getDescentPeriods(int[] prices) {
        long ans = 0, n = prices.length;
        for (int i = 0; i < n; ) {
            long count = 1; int j = i + 1;
            while (j < n && prices[j] == prices[j - 1] - 1) {
                count += j - i + 1; j++;
            }
            ans += count; i = j;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().getDescentPeriods(new int[]{3,2,1,4}) == 7;
        assert new Solution().getDescentPeriods(new int[]{8,6,7,7}) == 4;
        assert new Solution().getDescentPeriods(new int[]{1}) == 1;
        assert new Solution().getDescentPeriods(new int[]{7,6,5,4,3,2,1}) == 28;
    }

}
