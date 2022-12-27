package problem.p2517maximumtastinessofcandybasket;

import java.util.Arrays;

/**
 * 2517. Maximum Tastiness of Candy Basket
 *
 * https://leetcode.cn/problems/maximum-tastiness-of-candy-basket/
 *
 * You are given an array of positive integers price where price[i] denotes
 * the price of the ith candy and a positive integer k.
 *
 * The store sells baskets of k distinct candies. The tastiness of a candy
 * basket is the smallest absolute difference of the prices of any two
 * candies in the basket.
 *
 * Return the maximum tastiness of a candy basket.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);

        int l = 0, r = price[price.length - 1], ans = 0;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(price, mid, k)) {
                ans = mid;
                l = mid + 1;
            } else r = mid;
        }
        return ans;
    }

    private boolean check(int[] price, int v, int k) {
        int prev = price[0];
        for (int i = 1; i < price.length; i++) {
            if (prev + v <= price[i]) {
                prev = price[i];
                if (--k == 1) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().maximumTastiness(new int[]{13,5,1,8,21,2}, 3) == 8;
        assert new Solution().maximumTastiness(new int[]{1,3,1}, 2) == 2;
        assert new Solution().maximumTastiness(new int[]{7,7,7,7}, 2) == 0;
    }

}
