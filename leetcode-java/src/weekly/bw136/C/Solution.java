package weekly.bw136.C;

/**
 * 100385. Minimum Number of Flips to Make Binary Grid Palindromic II
 *
 * https://leetcode.cn/contest/biweekly-contest-136/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-ii/
 *
 * You are given an m x n binary matrix grid.
 *
 * A row or column is considered palindromic if its values read the same forward and backward.
 *
 * You can flip any number of cells in grid from 0 to 1, or from 1 to 0.
 *
 * Return the minimum number of cells that need to be flipped to make all rows
 * and columns palindromic, and the total number of 1's in grid divisible by 4.
 */

public class Solution {

    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m * n < 4) {
            int ans = 0;
            for (var row : grid) for (var v : row) ans += v;
            return ans;
        }

        // count[i] 表示 1 的个数为 i 的四元组个数
        int[] count = new int[5];
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int curr = grid[i][j] + grid[i][n - j - 1]
                    + grid[m - i - 1][j] + grid[m - i - 1][n - j - 1];
                count[curr]++;
            }
        }

        int ans = 0;
        // 四元组中1的个数为1的最佳处理方案是变为0
        ans += count[1];
        // 四元组中1的个数为2的可以选择变为0或者变为4
        ans += 2 * count[2];
        // 四元组中1的个数为3的最佳处理方案是变为4
        ans += count[3];

        // 中间还会有几个独立的格子, 这部分格子中1的个数也必须为4的倍数
        if (m % 2 != 0 || n % 2 != 0) {
            int[] midCount = new int[3];
            if (m % 2 != 0 && n % 2 != 0) {
                // 最中间独立的格子只能为0
                ans += grid[m / 2][n / 2];
            }

            // 有个中间行
            if (m % 2 != 0) {
                for (int l = 0, r = n - 1; l < r; l++, r--) {
                    midCount[grid[m / 2][l] + grid[m / 2][r]]++;
                }
            }

            // 有个中间列
            if (n % 2 != 0) {
                for (int l = 0, r = m - 1; l < r; l++, r--) {
                    midCount[grid[l][n / 2] + grid[r][n / 2]]++;
                }
            }

            // 如果有2个1的二元组的个数为偶数, 则已满足1的个数为4的倍数
            if (midCount[2] % 2 == 0) {
                ans += midCount[1];
            } else {
                // 如何可以凑4的倍数个1
                if (midCount[1] != 0) ans += midCount[1];
                else ans += midCount[1] + 2; // 否则只能变0
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minFlips(new int[][]{
            {1},
            {1},
            {1},
            {0}
        }) == 1;

        assert new Solution().minFlips(new int[][]{
            {1,0,0},
            {0,1,0},
            {0,0,1}
        }) == 3;

        assert new Solution().minFlips(new int[][]{
            {0,1},
            {0,1},
            {0,0}
        }) == 2;

        assert new Solution().minFlips(new int[][]{
            {1},
            {1},
        }) == 2;
    }

}
