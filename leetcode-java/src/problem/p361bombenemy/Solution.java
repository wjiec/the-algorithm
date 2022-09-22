package problem.p361bombenemy;

/**
 * 361. Bomb Enemy
 *
 * https://leetcode.cn/problems/bomb-enemy/
 *
 * Given an m x n matrix grid where each cell is either a wall 'W', an enemy 'E' or empty '0', return
 * the maximum enemies you can kill using one bomb. You can only place the bomb in an empty cell.
 *
 * The bomb kills all the enemies in the same row and column from the planted point until it hits
 * the wall since it is too strong to be destroyed.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] rows = new int[m][n];
        for (int i = 0; i < m; i++) {
            int curr = 0;
            for (int j = 0; j < n; j++) {
                switch (grid[i][j]) {
                    case 'E' -> curr++;
                    case 'W' -> curr = 0;
                    case '0' -> rows[i][j] += curr;
                }
            }

            curr = 0;
            for (int j = n - 1; j >= 0; j--) {
                switch (grid[i][j]) {
                    case 'E' -> curr++;
                    case 'W' -> curr = 0;
                    case '0' -> rows[i][j] += curr;
                }
            }
        }

        int[][] cols = new int[m][n];
        for (int j = 0; j < n; j++) {
            int curr = 0;
            for (int i = 0; i < m; i++) {
                switch (grid[i][j]) {
                    case 'E' -> curr++;
                    case 'W' -> curr = 0;
                    case '0' -> cols[i][j] += curr;
                }
            }

            curr = 0;
            for (int i = m - 1; i >= 0; i--) {
                switch (grid[i][j]) {
                    case 'E' -> curr++;
                    case 'W' -> curr = 0;
                    case '0' -> cols[i][j] += curr;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, rows[i][j] + cols[i][j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxKilledEnemies(new char[][]{{'0','E','0','0'},{'E','0','W','E'},{'0','E','0','0'}}) == 3;
        assert new Solution().maxKilledEnemies(new char[][]{{'W','W','W'},{'0','0','0'},{'E','E','E'}}) == 1;
    }

}
