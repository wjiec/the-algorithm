package offer.p46bashuzifanyichengzifuchuanlcof;

/**
 * 剑指 Offer 46. 把数字翻译成字符串
 *
 * https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 *
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 *
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 */

public class Solution {

    public int translateNum(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        int[] dp = new int[digits.length + 1]; dp[0] = dp[1] = 1;

        for (int i = 2; i <= digits.length; i++) {
            dp[i] = dp[i - 1];
            if (digits[i - 2] == '1' || (digits[i - 2] == '2' && digits[i - 1] <= '5')) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[digits.length];
    }

    public static void main(String[] args) {
        assert new Solution().translateNum(25) == 2;
        assert new Solution().translateNum(12258) == 5;
    }

}
