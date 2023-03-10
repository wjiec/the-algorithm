package weekly.w335.D;

/**
 * 2585. Number of Ways to Earn Points
 *
 * https://leetcode.cn/contest/weekly-contest-335/problems/number-of-ways-to-earn-points/
 *
 * There is a test that has n types of questions. You are given an integer target and
 * a 0-indexed 2D integer array types where types[i] = [counti, marksi] indicates that
 * there are counti questions of the ith type, and each one of them is worth marksi points.
 *
 * Return the number of ways you can earn exactly target points in the exam. Since the answer
 * may be too large, return it modulo 109 + 7.
 *
 * Note that questions of the same type are indistinguishable.
 *
 * For example, if there are 3 questions of the same type, then solving the 1st and 2nd
 * questions is the same as solving the 1st and 3rd questions, or the 2nd and 3rd questions.
 */

public class Solution {

    public int waysToReachTarget(int target, int[][] types) {
        final int MOD = 1_000_000_007;
        // dp[i][j] 表示使用做前 i 道题目组成 j 分的方案数
        int[][] dp = new int[types.length + 1][target + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= types.length; i++) {
            int cnt = types[i - 1][0], pts = types[i - 1][1];
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; k <= cnt && k <= j / pts; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - k * pts]) % MOD;
                }
            }
        }
        return dp[types.length][target];
    }

    public static void main(String[] args) {
        assert new Solution().waysToReachTarget(1, new int[][]{{6, 1}, {3, 2}, {2, 3}}) == 1;
        assert new Solution().waysToReachTarget(2, new int[][]{{6, 1}, {3, 2}, {2, 3}}) == 2;
        assert new Solution().waysToReachTarget(2, new int[][]{{6, 1}, {3, 2}, {2, 3}}) == 2;
        assert new Solution().waysToReachTarget(6, new int[][]{{6, 1}, {3, 2}, {2, 3}}) == 7;
        assert new Solution().waysToReachTarget(5, new int[][]{{50, 1}, {50, 2}, {50, 5}}) == 4;
        assert new Solution().waysToReachTarget(18, new int[][]{{6, 1}, {3, 2}, {2, 3}}) == 1;
    }

}
