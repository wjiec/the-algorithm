package problem.p1672richestcustomerwealth;

/**
 * 1672. Richest Customer Wealth
 *
 * https://leetcode-cn.com/problems/richest-customer-wealth/
 *
 * You are given an m x n integer grid accounts where accounts[i][j] is the
 * amount of money the ith customer has in the jth bank. Return the wealth that the richest customer has.
 *
 * A customer's wealth is the amount of money they have in all their bank accounts.
 *
 * The richest customer is the customer that has the maximum wealth.
 */

public class Solution {

    public int maximumWealth(int[][] accounts) {
        int ans = 0;
        for (var account : accounts) {
            int curr = 0;
            for (var n : account) curr += n;
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumWealth(new int[][]{
            {1,2,3},
            {3,2,1}
        }) == 6;

        assert new Solution().maximumWealth(new int[][]{
            {1,5},
            {7,3},
            {3,5}
        }) == 10;

        assert new Solution().maximumWealth(new int[][]{
            {2,8,7},
            {7,1,3},
            {1,9,5}
        }) == 17;
    }

}
