package weekly.w457.D;

import ability.Benchmark;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Q4. Minimum Moves to Reach Target in Grid
 *
 * https://leetcode.cn/contest/weekly-contest-457/problems/minimum-moves-to-reach-target-in-grid/
 *
 * You are given four integers sx, sy, tx, and ty, representing two
 * points (sx, sy) and (tx, ty) on an infinitely large 2D grid.
 *
 * You start at (sx, sy).
 *
 * At any point (x, y), define m = max(x, y). You can either:
 *
 * Move to (x + m, y), or
 * Move to (x, y + m).
 *
 * Return the minimum number of moves required to reach (tx, ty).
 * If it is impossible to reach the target, return -1.
 */

public class Solution {

    public int minMoves(int sx, int sy, int tx, int ty) {
        // 从 (tx, ty) 反向走到 (sx, sy)
        //  - 如果想从 (cx, cy) 走到 (tx, ty) 就只能是以下两种情况
        //      - (cx, cy) -> (tx = cx + max(cx, cy), ty = cy)
        //          - 如果 cx > cy = ty, 那么 tx = cx * 2, 且 tx 为偶数
        //                                  cx = tx / 2 > ty
        //          - 如果 ty = cy > cx, 那么 tx = cx + cy = ty
        //                                  cx = tx - ty < ty
        //
        //      - (cx, cy) -> (tx = cx, ty = cy + max(cx, cy))
        //          - 如果 tx = cx > cy, 那么 ty = cy + cx
        //                                  cy = ty - (cx = tx) < cx
        //
        //          - 如果 cy > cx = tx, 那么 ty = cy * 2, 且 ty 为偶数
        //                                  cy = ty / 2 > tx
        // 枚举所有可能
        Queue<int[]> q = new ArrayDeque<>(); q.add(new int[]{tx, ty, 0});
        while (!q.isEmpty()) {
            var curr = q.remove();
            tx = curr[0]; ty = curr[1];
            if (tx < sx || ty < sy) continue;
            if (tx == sx && ty == sy) return curr[2];

            if (tx % 2 == 0 && tx / 2 >= ty) q.add(new int[]{tx / 2, ty, curr[2] + 1});
            if (tx >= ty && tx - ty <= ty) q.add(new int[]{tx - ty, ty, curr[2] + 1});

            if (ty >= tx && ty - tx <= tx) q.add(new int[]{tx, ty - tx, curr[2] + 1});
            if (ty % 2 == 0 && ty / 2 >= tx) q.add(new int[]{tx, ty / 2, curr[2] + 1});
        }
        return -1;
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().minMoves(2, 0, 32656, 28320) == 20;
        });

        assert new Solution().minMoves(1, 2, 5, 4) == 2;
        assert new Solution().minMoves(0, 1, 2, 3) == 3;
        assert new Solution().minMoves(1, 1, 2, 2) == -1;
    }

}
