package offer2.p112;

/**
 * 剑指 Offer II 112. 最长递增路径
 *
 * https://leetcode.cn/problems/fpTFWP/
 *
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 *
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 */

public class Solution {

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        memo = new int[m][n];

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(matrix, i, j));
            }
        }
        return ans;
    }

    private int m = 0, n = 0;
    private int[][] memo = null;
    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int dfs(int[][] matrix, int x, int y) {
        if (memo[x][y] == 0) {
            int curr = matrix[x][y], ans = 0;
            for (var dir : dirs) {
                int dx = x + dir[0], dy = y + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && matrix[dx][dy] < curr) {
                    ans = Math.max(ans, dfs(matrix, dx, dy));
                }
            }
            memo[x][y] = ans + 1;
        }
        return memo[x][y];
    }

    public static void main(String[] args) {
        assert new Solution().longestIncreasingPath(new int[][]{{1,2}}) == 2;
        assert new Solution().longestIncreasingPath(new int[][]{{1},{2}}) == 2;

        assert new Solution().longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}}) == 4;
        assert new Solution().longestIncreasingPath(new int[][]{{3,4,5},{3,2,6},{2,2,1}}) == 4;
        assert new Solution().longestIncreasingPath(new int[][]{{1}}) == 1;
        assert new Solution().longestIncreasingPath(new int[][]{{3,3,3},{3,3,3},{3,3,3}}) == 1;
    }

}
