package daily.d220129p1765mapofhighestpeak;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 1765. Map of Highest Peak
 *
 * https://leetcode-cn.com/problems/map-of-highest-peak/
 *
 * You are given an integer matrix isWater of size m x n that represents a map of land and water cells.
 *
 * If isWater[i][j] == 0, cell (i, j) is a land cell.
 * If isWater[i][j] == 1, cell (i, j) is a water cell.
 * You must assign each cell a height in a way that follows these rules:
 *
 * The height of each cell must be non-negative.
 *
 * If the cell is a water cell, its height must be 0.
 *
 * Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another cell
 * if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
 *
 * Find an assignment of heights such that the maximum height in the matrix is maximized.
 *
 * Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height.
 *
 * If there are multiple solutions, return any of them.
 */

public class Solution {

    private final int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;

        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(ans[i], -1);

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    ans[i][j] = 0;
                    queue.add(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] xy = queue.remove();
            for (var dir : dirs) {
                int x = xy[0] + dir[0], y = xy[1] + dir[1];
                if (0 <= x && x < m && 0 <= y && y < n && ans[x][y] == -1) {
                    ans[x][y] = ans[xy[0]][xy[1]] + 1;
                    queue.add(new int[]{x, y});
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().highestPeak(new int[][]{
            {0, 1},
            {0, 0}
        })));

        System.out.println(Arrays.deepToString(new Solution().highestPeak(new int[][]{
            {0, 0, 1},
            {1, 0, 0},
            {0, 0, 0}
        })));
    }

}
