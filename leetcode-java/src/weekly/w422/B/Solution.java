package weekly.w422.B;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 3341. Find Minimum Time to Reach Last Room I
 *
 * https://leetcode.cn/contest/weekly-contest-422/problems/find-minimum-time-to-reach-last-room-i/
 *
 * There is a dungeon with n x m rooms arranged as a grid.
 *
 * You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time
 * in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0
 * and can move to an adjacent room. Moving between adjacent rooms takes exactly one second.
 *
 * Return the minimum time to reach the room (n - 1, m - 1).
 *
 * Two rooms are adjacent if they share a common wall, either horizontally or vertically.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // 转换为边权为 1, 且到达节点有最小权重要求的无向图求单源最短路径
    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length, n = moveTime[0].length;

        long[][] dis = new long[m][n];
        for (var row : dis) Arrays.fill(row, Long.MAX_VALUE);
        dis[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingLong(i -> dis[i[0]][i[1]]));
        pq.add(new int[]{0, 0});

        while (!pq.isEmpty()) {
            var curr = pq.remove();
            if (curr[0] == m - 1 && curr[1] == n - 1) break;

            for (var dir : dirs) {
                int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n) {
                    long minCost = Math.max(moveTime[dx][dy] + 1, dis[curr[0]][curr[1]] + 1);
                    if (minCost < dis[dx][dy]) {
                        dis[dx][dy] = minCost;
                        pq.add(new int[]{dx, dy});
                    }
                }
            }
        }

        return (int) dis[m - 1][n - 1];
    }

    public static void main(String[] args) {
        assert new Solution().minTimeToReach(new int[][]{{0, 4}, {4, 4}}) == 6;
        assert new Solution().minTimeToReach(new int[][]{{0, 0, 0}, {0, 0, 0}}) == 3;
        assert new Solution().minTimeToReach(new int[][]{{0, 1}, {1, 2}}) == 3;
    }

}
