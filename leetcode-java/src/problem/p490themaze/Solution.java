package problem.p490themaze;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 490. The Maze
 *
 * https://leetcode.cn/problems/the-maze/
 *
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
 * The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop
 * rolling until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol]
 * and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination,
 * otherwise return false.
 *
 * You may assume that the borders of the maze are all walls (see examples).
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;

        boolean[][] visited = new boolean[m][n];
        visited[start[0]][start[1]] = true;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            if (curr[0] == destination[0] && curr[1] == destination[1]) return true;
            for (var dir : dirs) {
                int[] next = rolling(maze, curr[0], curr[1], dir[0], dir[1]);
                if (!visited[next[0]][next[1]]) {
                    queue.add(next);
                    visited[next[0]][next[1]] = true;
                }
            }
        }
        return false;
    }

    private int[] rolling(int[][] maze, int x, int y, int dx, int dy) {
        int m = maze.length, n = maze[0].length;
        while (x + dx >= 0 && x + dx < m && y + dy >= 0 && y + dy < n && maze[x + dx][y + dy] == 0) {
            x += dx; y += dy;
        }
        return new int[]{x, y};
    }

    public static void main(String[] args) {
    }

}
