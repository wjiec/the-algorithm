package weekly.bw77.p2countunguardedcellsinthegrid;

import java.util.HashMap;
import java.util.Map;

/**
 * 6053. Count Unguarded Cells in the Grid
 *
 * https://leetcode-cn.com/contest/biweekly-contest-77/problems/count-unguarded-cells-in-the-grid/
 *
 * You are given two integers m and n representing a 0-indexed m x n grid. You are also given
 * two 2D integer arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj]
 * represent the positions of the ith guard and jth wall respectively.
 *
 * A guard can see every cell in the four cardinal directions (north, east, south, or west) starting
 * from their position unless obstructed by a wall or another guard.
 *
 * A cell is guarded if there is at least one guard that can see it.
 *
 * Return the number of unoccupied cells that are not guarded.
 */

public class Solution {

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] board = new int[m][n];
        for (var wall : walls) board[wall[0]][wall[1]] = -1;
        for (var guard : guards) board[guard[0]][guard[1]] = 1;
        Map<Integer, Map<Integer, Integer>> loc = new HashMap<>();
        for (int i = 0; i < guards.length; i++) {
            if (!loc.containsKey(guards[i][0])) {
                loc.put(guards[i][0], new HashMap<>());
            }
            loc.get(guards[i][0]).put(guards[i][1], i);
        }

        // [up, right, bottom, left]
        boolean[][] dirs = new boolean[guards.length][4];
        for (int i = 0; i < guards.length; i++) {
            int x = guards[i][0], y = guards[i][1];
            board[x][y] = 2;

            if (!dirs[i][0]) {
                dirs[i][0] = true;
                for (int xx = x - 1; xx >= 0; xx--) {
                    if (board[xx][y] == -1) break;
                    if (board[xx][y] == 1) dirs[loc.get(xx).get(y)][0] = true;
                    board[xx][y] = 2;
                }
            }

            if (!dirs[i][1]) {
                dirs[i][1] = true;
                for (int yy = y + 1; yy < n; yy++) {
                    if (board[x][yy] == -1) break;
                    if (board[x][yy] == 1) dirs[loc.get(x).get(yy)][1] = true;
                    board[x][yy] = 2;
                }
            }

            if (!dirs[i][2]) {
                dirs[i][2] = true;
                for (int xx = x + 1; xx < m; xx++) {
                    if (board[xx][y] == -1) break;
                    if (board[xx][y] == 1) dirs[loc.get(xx).get(y)][2] = true;
                    board[xx][y] = 2;
                }
            }

            if (!dirs[i][3]) {
                dirs[i][3] = true;
                for (int yy = y - 1; yy >= 0; yy--) {
                    if (board[x][yy] == -1) break;
                    if (board[x][yy] == 1) dirs[loc.get(x).get(yy)][3] = true;
                    board[x][yy] = 2;
                }
            }
        }

        int ans = 0;
        for (var line : board) {
            for (var v : line) {
                if (v == 0) ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countUnguarded(4, 6, new int[][]{{0,0},{1,1},{2,3}}, new int[][]{{0,1},{2,2},{1,4}}) == 7;
    }

}
