package problem.p764largestplussign;

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

    public static void main(String[] args) {
        assert new Solution().orderOfLargestPlusSign(5, new int[][]{{4, 2}}) == 2;
        assert new Solution().orderOfLargestPlusSign(1, new int[][]{{0, 0}}) == 0;
    }

}
