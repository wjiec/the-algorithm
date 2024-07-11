package weekly.w405.C;

/**
 * 3212. Count Submatrices With Equal Frequency of X and Y
 *
 * https://leetcode.cn/contest/weekly-contest-405/problems/count-submatrices-with-equal-frequency-of-x-and-y/
 *
 * Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or '.', return the number of submatrices that contain:
 *
 * grid[0][0]
 * an equal frequency of 'X' and 'Y'.
 * at least one 'X'.
 */

public class Solution {

    public int numberOfSubmatrices(char[][] grid) {
        int ans = 0;
        int m = grid.length, n = grid[0].length;
        int[][] sum = new int[m + 1][n + 1];
        int[][] cnt = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                var c = grid[i - 1][j - 1];
                var v = c == 'X' ? 1 : (c == 'Y' ? -1 : 0);
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + v;

                cnt[i][j] = cnt[i - 1][j] + cnt[i][j - 1] - cnt[i - 1][j - 1] + (c == 'X' ? 1 : 0);
                if (sum[i][j] == 0 && cnt[i][j] != 0) ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfSubmatrices(new char[][]{{'X', 'Y', '.'}, {'Y', '.', ','}}) == 3;
    }

}
