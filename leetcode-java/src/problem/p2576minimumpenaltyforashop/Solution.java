package problem.p2576minimumpenaltyforashop;

/**
 * 6250. Minimum Penalty for a Shop
 *
 * https://leetcode.cn/problems/minimum-penalty-for-a-shop/
 *
 * You are given the customer visit log of a shop represented by a 0-indexed string
 * customers consisting only of characters 'N' and 'Y':
 *
 * if the ith character is 'Y', it means that customers come at the ith hour
 * whereas 'N' indicates that no customers come at the ith hour.
 * If the shop closes at the jth hour (0 <= j <= n), the penalty is calculated as follows:
 *
 * For every hour when the shop is open and no customers come, the penalty increases by 1.
 * For every hour when the shop is closed and customers come, the penalty increases by 1.
 * Return the earliest hour at which the shop must be closed to incur a minimum penalty.
 *
 * Note that if a shop closes at the jth hour, it means the shop is closed at the hour j.
 */

public class Solution {

    public int bestClosingTime(String customers) {
        char[] chars = customers.toCharArray();
        int ans = 0, cost = 0, minCost = 0;
        for (var c : chars) if (c == 'Y') minCost = ++cost;
        for (int i = 1; i <= chars.length; i++) {
            if (chars[i - 1] == 'Y') {
                if (--cost < minCost) {
                    ans = i;
                    minCost = cost;
                }
            } else cost++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().bestClosingTime("YYNY") == 2;
        assert new Solution().bestClosingTime("NNNNN") == 0;
        assert new Solution().bestClosingTime("YYYY") == 4;
    }

}
