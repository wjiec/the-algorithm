package problem.p417pacificatlanticwaterflow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 417. Pacific Atlantic Water Flow
 *
 * https://leetcode-cn.com/problems/pacific-atlantic-water-flow/
 *
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
 * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches
 * the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights
 * where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells
 * directly north, south, east, and west if the neighboring cell's height is less than or equal to
 * the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water
 * can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 */

public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        for (int i = 0; i < m; i++) pacific[i][0] = true;
        for (int i = 0; i < n; i++) pacific[0][i] = true;
        bfs(heights, pacific);

        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) atlantic[i][n - 1] = true;
        for (int i = 0; i < n; i++) atlantic[m - 1][i] = true;
        bfs(heights, atlantic);

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    ans.add(List.of(i, j));
                }
            }
        }
        return ans;
    }

    private void bfs(int[][] heights, boolean[][] map) {
        int m = heights.length, n = heights[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]) queue.add(new int[]{i, j});
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int x = curr[0], y = curr[1];
            for (var dir : dirs) {
                int a = x + dir[0], b = y + dir[1];
                if (a >= 0 && b >= 0 && a < m && b < n && !map[a][b] && heights[a][b] >= heights[x][y]) {
                    queue.add(new int[]{a, b});
                    map[a][b] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().pacificAtlantic(new int[][]{
            {1,2,2,3,5},
            {3,2,3,4,4},
            {2,4,5,3,1},
            {6,7,1,4,5},
            {5,1,1,2,4}
        }));

        System.out.println(new Solution().pacificAtlantic(new int[][]{
            {2,1},
            {1,2}
        }));
    }

}
