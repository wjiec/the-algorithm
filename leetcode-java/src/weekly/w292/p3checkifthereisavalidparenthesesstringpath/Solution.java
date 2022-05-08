package weekly.w292.p3checkifthereisavalidparenthesesstringpath;

import java.util.HashSet;
import java.util.Set;

/**
 * 6059. Check if There Is a Valid Parentheses String Path
 *
 * https://leetcode-cn.com/contest/weekly-contest-292/problems/check-if-there-is-a-valid-parentheses-string-path/
 *
 * A parentheses string is a non-empty string consisting only of '(' and ')'.
 * It is valid if any of the following conditions is true:
 *
 * It is ().
 * It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
 * It can be written as (A), where A is a valid parentheses string.
 * You are given an m x n matrix of parentheses grid. A valid parentheses string path in the
 * grid is a path satisfying all of the following conditions:
 *
 * The path starts from the upper left cell (0, 0).
 * The path ends at the bottom-right cell (m - 1, n - 1).
 * The path only ever moves down or right.
 * The resulting parentheses string formed by the path is valid.
 * Return true if there exists a valid parentheses string path in the grid. Otherwise, return false.
 */

public class Solution {

    private int m = 0, n = 0;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length; n = grid[0].length;
        Set<Integer>[][] map = new HashSet[m][n];
        return dfs(grid, 0, 0, 0, map);
    }

    private boolean dfs(char[][] grid, int x, int y, int opens, Set<Integer>[][] map) {
        if (map[x][y] != null && map[x][y].contains(opens)) return false;
        if (map[x][y] == null) map[x][y] = new HashSet<>();

        map[x][y].add(opens);
        if (grid[x][y] == '(') opens++; else opens--;
        if (opens < 0) return false;

        if (x == m - 1 && y == n - 1) return opens == 0;
        if (x < m - 1 && dfs(grid, x + 1, y, opens, map)) return true;
        return y < n - 1 && dfs(grid, x, y + 1, opens, map);
    }

    public static void main(String[] args) {
        assert new Solution().hasValidPath(new char[][]{
            {'(','(','('},
            {')','(',')'},
            {'(','(',')'},
            {'(','(',')'}
        });
    }

}
