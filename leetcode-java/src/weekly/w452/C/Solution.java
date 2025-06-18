package weekly.w452.C;

import ability.Benchmark;

import java.util.*;

/**
 * Q3. Minimum Moves to Clean the Classroom
 *
 * https://leetcode.cn/contest/weekly-contest-452/problems/minimum-moves-to-clean-the-classroom/description/
 *
 * You are given an m x n grid classroom where a student volunteer is tasked with cleaning up litter
 * scattered around the room. Each cell in the grid is one of the following:
 *
 * 'S': Starting position of the student
 * 'L': Litter that must be collected (once collected, the cell becomes empty)
 * 'R': Reset area that restores the student's energy to full capacity, regardless of their current energy level (can be used multiple times)
 * 'X': Obstacle the student cannot pass through
 * '.': Empty space
 *
 * You are also given an integer energy, representing the student's maximum energy capacity.
 * The student starts with this energy from the starting position 'S'.
 *
 * Each move to an adjacent cell (up, down, left, or right) costs 1 unit of energy.
 * If the energy reaches 0, the student can only continue if they are on a reset area 'R',
 * which resets the energy to its maximum capacity energy.
 *
 * Return the minimum number of moves required to collect all litter items, or -1 if it's impossible.
 */

public class Solution {

    private static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /** @noinspection DuplicatedCode */
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        char[][] grid = new char[m][];
        for (int i = 0; i < m; i++) grid[i] = classroom[i].toCharArray();

        int sx = 0, sy = 0, cnt = 0;
        int[][] id = new int[m][n];
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                switch (grid[i][j]) {
                    case 'L' -> { points.add(new int[]{i, j}); id[i][j] = cnt++; }
                    case 'R' -> points.add(new int[]{i, j});
                    case 'S' -> { sx = i; sy = j; }
                }
            }
        }
        if (cnt == 0) return 0; // 没有垃圾

        points.add(new int[]{sx, sy});
        int[][][][] distance = new int[m][n][m][n];
        for (var point : points) {
            int px = point[0], py = point[1];

            int[][] dis = distance[px][py];
            for (var row : dis) Arrays.fill(row, Integer.MAX_VALUE);

            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{px, py}); dis[px][py] = 0;
            while (!q.isEmpty()) {
                var curr = q.remove();
                int x = curr[0], y = curr[1], nextStep = dis[x][y] + 1;
                for (var dir : dirs) {
                    int dx = x + dir[0], dy = y + dir[1];
                    if (dx >= 0 && dx < m && dy >= 0 && dy < n && grid[dx][dy] != 'X' && dis[dx][dy] > nextStep) {
                        dis[dx][dy] = nextStep; q.add(new int[]{dx, dy});
                    }
                }
            }
        }
        points.remove(points.size() - 1);

        int[][][][] seen = new int[m][n][energy + 1][1 << cnt];
        for (var a : seen) for (var b : a) for (var c : b) Arrays.fill(c, 1000);

        int ans = Integer.MAX_VALUE;
        Queue<int[]> queue = new ArrayDeque<>();
        seen[sx][sy][energy][(1 << cnt) - 1] = 0;
        // [x, y, energy, mask, step]
        queue.add(new int[]{sx, sy, energy, (1 << cnt) - 1, 0});
        while (!queue.isEmpty()) {
            var curr = queue.remove();
            int x = curr[0], y = curr[1], currEnergy = curr[2], currMask = curr[3], currStep = curr[4];
            if (currMask == 0) { ans = Math.min(ans, currStep); continue; }

            // 直接走到某一个垃圾点, 或者走到补给点
            for (var point : points) {
                int dx = point[0], dy = point[1], idMask = 1 << id[dx][dy];
                boolean isResetPoint = grid[dx][dy] == 'R';

                // 检查当前剩余的能量是否能走到这个目标点
                int dis = distance[x][y][dx][dy];
                if (dis <= currEnergy && (x != dx || y != dy)) { // 可以走到当前这个目标点
                    int nextEnergy = currEnergy - dis, nextMask = currMask, nextStep = currStep + dis;
                    if (isResetPoint) nextEnergy = energy;
                    else if ((nextMask & idMask) != 0) nextMask ^= idMask;

                    if (nextStep < seen[dx][dy][nextEnergy][nextMask]) {
                        seen[dx][dy][nextEnergy][nextMask] = nextStep;
                        queue.add(new int[]{dx, dy, nextEnergy, nextMask, nextStep});
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().minMoves(new String[]{
                "S...................", // 0
                "....................", // 1
                "....................", // 2
                "....................", // 3
                ".....RL........RR...", // 4
                "....................", // 5
                "......L....R..L.....", // 6
                ".....L..............", // 7
                ".........L........LL", // 8
                "....................", // 9
                "....................", // 10
                "....................", // 11
                "........L...........", // 12
                "....................", // 13
                "....................", // 14
                "....................", // 15
                "....L...............", // 16
                "....................", // 17
                ".....R..............", // 18
                "..............L....." // 19
            }, 20) == 71;
        });

        assert new Solution().minMoves(new String[]{".S", "LL"}, 3) == 2;

        assert new Solution().minMoves(new String[]{"S.", "XL"}, 2) == 2;
        assert new Solution().minMoves(new String[]{"LS", "RL"}, 4) == 3;
        assert new Solution().minMoves(new String[]{"L.S", "RXL"}, 3) == -1;
    }

}
