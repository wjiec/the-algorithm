package weekly.w432.B;

import common.PrettyPrinter;

import java.util.Arrays;

public class Solution {

    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;
        int[][][] dp = new int[m + 1][n + 1][3];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int curr = coins[i - 1][j - 1];

                // 0 表示没有使用感化技能
                dp[i][j][0] = curr + Math.max(dp[i - 1][j][0], dp[i][j - 1][0]);
                // 1 表示使用了 1 次感化技能
                if (curr >= 0) dp[i][j][1] = curr + Math.max(dp[i - 1][j][1], dp[i][j - 1][1]);
                if (curr < 0) dp[i][j][1] = Math.max(dp[i][j][1], Math.max(dp[i - 1][j][1], dp[i][j - 1][1])); // 感化
                // 2 表示使用了 2 次感化技能
                if (curr >= 0) dp[i][j][2] = curr + Math.max(dp[i - 1][j][2], dp[i][j - 1][2]);
                if (curr < 0) dp[i][j][2] = Math.max(dp[i][j][2], Math.max(dp[i - 1][j][1], dp[i][j - 1][1])); // 感化

                System.out.printf("%d,%d = %d, dp = %s\n", i - 1, j - 1, coins[i - 1][j - 1], Arrays.toString(dp[i][j]));
            }
        }

        PrettyPrinter.println(coins);
        PrettyPrinter.println(dp);

        return Math.max(dp[m][n][0], Math.max(dp[m][n][1], dp[m][n][2]));
    }

    public static void main(String[] args) {
        assert new Solution().maximumAmount(new int[][]{
            {0,  1, -1},
            {1, -2,  3},
            {2, -3,  4}
        }) == 8;
        assert new Solution().maximumAmount(new int[][]{{10,10,10},{10,10,10},{10,10,10}}) == 40;
    }

}
