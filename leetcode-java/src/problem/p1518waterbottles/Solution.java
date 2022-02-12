package problem.p1518waterbottles;

/**
 * 1518. Water Bottles
 *
 * https://leetcode-cn.com/problems/water-bottles/
 *
 * Given numBottles full water bottles, you can exchange numExchange empty water bottles for one full water bottle.
 *
 * The operation of drinking a full water bottle turns it into an empty bottle.
 *
 * Return the maximum number of water bottles you can drink.
 */

public class Solution {

    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            int div = numBottles / numExchange, mod = numBottles % numExchange;
            numBottles = div + mod;
            ans += div;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numWaterBottles(9, 3) == 13;
        assert new Solution().numWaterBottles(15, 4) == 19;
        assert new Solution().numWaterBottles(5, 5) == 6;
        assert new Solution().numWaterBottles(2, 3) == 2;
    }

}
