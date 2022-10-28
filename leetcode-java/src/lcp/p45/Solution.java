package lcp.p45;

import common.Checker;
import common.TODO;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * LCP 45. 自行车炫技赛场
 *
 * https://leetcode.cn/problems/kplEvH/
 *
 * 「力扣挑战赛」中 N*M 大小的自行车炫技赛场的场地由一片连绵起伏的上下坡组成，
 * 场地的高度值记录于二维数组 terrain 中，场地的减速值记录于二维数组 obstacle 中。
 *
 * 若选手骑着自行车从高度为 h1 且减速值为 o1 的位置到高度为 h2 且减速值为 o2 的相邻
 * 位置（上下左右四个方向），速度变化值为 h1-h2-o2（负值减速，正值增速）。
 * 选手初始位于坐标 position 处且初始速度为 1，请问选手可以刚好到其他哪些位置时速度依旧为 1。
 *
 * 请以二维数组形式返回这些位置。若有多个位置则按行坐标升序排列，若有多个位置行坐标相同则按列坐标升序排列。
 *
 * 注意： 骑行过程中速度不能为零或负值
 */

public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    @TODO
    public int[][] bicycleYard(int[] position, int[][] terrain, int[][] obstacle) {
        int m = terrain.length, n = terrain[0].length;
        // visited[i][j][k] 表示是否能从 position 到 [i][j]] 最终速度为 k
        boolean[][][] visited = new boolean[m][n][105];

        // [x, y, speed]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{position[0], position[1], 1});

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int x = curr[0], y = curr[1];
            for (var dir : dirs) {
                int dx = x + dir[0], dy = y + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n) {
                    if (dx == position[0] && dy == position[1]) continue;

                    int speed = curr[2] + terrain[x][y] - terrain[dx][dy] - obstacle[dx][dy];
                    if (speed > 0 && !visited[dx][dy][speed]) {
                        visited[dx][dy][speed] = true;
                        queue.add(new int[]{dx, dy, speed});
                    }
                }
            }
        }

        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j][1]) {
                    ans.add(new int[]{i, j});
                }
            }
        }
        return ans.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().bicycleYard(
            new int[]{0, 0}, new int[][]{{0, 0}, {0, 0}}, new int[][]{{0, 0}, {0, 0}}
        ), new int[][]{{0, 1}, {1, 0}, {1, 1}});

        assert Checker.check(new Solution().bicycleYard(
            new int[]{1, 1}, new int[][]{{5, 0}, {0, 6}}, new int[][]{{0, 6}, {7, 0}}
        ), new int[][]{{0, 1}});
    }

}
