package lcs.p3;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LCS 03. 主题空间
 *
 * https://leetcode.cn/problems/YesdPw/
 *
 * 「以扣会友」线下活动所在场地由若干主题空间与走廊组成，场地的地图记作由一维字符串型
 * 数组 grid，字符串中仅包含 "0"～"5" 这 6 个字符。
 *
 * 地图上每一个字符代表面积为 1 的区域，其中 "0" 表示走廊，其他字符表示主题空间。
 * 相同且连续（连续指上、下、左、右四个方向连接）的字符组成同一个主题空间。
 *
 * 假如整个 grid 区域的外侧均为走廊。请问，不与走廊直接相邻的主题空间的最大面积是多少？
 * 如果不存在这样的空间请返回 0。
 */

public class Solution {

    public int largestArea(String[] grid) {
        char[][] board = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) board[i] = grid[i].toCharArray();

        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '0' && !visited[i][j]) {
                    ans = Math.max(ans, bfs(board, visited, i, j));
                }
            }
        }
        return ans;
    }

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int bfs(char[][] board, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        boolean connection = true;
        int m = board.length, n = board[0].length, count = 1;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            for (var dir : dirs) {
                int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n) {
                    connection = connection && board[dx][dy] != '0';
                    if (!visited[dx][dy] && board[dx][dy] == board[x][y]) {
                        queue.add(new int[]{dx, dy});
                        visited[dx][dy] = true; count++;
                    }
                } else connection = false;
            }
        }

        return connection ? count : 0;
    }

    public static void main(String[] args) {
        assert new Solution().largestArea(new String[]{"110","231","221"}) == 1;
        assert new Solution().largestArea(new String[]{"11111100000","21243101111","21224101221","11111101111"}) == 3;
    }

}
