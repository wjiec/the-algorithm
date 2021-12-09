package daily.d211209p794validtictactoestate;

/**
 * 794. Valid Tic-Tac-Toe State
 *
 * https://leetcode-cn.com/problems/valid-tic-tac-toe-state/
 *
 * Given a Tic-Tac-Toe board as a string array board, return true if and only if
 * it is possible to reach this board position during the course of a valid tic-tac-toe game.
 *
 * The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'.
 *
 * The ' ' character represents an empty square.
 *
 * Here are the rules of Tic-Tac-Toe:
 *
 * Players take turns placing characters into empty squares ' '.
 * The first player always places 'X' characters, while the second player always places 'O' characters.
 * 'X' and 'O' characters are always placed into empty squares, never filled ones.
 * The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 */

public class Solution {

    public boolean validTicTacToe(String[] board) {
        int x = 0, o = 0;
        char[][] map = new char[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                map[i][j] = board[i].charAt(j);
                switch (map[i][j]) {
                    case 'X': x++; break;
                    case 'O': o++; break;
                }
            }
        }

        if (x < o || x - o > 1) return false;

        boolean oWin = win(map, 'O'), xWin = win(map, 'X');
        if (xWin && !oWin && x == o) return false;
        if (oWin && !xWin && x != o) return false;
        return (oWin != xWin) || !oWin;
    }

    private boolean win(char[][] map, char c) {
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == c && map[i][1] == c && map[i][2] == c) return true;
            if (map[0][i] == c && map[1][i] == c && map[2][i] == c) return true;
        }

        if (map[0][0] == c && map[1][1] == c && map[2][2] == c) return true;
        if (map[0][2] == c && map[1][1] == c && map[2][0] == c) return true;

        return false;
    }

    public static void main(String[] args) {
        assert !new Solution().validTicTacToe(new String[]{
            "OXX",
            "XOX",
            "OXO"
        });
        assert new Solution().validTicTacToe(new String[]{
            "XXX",
            "OOX",
            "OOX"
        });
        assert !new Solution().validTicTacToe(new String[]{
            "XXX",
            "XOO",
            "OO "
        });


        assert !new Solution().validTicTacToe(new String[]{"O  ", "   ", "   "});
        assert !new Solution().validTicTacToe(new String[]{"XOX", " X ", "   "});
        assert !new Solution().validTicTacToe(new String[]{"XXX", "   ", "OOO"});
        assert new Solution().validTicTacToe(new String[]{"XOX", "O O", "XOX"});
    }

}
