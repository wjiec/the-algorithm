package problem.p764largestplussign;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 764. Largest Plus Sign
 *
 * https://leetcode.cn/problems/largest-plus-sign/
 *
 * You are given an integer n. You have an n x n binary grid grid with all values initially 1's except
 * for some indices given in the array mines. The ith element of the array mines is defined as mines[i] = [xi, yi]
 * where grid[xi][yi] == 0.
 *
 * Return the order of the largest axis-aligned plus sign of 1's contained in grid. If there is none, return 0.
 *
 * An axis-aligned plus sign of 1's of order k has some center grid[r][c] == 1 along
 * with four arms of length k - 1 going up, down, left, and right, and made of 1's.
 *
 * Note that there could be 0's or 1's beyond the arms of the plus sign,
 * only the relevant area of the plus sign is checked for 1's.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        Set<Integer> set = new HashSet<>();
        for (var mine : mines) set.add(mine[0] << 16 | mine[1]);

        int ans = 0;
        int[][] dp = new int[n][n];
        for (int x = 0, c; x < n; x++) {
            c = 0;
            for (int y = 0; y < n; y++) {
                c = set.contains(x << 16 | y) ? 0 : c + 1;
                dp[x][y] = c;
            }

            c = 0;
            for (int y = n - 1; y >= 0; y--) {
                c = set.contains(x << 16 | y) ? 0 : c + 1;
                dp[x][y] = Math.min(dp[x][y], c);;
            }
        }

        for (int y = 0, c; y < n; y++) {
            c = 0;
            for (int x = 0; x < n; x++) {
                c = set.contains(x << 16 | y) ? 0 : c + 1;
                dp[x][y] = Math.min(dp[x][y], c);
            }

            c = 0;
            for (int x = n - 1; x >= 0; x--) {
                c = set.contains(x << 16 | y) ? 0 : c + 1;
                dp[x][y] = Math.min(dp[x][y], c);;
                if (dp[x][y] > ans) ans = dp[x][y];
            }
        }

        return ans;
    }

    private static class Daily {
        public int orderOfLargestPlusSign(int n, int[][] mines) {
            boolean[][] mine = new boolean[n][n];
            for (var x : mines) mine[x[0]][x[1]] = true;

            int[][] row = new int[n][n], col = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(row[i], Integer.MAX_VALUE / 2);
                Arrays.fill(col[i], Integer.MAX_VALUE / 2);
            }

            for (int x = 0; x < n; x++) {
                // 从左往右或从上到下计算距离
                for (int y = 0; y < n; y++) {
                    if (y == 0 || x == 0) {
                        row[x][y] = Math.min(row[x][y], mine[x][y] ? 0 : 1);
                        col[y][x] = Math.min(col[y][x], mine[y][x] ? 0 : 1);
                    } else {
                        row[x][y] = Math.min(row[x][y], mine[x][y] ? 0 : (row[x][y - 1] + 1));
                        col[y][x] = Math.min(col[y][x], mine[y][x] ? 0 : (col[y - 1][x] + 1));
                    }
                }

                // 从右往左或从下到上计算距离
                for (int y = n - 1; y >= 0; y--) {
                    if (y == n - 1 || x == n - 1) {
                        row[x][y] = Math.min(row[x][y], mine[x][y] ? 0 : 1);
                        col[y][x] = Math.min(col[y][x], mine[y][x] ? 0 : 1);
                    } else {
                        row[x][y] = Math.min(row[x][y], mine[x][y] ? 0 : (row[x][y + 1] + 1));
                        col[y][x] = Math.min(col[y][x], mine[y][x] ? 0 : (col[y + 1][x] + 1));
                    }
                }
            }

            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ans = Math.max(ans, Math.min(row[i][j], col[i][j]));
                }
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().orderOfLargestPlusSign(5, new int[][]{{4, 2}}) == 2;
        assert new Solution().orderOfLargestPlusSign(1, new int[][]{{0, 0}}) == 0;

        assert new Daily().orderOfLargestPlusSign(10, new int[][]{
            {0,0},{0,1},{0,2},{0,7},{1,2},{1,3},{1,9},{2,3},
            {2,5},{2,7},{2,8},{3,2},{3,5},{3,7},{4,2},{4,3},
            {4,5},{4,7},{5,1},{5,4},{5,8},{5,9},{7,2},{7,5},
            {7,7},{7,8},{8,5},{8,8},{9,0},{9,1},{9,2},{9,8}
        }) == 4;
        assert new Daily().orderOfLargestPlusSign(3, new int[][]{{0,0},{0,2},{1,0},{1,1},{1,2},{2,2}}) == 1;
        assert new Daily().orderOfLargestPlusSign(5, new int[][]{{4, 2}}) == 2;
        assert new Daily().orderOfLargestPlusSign(1, new int[][]{{0, 0}}) == 0;
    }

}
