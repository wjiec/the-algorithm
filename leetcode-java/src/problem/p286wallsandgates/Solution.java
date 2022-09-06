package problem.p286wallsandgates;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 286. Walls and Gates
 *
 * https://leetcode.cn/problems/walls-and-gates/
 *
 * You are given an m x n grid rooms initialized with these three possible values.
 *
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF
 * as you may assume that the distance to a gate is less than 2147483647.
 *
 * Fill each empty room with the distance to its nearest gate. If it is impossible to
 * reach a gate, it should be filled with INF.
 */

public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length, n = rooms[0].length;

        // [x, y, dist]
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[]{i, j, 0});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            for (var dir : dirs) {
                int x = curr[0] + dir[0], y = curr[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (rooms[x][y] > curr[2] + 1) {
                        rooms[x][y] = curr[2] + 1;
                        queue.add(new int[]{x, y, curr[2] + 1});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] r1 = new int[][]{
            {2147483647,-1,0,2147483647},
            {2147483647,2147483647,2147483647,-1},
            {2147483647,-1,2147483647,-1},
            {0,-1,2147483647,2147483647}
        };
        new Solution().wallsAndGates(r1);
        assert Checker.check(r1, new int[][]{{3,-1,0,1},{2,2,1,-1},{1,-1,2,-1},{0,-1,3,4}});
    }

}
