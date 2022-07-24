package weekly.w303.B;

import java.util.HashMap;
import java.util.Map;

/**
 * 6125. Equal Row and Column Pairs
 *
 * https://leetcode.cn/contest/weekly-contest-303/problems/equal-row-and-column-pairs/
 *
 * Given a 0-indexed n x n integer matrix grid, return the number of
 * pairs (Ri, Cj) such that row Ri and column Cj are equal.
 *
 * A row and column pair is considered equal if they contain the
 * same elements in the same order (i.e. an equal array).
 */

public class Solution {

    public int equalPairs(int[][] grid) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < grid.length; j++) {
                sb.append(grid[i][j]).append(",");
            }
            map.merge(sb.toString(), 1, Integer::sum);
        }

        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < grid.length; j++) {
                sb.append(grid[j][i]).append(",");
            }
            ans += map.getOrDefault(sb.toString(), 0);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
