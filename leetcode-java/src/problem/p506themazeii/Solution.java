package problem.p506themazeii;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 505. The Maze II
 *
 * https://leetcode.cn/problems/the-maze-ii/
 *
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
 * The ball can go through the empty spaces by rolling up, down, left or right, but it won't
 * stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol]
 * and destination = [destinationrow, destinationcol], return the shortest distance for the ball to stop
 * at the destination. If the ball cannot stop at destination, return -1.
 *
 * The distance is the number of empty spaces traveled by the ball from the start position (excluded) to
 * the destination (included).
 *
 * You may assume that the borders of the maze are all walls (see examples).
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;

        int[][] visited = new int[m][n];
        for (var row : visited) Arrays.fill(row, Integer.MAX_VALUE);
        visited[start[0]][start[1]] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            for (var dir : dirs) {
                int[] next = rolling(maze, curr[0], curr[1], dir[0], dir[1]);
                int nextCount = visited[curr[0]][curr[1]] + Math.abs(next[0] - curr[0]) + Math.abs(next[1] - curr[1]);
                if (nextCount < visited[next[0]][next[1]]) {
                    queue.add(new int[]{next[0], next[1]});
                    visited[next[0]][next[1]] = nextCount;
                }
            }
        }
        return visited[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : visited[destination[0]][destination[1]];
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
