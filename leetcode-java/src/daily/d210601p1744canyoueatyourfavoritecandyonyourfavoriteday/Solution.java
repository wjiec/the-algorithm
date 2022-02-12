package daily.d210601p1744canyoueatyourfavoritecandyonyourfavoriteday;

import common.Checker;

/**
 * 1744. Can You Eat Your Favorite Candy on Your Favorite Day?
 *
 * https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/
 *
 * You are given a (0-indexed) array of positive integers candiesCount where candiesCount[i]
 * represents the number of candies of the ith type you have.
 * You are also given a 2D array queries where queries[i] = [favoriteTypei, favoriteDayi, dailyCapi].
 *
 * You play a game with the following rules:
 *
 * You start eating candies on day 0.
 * You cannot eat any candy of type i unless you have eaten all candies of type i - 1.
 * You must eat at least one candy per day until you have eaten all the candies.
 *
 * Construct a boolean array answer such that answer.length == queries.length
 * and answer[i] is true if you can eat a candy of type favoriteTypei on day favoriteDayi
 * without eating more than dailyCapi candies on any day, and false otherwise.
 * Note that you can eat different types of candy on the same day, provided that you follow rule 2.
 *
 * Return the constructed array answer.
 */

public class Solution {

    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int m = candiesCount.length, n = queries.length;
        long[] sum = new long[m]; sum[0] = candiesCount[0];
        for (int i = 1; i < m; i++) {
            sum[i] = candiesCount[i] + sum[i - 1];
        }

        boolean[] ans = new boolean[n];
        for (int i = 0; i < n; i++) {
            long type = queries[i][0], day = queries[i][1], cap = queries[i][2];

            long d = day + 1, c = (day + 1) * cap, t = type == 0 ? 1 : sum[(int) (type - 1)] + 1, v = sum[(int) type];
            ans[i] = !(d > v || c < t);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().canEat(new int[]{7,4,5,3,8}, new int[][]{{0,2,2},{4,2,4},{2,13,1000000000}}),
            new boolean[]{true,false,true});
        assert Checker.check(new Solution().canEat(new int[]{5,2,6,4,1}, new int[][]{{3,1,2},{4,10,3},{3,10,100},{4,100,30},{1,3,1}}),
            new boolean[]{false,true,true,false,false});
    }

}
