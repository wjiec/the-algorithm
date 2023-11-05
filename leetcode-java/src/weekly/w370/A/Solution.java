package weekly.w370.A;

/**
 * 100115. Find Champion I
 *
 * https://leetcode.cn/contest/weekly-contest-370/problems/find-champion-i/
 *
 * There are n teams numbered from 0 to n - 1 in a tournament.
 *
 * Given a 0-indexed 2D boolean matrix grid of size n * n.
 *
 * For all i, j that 0 <= i, j <= n - 1 and i != j team i is stronger than
 * team j if grid[i][j] == 1, otherwise, team j is stronger than team i.
 *
 * Team a will be the champion of the tournament if there is no team b that is stronger than team a.
 *
 * Return the team that will be the champion of the tournament.
 */

public class Solution {

    public int findChampion(int[][] grid) {
        int ans = 0, win = 0;
        for (int i = 0; i < grid.length; i++) {
            int curr = 0;
            for (int j = 0; j < grid[0].length; j++) {
                if (i == j) continue;
                if (grid[i][j] == 1) curr++;
            }
            if (curr > win) {
                ans = i; win = curr;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
