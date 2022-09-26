package problem.p1926nearestexitfromentranceinmaze;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1926. Nearest Exit from Entrance in Maze
 *
 * https://leetcode.cn/problems/nearest-exit-from-entrance-in-maze/
 *
 * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and
 * walls (represented as '+'). You are also given the entrance of the maze, where
 * entrance = [entrancerow, entrancecol] denotes the row and column of the cell
 * you are initially standing at.
 *
 * In one step, you can move one cell up, down, left, or right.
 * You cannot step into a cell with a wall, and you cannot step outside the maze.
 * Your goal is to find the nearest exit from the entrance. An exit is defined as an empty
 * cell that is at the border of the maze. The entrance does not count as an exit.
 *
 * Return the number of steps in the shortest path from the entrance to the nearest
 * exit, or -1 if no such path exists.
 */

public class Solution {

    private final int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length, n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[entrance[0]][entrance[1]] = true;

        // [x, y, step]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{entrance[0], entrance[1], 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int x = curr[0], y = curr[1], s = curr[2];
            for (var dir : dirs) {
                int dx = x + dir[0], dy = y + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && !visited[dx][dy] && maze[dx][dy] == '.') {
                    visited[dx][dy] = true;
                    if (dx == 0 || dy == 0 || dx == m - 1 || dy == n - 1) return s + 1;
                    queue.add(new int[]{dx, dy, s + 1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().nearestExit(new char[][]{
            {'+','+','.','+'},
            {'.','.','.','+'},
            {'+','+','+','.'}
        }, new int[]{1,2}) == 1;

        assert new Solution().nearestExit(new char[][]{
            {'+','+','+'},
            {'.','.','.'},
            {'+','+','+'}
        }, new int[]{1,0}) == 2;

        assert new Solution().nearestExit(new char[][]{
            {'.','+'}
        }, new int[]{0,0}) == -1;
    }

}
