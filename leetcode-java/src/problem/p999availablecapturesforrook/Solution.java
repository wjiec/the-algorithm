package problem.p999availablecapturesforrook;

/**
 * 999. Available Captures for Rook
 *
 * https://leetcode-cn.com/problems/available-captures-for-rook/
 *
 * On an 8 x 8 chessboard, there is exactly one white rook 'R' and some number of white bishops 'B',
 * black pawns 'p', and empty squares '.'.
 *
 * When the rook moves, it chooses one of four cardinal directions (north, east, south, or west),
 * then moves in that direction until it chooses to stop, reaches the edge of the board, captures a black pawn,
 * or is blocked by a white bishop.
 *
 * A rook is considered attacking a pawn if the rook can capture the pawn on the rook's turn.
 * The number of available captures for the white rook is the number of pawns that the rook is attacking.
 *
 * Return the number of available captures for the white rook.
 */

public class Solution {

    public int numRookCaptures(char[][] board) {
        int x = 0, y = 0, ans = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    x = i; y = j; break;
                }
            }
        }

        boolean px = true, nx = true;
        for (int i = 1; i < 8; i++) {
            if (px && x + i < 8 && board[x + i][y] != '.') {
                px = false;
                if (board[x + i][y] == 'p') ans++;
            }
            if (nx && x - i >= 0 && board[x - i][y] != '.') {
                nx = false;
                if (board[x - i][y] == 'p') ans++;
            }

            if (!px && !nx) break;
        }

        boolean py = true, ny = true;
        for (int i = 1; i < 8; i++) {
            if (py && y + i < 8 && board[x][y + i] != '.') {
                py = false;
                if (board[x][y + i] == 'p') ans++;
            }
            if (ny && y - i >= 0 && board[x][y - i] != '.') {
                ny = false;
                if (board[x][y - i] == 'p') ans++;
            }

            if (!py && !ny) break;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numRookCaptures(new char[][]{
            {'.','.','.','.','.','.','.','.'},
            {'.','.','.','p','.','.','.','.'},
            {'.','.','.','R','.','.','.','p'},
            {'.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.'},
            {'.','.','.','p','.','.','.','.'},
            {'.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.'}
        }) == 3;

        assert new Solution().numRookCaptures(new char[][]{
            {'.','.','.','.','.','.','.','.'},
            {'.','p','p','p','p','p','.','.'},
            {'.','p','p','B','p','p','.','.'},
            {'.','p','B','R','B','p','.','.'},
            {'.','p','p','B','p','p','.','.'},
            {'.','p','p','p','p','p','.','.'},
            {'.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.'}
        }) == 0;

        assert new Solution().numRookCaptures(new char[][]{
            {'.','.','.','.','.','.','.','.'},
            {'.','.','.','p','.','.','.','.'},
            {'.','.','.','p','.','.','.','.'},
            {'p','p','.','R','.','p','B','.'},
            {'.','.','.','.','.','.','.','.'},
            {'.','.','.','B','.','.','.','.'},
            {'.','.','.','p','.','.','.','.'},
            {'.','.','.','.','.','.','.','.'}
        }) == 3;
    }

}
