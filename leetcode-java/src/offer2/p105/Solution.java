package offer2.p105;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 105. 岛屿的最大面积
 *
 * https://leetcode.cn/problems/ZL6zAn/
 *
 * 给定一个由 0 和 1 组成的非空二维数组 grid ，用来表示海洋岛屿地图。
 *
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
 * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    ans = Math.max(ans, bfs(grid, i, j));
                }
            }
        }
        return ans;
    }

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int bfs(int[][] grid, int x, int y) {
        int ans = 0, m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();

        grid[x][y] = 0;
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();

            ans++;
            for (var dir : dirs) {
                int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && grid[dx][dy] == 1) {
                    grid[dx][dy] = 0;
                    queue.add(new int[]{dx, dy});
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxAreaOfIsland(new int[][]{
            {0,0,1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,1,0,0,0,0,0,0,0,0},
            {0,1,0,0,1,1,0,0,1,0,1,0,0},
            {0,1,0,0,1,1,0,0,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0}
        }) == 6;

        assert new Solution().maxAreaOfIsland(new int[][]{{0,0,0,0,0,0,0,0}}) == 0;
    }

}
