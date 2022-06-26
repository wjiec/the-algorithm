package weekly.w299.B;

import java.util.Arrays;

public class Solution {

    public int countHousePlacements(int n) {
        int MOD = 1_000_000_007;
        // 0 - no set
        // 1 - up
        // 2 - down
        // 3 - up and down
        int[][] dp = new int[n][4];
        Arrays.fill(dp[0], 1);

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][0] = (dp[i][0] + dp[i - 1][1]) % MOD;
            dp[i][0] = (dp[i][0] + dp[i - 1][2]) % MOD;
            dp[i][0] = (dp[i][0] + dp[i - 1][3]) % MOD;

            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;

            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;

            dp[i][3] = dp[i - 1][0];
        }

        int ans = 0;
        ans = (ans + dp[n - 1][0]) % MOD;
        ans = (ans + dp[n - 1][1]) % MOD;
        ans = (ans + dp[n - 1][2]) % MOD;
        ans = (ans + dp[n - 1][3]) % MOD;

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countHousePlacements(1) == 4;
        assert new Solution().countHousePlacements(2) == 9;
        assert new Solution().countHousePlacements(3) == 25;
    }

}
