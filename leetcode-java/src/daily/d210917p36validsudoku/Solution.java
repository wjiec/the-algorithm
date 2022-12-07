package daily.d210917p36validsudoku;

/**
 * 36. Valid Sudoku
 *
 * https://leetcode-cn.com/problems/valid-sudoku/
 *
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!checkSudoku(board, 0, 9, i, i + 1)) {
                return false;
            }
            if (!checkSudoku(board, i, i + 1, 0, 9)) {
                return false;
            }

            if (i % 3 == 0) {
                for (int j = 0; j < 9; j += 3) {
                    if (!checkSudoku(board, i, i + 3, j, j + 3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkSudoku(char[][] board, int x0, int x1, int y0, int y1) {
        boolean[] map = new boolean[10];
        for (int x = x0; x < x1; x++) {
            for (int y = y0; y < y1; y++) {
                if (board[x][y] != '.') {
                    int v = board[x][y] - '0';
                    if (map[v]) return false;
                    map[v] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().isValidSudoku(new char[][]{
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        });

        assert !new Solution().isValidSudoku(new char[][]{
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        });
    }

}
