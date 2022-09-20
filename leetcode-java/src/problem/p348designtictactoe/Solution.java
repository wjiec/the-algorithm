package problem.p348designtictactoe;

/**
 * 348. Design Tic-Tac-Toe
 *
 * https://leetcode.cn/problems/design-tic-tac-toe/
 *
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 *
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves are allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Implement the TicTacToe class:
 *
 * TicTacToe(int n) Initializes the object the size of the board n.
 * int move(int row, int col, int player) Indicates that the player with id player plays
 * at the cell (row, col) of the board. The move is guaranteed to be a valid move.
 */

public class Solution {

    private static class TicTacToe {
        private final int n;
        private final int[][] cols, rows;
        private final int[] ltrb, rtlb;
        public TicTacToe(int n) {
            this.n = n;
            cols = new int[n][3];
            rows = new int[n][3];
            ltrb = new int[3]; // 左上右下
            rtlb = new int[3]; // 右上左下
        }

        public int move(int row, int col, int player) {
            if (++cols[col][player] == n) return player;
            if (++rows[row][player] == n) return player;
            if (row == col && ++ltrb[player] == n) return player;
            if (row + col == n - 1 && ++rtlb[player] == n) return player;

            return 0;
        }
    }

    public static void main(String[] args) {
        TicTacToe toe1 = new TicTacToe(2);
        assert toe1.move(0, 0, 2) == 0;
        assert toe1.move(0, 1, 1) == 0;
        assert toe1.move(1, 1, 2) == 2;

        TicTacToe toe = new TicTacToe(3);
        assert toe.move(0, 0, 1) == 0;
        assert toe.move(0, 2, 2) == 0;
        assert toe.move(2, 2, 1) == 0;
        assert toe.move(1, 1, 2) == 0;
        assert toe.move(2, 0, 1) == 0;
        assert toe.move(1, 0, 2) == 0;
        assert toe.move(2, 1, 1) == 1;
    }

}
