package problem.p2018checkifwordcanbeplacedincrossword;

/**
 * 2018. Check if Word Can Be Placed In Crossword
 *
 * https://leetcode.cn/problems/check-if-word-can-be-placed-in-crossword/
 *
 * You are given an m x n matrix board, representing the current state of a crossword puzzle.
 * The crossword contains lowercase English letters (from solved words), ' ' to represent any empty
 * cells, and '#' to represent any blocked cells.
 *
 * A word can be placed horizontally (left to right or right to left) or vertically (top to bottom
 * or bottom to top) in the board if:
 *
 * It does not occupy a cell containing the character '#'.
 * The cell each letter is placed in must either be ' ' (empty) or match the letter already on the board.
 * There must not be any empty cells ' ' or other lowercase letters directly left or right of the word
 * if the word was placed horizontally.
 * There must not be any empty cells ' ' or other lowercase letters directly above or below the word
 * if the word was placed vertically.
 *
 * Given a string word, return true if word can be placed in board, or false otherwise.
 */

public class Solution {

    public boolean placeWordInCrossword(char[][] board, String word) {
        char[] chars = word.toCharArray();
        int m = board.length, n = board[0].length, l = chars.length;

        for (var row : board) {
            for (int j = 0; j < n; j++) {
                if (row[j] == '#') continue;

                int jl = j; boolean fwd = true, rev = true;
                for (; j < n && row[j] != '#'; j++) {
                    if (j - jl >= l || (row[j] != ' ' && row[j] != chars[j - jl])) fwd = false;
                    if (j - jl >= l || (row[j] != ' ' && row[j] != chars[l - 1 - j + jl])) rev = false;
                }
                if ((fwd || rev) && j - jl == l) return true;
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (board[i][j] == '#') continue;

                int il = i; boolean fwd = true, rev = true;
                for (; i < m && board[i][j] != '#'; i++) {
                    if (i - il >= l || (board[i][j] != ' ' && board[i][j] != chars[i - il])) fwd = false;
                    if (i - il >= l || (board[i][j] != ' ' && board[i][j] != chars[l - 1 - i + il])) rev = false;
                }
                if ((fwd || rev) && i - il == l) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        assert new Solution().placeWordInCrossword(new char[][]{
            {'#', ' ', '#'},
            {' ', ' ', '#'},
            {'#', 'c', ' '}
        }, "abc");

        assert !new Solution().placeWordInCrossword(new char[][]{
            {' ', '#', 'a'},
            {' ', '#', 'c'},
            {' ', '#', 'a'}
        }, "ac");

        assert new Solution().placeWordInCrossword(new char[][]{
            {'#', ' ', '#'},
            {' ', ' ', '#'},
            {'#', ' ', 'c'}
        }, "ac");
    }

}
