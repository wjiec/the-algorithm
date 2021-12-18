package daily.d211218p419battleshipsinaboard;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 419. Battleships in a Board
 *
 * https://leetcode-cn.com/problems/battleships-in-a-board/
 *
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.',
 * return the number of the battleships on board.
 *
 * Battleships can only be placed horizontally or vertically on board.
 *
 * In other words, they can only be made of the shape 1 x k
 * (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size.
 *
 * At least one horizontal or vertical cell separates between
 * two battleships (i.e., there are no adjacent battleships).
 */

public class Solution {

    private int count = 0;
    private final int[] dx = new int[]{1, 0, -1, 0};
    private final int[] dy = new int[]{0, 1, 0, -1};

    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    find(board, i, j);
                }
            }
        }
        return count;
    }

    private void find(char[][] board, int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});

        count++;
        while (!queue.isEmpty()) {
            var xy = queue.remove();
            board[xy[0]][xy[1]] = ' ';
            for (int i = 0; i < 4; i++) {
                int a = xy[0] + dx[i], b = xy[1] + dy[i];
                if (a >= 0 && a < board.length && b >= 0 && b < board[0].length && board[a][b] == 'X') {
                    queue.add(new int[]{a, b});
                }
            }
        }
    }

    public int countBattleshipsOptimize(char[][] board) {
        int ans = 0, m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    if ((i == 0 || board[i - 1][j] == '.') && (j == 0 || board[i][j - 1] == '.')) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countBattleships(new char[][]{
            {'X','.','.','X'},
            {'.','.','.','X'},
            {'.','.','.','X'}
        }) == 2;

        assert new Solution().countBattleships(new char[][]{
            {'.'}
        }) == 0;

        assert new Solution().countBattleshipsOptimize(new char[][]{
            {'X','.','.','X'},
            {'.','.','.','X'},
            {'.','.','.','X'}
        }) == 2;

        assert new Solution().countBattleshipsOptimize(new char[][]{
            {'.'}
        }) == 0;
    }

}
