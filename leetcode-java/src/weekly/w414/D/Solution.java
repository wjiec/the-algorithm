package weekly.w414.D;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 3283. Maximum Number of Moves to Kill All Pawns
 *
 * https://leetcode.cn/problems/maximum-number-of-moves-to-kill-all-pawns
 *
 * There is a 50 x 50 chessboard with one knight and some pawns on it. You are given
 * two integers kx and ky where (kx, ky) denotes the position of the knight, and a 2D
 * array positions where positions[i] = [xi, yi] denotes the position of the pawns
 * on the chessboard.
 *
 * Alice and Bob play a turn-based game, where Alice goes first. In each player's turn:
 *
 * The player selects a pawn that still exists on the board and captures it with the knight
 * in the fewest possible moves. Note that the player can select any pawn, it might not be one
 * that can be captured in the least number of moves.
 *
 * In the process of capturing the selected pawn, the knight may pass other pawns without capturing
 * them. Only the selected pawn can be captured in this turn.
 *
 * Alice is trying to maximize the sum of the number of moves made by both players until there are no
 * more pawns on the board, whereas Bob tries to minimize them.
 *
 * Return the maximum total number of moves made during the game that Alice can achieve, assuming both
 * players play optimally.
 *
 * Note that in one move, a chess knight has eight possible positions it can move to, as illustrated below.
 * Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.
 */

public class Solution {

    private static final int[][] dirs = new int[][]{
        {2, 1}, {2, -1},{-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
    };

    public int maxMoves(int kx, int ky, int[][] positions) {
        int n = positions.length;

        // 计算棋盘上所有兵到其他格子的步数
        int[][][] dis = new int[n][50][50];
        for (int i = 0; i < n; i++) {
            for (var row : dis[i]) Arrays.fill(row, -1);

            Queue<int[]> q = new ArrayDeque<>();
            q.add(positions[i]);
            dis[i][positions[i][0]][positions[i][1]] = 0;

            while (!q.isEmpty()) {
                var curr = q.remove();
                int x = curr[0], y = curr[1];

                for (var dir : dirs) {
                    int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                    if (dx >= 0 && dx < 50 && dy >= 0 && dy < 50 && dis[i][dx][dy] == -1) {
                        dis[i][dx][dy] = dis[i][x][y] + 1;
                        q.add(new int[]{dx, dy});
                    }
                }
            }
        }

        // 然后从第一回合开始模拟选择吃兵的过程
        int[][] memo = new int[n + 1][1 << n];
        for (var row : memo) Arrays.fill(row, -1);
        return dfs(n, (1 << n) - 1, kx, ky, dis, positions, memo);
    }

    // 计算当前马在第 i 个位置, 剩余的棋子为 mask, 同时马的位置在 kx, ky 时的最大移动步数
    // 第 n 个位置表示马的初始位置
    private int dfs(int i, int mask, int kx, int ky, int[][][] dis, int[][] positions, int[][] memo) {
        if (mask == 0) return 0; // 没有棋子了
        if (memo[i][mask] != -1) return memo[i][mask];

        int n = positions.length;
        int cx = i == n ? kx : positions[i][0];
        int cy = i == n ? ky : positions[i][1];

        // 计算当前是第几回合, 也就是 mask 中 0 的数量
        int ans = 0;
        if ((positions.length - Integer.bitCount(mask)) % 2 == 0) { // Alice 回合
            // Alice 的目标是最大化总移动次数, 所以找最远的兵
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0) {
                    ans = Math.max(ans, dis[j][cx][cy] + dfs(j, mask ^ (1 << j), kx, ky, dis, positions, memo));
                }
            }
        } else { // Bob 回合
            // Bob 的目标是最小化总移动次数
            ans = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0) {
                    ans = Math.min(ans, dis[j][cx][cy] + dfs(j, mask ^ (1 << j), kx, ky, dis, positions, memo));
                }
            }
        }

        return memo[i][mask] = ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxMoves(1, 1, new int[][]{{0, 0}}) == 4;
    }

}
