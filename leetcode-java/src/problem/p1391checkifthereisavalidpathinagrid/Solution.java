package problem.p1391checkifthereisavalidpathinagrid;

import common.PrettyPrinter;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Set;

/**
 * 1391. Check if There is a Valid Path in a Grid
 *
 * https://leetcode.cn/problems/check-if-there-is-a-valid-path-in-a-grid/
 *
 * You are given an m x n grid. Each cell of grid represents a street. The street of grid[i][j] can be:
 *
 * 1 which means a street connecting the left cell and the right cell.
 * 2 which means a street connecting the upper cell and the lower cell.
 * 3 which means a street connecting the left cell and the lower cell.
 * 4 which means a street connecting the right cell and the lower cell.
 * 5 which means a street connecting the left cell and the upper cell.
 * 6 which means a street connecting the right cell and the upper cell.
 *
 * You will initially start at the street of the upper-left cell (0, 0). A valid path
 * in the grid is a path that starts from the upper left cell (0, 0) and ends at the
 * bottom-right cell (m - 1, n - 1). The path should only follow the streets.
 *
 * Notice that you are not allowed to change any street.
 * Return true if there is a valid path in the grid or false otherwise.
 */

@SuppressWarnings("unchecked")
public class Solution {

    private final int[] top = new int[]{-1, 0};
    private final int[] right = new int[]{0, 1};
    private final int[] bottom = new int[]{1, 0};
    private final int[] left = new int[]{0, -1};

    private final int[][][] dirs = new int[][][]{
        null,
        { left, right }, // 1
        { top, bottom }, // 2
        { left, bottom }, // 3
        { right, bottom }, // 4
        { top, left }, // 5
        { top, right } // 6
    };

    private final Set<Integer> tSet = Set.of(2, 3, 4);
    private final Set<Integer> rSet = Set.of(1, 3, 5);
    private final Set<Integer> bSet = Set.of(2, 5, 6);
    private final Set<Integer> lSet = Set.of(1, 4, 6);

    private final Set<Integer>[][] cond = new Set[][]{
        null,
        {lSet, rSet}, // 1
        {tSet, bSet}, // 2
        {lSet, bSet}, // 3
        {rSet, bSet}, // 4
        {tSet, lSet}, // 5
        {tSet, rSet}, // 6
    };

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int x = curr[0], y = curr[1];
            int[][] dirs = this.dirs[grid[x][y]];
            for (int i = 0; i < dirs.length; i++) {
                int dx = x + dirs[i][0], dy = y + dirs[i][1];
                Set<Integer> cond = this.cond[grid[x][y]][i];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && !visited[dx][dy] && cond.contains(grid[dx][dy])) {
                    visited[dx][dy] = true;
                    queue.add(new int[]{dx, dy});
                }
            }
        }

        return visited[m - 1][n - 1];
    }

    public static void main(String[] args) {
        assert new Solution().hasValidPath(new int[][]{{2,4,3},{6,5,2}});
        assert !new Solution().hasValidPath(new int[][]{{1,2,1},{1,2,1}});
        assert !new Solution().hasValidPath(new int[][]{{1,1,2}});
        assert new Solution().hasValidPath(new int[][]{{1,1,1,1,1,1,3}});
        assert new Solution().hasValidPath(new int[][]{{2},{2},{2},{2},{2},{2},{6}});
    }

}
