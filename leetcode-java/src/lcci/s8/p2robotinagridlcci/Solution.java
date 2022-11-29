package lcci.s8.p2robotinagridlcci;

import common.PrettyPrinter;

import java.util.*;

/**
 * 面试题 08.02. 迷路的机器人
 *
 * https://leetcode.cn/problems/robot-in-a-grid-lcci/
 *
 * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。
 * 设计一种算法，寻找机器人从左上角移动到右下角的路径。
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。如果没有可行的路径，返回空数组。
 */

public class Solution {

    public List<List<Integer>> pathWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = -grid[i][j];
            }
        }
        if (grid[0][0] < 0) return Collections.emptyList();

        Queue<int[]> queue = new ArrayDeque<>();
        grid[0][0] = 1;
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int x = curr[0], y = curr[1];

            if (x + 1 < m && grid[x + 1][y] == 0) {
                grid[x + 1][y] = grid[x][y] + 1;
                queue.add(new int[]{x + 1, y});
            }
            if (y + 1 < n && grid[x][y + 1] == 0) {
                grid[x][y + 1] = grid[x][y] + 1;
                queue.add(new int[]{x, y + 1});
            }
        }

        int steps = grid[m - 1][n - 1];
        if (steps <= 0) return Collections.emptyList();

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < steps; i++) ans.add(null);
        for (int x = m - 1, y = n - 1; x >= 0 && y >= 0; ) {
            ans.set(grid[x][y] - 1, List.of(x, y));
            if (x - 1 >= 0 && grid[x - 1][y] == grid[x][y] - 1) x -= 1;
            else y -= 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().pathWithObstacles(new int[][]{
            {1}
        }));

        System.out.println(new Solution().pathWithObstacles(new int[][]{
            {0,0,0},
            {0,1,0},
            {0,0,0}
        }));
    }

}
