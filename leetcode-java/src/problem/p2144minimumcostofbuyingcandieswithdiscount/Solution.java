package problem.p2144minimumcostofbuyingcandieswithdiscount;

import java.util.Arrays;

/**
 * 2144. Minimum Cost of Buying Candies With Discount
 *
 * https://leetcode-cn.com/problems/minimum-cost-of-buying-candies-with-discount/
 *
 * A shop is selling candies at a discount. For every two candies sold, the shop gives a third candy for free.
 *
 * The customer can choose any candy to take away for free as long as the cost of the chosen candy is
 * less than or equal to the minimum cost of the two candies bought.
 *
 * For example, if there are 4 candies with costs 1, 2, 3, and 4, and the customer buys candies with costs 2 and 3,
 * they can take the candy with cost 1 for free, but not the candy with cost 4.
 *
 * Given a 0-indexed integer array cost, where cost[i] denotes the cost of the ith candy,
 * return the minimum cost of buying all the candies.
 */

public class Solution {

    public int minimumCost(int[] cost) {
        Arrays.sort(cost);

        int ans = 0;
        for (int i = cost.length - 1; i >= 0; i--) {
            ans += cost[i--];
            if (i >= 0) ans += cost[i--];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumCost(new int[]{1,2,3}) == 5;
        assert new Solution().minimumCost(new int[]{6,5,7,9,2,2}) == 23;
        assert new Solution().minimumCost(new int[]{5,5}) == 10;
    }

}
