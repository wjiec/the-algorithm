package problem.p289gameoflife;

import common.PrettyPrinter;

/**
 * 289. Game of Life
 *
 * https://leetcode-cn.com/problems/game-of-life/
 *
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a
 * cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state:
 * live (represented by a 1) or dead (represented by a 0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
 * using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *
 * The next state is created by applying the above rules simultaneously to every cell in the current state,
 * where births and deaths occur simultaneously.
 *
 * Given the current state of the m x n grid board, return the next state.
 */

public class Solution {

    private final int[][] dirs = new int[][]{
        {1, 0}, {-1, 0}, {0, 1}, {0, -1},
        {1, 1}, {-1, 1}, {1, -1}, {-1, -1}
    };

    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int count = lives(board, i, j);
                if (board[i][j] == 1 && (count == 2 || count == 3)) {
                    board[i][j] |= 1 << 16;
                } else if (board[i][j] == 0 && count == 3) {
                    board[i][j] |= 1 << 16;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 16;
            }
        }
    }

    private int lives(int[][] board, int x, int y) {
        int count = 0;
        for (var dir : dirs) {
            int a = x + dir[0], b = y + dir[1];
            if (a >= 0 && a < board.length
                && b >= 0 && b < board[0].length
                && (board[a][b] & 0xffff) == 1
            ) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] b0 = new int[][]{{0,1,0},{0,0,1}, {1,1,1}, {0,0,0}};
        new Solution().gameOfLife(b0);
        PrettyPrinter.println(b0);

        int[][] b1 = new int[][]{{1,1},{1,0}};
        new Solution().gameOfLife(b1);
        PrettyPrinter.println(b1);
    }

}
