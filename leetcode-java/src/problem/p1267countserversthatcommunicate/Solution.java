package problem.p1267countserversthatcommunicate;

/**
 * 1267. Count Servers that Communicate
 *
 * https://leetcode.cn/problems/count-servers-that-communicate/
 *
 * You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that
 * on that cell there is a server and 0 means that it is no server. Two servers are said to communicate
 * if they are on the same row or on the same column.
 *
 * Return the number of servers that communicate with any other server.
 */

public class Solution {

    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rows = new int[m], cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows[i]++; cols[j]++;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && (rows[i] > 1 || cols[j] > 1)) ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countServers(new int[][]{
            {1,0,0,1,0},
            {0,0,0,0,0},
            {0,0,0,1,0}
        }) == 3;

        assert new Solution().countServers(new int[][]{
            {1,0},
            {0,1}
        }) == 0;

        assert new Solution().countServers(new int[][]{
            {1,0},
            {1,1}
        }) == 3;

        assert new Solution().countServers(new int[][]{
            {1,1,0,0},
            {0,0,1,0},
            {0,0,1,0},
            {0,0,0,1}
        }) == 4;
    }

}
