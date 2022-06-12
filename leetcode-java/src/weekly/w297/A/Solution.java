package weekly.w297.A;

import common.Checker;

/**
 * 5259. Calculate Amount Paid in Taxes
 *
 * https://leetcode.cn/contest/weekly-contest-297/problems/calculate-amount-paid-in-taxes/
 *
 * You are given a 0-indexed 2D integer array brackets where brackets[i] = [upperi, percenti] means
 * that the ith tax bracket has an upper bound of upperi and is taxed at a rate of percenti.
 * The brackets are sorted by upper bound (i.e. upperi-1 < upperi for 0 < i < brackets.length).
 *
 * Tax is calculated as follows:
 *
 * The first upper0 dollars earned are taxed at a rate of percent0.
 * The next upper1 - upper0 dollars earned are taxed at a rate of percent1.
 * The next upper2 - upper1 dollars earned are taxed at a rate of percent2.
 * And so on.
 * You are given an integer income representing the amount of money you earned. Return the amount of money
 * that you have to pay in taxes. Answers within 10-5 of the actual answer will be accepted.
 */

public class Solution {

    public double calculateTax(int[][] brackets, int income) {
        int[][] list = new int[brackets.length + 1][2];
        for (int i = 0, j = 1; i < brackets.length; i++, j++) {
            list[j] = brackets[i];
        }

        double ans = 0;
        for (int i = 1; i < list.length && income > 0; i++) {
            int len = Math.min(income, list[i][0] - list[i - 1][0]);
            ans += ((double) list[i][1] / 100.0) * len;
            income -= len;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().calculateTax(new int[][]{{3,50},{7,10}, {12,25}}, 10), 2.650000);
        assert Checker.check(new Solution().calculateTax(new int[][]{{1,0},{4,25}, {5,50}}, 2), 0.25);
        assert Checker.check(new Solution().calculateTax(new int[][]{{2,50}}, 0), 0.0);
    }

}
