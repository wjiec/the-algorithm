package weekly.bw164.C;

/**
 * Q3. Twisted Mirror Path Count
 *
 * https://leetcode.cn/contest/biweekly-contest-164/problems/twisted-mirror-path-count/
 *
 * Given an m x n binary grid where:
 *
 * grid[i][j] == 0 represents an empty cell, and
 * grid[i][j] == 1 represents a mirror.
 *
 * A robot starts at the top-left corner of the grid (0, 0) and wants to
 * reach the bottom-right corner (m - 1, n - 1). It can move only right or down.
 *
 * If the robot attempts to move into a mirror cell, it is reflected before entering that cell:
 *
 * If it tries to move right into a mirror, it is turned down and moved into
 * the cell directly below the mirror.
 *
 * If it tries to move down into a mirror, it is turned right and moved into
 * the cell directly to the right of the mirror.
 *
 * If this reflection would cause the robot to move outside the grid boundaries,
 * the path is considered invalid and should not be counted.
 *
 * Return the number of unique valid paths from (0, 0) to (m - 1, n - 1).
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 * Note: If a reflection moves the robot into a mirror cell, the robot is immediately
 * reflected again based on the direction it used to enter that mirror: if it entered
 * while moving right, it will be turned down; if it entered while moving down, it
 * will be turned right. This process will continue until either the last cell is reached,
 * the robot moves out of bounds or the robot moves to a non-mirror cell.
 */

public class Solution {

    /** @noinspection DuplicatedCode*/
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;

        int m = grid.length, n = grid[0].length;
        // dp[i][j] 表示进入 grid[i][j] 的方案数
        int[][] dp = new int[m][n]; dp[0][0] = 1;
        // topMirrors[i][j] 表示从上方进入当前镜子的有效路径数量
        int[][] topMirrors = new int[m][n];
        // leftMirrors[i][j] 表示从左方进入当前镜子的有效路径数量
        int[][] leftMirrors = new int[m][n];

        // 初始条件, 一直向右走
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1];
            if (grid[0][j] == 1) {
                leftMirrors[0][j] = 1;
                break;
            }
        }
        // 初始条件, 一直向下走
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0];
            if (grid[i][0] == 1) {
                topMirrors[i][0] = 1;
                break;
            }
        }

        // 枚举所有格子
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                boolean mirrorTop = grid[i - 1][j] == 1;
                boolean mirrorLeft = grid[i][j - 1] == 1;;
                boolean mirrorCorner = grid[i - 1][j - 1] == 1;

                // 如果当前格子是正常的, 那么简单走个转移
                if (grid[i][j] == 0) {
                    // 如果上方是镜子的话, 那么就是从 (i - 1, j - 1) 处来
                    //  - 如果 (i - 1, j - 1) 是个镜子, 那么就是从镜子的上方来
                    if (mirrorTop) {
                        if (mirrorCorner) dp[i][j] = (dp[i][j] + topMirrors[i - 1][j - 1]) % MOD;
                        else dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
                    } else {
                        // 如果上方不是镜子, 那么直接从上方转移
                        dp[i][j] = (dp[i][j] + dp[i - 1][j]) % MOD;
                    }

                    // 如果左方是镜子的话, 那么就是从 (i - 1, j - 1) 处来
                    //  - 如果 (i - 1, j - 1) 是个镜子, 那么就是从镜子的左方来
                    if (mirrorLeft) {
                        if (mirrorCorner) dp[i][j] = (dp[i][j] + leftMirrors[i - 1][j - 1]) % MOD;
                        else dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
                    } else {
                        // 如果左方不是镜子, 那么直接从左方转移
                        dp[i][j] = (dp[i][j] + dp[i][j - 1]) % MOD;
                    }
                } else {
                    // 否则如果当前的位置是一个镜子, 我们需要根据来的路径进行分类讨论

                    // 如果上方是个镜子的话, 那么就是从 (i - 1, j - 1) 处来
                    //  - 如果 (i - 1, j - 1) 也是个镜子的话, 那就需要从该镜子的上方来
                    if (mirrorTop) {
                        if (mirrorCorner) topMirrors[i][j] = (topMirrors[i][j] + topMirrors[i - 1][j - 1]) % MOD;
                        else topMirrors[i][j] = (topMirrors[i][j] + dp[i - 1][j - 1]) % MOD;
                    } else {
                        // 如果上方不是镜子, 那么直接从上方转移
                        topMirrors[i][j] = (topMirrors[i][j] + dp[i - 1][j]) % MOD;
                    }

                    // 如果左方是镜子的话, 那么就是从 (i - 1, j - 1) 处来
                    //  - 如果 (i - 1, j - 1) 也是个镜子, 那么就是从该镜子的左方来
                    if (mirrorLeft) {
                        if (mirrorCorner) leftMirrors[i][j] = (leftMirrors[i][j] + leftMirrors[i - 1][j - 1]) % MOD;
                        else leftMirrors[i][j] = (leftMirrors[i][j] + dp[i - 1][j - 1]) % MOD;
                    } else {
                        // 如果左方不是镜子, 那么直接从左方转移
                        leftMirrors[i][j] = (leftMirrors[i][j] + dp[i][j - 1]) % MOD;
                    }
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        assert new Solution().uniquePaths(new int[][]{{0,0,0},{1,1,1},{0,1,0}}) == 2;

        assert new Solution().uniquePaths(new int[][]{{0,1},{1,0}}) == 2;
        assert new Solution().uniquePaths(new int[][]{{0,1,0},{0,0,1},{1,0,0}}) == 5;
        assert new Solution().uniquePaths(new int[][]{{0,0},{0,0}}) == 2;
        assert new Solution().uniquePaths(new int[][]{{0,1,1},{1,1,0}}) == 1;
    }

}
