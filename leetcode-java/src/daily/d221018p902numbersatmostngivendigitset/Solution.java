package daily.d221018p902numbersatmostngivendigitset;

/**
 * 902. Numbers At Most N Given Digit Set
 *
 * https://leetcode.cn/problems/numbers-at-most-n-given-digit-set/
 *
 * Given an array of digits which is sorted in non-decreasing order. You can write numbers
 * using each digits[i] as many times as we want.
 *
 * For example, if digits = ['1','3','5'], we may write numbers such as '13', '551', and '1351315'.
 *
 * Return the number of positive integers that can be generated that are less than or equal to a given integer n.
 */

public class Solution {

    public int atMostNGivenDigitSet(String[] digits, int n) {
        int nLen = 0;
        for (int v = n; v != 0; v /= 10) nLen++;

        int[] nBits = new int[nLen];
        for (int i = nLen - 1, v = n; i >= 0; v /= 10) {
            nBits[i--] = v % 10;
        }

        int[] dgs = new int[digits.length];
        for (int i = 0; i < digits.length; i++) {
            dgs[i] = digits[i].charAt(0) - '0';
        }

        // dp[i][0] 表示由 digits 构成且小于 n 的前 i 位的数字的个数
        // dp[i][1] 表示由 digits 构成且等于 n 的前 i 位的数字的个数
        int[][] dp = new int[nLen + 1][2]; dp[0][1] = 1;
        for (int i = 1; i <= nLen; i++) {
            for (int dg : dgs) {
                if (dg == nBits[i - 1]) dp[i][1] = dp[i - 1][1];
                else if (dg < nBits[i - 1]) dp[i][0] += dp[i - 1][1];
                else break;
            }
            if (i != 1) dp[i][0] += dgs.length + dp[i - 1][0] * dgs.length;
        }
        return dp[nLen][0] + dp[nLen][1];
    }

    public static void main(String[] args) {
        assert new Solution().atMostNGivenDigitSet(new String[]{"1"}, 834) == 3;
        assert new Solution().atMostNGivenDigitSet(new String[]{"5", "6"}, 19) == 2;
        assert new Solution().atMostNGivenDigitSet(new String[]{"3", "4", "8"}, 4) == 2;
        assert new Solution().atMostNGivenDigitSet(new String[]{"3", "4", "5", "6"}, 64) == 18;

        assert new Solution().atMostNGivenDigitSet(new String[]{"1","3","5","7"}, 100) == 20;
        assert new Solution().atMostNGivenDigitSet(new String[]{"1","4","9"}, 1000000000) == 29523;
        assert new Solution().atMostNGivenDigitSet(new String[]{"7"}, 8) == 1;
    }

}
