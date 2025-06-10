package weekly.w450.C;

import java.util.*;

/**
 * Q3. Grid Teleportation Traversal
 *
 * https://leetcode.cn/contest/weekly-contest-450/problems/grid-teleportation-traversal/
 *
 * You are given a 2D character grid matrix of size m x n, represented as an array of strings,
 * where matrix[i][j] represents the cell at the intersection of the ith row and jth column.
 *
 * Each cell is one of the following:
 *
 * '.' representing an empty cell.
 * '#' representing an obstacle.
 * An uppercase letter ('A'-'Z') representing a teleportation portal.
 *
 * You start at the top-left cell (0, 0), and your goal is to reach the bottom-right cell (m - 1, n - 1).
 * You can move from the current cell to any adjacent cell (up, down, left, right) as long as the
 * destination cell is within the grid bounds and is not an obstacle.
 *
 * If you step on a cell containing a portal letter and you haven't used that portal letter before, you
 * may instantly teleport to any other cell in the grid with the same letter. This teleportation does
 * not count as a move, but each portal letter can be used at most once during your journey.
 *
 * Return the minimum number of moves required to reach the bottom-right cell. If it is not possible
 * to reach the destination, return -1.
 */

public class Solution {

    private final static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private record Item(int x, int y, int mask, int s) {}

    // 如果当前位置是大写字母, 可以跳到其他相同的大写字母上, 但是不算移动次数
    //  - 每个字母只能使用一次
    public int minMoves(String[] matrix) {
        // 走到一个传送门位置, 就标记这个字母不可用, 因为我们会走所有的路径
        int m = matrix.length, n = matrix[0].length();
        char[][] chars = new char[m][n];
        //noinspection unchecked
        List<int[]>[] coords = new List[128];
        Arrays.setAll(coords, i -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            chars[i] = matrix[i].toCharArray();
            for (int j = 0; j < n; j++) {
                coords[chars[i][j]].add(new int[]{i, j});
            }
        }

        boolean[][] seen = new boolean[m][n]; seen[0][0] = true;
        Queue<Item> queue = new ArrayDeque<>();
        if ('A' <= chars[0][0] && chars[0][0] <= 'Z') {
            int charMask = 1 << (chars[0][0] - 'A');
            for (var curr : coords[chars[0][0]]) {
                seen[curr[0]][curr[1]] = true;
                queue.add(new Item(curr[0], curr[1], charMask, 0));
            }
        } else queue.add(new Item(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            var curr = queue.remove();
            if (curr.x == m - 1 && curr.y == n - 1) return curr.s;

            for (var dir : dirs) {
                int dx = curr.x + dir[0], dy = curr.y + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && !seen[dx][dy] && chars[dx][dy] != '#') {
                    seen[dx][dy] = true;
                    if ('A' <= chars[dx][dy] && chars[dx][dy] <= 'Z') {
                        var charMask = 1 << (chars[dx][dy] - 'A');
                        if ((curr.mask & charMask) == 0) {
                            for (var jump : coords[chars[dx][dy]]) {
                                seen[jump[0]][jump[1]] = true;
                                queue.add(new Item(jump[0], jump[1], curr.mask | charMask, curr.s + 1));
                            }
                        }
                    } else queue.add(new Item(dx, dy, curr.mask, curr.s + 1));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().minMoves(new String[]{"BA"}) == 1;
        assert new Solution().minMoves(new String[]{"A..",".A.","..."}) == 2;
        assert new Solution().minMoves(new String[]{".#...",".#.#.",".#.#.","...#."}) == 13;
    }

}
