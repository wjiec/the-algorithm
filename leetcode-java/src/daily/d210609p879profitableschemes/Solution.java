package daily.d210609p879profitableschemes;

/**
 * 879. Profitable Schemes
 *
 * https://leetcode-cn.com/problems/profitable-schemes/
 *
 * There is a group of n members, and a list of various crimes they could commit.
 * The ith crime generates a profit[i] and requires group[i] members to participate in it.
 * If a member participates in one crime, that member can't participate in another crime.
 *
 * Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit,
 * and the total number of members participating in that subset of crimes is at most n.
 *
 * Return the number of schemes that can be chosen. Since the answer may be very large, return it modulo 109 + 7.
 */

public class Solution {

    private static int MOD = (int) (1e9 + 7);

    // @TODO dp
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int[][][] dp = new int[group.length + 1][n + 1][minProfit + 1]; dp[0][0][0] = 1;
        for (int i = 1; i <= group.length; i++) {
            int g = group[i - 1], p = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j < g) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        // dp[i][j][0] 表示前i项工作使用 j 个人达到最小 0 利润的人数
                        // 如果
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - g][Math.max(0, k - p)]) % MOD;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= n; i++) {
            ans = (ans + dp[group.length][i][minProfit]) % MOD;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().profitableSchemes(5, 3, new int[]{2,2}, new int[]{2,3}) == 2;
        assert new Solution().profitableSchemes(10, 5, new int[]{2,3,5}, new int[]{6,7,8}) == 7;
    }

}
