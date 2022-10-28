package lcp.p41;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LCP 41. 黑白翻转棋
 *
 * https://leetcode.cn/problems/fHi6rV/
 *
 * 在 n*m 大小的棋盘中，有黑白两种棋子，黑棋记作字母 "X", 白棋记作字母 "O"，空余位置记作 "."。
 * 当落下的棋子与其他相同颜色的棋子在行、列或对角线完全包围（中间不存在空白位置）另一种颜色的棋子，则可以翻转这些棋子的颜色。
 */

public class Solution {

    private final char BLANK = '.';
    private final char BLACK = 'X';

    public int flipChess(String[] chessboard) {
        char[][] board = new char[chessboard.length][];
        for (int i = 0; i < chessboard.length; i++) {
            board[i] = chessboard[i].toCharArray();
        }

        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == BLANK) {
                    char[][] nb = copy(board);
                    nb[i][j] = BLACK;
                    ans = Math.max(ans, dfs(nb, i, j));
                }
            }
        }
        return ans;
    }

    private char[][] copy(char[][] board) {
        char[][] nb = new char[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, nb[i], 0, board[0].length);
        }
        return nb;
    }

    private final int[][] dirs = new int[][]{
        {0, 1},   // right
        {0, -1},  // left
        {1, 0},   // down
        {-1, 0},  // up
        {-1, -1}, // left-up
        {-1, 1},  // right-up
        {1, 1},   // right-down
        {1, -1},  // left-down
    };

    private int dfs(char[][] board, int x, int y) {
        int ans = 0, m = board.length, n = board[0].length;
        for (var dir : dirs) {
            List<int[]> paths = next(board, x, y, dir[0], dir[1], m, n);
            if (!paths.isEmpty()) {
                for (var xy : paths) board[xy[0]][xy[1]] = BLACK;
                for (var xy : paths) ans += dfs(board, xy[0], xy[1]);
            }
            ans += paths.size();
        }

        return ans;
    }

    private List<int[]> next(char[][] board, int x, int y, int dx, int dy, int m, int n) {
        List<int[]> ans = new ArrayList<>();
        for (x += dx, y += dy; x >= 0 && x < m && y >= 0 && y < n; x += dx, y += dy) {
            if (board[x][y] == BLANK) return Collections.emptyList();
            if (board[x][y] == BLACK) return ans;

            ans.add(new int[]{x, y});
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        assert new Solution().flipChess(new String[]{
            ".X.",
            ".O.",
            "XO."
        }) == 2;
        assert new Solution().flipChess(new String[]{
            ".......",
            ".......",
            ".......",
            "X......",
            ".O.....",
            "..O....",
            "....OOX"
        }) == 4;
    }

}
