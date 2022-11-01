package lcp.p56;

import common.PrettyPrinter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * LCP 56. 信物传送
 *
 * https://leetcode.cn/problems/6UEx57/
 *
 * 欢迎各位勇者来到力扣城，本次试炼主题为「信物传送」。
 *
 * 本次试炼场地设有若干传送带，matrix[i][j] 表示第 i 行 j 列的传送带运作方向，"^","v","<",">" 这四种符号
 * 分别表示 上、下、左、右 四个方向。信物会随传送带的方向移动。勇者每一次施法操作，可临时变更一处传送带的方向，
 * 在物品经过后传送带恢复原方向。
 *
 * 通关信物初始位于坐标 start处，勇者需要将其移动到坐标 end 处，请返回勇者施法操作的最少次数。
 *
 * 注意：
 * start 和 end 的格式均为 [i,j]
 */

public class Solution {

    private final char[] cdirs = new char[]{'<', '>', '^', 'v'};
    private final int[][] idirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int conveyorBelt(String[] matrix, int[] start, int[] end) {
        char[][] ground = new char[matrix.length][];
        for (int i = 0; i < matrix.length; i++) ground[i] = matrix[i].toCharArray();

        int m = ground.length, n = ground[0].length;
        int[][] visited = new int[m][n];
        Queue<int[]> queue = new ArrayDeque<>();

        int sx = start[0], sy = start[1], ex = end[0], ey = end[1];
        visited[sx][sy] = 0; queue.add(new int[]{sx, sy, 0});
        for (var row : visited) Arrays.fill(row, Integer.MAX_VALUE);

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int x = curr[0], y = curr[1], c = curr[2];

            for (int i = 0; i < cdirs.length; i++) {
                int dx = x + idirs[i][0], dy = y + idirs[i][1];
                int dc = c + (cdirs[i] == ground[x][y] ? 0 : 1);
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && visited[dx][dy] > dc) {
                    visited[dx][dy] = dc;
                    queue.add(new int[]{dx, dy, dc});
                }
            }
        }

        return visited[ex][ey];
    }

    public static void main(String[] args) {
        assert new Solution().conveyorBelt(new String[]{">>v","v^<","<><"}, new int[]{0, 1}, new int[]{2, 0}) == 1;
        assert new Solution().conveyorBelt(new String[]{">>v",">>v","^<<"}, new int[]{0, 0}, new int[]{1, 1}) == 0;
        assert new Solution().conveyorBelt(new String[]{">^^>","<^v>","^v^<"}, new int[]{0, 0}, new int[]{1, 3}) == 3;
    }

}
