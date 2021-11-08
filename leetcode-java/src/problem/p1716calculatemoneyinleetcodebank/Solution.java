package problem.p1716calculatemoneyinleetcodebank;

/**
 * 1716. Calculate Money in Leetcode Bank
 *
 * https://leetcode-cn.com/problems/calculate-money-in-leetcode-bank/
 *
 * Hercy wants to save money for his first car. He puts money in the Leetcode bank every day.
 *
 * He starts by putting in $1 on Monday, the first day.
 *
 * Every day from Tuesday to Sunday, he will put in $1 more than the day before.
 *
 * On every subsequent Monday, he will put in $1 more than the previous Monday.
 *
 * Given n, return the total amount of money he will have in the Leetcode bank at the end of the nth day.
 */

public class Solution {

    public int totalMoney(int n) {
        int div = n / 7, mod = n % 7, ans = 0, base = 1;
        for (; base <= div; base++) {
            ans += 7 * (base + base + 6) / 2;
        }
        return ans + (mod * (base + base + mod - 1) / 2);
    }

    public static void main(String[] args) {
        assert new Solution().totalMoney(4) == 10;
        assert new Solution().totalMoney(10) == 37;
        assert new Solution().totalMoney(20) == 96;
    }

}
