package offer.p10qingwatiaotaijiewentilcof;

/**
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 *
 * https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */

public class Solution {

    public int numWays(int n) {
        int[] dp = new int[n + 1]; dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (i > 1) dp[i] = (dp[i] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        assert new Solution().numWays(2) == 2;
        assert new Solution().numWays(7) == 21;
        assert new Solution().numWays(0) == 1;
    }

}
