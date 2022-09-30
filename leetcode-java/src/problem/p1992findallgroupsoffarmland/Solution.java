package problem.p1992findallgroupsoffarmland;

import common.PrettyPrinter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 1992. Find All Groups of Farmland
 *
 * https://leetcode.cn/problems/find-all-groups-of-farmland/
 *
 * You are given a 0-indexed m x n binary matrix land where a 0 represents a
 * hectare of forested land and a 1 represents a hectare of farmland.
 *
 * To keep the land organized, there are designated rectangular areas of hectares that
 * consist entirely of farmland. These rectangular areas are called groups.
 * No two groups are adjacent, meaning farmland in one group is not four-directionally
 * adjacent to another farmland in a different group.
 *
 * land can be represented by a coordinate system where the top left corner of
 * land is (0, 0) and the bottom right corner of land is (m-1, n-1).
 * Find the coordinates of the top left and bottom right corner of each
 * group of farmland. A group of farmland with a top left corner at (r1, c1) and
 * a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].
 *
 * Return a 2D array containing the 4-length arrays described above for each
 * group of farmland in land. If there are no groups of farmland, return an
 * empty array. You may return the answer in any order.
 */

public class Solution {

    public int[][] findFarmland(int[][] land) {
        int m = land.length, n = land[0].length;
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1) {
                    ans.add(bfs(land, i, j));
                }
            }
        }
        return ans.toArray(new int[][]{});
    }

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int[] bfs(int[][] land, int x, int y) {
        int m = land.length, n = land[0].length;
        land[x][y] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});

        int rx = x, ry = y;
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            rx = Math.max(rx, curr[0]);
            ry = Math.max(ry, curr[1]);

            for (var dir : dirs) {
                int dx = curr[0] + dir[0];
                int dy = curr[1] + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && land[dx][dy] == 1) {
                    land[dx][dy] = 0;
                    queue.add(new int[]{dx, dy});
                }
            }
        }

        return new int[]{x, y, rx, ry};
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().findFarmland(new int[][]{{1,0,0},{0,1,1},{0,1,1}}));
        PrettyPrinter.println(new Solution().findFarmland(new int[][]{{1,1},{1,1}}));
        PrettyPrinter.println(new Solution().findFarmland(new int[][]{{0}}));
    }

}
