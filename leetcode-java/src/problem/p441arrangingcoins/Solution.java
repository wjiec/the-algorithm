package problem.p441arrangingcoins;

/**
 * 441. Arranging Coins
 *
 * https://leetcode-cn.com/problems/arranging-coins/
 *
 * You have n coins and you want to build a staircase with these coins.
 * The staircase consists of k rows where the ith row has exactly i coins.
 * The last row of the staircase may be incomplete.
 *
 * Given the integer n, return the number of complete rows of the staircase you will build.
 */

public class Solution {

    public int arrangeCoins(int n) {
        return (int) (Math.sqrt((1 + 8 * (long) n)) - 1) / 2;
    }

    public static void main(String[] args) {
        assert new Solution().arrangeCoins(1) == 1;
        assert new Solution().arrangeCoins(3) == 2;
        assert new Solution().arrangeCoins(5) == 2;
        assert new Solution().arrangeCoins(6) == 3;
        assert new Solution().arrangeCoins(8) == 3;
        assert new Solution().arrangeCoins(1280) == 50;
        assert new Solution().arrangeCoins(1804289383) == 60070;
    }

}
