package problem.p130surroundedregions;

import common.PrettyPrinter;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 130. Surrounded Regions
 *
 * https://leetcode-cn.com/problems/surrounded-regions/
 *
 * Given an m x n matrix board containing 'X' and 'O',
 * capture all regions that are 4-directionally surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 */

public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        boolean[][] range = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O' && !range[i][0]) {
                mark(board, range, i, 0);
            }

            if (board[i][n - 1] == 'O' && !range[i][n - 1]) {
                mark(board, range, i, n - 1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O' && !range[0][i]) {
                mark(board, range, 0, i);
            }

            if (board[m - 1][i] == 'O' && !range[m - 1][i]) {
                mark(board, range, m - 1, i);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !range[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void mark(char[][] board, boolean[][] range, int x, int y) {
        range[x][y] = true;
        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (queue.add(new int[]{x, y}); !queue.isEmpty(); ) {
            int[] curr = queue.remove();

            range[curr[0]][curr[1]] = true;
            for (var dir : dirs) {
                int a = curr[0] + dir[0], b = curr[1] + dir[1];
                if (a >= 0 && a < m && b >= 0 && b < n && board[a][b] == 'O' && !range[a][b]) {
                    range[a][b] = true;
                    queue.add(new int[]{a, b});
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] b1 = new char[][]{
            {'X','X','X','X'},
            {'X','O','O','X'},
            {'X','X','O','X'},
            {'X','O','X','X'}
        };
        new Solution().solve(b1);
        PrettyPrinter.println(b1);

        char[][] b2 = new char[][]{
            {'X'},
        };
        new Solution().solve(b2);
        PrettyPrinter.println(b2);
    }

}
