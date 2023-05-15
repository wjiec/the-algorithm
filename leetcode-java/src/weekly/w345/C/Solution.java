package weekly.w345.C;

import java.util.HashMap;
import java.util.Map;

/**
 * 2684. Maximum Number of Moves in a Grid
 *
 * https://leetcode.cn/contest/weekly-contest-345/problems/maximum-number-of-moves-in-a-grid/
 *
 * You are given a 0-indexed m x n matrix grid consisting of positive integers.
 *
 * You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
 *
 * From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1)
 * and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger
 * than the value of the current cell.
 *
 * Return the maximum number of moves that you can perform.
 */

public class Solution {

    public int maxMoves(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            ans = Math.max(ans, moves(grid, i, 0));
        }
        return ans;
    }

    private final Map<Integer, Integer> memo = new HashMap<>();

    private int moves(int[][] grid, int x, int y) {
        int key = (x << 16) | y;
        if (!memo.containsKey(key)) {
            int m = grid.length, n = grid[0].length, ans = 0;
            if (y + 1 < n) {
                if (x - 1 >= 0 && grid[x - 1][y + 1] > grid[x][y]) ans = Math.max(ans, moves(grid, x - 1, y + 1) + 1);
                if (grid[x][y + 1] > grid[x][y]) ans = Math.max(ans, moves(grid, x, y + 1) + 1);
                if (x + 1 < m && grid[x + 1][y + 1] > grid[x][y]) ans = Math.max(ans, moves(grid, x + 1, y + 1) + 1);
            }
            memo.put(key, ans);
        }
        return memo.get(key);
    }

    public static void main(String[] args) {
    }

}
