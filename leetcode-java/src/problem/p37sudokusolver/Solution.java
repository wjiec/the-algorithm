package problem.p37sudokusolver;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * 37. Sudoku Solver
 *
 * https://leetcode.cn/problems/sudoku-solver/
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final int[] rows = new int[10];
    private final int[] cols = new int[10];
    private final int[] blocks = new int[10];

    public void solveSudoku(char[][] board) {
        List<int[]> blanks = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int shr = board[i][j] - '0';
                    rows[i] |= 1 << shr;
                    cols[j] |= 1 << shr;
                    blocks[blk(i, j)] |= 1 << shr;
                } else blanks.add(new int[]{i, j});
            }
        }

        dfs(blanks, board, 0);
    }

    private boolean dfs(List<int[]> list, char[][] board, int i) {
        if (i == list.size()) return true;

        int[] curr = list.get(i);
        for (int shr = 1; shr <= 9; shr++) {
            if (!contains(curr[0], curr[1], shr)) {
                flip(curr[0], curr[1], shr);
                board[curr[0]][curr[1]] = (char) ('0' + shr);
                if (dfs(list, board, i + 1)) return true;
                flip(curr[0], curr[1], shr);
            }
        }
        return false;
    }

    private boolean contains(int x, int y, int shr) {
        int v = 1 << shr, bid = blk(x, y);
        return (rows[x] & v) != 0 || (cols[y] & v) != 0 || (blocks[bid] & v) != 0;
    }

    private void flip(int x, int y, int shr) {
        rows[x] = flip(rows[x], shr);
        cols[y] = flip(cols[y], shr);
        blocks[blk(x, y)] = flip(blocks[blk(x, y)], shr);
    }

    private int flip(int v, int shr) {
        int b = 1 << shr;
        if ((v & b) == 0) return v | b;
        return v & (~b);
    }

    private int blk(int i, int j) { return 3 * (i / 3) + j / 3; }

    public static void main(String[] args) {
        char[][] board = new char[][]{
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        new Solution().solveSudoku(board);
        PrettyPrinter.println(board);
    }

}
