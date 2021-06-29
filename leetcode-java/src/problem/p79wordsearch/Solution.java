package problem.p79wordsearch;

/**
 * 79. Word Search
 *
 * https://leetcode-cn.com/problems/word-search/
 *
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 *
 * The same letter cell may not be used more than once.
 */

public class Solution {

    public boolean exist(char[][] board, String word) {
        char[] target = word.toCharArray();
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == target[0]) {
                    if (findExists(board, target, i, j, 1)) return true;
                }
            }
        }
        return false;
    }

    private final int[] dx = new int[]{0, 1, 0, -1};
    private final int[] dy = new int[]{1, 0, -1, 0};

    private boolean findExists(char[][] board, char[] target, int x, int y, int idx) {
        if (idx == target.length) return true;

        board[x][y] = 0; // mark visited
        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i], yy = y + dy[i];
            if (xx >= 0 && yy >= 0 && xx < board.length && yy < board[0].length) {
                if (board[xx][yy] == target[idx]) {
                    if (findExists(board, target, xx, yy, idx + 1)) return true;
                }
            }
        }
        board[x][y] = target[idx - 1];

        return false;
    }

    public static void main(String[] args) {
        assert new Solution().exist(new char[][]{
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        }, "ABCCED");

        assert new Solution().exist(new char[][]{
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        }, "SEE");

        assert !new Solution().exist(new char[][]{
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        }, "ABCB");

        assert !new Solution().exist(new char[][]{
            {'a','b'},
            {'c','d'}
        }, "abcd");

        assert !new Solution().exist(new char[][]{
            {'a','a'},
        }, "aaa");

        assert new Solution().exist(new char[][]{
            {'C','A','A'},
            {'A','A','A'},
            {'B','C','D'},
        }, "AAB");
    }

}
