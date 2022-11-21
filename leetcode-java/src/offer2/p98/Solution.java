package offer2.p98;

/**
 * 剑指 Offer II 098. 路径的数目
 *
 * https://leetcode.cn/problems/2AoeFn/
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 0; i < n; i++) dp[0][i] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        assert new Solution().uniquePaths(3, 7) == 28;
        assert new Solution().uniquePaths(3, 2) == 3;
        assert new Solution().uniquePaths(7, 3) == 28;
        assert new Solution().uniquePaths(3, 3) == 6;
    }

}
