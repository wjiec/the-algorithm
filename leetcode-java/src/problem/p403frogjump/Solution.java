package problem.p403frogjump;

public class Solution {

    public boolean canCross(int[] stones) {
        int n = stones.length;
        for (int i = 1; i < n; i++) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }

        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int k = stones[i] - stones[j];
                if (k > j + 1) break;

                dp[i][k] = dp[j][k] || dp[j][k - 1] || dp[j][k + 1];
                if (i == n - 1 && dp[i][k]) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert !new Solution().canCross(new int[]{0, 2});

        assert new Solution().canCross(new int[]{0,1,3,5,6,8,12,17});
        assert !new Solution().canCross(new int[]{0,1,2,3,4,8,9,11});
    }

}
