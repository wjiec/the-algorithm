package weekly.w437.D;

import java.util.HashMap;
import java.util.Map;

/**
 * 3459. Length of Longest V-Shaped Diagonal Segment
 *
 * https://leetcode.cn/contest/weekly-contest-437/problems/length-of-longest-v-shaped-diagonal-segment/
 *
 * You are given a 2D integer matrix grid of size n x m, where each element is either 0, 1, or 2.
 *
 * A V-shaped diagonal segment is defined as:
 *
 * The segment starts with 1.
 * The subsequent elements follow this infinite sequence: 2, 0, 2, 0, ....
 *
 * The segment:
 * Starts along a diagonal direction (top-left to bottom-right, bottom-right to top-left,
 * top-right to bottom-left, or bottom-left to top-right).
 *
 * Continues the sequence in the same diagonal direction.
 *
 * Makes at most one clockwise 90-degree turn to another diagonal direction while maintaining the sequence.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    public int lenOfVDiagonal(int[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // 实际上就是一行或者一列选择一个, 看走的方向进行剪枝

                    // 往上走, 检查行是否还够
                    if (i + 1 > ans) ans = Math.max(ans, 1 + dfs(grid, i, j, 0, -1, -1, false));
                    // 往右走, 检查剩余的列是否还够
                    if (n - j + 1 > ans) ans = Math.max(ans, 1 + dfs(grid, i, j, 0, -1, 1, false));
                    // 往左走, 检查剩余的列是否还够
                    if (m - i + 1 > ans) ans = Math.max(ans, 1 + dfs(grid, i, j, 0, 1, 1, false));
                    // 往下走, 检查剩余的行是否还够
                    if (j + 1 > ans) ans = Math.max(ans, 1 + dfs(grid, i, j, 0, 1, -1, false));
                }
            }
        }
        return ans;
    }

    private final Map<Long, Integer> memo = new HashMap<>();

    // 当前要执行第 c 次操作, turn 表示是否是折过之后的, c & 1 表示取 {2, 0}
    private int dfs(int[][] grid, int x, int y, int c, int dx, int dy, boolean turn) {
        int m = grid.length, n = grid[0].length;

        // 构造记忆化搜索的 key
        long key = x; key = (key << 10) | y;
        key = (key << 2) | (dx + 1); key = (key << 2) | (dy + 1);
        key = (key << 1) | (turn ? 1 : 0);
        if (memo.containsKey(key)) return memo.get(key);

        // 下一步可以跳的位置
        int nx = x + dx, ny = y + dy, nv = (c & 1) == 0 ? 2 : 0;
        if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
            // 如果已经折过了, 那么就没办法继续了
            if (turn) return 0;

            // 否则还可以考虑抢救一下, 根据表修改转换方向
            if (dx == -1 && dy == -1) { dy = 1; }
            else if (dx == -1 && dy == 1) { dx = 1; }
            else if (dx == 1 && dy == -1) { dx = -1; }
            else if (dx == 1 && dy == 1) { dy = -1; }
            return dfs(grid, x, y, c, dx, dy, true);
        }

        int ans = 0;
        // 检查下一步是否可以跳
        if (grid[nx][ny] == nv) ans = Math.max(ans, 1 + dfs(grid, nx, ny, c + 1, dx, dy, turn));

        // 或者可以直接转方向
        if (!turn && grid[x][y] != 1) {
            if (dx == -1 && dy == -1) { dy = 1; }
            else if (dx == -1 && dy == 1) { dx = 1; }
            else if (dx == 1 && dy == -1) { dx = -1; }
            else if (dx == 1 && dy == 1) { dy = -1; }
            ans = Math.max(ans, dfs(grid, x, y, c, dx, dy, true));
        }

        memo.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().lenOfVDiagonal(new int[][]{
            {0,0,1,0},
            {0,2,2,0}
        }) == 3;

        assert new Solution().lenOfVDiagonal(new int[][]{{2,2,1,2,2},{2,0,2,2,0},{2,0,1,1,0},{1,0,2,2,2},{2,0,0,2,2}}) == 5;
        assert new Solution().lenOfVDiagonal(new int[][]{
            {2,2,2,2,2},
            {2,0,2,2,0},
            {2,0,1,1,0},
            {1,0,2,2,2},
            {2,0,0,2,2}
        }) == 4;
        assert new Solution().lenOfVDiagonal(new int[][]{{1,2,2,2,2},{2,2,2,2,0},{2,0,0,0,0},{0,0,2,2,2},{2,0,0,2,0}}) == 5;
        assert new Solution().lenOfVDiagonal(new int[][]{{1}}) == 1;
    }

}
