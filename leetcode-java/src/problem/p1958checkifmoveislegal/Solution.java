package problem.p1958checkifmoveislegal;

/**
 * 1958. Check if Move is Legal
 *
 * https://leetcode.cn/problems/check-if-move-is-legal/
 *
 * You are given a 0-indexed 8 x 8 grid board, where board[r][c] represents the cell (r, c) on a game board.
 * On the board, free cells are represented by '.', white cells are represented by 'W', and
 * black cells are represented by 'B'.
 *
 * Each move in this game consists of choosing a free cell and changing it to the color you are playing
 * as (either white or black). However, a move is only legal if, after changing it, the cell becomes
 * the endpoint of a good line (horizontal, vertical, or diagonal).
 *
 * A good line is a line of three or more cells (including the endpoints) where the endpoints of the
 * line are one color, and the remaining cells in the middle are the opposite color
 * (no cells in the line are free). You can find examples for good lines in the figure below:
 *
 *
 * Given two integers rMove and cMove and a character color representing the color you are playing
 * as (white or black), return true if changing cell (rMove, cMove) to color color is a legal move, or
 * false if it is not legal.
 */

public class Solution {

    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        board[rMove][cMove] = color;
        if (check(board, rMove, cMove, color, 0, 1)) return true;
        if (check(board, rMove, cMove, color, 0, -1)) return true;
        if (check(board, rMove, cMove, color, 1, 0)) return true;
        if (check(board, rMove, cMove, color, -1, 0)) return true;

        if (check(board, rMove, cMove, color, -1, -1)) return true;
        if (check(board, rMove, cMove, color, -1, 1)) return true;
        if (check(board, rMove, cMove, color, 1, 1)) return true;
        if (check(board, rMove, cMove, color, 1, -1)) return true;

        return false;
    }

    private boolean check(char[][] board, int x, int y, char c, int dx, int dy) {
        int count = 1, n = 8;
        while (true) {
            x += dx; y += dy; count++;
            if (x >= 0 && x < n && y >= 0 && y < n) {
                if (board[x][y] == '.') break;
                if (board[x][y] == c) return count >= 3;
            } else break;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().checkMove(new char[][]{
            {'.','.','.','B','.','.','.','.'},
            {'.','.','.','W','.','.','.','.'},
            {'.','.','.','W','.','.','.','.'},
            {'.','.','.','W','.','.','.','.'},
            {'W','B','B','.','W','W','W','B'},
            {'.','.','.','B','.','.','.','.'},
            {'.','.','.','B','.','.','.','.'},
            {'.','.','.','W','.','.','.','.'}
        }, 4, 3, 'B');

        assert !new Solution().checkMove(new char[][]{
            {'.','.','.','.','.','.','.','.'},
            {'.','B','.','.','W','.','.','.'},
            {'.','.','W','.','.','.','.','.'},
            {'.','.','.','W','B','.','.','.'},
            {'.','.','.','.','.','.','.','.'},
            {'.','.','.','.','B','W','.','.'},
            {'.','.','.','.','.','.','W','.'},
            {'.','.','.','.','.','.','.','B'}
        }, 4, 4, 'W');
    }

}
