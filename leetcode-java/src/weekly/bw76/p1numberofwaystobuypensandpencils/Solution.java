package weekly.bw76.p1numberofwaystobuypensandpencils;

/**
 * 6061. Number of Ways to Buy Pens and Pencils
 *
 * https://leetcode-cn.com/contest/biweekly-contest-76/problems/number-of-ways-to-buy-pens-and-pencils/
 *
 * You are given an integer total indicating the amount of money you have.
 *
 * You are also given two integers cost1 and cost2 indicating the price of a pen and pencil respectively.
 *
 * You can spend part or all of your money to buy multiple quantities (or none) of each kind of writing utensil.
 *
 * Return the number of distinct ways you can buy some number of pens and pencils.
 */

public class Solution {

    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long ans = 0;
        for (int i = 0; i <= total; i += cost1) {
            ans += (total - i) / cost2 + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().waysToBuyPensPencils(20, 10, 5) == 9;
        assert new Solution().waysToBuyPensPencils(5, 10, 10) == 1;
        assert new Solution().waysToBuyPensPencils(1000000, 1, 1) == 500001500001L;
    }

}
