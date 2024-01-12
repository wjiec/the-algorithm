package weekly.w379.B;

/**
 * 3001. Minimum Moves to Capture The Queen
 *
 * https://leetcode.cn/contest/weekly-contest-379/problems/minimum-moves-to-capture-the-queen/
 *
 * There is a 1-indexed 8 x 8 chessboard containing 3 pieces.
 *
 * You are given 6 integers a, b, c, d, e, and f where:
 *
 * (a, b) denotes the position of the white rook.
 * (c, d) denotes the position of the white bishop.
 * (e, f) denotes the position of the black queen.
 *
 * Given that you can only move the white pieces, return the minimum
 * number of moves required to capture the black queen.
 *
 * Note that:
 *
 * Rooks can move any number of squares either vertically or horizontally, but cannot jump over other pieces.
 * Bishops can move any number of squares diagonally, but cannot jump over other pieces.
 * A rook or a bishop can capture the queen if it is located in a square that they can move to.
 * The queen does not move.
 */

public class Solution {

    @SuppressWarnings("DuplicatedCode")
    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        for (int x = c, y = d; x >= 0 && y >= 0; x--, y--) {
            if (x == a && y == b) break;
            if (x == e && y == f) return 1;
        }
        for (int x = c, y = d; x <= 8 && y <= 8; x++, y++) {
            if (x == a && y == b) break;
            if (x == e && y == f) return 1;
        }
        for (int x = c, y = d; x <= 8 && y >= 0; x++, y--) {
            if (x == a && y == b) break;
            if (x == e && y == f) return 1;
        }
        for (int x = c, y = d; x >= 0 && y <= 8; x--, y++) {
            if (x == a && y == b) break;
            if (x == e && y == f) return 1;
        }
        for (int x = a, y = b; x <= 8; x++) {
            if (x == c && y == d) break;
            if (x == e && y == f) return 1;
        }
        for (int x = a, y = b; x >= 0; x--) {
            if (x == c && y == d) break;
            if (x == e && y == f) return 1;
        }
        for (int x = a, y = b; y <= 8; y++) {
            if (x == c && y == d) break;
            if (x == e && y == f) return 1;
        }
        for (int x = a, y = b; y >= 0; y--) {
            if (x == c && y == d) break;
            if (x == e && y == f) return 1;
        }

        return 2;
    }

    public static void main(String[] args) {
        assert new Solution().minMovesToCaptureTheQueen(4,3,3,4,2,5) == 1;
        assert new Solution().minMovesToCaptureTheQueen(6,1,7,8,8,7) == 1;
        assert new Solution().minMovesToCaptureTheQueen(5,8,8,8,1,8) == 1;
    }

}
