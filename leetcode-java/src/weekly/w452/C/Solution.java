package weekly.w452.C;

import ability.Benchmark;
import common.Tag;

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

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        char[][] grid = new char[m][];
        for (int i = 0; i < m; i++) grid[i] = classroom[i].toCharArray();

        int sx = 0, sy = 0, cnt = 0;
        List<Integer> id = new ArrayList<>();
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                switch (grid[i][j]) {
                    case 'L' -> { points.add(new int[]{i, j}); id.add(cnt++); }
                    case 'R' -> { points.add(new int[]{i, j}); id.add(-1); }
                    case 'S' -> { sx = i; sy = j; }
                }
            }
        }
        if (cnt == 0) return 0; // 没有垃圾

        int pl = points.size();
        // [index, energy, mask, step]
        Queue<int[]> queue = new ArrayDeque<>();
        // 尝试从起点看看到每个垃圾点或者补给点的最短距离
        int[] fromStart = distance(grid, sx, sy, points);
        // 记忆化 seen[i][j][k] 表示当前在 i 点, 剩余能量为 j, 且当前已收集的垃圾为 k 是否已经遍历过
        int[][][] seen = new int[pl][energy + 1][1 << cnt];
        for (int i = 0; i < pl; i++) {
            for (var row : seen[i]) Arrays.fill(row, Integer.MAX_VALUE);
            // 如果可以从起点走到当前垃圾点或者补给点, 就将其加入队列
            if (fromStart[i] <= energy) {
                int pEnergy = energy - fromStart[i], pMask = 0;
                switch (grid[points.get(i)[0]][points.get(i)[1]]) {
                    case 'L' -> pMask |= 1 << id.get(i);
                    case 'R' -> pEnergy = energy;
                }

                seen[i][pEnergy][pMask] = fromStart[i];
                queue.add(new int[]{i, pEnergy, pMask, fromStart[i]});
            }
        }

        // dis[i][j] 表示从 point[i] 走到 point[j] 的最短距离
        int[][] dis = new int[pl][];
        for (int i = 0; i < pl; i++) {
            dis[i] = distance(grid, points.get(i)[0], points.get(i)[1], points);
        }

        // 开始枚举所有可能的走法
        int ans = Integer.MAX_VALUE, allMask = (1 << cnt) - 1;
        while (!queue.isEmpty()) {
            var curr = queue.remove();
            int i = curr[0], cEnergy = curr[1], cMask = curr[2], cStep = curr[3];
            if ((cMask ^ allMask) == 0) { ans = Math.min(ans, cStep); continue; }
            if (energy == 0) continue;

            // 尝试从这个点走到其他的点, 最终完成遍历
            for (int j = 0; j < pl; j++) {
                int pEnergy = cEnergy - dis[i][j], pMask = cMask;
                if (pEnergy < 0 || j == i) continue; // 无法走到或者相同的点

                // 检查是否需要更新下一步的 mask 以及 energy
                switch (grid[points.get(j)[0]][points.get(j)[1]]) {
                    case 'L' -> {
                        // 已经收集过了就没必要再收集了
                        if ((pMask & (1 << id.get(j))) != 0) {
                            continue;
                        }
                        // 收集一个垃圾
                        pMask |= 1 << id.get(j);
                    }
                    case 'R' -> pEnergy = energy; // 恢复能量
                }

                int pStep = cStep + dis[i][j];
                if (pStep < seen[j][pEnergy][pMask] && pStep < ans) {
                    seen[j][pEnergy][pMask] = pStep;
                    queue.add(new int[]{j, pEnergy, pMask, pStep});
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

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

    @Tag("旅行商问题")
    private static class Optimization {
        private static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int minMoves(String[] classroom, int energy) {
            int m = classroom.length, n = classroom[0].length();
            char[][] grid = new char[m][];
            for (int i = 0; i < m; i++) grid[i] = classroom[i].toCharArray();

            int sx = 0, sy = 0, cnt = 0;
            int[][] id = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    switch (grid[i][j]) {
                        case 'L' -> id[i][j] = ~(1 << (cnt++));
                        case 'S' -> { sx = i; sy = j; }
                    }
                }
            }
            if (cnt == 0) return 0; // 没有垃圾

            boolean[][][][] seen = new boolean[m][n][energy + 1][1 << cnt];
            seen[sx][sy][energy][(1 << cnt) - 1] = true;
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{sx, sy, energy, (1 << cnt) - 1});
            for (int ans = 0; !queue.isEmpty(); ans++) {
                Queue<int[]> next = new ArrayDeque<>();
                while (!queue.isEmpty()) {
                    var curr = queue.remove();
                    int x = curr[0], y = curr[1], e = curr[2], mask = curr[3];
                    if (mask == 0) return ans;
                    if (e == 0) continue;

                    for (var dir : dirs) {
                        int dx = x + dir[0], dy = y + dir[1];
                        if (dx >= 0 && dx < m && dy >= 0 && dy < n && grid[dx][dy] != 'X') {
                            int ne = grid[dx][dy] == 'R' ? energy : (e - 1);
                            int nm = grid[dx][dy] == 'L' ? (mask & id[dx][dy]) : mask;
                            if (!seen[dx][dy][ne][nm]) {
                                seen[dx][dy][ne][nm] = true;
                                next.add(new int[]{dx, dy, ne, nm});
                            }
                        }
                    }
                }
                queue = next;
            }
            return -1;
        }
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

        Benchmark.benchmark("Optimization", () -> {
            assert new Optimization().minMoves(new String[]{
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

            assert new Optimization().minMoves(new String[]{
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

        assert new Optimization().minMoves(new String[]{".S", "LL"}, 3) == 2;
        assert new Optimization().minMoves(new String[]{"S.", "XL"}, 2) == 2;
        assert new Optimization().minMoves(new String[]{"LS", "RL"}, 4) == 3;
        assert new Optimization().minMoves(new String[]{"L.S", "RXL"}, 3) == -1;
    }

}
