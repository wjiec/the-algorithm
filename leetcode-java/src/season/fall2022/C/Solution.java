package season.fall2022.C;

import common.PrettyPrinter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 3. 弹珠游戏
 *
 * https://leetcode.cn/contest/season/2022-fall/problems/EXvqDp/
 *
 * 欢迎各位来到「力扣嘉年华」，接下来将为各位介绍在活动中广受好评的弹珠游戏。
 *
 * N*M 大小的弹珠盘的初始状态信息记录于一维字符串型数组 plate 中，数组中的每个元素为仅由 "O"、"W"、"E"、"." 组成的字符串。其中：
 *
 * "O" 表示弹珠洞（弹珠到达后会落入洞中，并停止前进）；
 * "W" 表示逆时针转向器（弹珠经过时方向将逆时针旋转 90 度）；
 * "E" 表示顺时针转向器（弹珠经过时方向将顺时针旋转 90 度）；
 * "." 表示空白区域（弹珠可通行）。
 * 游戏规则要求仅能在边缘位置的 空白区域 处（弹珠盘的四角除外）沿 与边缘垂直 的方向打入弹珠，并且打入后的每颗弹珠最多能 前进 num 步。
 *
 * 请返回符合上述要求且可以使弹珠最终入洞的所有打入位置。你可以 按任意顺序 返回答案。
 *
 * 注意：
 *
 * 若弹珠已到达弹珠盘边缘并且仍沿着出界方向继续前进，则将直接出界。
 */

public class Solution {

    public int[][] ballGame(int num, String[] plate) {
        int m = plate.length, n = plate[0].length();
        char[][] board = new char[plate.length][];
        for (int i = 0; i < plate.length; i++) {
            board[i] = plate[i].toCharArray();
        }

        // [x, y, direct, step]: 1 up, 2 down, 3 left, 4 right
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    queue.add(new int[]{i, j, 1, 0});
                    queue.add(new int[]{i, j, 2, 0});
                    queue.add(new int[]{i, j, 3, 0});
                    queue.add(new int[]{i, j, 4, 0});
                }
            }
        }

        List<int[]> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int x = curr[0], y = curr[1], d = curr[2], s = curr[3];
            if (s > num) continue;

            switch (d) {
                case 1 -> x--;
                case 2 -> x++;
                case 3 -> y--;
                case 4 -> y++;
            }

            int ox = curr[0], oy = curr[1];
            if (x < 0 || x == m || y < 0 || y == n) {
                if (board[ox][oy] == '.' && !isCoron(ox, oy, m, n)) {
                    ans.add(new int[]{curr[0], curr[1]});
                }
                continue;
            }

            switch (board[x][y]) {
                case '.' -> queue.add(new int[]{x, y, d, s + 1});
                case 'E' -> queue.add(new int[]{x, y, anticlockwise(d), s + 1});
                case 'W' -> queue.add(new int[]{x, y, clockwise(d), s + 1});
            }
        }
        if (ans.isEmpty()) return new int[][]{};
        return ans.toArray(new int[][]{});
    }

    // 1 up, 2 down, 3 left, 4 right
    private int anticlockwise(int d) {
        switch (d) {
            case 1 -> { return 3; }
            case 2 -> { return 4; }
            case 3 -> { return 2; }
            case 4 -> { return 1; }
        }
        return d;
    }

    // 1 up, 2 down, 3 left, 4 right
    private int clockwise(int d) {
        switch (d) {
            case 1 -> { return 4; }
            case 2 -> { return 3; }
            case 3 -> { return 1; }
            case 4 -> { return 2; }
        }
        return d;
    }

    private boolean isCoron(int x, int y, int m, int n) {
        if (x == 0 && y == 0) return true;
        if (x == m - 1 && y == 0) return true;
        if (x == 0 && y == n - 1) return true;
        return x == m - 1 && y == n - 1;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().ballGame(4, new String[]{"..E.",".EOW","..W."}));
        PrettyPrinter.println(new Solution().ballGame(5, new String[]{".....","..E..",".WO..","....."}));
        PrettyPrinter.println(new Solution().ballGame(3, new String[]{".....","....O","....O","....."}));
        PrettyPrinter.println(new Solution().ballGame(3, new String[]{".....","....O","....O","....."}));
    }

}
