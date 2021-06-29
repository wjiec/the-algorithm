package offer.p12juzhenzhongdelujinglcof;

/**
 * 剑指 Offer 12. 矩阵中的路径
 *
 * https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
 *
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
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
