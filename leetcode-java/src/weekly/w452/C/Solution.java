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

    private final List<int[]> points = new ArrayList<>();
    private final List<Integer> id = new ArrayList<>();

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        grid = new char[m][];
        for (int i = 0; i < m; i++) grid[i] = classroom[i].toCharArray();

        int sx = 0, sy = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                switch (grid[i][j]) {
                    case 'L', 'R' -> points.add(new int[]{i, j});
                    case 'S' -> { sx = i; sy = j; }
                }
            }
        }
        points.sort(Comparator.comparingInt(ij -> grid[ij[0]][ij[1]]));

        int cnt = 0, pl = points.size();
        for (int[] point : points) {
            switch (grid[point[0]][point[1]]) {
                case 'L' -> id.add(cnt++);
                case 'R' -> id.add(-1);
            }
        }
        if (cnt == 0) return 0; // 没有垃圾

        // dis[i][j] 表示从 point[i] 走到 point[j] 的最短距离
        dis = new int[pl][];
        for (int i = 0; i < pl; i++) {
            dis[i] = distance(grid, points.get(i)[0], points.get(i)[1], points);
        }

        this.energy = energy; this.allMask = (1 << cnt) - 1;
        // 记忆化 seen[i][j][k] 表示当前在 i 点, 剩余能量为 j, 且当前已收集的垃圾为 k 是否已经遍历过
        seen = new int[pl][energy + 1][1 << cnt];
        for (int i = 0; i < pl; i++) {
            for (var row : seen[i]) Arrays.fill(row, Integer.MAX_VALUE);
        }
        // 尝试从起点看看到每个垃圾点或者补给点的最短距离
        int[] fromStart = distance(grid, sx, sy, points);
        for (int i = 0; i < pl; i++) {
            for (var row : seen[i]) Arrays.fill(row, Integer.MAX_VALUE);
            // 如果可以从起点走到当前垃圾点或者补给点, 就将其加入队列
            if (fromStart[i] <= energy) {
                int pEnergy = energy - fromStart[i], pMask = 0;
                switch (grid[points.get(i)[0]][points.get(i)[1]]) {
                    case 'L' -> pMask |= 1 << id.get(i);
                    case 'R' -> pEnergy = energy;
                }

                // 开始枚举所有可能的走法
                seen[i][pEnergy][pMask] = fromStart[i];
                dfs(i, pEnergy, pMask, fromStart[i]);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int[][] dis = null;
    private char[][] grid = null;
    private int[][][] seen = null;
    private int energy = 0, allMask = 0;
    private int ans = Integer.MAX_VALUE;

    private void dfs(int i, int energy, int mask, int step) {
        if (mask == allMask) { ans = Math.min(ans, step); return; }

        for (int j = 0; j < points.size(); j++) {
            int nextEnergy = energy - dis[i][j], nextMask = mask;
            if (nextEnergy < 0 || j == i) continue; // 无法走到或者相同的点

            // 检查是否需要更新下一步的 mask 以及 energy
            switch (grid[points.get(j)[0]][points.get(j)[1]]) {
                case 'L' -> {
                    // 已经收集过了就没必要再收集了
                    if ((nextMask & (1 << id.get(j))) != 0) {
                        continue;
                    }
                    // 收集一个垃圾
                    nextMask |= 1 << id.get(j);
                }
                case 'R' -> nextEnergy = this.energy; // 恢复能量
            }

            int nextStep = step + dis[i][j];
            if (nextStep < seen[j][nextEnergy][nextMask] && nextStep < ans) {
                seen[j][nextEnergy][nextMask] = nextStep;
                dfs(j, nextEnergy, nextMask, nextStep);
            }
        }
    }

    private static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // 计算从 (x, y) 到 points 中每个点的最短距离
    private int[] distance(char[][] grid, int x, int y, List<int[]> points) {
        int m = grid.length, n = grid[0].length;

        int[][] seen = new int[m][n];
        for (var row : seen) Arrays.fill(row, m * n + 1);

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y}); seen[x][y] = 0;

        while (!queue.isEmpty()) {
            var curr = queue.remove();
            int cx = curr[0], cy = curr[1], nextStep = seen[cx][cy] + 1;
            for (var dir : dirs) {
                int dx = cx + dir[0], dy = cy + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && grid[dx][dy] != 'X' && nextStep < seen[dx][dy]) {
                    seen[dx][dy] = nextStep; queue.add(new int[]{dx, dy});
                }
            }
        }

        int[] ans = new int[points.size()];
        for (int i = 0; i < points.size(); i++) {
            ans[i] = seen[points.get(i)[0]][points.get(i)[1]];
        }
        return ans;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().minMoves(new String[]{
                "LLLRXLRSX.RXL",
                "XRLL..XXR.R.R",
                "RXR..R......R",
                "X..XXRRRXXRR.",
                "..RRRXRXR...R",
                "XRRXRX.R.RRR.",
                ".XR.XXR...R.R",
                "X.RXRXXRX.XXR",
                "XRX.RRXX.XR..",
                "R.R.X.RRR...X",
                "RRRXX...XXRR.",
                "R.R.RXRXX..RR",
                ".X......R..XR",
                ".XRRRX...RX.R",
                "R...X.X...XR."
            }, 45) == 25;

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
