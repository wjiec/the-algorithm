package problem.p357countnumberswithuniquedigits;

/**
 * 357. Count Numbers with Unique Digits
 *
 * https://leetcode-cn.com/problems/count-numbers-with-unique-digits/
 *
 * Given an integer n, return the count of all numbers with unique digits, x, where 0 <= x < 10n.
 */

public class Solution {

    public int countNumbersWithUniqueDigits(int n) {
        int[] dp = new int[n + 1]; dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            // [0, 10^n - 1]的方案数是[0, 10^(n - 1) - 1] + n位数的方案数
            // 如：5位数方案数 = [0, 9999]的方案数 + [10000, 99999]中的方案数
            dp[i] = dp[i - 1] + calc(i);
        }
        return dp[n];
    }

    // n位数里可以有多少个数是不包含重复数字的
    private int calc(int n) {
        int v = 9; // 第一位不能为0, 所以有9种可能[1, 9]
        // 第二位也有9种选择, 因为第二位可以选0且加上非第一位选择的数字
        // 第三位及其之后就剩下X - 1种选择
        for (int i = 1, b = 9; i < n; i++, b--) {
            v *= b;
        }
        return v;
    }

    public static void main(String[] args) {
        assert new Solution().countNumbersWithUniqueDigits(0) == 1;
        assert new Solution().countNumbersWithUniqueDigits(1) == 10;
        assert new Solution().countNumbersWithUniqueDigits(2) == 91;
        assert new Solution().countNumbersWithUniqueDigits(3) == 739;
    }

}
