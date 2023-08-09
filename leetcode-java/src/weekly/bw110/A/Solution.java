package weekly.bw110.A;

/**
 * 2806. Account Balance After Rounded Purchase
 *
 * https://leetcode.cn/contest/biweekly-contest-110/problems/account-balance-after-rounded-purchase/
 *
 * Initially, you have a bank account balance of 100 dollars.
 *
 * You are given an integer purchaseAmount representing the amount you will spend on a purchase in dollars.
 *
 * At the store where you will make the purchase, the purchase amount is rounded to the nearest multiple of 10.
 * In other words, you pay a non-negative amount, roundedAmount, such that roundedAmount is a multiple of 10
 * and abs(roundedAmount - purchaseAmount) is minimized.
 *
 * If there is more than one nearest multiple of 10, the largest multiple is chosen.
 *
 * Return an integer denoting your account balance after making a purchase worth
 * purchaseAmount dollars from the store.
 *
 * Note: 0 is considered to be a multiple of 10 in this problem.
 */

public class Solution {

    public int accountBalanceAfterPurchase(int purchaseAmount) {
        purchaseAmount = ((purchaseAmount + ((purchaseAmount % 10) >= 5 ? 10 : 0)) / 10) * 10;
        return 100 - purchaseAmount;
    }

    public static void main(String[] args) {
        assert new Solution().accountBalanceAfterPurchase(15) == 80;
        assert new Solution().accountBalanceAfterPurchase(11) == 90;
    }

}
