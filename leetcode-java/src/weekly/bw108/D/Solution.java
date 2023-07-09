package weekly.bw108.D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 6928. Number of Black Blocks
 *
 * https://leetcode.cn/contest/biweekly-contest-108/problems/number-of-black-blocks/
 *
 * You are given two integers m and n representing the dimensions of a 0-indexed m x n grid.
 *
 * You are also given a 0-indexed 2D integer matrix coordinates, where coordinates[i] = [x, y] indicates
 * that the cell with coordinates [x, y] is colored black. All cells in the grid that do not appear
 * in coordinates are white.
 *
 * A block is defined as a 2 x 2 submatrix of the grid. More formally, a block with cell [x, y] as its
 * top-left corner where 0 <= x < m - 1 and 0 <= y < n - 1 contains the
 * coordinates [x, y], [x + 1, y], [x, y + 1], and [x + 1, y + 1].
 *
 * Return a 0-indexed integer array arr of size 5 such that arr[i] is the number of blocks that
 * contains exactly i black cells.
 */

public class Solution {

    private final long[] ans = new long[]{0, 0, 0, 0, 0};
    private final Map<Integer, Set<Integer>> map = new HashMap<>();
    private final Set<Long> visited = new HashSet<>();

    public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
        for (var coordinate : coordinates) {
            map.computeIfAbsent(coordinate[0], v -> new HashSet<>())
                .add(coordinate[1]);
        }

        for (var coordinate : coordinates) {
            int x = coordinate[0], y = coordinate[1];
            check(x, y, m, n);
            check(x - 1, y, m, n);
            check(x, y - 1, m, n);
            check(x - 1, y - 1, m, n);
        }

        ans[0] += (m - 1L) * (n - 1L);
        return ans;
    }

    // {hash, count}
    private void check(int x, int y, int m, int n) {
        long key = ((long) x << 32) | y;
        if (x >= 0 && x + 1 < m && y >= 0 && y + 1 < n && visited.add(key)) {
            int count = 0;
            if (map.containsKey(x) && map.get(x).contains(y)) count++;
            if (map.containsKey(x) && map.get(x).contains(y + 1)) count++;
            if (map.containsKey(x + 1) && map.get(x + 1).contains(y)) count++;
            if (map.containsKey(x + 1) && map.get(x + 1).contains(y + 1)) count++;
            if (count != 0) { ans[count]++; ans[0]--; }
        }
    }

    public static void main(String[] args) {
    }

}
